package com.cydeo.step_definitions;

import com.cydeo.pages.GoogleSearchPage;
import com.cydeo.utilities.BrowserUtils;
import com.cydeo.utilities.Driver;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.Keys;

public class Google_StepDefinitions {


    @When("user is on the Google search page")
    public void user_is_on_the_google_search_page() {
        Driver.getDriver().get("https://www.google.com");
    }
    @Then("user should see title is {word}")
    public void user_should_see_title_is_google(String word) {

        String actualTitle = Driver.getDriver().getTitle();
        String expectedTitle = word;

        Assert.assertEquals(actualTitle,expectedTitle);


    }
    GoogleSearchPage googleSearchPage = new GoogleSearchPage();

    @When("user search for {word}")
    public void user_search_for_apple(String keyWord) {
      googleSearchPage.searchBox.sendKeys(keyWord + Keys.ENTER);
    }
    @Then("title should be able to see apple in the title")
    public void title_should_be_able_to_see_apple_in_the_title() {
        BrowserUtils.verifyTitle("apple - Google Search");
    }


}
