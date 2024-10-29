package org.example.Utils;

import com.google.common.collect.ImmutableMap;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;

public class AndroidActions extends AppiumUtils {

    AndroidDriver driver;

    public AndroidActions(AndroidDriver driver){
        this.driver = driver;
    }

    public void scrollElement(String elementTillScroll){
        driver.findElement(AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\""+ elementTillScroll +"\"));"));
    }

    public void longPressAction(WebElement element){
        driver.executeScript("mobile: longClickGesture", ImmutableMap.of(
                "elementId", ((RemoteWebElement)element).getId(),
                "duration", 2000
        ));
    }
}
