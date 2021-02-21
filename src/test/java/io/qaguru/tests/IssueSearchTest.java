package io.qaguru.tests;

import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static io.qameta.allure.Allure.step;
import static org.openqa.selenium.By.linkText;

public class IssueSearchTest {
    private static final String REPOSITORY = "eroshenkoam/allure-example";
    private static final int ISSUE_NUMBER = 68;

    @Test
    void issueSearchTestDefault() {
        open("https://github.com/");

        $(".header-search-input").click();
        $(".header-search-input").setValue(REPOSITORY);
        $(".header-search-input").pressEnter();

        $(linkText(REPOSITORY)).click();

        $(byText("Issues")).click();

        $("#issue_" + ISSUE_NUMBER).should(exist);
    }

    @Test
    void issueSearchTestLambda() {
        step("Открываем главную страницу", () -> {
            open("https://github.com/");
        });

        step("Поиск репозитория " + REPOSITORY, () -> {
            $(".header-search-input").click();
            $(".header-search-input").setValue(REPOSITORY);
            $(".header-search-input").pressEnter();
        });

        step("Переход в репозиторий " + REPOSITORY, () -> {
            $(linkText(REPOSITORY)).click();
        });

        step("Переход в раздел Issues", () -> {
            $(byText("Issues")).click();
        });

        step("Проверка существования issue #" + ISSUE_NUMBER, () -> {
            $("#issue_" + ISSUE_NUMBER).should(exist);
        });
    }

}
