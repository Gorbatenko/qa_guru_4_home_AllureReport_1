package io.qaguru.steps;

import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static org.openqa.selenium.By.linkText;

public class BaseSteps {
    @Step("Открываем главную страницу")
    public void openMainPage() {
        open("https://github.com/");
    }

    @Step("Поиск репозитория {repository}")
    public void searchRepository(String repository) {
        $(".header-search-input").click();
        $(".header-search-input").setValue(repository);
        $(".header-search-input").pressEnter();
    }

    @Step("Переход в репозиторий {repository}")
    public void goToRepository(String repository) {
        $(linkText(repository)).click();
    }

    @Step("Переход в раздел Issues")
    public void openRepositoryIssues() {
        $(byText("Issues")).click();
    }

    @Step("Проверка существования issue #{number}")
    public void shouldSeeIssueWithNumber(int number) {
        $("#issue_" + number).should(exist);
    }
}
