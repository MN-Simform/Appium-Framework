package org.example.PageObjects;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.example.Utils.AndroidActions;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;


public class LoginPage extends AndroidActions {

    AndroidDriver driver;

    public LoginPage(AndroidDriver driver){
        super(driver);
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver),this);
    }

//    @AndroidFindBy(xpath = "//android.widget.EditText[@resource-id=\"com.androidsample.generalstore:id/nameField\"]")
    @AndroidFindBy(id = "com.androidsample.generalstore:id/nameField")
    private WebElement nameField;

    @AndroidFindBy(xpath = "//android.widget.RadioButton[@resource-id=\"com.androidsample.generalstore:id/radioMale\"]")
    private  WebElement genderMale;

    @AndroidFindBy(xpath = "//android.widget.RadioButton[@resource-id=\"com.androidsample.generalstore:id/radioFemale\"]")
    private  WebElement genderFemale;

    @AndroidFindBy(xpath = "//android.widget.TextView[@resource-id=\"android:id/text1\"]")
    private WebElement countryDropDown;

    @AndroidFindBy(xpath = "//android.widget.Button[@resource-id=\"com.androidsample.generalstore:id/btnLetsShop\"]")
    private WebElement shopButton;

    public WebElement getNameField(){
        return nameField;
    }

    public void setNameField(String name){
        waitForElement(nameField, driver);
        nameField.sendKeys(name);
        driver.hideKeyboard();
    }

    public WebElement getGenderMale() {
        return genderMale;
    }

    public void setGenderMale() {
        genderMale.click();
    }

    public WebElement getGenderFemale() {
        return genderFemale;
    }

    public void setGender(String gender) {
        if(gender.contains("Male")){
            genderMale.click();
        } else {
            genderFemale.click();
        }
    }

    public void selectCountry(String country){
        countryDropDown.click();
        scrollElement(country);
        driver.findElement(AppiumBy.xpath("//android.widget.TextView[@resource-id=\"android:id/text1\" and @text=\""+country+"\"]")).click();
    }

    public void clickLetsShop(){
        shopButton.click();
    }
}
