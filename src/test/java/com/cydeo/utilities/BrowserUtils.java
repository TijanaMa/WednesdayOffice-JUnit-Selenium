package com.cydeo.utilities;

import org.junit.Assert;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class BrowserUtils {


    //convert miliseconds in seconds
    public static void sleep(int second){
        second*=1000;

        try{
            Thread.sleep(second);
        }catch (InterruptedException e){

        }

    }

    public static void switchWindowAndVerify(String expectedUrl, String expectedTitle){

        for (String eachWindowHandle : Driver.getDriver().getWindowHandles()) {
            Driver.getDriver().switchTo().window(eachWindowHandle);
            System.out.println("Current URL: " + Driver.getDriver().getCurrentUrl());
            if(Driver.getDriver().getCurrentUrl().contains(expectedUrl)){
                break;
            }
        }

        Assert.assertTrue(Driver.getDriver().getTitle().contains(expectedTitle));


    }

    public static void verifyTitle( String expectedTitle){

        Assert.assertEquals(Driver.getDriver().getTitle(),expectedTitle);

    }

    public static void verifyTitleContains( String expectedTitle){

        Assert.assertTrue(Driver.getDriver().getTitle().contains(expectedTitle));

    }

    public static void maxWindowImplicitWait(int seconds){
        Driver.getDriver().manage().window().maximize();
        Driver.getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(seconds));
    }

    public static void waitForInvisibilityOf(WebElement target){

        WebDriverWait wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(10));
        wait.until(ExpectedConditions.invisibilityOf(target));


    }

    public static void waitForVisibilityOf(WebElement target){

        WebDriverWait  wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(target));


    }

    public static void waitForTitleContains(String title){

        WebDriverWait  wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(10));
        wait.until(ExpectedConditions.titleContains(title));


    }

    public static List<String> dropdownOptions_as_String(WebElement dropdown_as_Webelement){
        Select select = new Select(dropdown_as_Webelement);

        //storing all options in list of webelements

        List<WebElement> actualMonth_as_WebElement = select.getOptions(); // ovo je web element i moramo da
        //uporedimo String(expectedMonth) sa webelement to je nemoguce, pa pravim novu listu

        List<String> actualMonths_as_String = new ArrayList<>();

        for (WebElement each : actualMonth_as_WebElement) {
            actualMonths_as_String.add(each.getText());
        }



    return actualMonths_as_String;

}

}
