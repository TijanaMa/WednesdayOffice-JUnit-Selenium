package com.cydeo.pages;

import com.cydeo.utilities.Driver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ButtonsPage {


    // page_url = https://demoqa.com/buttons

        @FindBy(xpath = "//*[@id='doubleClickBtn']")
        public WebElement doubleClickMe;

        @FindBy(css = "button[id='rightClickBtn']")
        public WebElement rightClickMe;

        @FindBy(xpath = "//button[.='Click Me']")
        public WebElement clickMe;

        @FindBy(xpath = "//*[@id='doubleClickMessage']")
        public WebElement doubleClickMessageYouHave;

        @FindBy(xpath = "//*[@id='rightClickMessage']")
        public WebElement rightClickMessageYouHave;

        @FindBy(xpath = "//*[@id='dynamicClickMessage']")
        public WebElement dynamicClickMessageYouHave;


        public ButtonsPage() {
            PageFactory.initElements(Driver.getDriver(), this);
        }
    }


