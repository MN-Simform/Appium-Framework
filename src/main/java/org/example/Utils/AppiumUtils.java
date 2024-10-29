package org.example.Utils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

public class AppiumUtils {

    WebDriverWait wait;
    AppiumDriverLocalService service;
    Properties prop = new Properties();

    String mainJSPath = "/lib/node_modules/appium/build/lib/main.js";


    public AppiumDriverLocalService startAppiumServer(String ipAddress, int port){
        service = new AppiumServiceBuilder().withAppiumJS(new File(mainJSPath))
                .withIPAddress(ipAddress).usingPort(port).build();
        service.start();
        return service;
    }

    public void waitForWebElementToAppear(AppiumDriver driver, WebElement element, String text){
        wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.attributeContains(element, "text", text));
    }

    public void waitForElement(WebElement element, AppiumDriver driver) {
        wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.refreshed(ExpectedConditions.visibilityOf(element)));
    }

    public List<HashMap<String, String>> getJsonData(String jsonFilePath) throws IOException {
        // Convert JSON file content to a JSON string
//        System.getProperty("user.dir") + "/src/test/java/testData/profile.json"
        String jsonContent = FileUtils.readFileToString(new File(jsonFilePath), StandardCharsets.UTF_8);

        ObjectMapper mapper = new ObjectMapper();
        List<HashMap<String, String>> data = mapper.readValue(
                jsonContent, new TypeReference<List<HashMap<String, String>>>() {}
        );
        return data;
    }

    public String getScreenshotPath(AppiumDriver driver, String testCaseName) throws IOException {
        File source = driver.getScreenshotAs(OutputType.FILE);
        String destinationFile = System.getProperty("user.dir")+"/reports" + testCaseName + ".png";
        FileUtils.copyFile(source, new File(destinationFile));
        return destinationFile;
    }

}
