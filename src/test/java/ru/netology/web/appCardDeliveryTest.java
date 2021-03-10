package ru.netology.web;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.*;

public class appCardDeliveryTest {

    @BeforeAll
    static void setUp() {
        Configuration.headless = true;
    }

    @Test
    void shouldRegisterByOrderCard() {
        open("http://0.0.0.0:9999/");

        User user1 = DataGenerator.Registration.usersRegistration();
        DateTimeFormatter formatDate = DateTimeFormatter.ofPattern("dd.MM.yyyy");


        $("[data-test-id=city] .input__control").setValue(user1.getCity());
        $("[data-test-id=date] .input__control[value]").sendKeys(Keys.chord(Keys.CONTROL, "a"), Keys.DELETE);
        $("[data-test-id=date] .input__control").setValue(user1.getLocalDate());
        $("[data-test-id=name] .input__control").setValue(user1.getName());
        $("[data-test-id=phone] .input__control").setValue(user1.getPhone());
        $("[data-test-id=agreement] .checkbox__box").click();
        $(".button .button__text").click();


        $(".button .button__text").click();
        $$(".button .button__text").find(exactText("Перепланировать")).click();
        $(withText("Успешно!")).shouldBe(visible, Duration.ofSeconds(6));

    }
}