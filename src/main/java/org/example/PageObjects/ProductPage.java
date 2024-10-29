package org.example.PageObjects;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.example.Utils.AndroidActions;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class ProductPage extends AndroidActions {

    AndroidDriver driver;

    public ProductPage(AndroidDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    @AndroidFindBy(xpath = "(//android.widget.TextView[@resource-id=\"com.androidsample.generalstore:id/productAddCart\"])")
    private List<WebElement> addToCart;

    @AndroidFindBy(xpath = "//android.widget.ImageButton[@resource-id=\"com.androidsample.generalstore:id/appbar_btn_cart\"]")
    private WebElement cartButton;


    public void addToCartProductByIndex(int index){
        addToCart.get(index).click();
    }

    public void goToCart(){
        cartButton.click();
    }
}
