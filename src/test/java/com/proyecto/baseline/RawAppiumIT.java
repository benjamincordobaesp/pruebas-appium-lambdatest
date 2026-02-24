package com.proyecto.baseline;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.URL;
import java.time.Duration;
import java.util.HashMap;

public class RawAppiumIT {

    @Test
    public void testCalculatorOnLambdaTest() throws Exception {
        String username = System.getenv("LT_USERNAME");
        String accessKey = System.getenv("LT_ACCESS_KEY");
        String gridUrl = "https://" + username + ":" + accessKey + "@mobile-hub.lambdatest.com/wd/hub";

        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability("appium:deviceName", "Pixel 7");
        capabilities.setCapability("appium:platformVersion", "13");
        capabilities.setCapability("appium:appPackage", "com.google.android.calculator");
        capabilities.setCapability("appium:appActivity", "com.android.calculator2.Calculator");

        HashMap<String, Object> ltOptions = new HashMap<String, Object>();
        ltOptions.put("w3c", true);
        ltOptions.put("isRealMobile", true);
        ltOptions.put("plugin", "java-junit");
        ltOptions.put("build", "Raw Appium Debug");
        ltOptions.put("name", "Vanilla Appium Execution");
        ltOptions.put("user", username);
        ltOptions.put("accessKey", accessKey);
        capabilities.setCapability("lt:options", ltOptions);

        AndroidDriver driver = new AndroidDriver(new URL(gridUrl), capabilities);

        try {
            System.out.println("DRIVER INITIALIZED: " + driver.getSessionId());
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

            System.out.println("Waiting for digit 5...");
            WebElement digit5 = wait.until(ExpectedConditions
                    .presenceOfElementLocated(AppiumBy.id("com.google.android.calculator:id/digit_5")));
            System.out.println("Clicking 5...");
            digit5.click();

            System.out.println("Waiting for plus...");
            WebElement plus = wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.accessibilityId("plus")));
            System.out.println("Clicking plus...");
            plus.click();

            System.out.println("Waiting for digit 4...");
            WebElement digit4 = wait.until(ExpectedConditions
                    .presenceOfElementLocated(AppiumBy.id("com.google.android.calculator:id/digit_4")));
            System.out.println("Clicking 4...");
            digit4.click();

            System.out.println("Waiting for equals...");
            WebElement equals = wait
                    .until(ExpectedConditions.presenceOfElementLocated(AppiumBy.accessibilityId("equals")));
            System.out.println("Clicking equals...");
            equals.click();

            System.out.println("Finding result...");
            WebElement result = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    AppiumBy.xpath("//android.widget.TextView[contains(@resource-id, 'result')]")));
            System.out.println("RESULT IS: " + result.getText());
        } finally {
            System.out.println("QUITING DRIVER...");
            driver.quit();
        }
    }
}
