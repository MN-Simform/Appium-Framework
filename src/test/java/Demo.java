import com.google.common.collect.ImmutableMap;
import io.appium.java_client.android.Activity;
import org.example.PageObjects.CartPage;
import org.example.PageObjects.LoginPage;
import org.example.PageObjects.ProductPage;
import org.openqa.selenium.JavascriptExecutor;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public class Demo extends Baseclass{

    LoginPage loginPage;
    ProductPage productPage;
    CartPage cartPage;

    @BeforeMethod
    public void preSetup(){
//        com.androidsample.generalstore/com.androidsample.generalstore.MainActivity
//        Activity activity = new Activity("com.androidsample.generalstore","com.androidsample.generalstore.MainActivity");
        ((JavascriptExecutor)driver).executeScript("mobile:startActivity", ImmutableMap.of(
                "intent", "com.androidsample.generalstore/com.androidsample.generalstore.MainActivity"
        ));
    }

    @Test(priority = 1)
    public void fillingLoginForm(){
        loginPage = new LoginPage(driver);

        loginPage.setNameField("Nimit");
        loginPage.setGender("Male");
//        loginPage.selectCountry("India");
        loginPage.clickLetsShop();
    }

    @Test(priority = 2)
    public void addProductToCart(){
        productPage = new ProductPage(driver);
        fillingLoginForm();
        productPage.addToCartProductByIndex(0);
        productPage.goToCart();
    }

    @Test(priority = 3)
    public void purchaseProduct(){
        cartPage = new CartPage(driver);
        addProductToCart();
        double totalSum = cartPage.getProductSum();
        double displayFilteredSum = cartPage.getTotalAmountDisplayed();
        Assert.assertEquals(totalSum, displayFilteredSum);
        cartPage.acceptTermAndCondition();
        cartPage.submitOrder();
    }

    @Test(priority = 4, dataProvider = "testData")
    public void fillingLoginFormWithDataProvider(String name, String gender, String country){
        loginPage = new LoginPage(driver);

        loginPage.setNameField(name);
        loginPage.setGender(gender);
        loginPage.selectCountry(country);
        loginPage.clickLetsShop();
    }

    @Test(priority = 5, dataProvider = "testDataJson")
    public void fillingLoginFormWithJson(HashMap<String,String> input){
        loginPage = new LoginPage(driver);

        loginPage.setNameField(input.get("name"));
        loginPage.setGender(input.get("gender"));
        loginPage.selectCountry(input.get("country"));
        loginPage.clickLetsShop();
    }

    @DataProvider
    public Object[][] testData() throws IOException {
        return new Object[][] {{"Nimit", "Male", "India"},{"MN", "Male", "Argentina"}};
    }

    @DataProvider
    public Object[][] testDataJson() throws IOException {
        List<HashMap<String, String>> data = getJsonData(System.getProperty("user.dir") + "/src/test/java/testData/profile.json");
        return new Object[][] {{data.get(0)},{data.get(1)}};
    }


}
