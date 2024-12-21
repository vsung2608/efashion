//
//package com.example.e_fashion;
//
//import com.example.e_fashion.util.JiraIssueCreator;
//import org.junit.jupiter.api.Test;
//import org.openqa.selenium.By;
//import org.openqa.selenium.MutableCapabilities;
//import org.openqa.selenium.WebElement;
//import org.openqa.selenium.chrome.ChromeOptions;
//import org.openqa.selenium.edge.EdgeOptions;
//import org.openqa.selenium.firefox.FirefoxOptions;
//import org.openqa.selenium.ie.InternetExplorerOptions;
//import org.openqa.selenium.remote.RemoteWebDriver;
//import org.openqa.selenium.safari.SafariOptions;
//import org.openqa.selenium.support.ui.ExpectedConditions;
//import org.openqa.selenium.support.ui.WebDriverWait;
//import org.springframework.boot.test.context.SpringBootTest;
//
//import java.net.URL;
//import java.time.Duration;
//import java.util.HashMap;
//import java.util.Map;
//
//@SpringBootTest
//class EFashionApplicationTests {
//    private final String USERNAME = "oauth-vsung2608-a2167";
//    private final String ACCESS_KEY = "b99fe3ec-e6a3-4059-b248-9e0157224e85";
//
//    public void test_compatibility_with_login_feature(String platform, String browser, String version) throws Exception {
//
//        RemoteWebDriver driver = null;
//        String summary = "";
//        try {
//            MutableCapabilities browserOptions = null;
//
//            switch (browser) {
//                case "Chrome" -> browserOptions = new ChromeOptions();
//                case "Safari" -> browserOptions = new SafariOptions();
//                case "Edge" -> browserOptions = new EdgeOptions();
//                case "Internet Explorer" -> browserOptions = new InternetExplorerOptions();
//                case "Firefox" -> browserOptions = new FirefoxOptions();
//            }
//
//            assert browserOptions != null;
//            browserOptions.setCapability("platformName", platform);
//            browserOptions.setCapability("browserVersion", version);
//            Map<String, Object> sauceOptions = new HashMap<>();
//            sauceOptions.put("username", USERNAME);
//            sauceOptions.put("accessKey", ACCESS_KEY);
//            sauceOptions.put("build", "selenium build");
//            sauceOptions.put("name", "Compatibility Test on " + platform + " with " + browser + "browser");
//            sauceOptions.put("tunnelIdentifier", "oauth-vsung2608-a2167_tunnel_name");
//            sauceOptions.put("screenResolution", "1600x1200");
//            browserOptions.setCapability("sauce:options", sauceOptions);
//
//            URL url = new URL("https://ondemand.eu-central-1.saucelabs.com:443/wd/hub");
//            driver = new RemoteWebDriver(url, browserOptions);
//
//            driver.get("http://localhost:8080/efashion/general/home-page");
//
//            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
//
//            WebElement logBtn = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("loginBtn")));
//            logBtn.click();
//
//            WebElement emailField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("email")));
//            emailField.sendKeys("vsung2608@gmail.com");
//
//            WebElement passwordField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("password")));
//            passwordField.sendKeys("vsung2608");
//
//            WebElement loginButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("login")));
//
//            loginButton.click();
//
//            Thread.sleep(5000);
//
//
//            summary = "Compatibility testing of login and redirect functionality on " + platform + " with " + browser + " " + version;
//            if (driver.getCurrentUrl().matches(".*dashboard.*")) {
//                System.out.println("Đăng nhập thành công!");
//
//                JiraIssueCreator.automationCreateIssue(platform, browser, version, "Passed", "", summary);
//                ((RemoteWebDriver) driver).executeScript("sauce:job-result=" + "passed");
//            } else {
//                System.out.println("Đăng nhập thất bại!");
//                JiraIssueCreator.automationCreateIssue(platform, browser, version, "Failed", "does not", summary);
//                ((RemoteWebDriver) driver).executeScript("sauce:job-result=" + "failed");
//            }
//        } catch (Exception e) {
//            JiraIssueCreator.automationCreateIssue(platform, browser, version, "Failed", "does not", summary);
//            ((RemoteWebDriver) driver).executeScript("sauce:job-result=" + "failed");
//        } finally {
//            if (driver != null) driver.quit();
//        }
//    }
//
//    @Test
//    public void test() throws Exception {
//        test_compatibility_with_login_feature("Windows 10", "Chrome", "latest");
//        Thread.sleep(20000);
//        test_compatibility_with_login_feature("Windows 10", "Edge", "latest");
//        Thread.sleep(20000);
//        test_compatibility_with_login_feature("Windows 10", "Firefox", "latest");
//        Thread.sleep(20000);
//        test_compatibility_with_login_feature("Windows 10", "Internet Explorer", "latest");
//        Thread.sleep(20000);
//        test_compatibility_with_login_feature("macOS 13", "Safari", "latest");
//    }
//}
