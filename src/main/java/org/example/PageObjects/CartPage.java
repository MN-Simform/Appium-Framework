package org.example.PageObjects;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.example.Utils.AndroidActions;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class CartPage extends AndroidActions {

    AndroidDriver driver;

    public CartPage(AndroidDriver driver){
        super(driver);
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    @AndroidFindBy(xpath = "//android.widget.TextView[@resource-id=\"com.androidsample.generalstore:id/productPrice\"]")
    private List<WebElement> productList;

    @AndroidFindBy(xpath = "//android.widget.TextView[@resource-id=\"com.androidsample.generalstore:id/totalAmountLbl\"]")
    private WebElement totalAmount;

    @AndroidFindBy(xpath = "//android.widget.TextView[@resource-id=\"com.androidsample.generalstore:id/termsButton\"]")
    private WebElement termAndCondition;

    @AndroidFindBy(xpath = "//android.widget.Button[@resource-id=\"android:id/button1\"]")
    private WebElement acceptButton;

    @AndroidFindBy(xpath = "//android.widget.Button[@resource-id=\"com.androidsample.generalstore:id/btnProceed\"]")
    private WebElement proceedButton;

    public List<WebElement> getProductList(){
        return productList;
    }

    public double getProductSum(){
        int count = productList.size();
        double totalAmount = 0;
        for (WebElement element : productList) {
            String price = element.getText();
            double actualPrice = getFilteredAmount(price);
            totalAmount += actualPrice;
        }
        return totalAmount;
    }

    public double getFilteredAmount(String amount){
        return Double.parseDouble(amount.substring(1));
    }

    public double getTotalAmountDisplayed(){
        return getFilteredAmount(totalAmount.getText());
    }

    public void acceptTermAndCondition(){
        longPressAction(termAndCondition);
        acceptButton.click();
    }

    public void submitOrder(){
        proceedButton.click();
    }

}
