package io.qaguru.tests;

import com.codeborne.selenide.logevents.SelenideLogger;
import io.qaguru.steps.BaseSteps;
import io.qameta.allure.*;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static io.qameta.allure.Allure.step;
import static org.openqa.selenium.By.linkText;

public class IssueSearchTest {
    private static final String REPOSITORY = "eroshenkoam/allure-example";
    private static final String BASE_URL = "https://github.com/";
    private static final int ISSUE_NUMBER = 68;
    public BaseSteps steps = new BaseSteps();

    @BeforeEach
    void setUp() {
        SelenideLogger.addListener("allure", new AllureSelenide());
    }

    @Test
    @Owner("GorbatenkoVA")
    @Severity(SeverityLevel.TRIVIAL)
    @Link(name = "Base URL", value = BASE_URL)
    @Feature("Issues")
    @Story("Поиск Issue")
    @DisplayName("Тест написан просто на Selenide")
    void issueSearchTestDefault() {
        open(BASE_URL);

        $(".header-search-input").setValue(REPOSITORY).submit();

        $(linkText(REPOSITORY)).click();

        $(byText("Issues")).click();

        $("#issue_" + ISSUE_NUMBER).shouldBe(visible);
    }

    @Test
    @Owner("GorbatenkoVA")
    @Severity(SeverityLevel.CRITICAL)
    @Link(name = "Base URL", value = BASE_URL)
    @Feature("Issues")
    @Story("Поиск Issue")
    @DisplayName("Тест написан с лямбда шагами")
    void issueSearchTestLambda() {
        step("Открываем главную страницу", () -> {
            open(BASE_URL);
        });

        step("Поиск репозитория " + REPOSITORY, () -> {
            $(".header-search-input").setValue(REPOSITORY).submit();
        });

        step("Переход в репозиторий " + REPOSITORY, () -> {
            $(linkText(REPOSITORY)).click();
        });

        step("Переход в раздел Issues", () -> {
            $(byText("Issues")).click();
        });

        step("Проверка существования issue #" + ISSUE_NUMBER, () -> {
            $("#issue_" + ISSUE_NUMBER).shouldBe(visible);
        });
    }

    @Test
    @Owner("GorbatenkoVA")
    @Severity(SeverityLevel.MINOR)
    @Link(name = "Base URL", value = BASE_URL)
    @Feature("Issues")
    @Story("Поиск Issue")
    @DisplayName("Тест написан с шагами в методах")
    void issueSearchTestSteps() {
        steps.openMainPage(BASE_URL);
        steps.searchRepository(REPOSITORY);
        steps.goToRepository(REPOSITORY);
        steps.openRepositoryIssues();
        steps.shouldSeeIssueWithNumber(ISSUE_NUMBER);
    }
}
