package io.qaguru.tests;

import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static org.openqa.selenium.By.linkText;

public class IssueSearchTest {
    private final String repository = "eroshenkoam/allure-example";
    private final int issue_number = 68;

    @Test
    void issueSearchTestDefault() {
        open("https://github.com/");

        $(".header-search-input").click();
        $(".header-search-input").setValue(repository);
        $(".header-search-input").pressEnter();

        $(linkText(repository)).click();

        $(byText("Issues")).click();

        $("#issue_" + issue_number).should(exist);
    }

}
