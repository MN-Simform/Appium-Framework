import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import org.example.Utils.AppiumUtils;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.time.Duration;
import java.util.Properties;

public class Baseclass extends AppiumUtils {

    AndroidDriver driver;
    AppiumDriverLocalService service;

    Properties prop = new Properties();
    FileInputStream fis;

    String mainJSPath = "/lib/node_modules/appium/build/lib/main.js";
    String ipAddress = "127.0.0.1";
    int port = 4723;

    @BeforeClass
    public void configureAppium() throws URISyntaxException, IOException {

//        service = new AppiumServiceBuilder().withAppiumJS(new File(mainJSPath))
//                .withIPAddress(ipAddress).usingPort(port).build();
//        service.start();

        fis = new FileInputStream("/home/nimit@simform.dom/IdeaProjects/AppiumFrameworkDesign/global.properties");
        prop.load(fis);

        service = startAppiumServer(prop.getProperty("ipAddress"), Integer.parseInt(prop.getProperty("port")));

        UiAutomator2Options options = new UiAutomator2Options();
//        options.setDeviceName("Pixel 8 Pro API 35");
        options.setDeviceName(prop.getProperty("androidDeviceName"));
        options.setApp("/home/nimit@simform.dom/IdeaProjects/AppiumApps/APKFiles/General-Store.apk");

        driver = new AndroidDriver(new URI("http://127.0.0.1:4723").toURL(),options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
    }

    @AfterClass
    public void tearDown(){
        driver.quit();
        service.stop();
    }
}