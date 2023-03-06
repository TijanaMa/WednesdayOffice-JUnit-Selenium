package com.cydeo.utilities;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;

import java.net.URL;
import java.time.Duration;

public class Driver {


    static String browser;


   private Driver(){

   }

   private static InheritableThreadLocal<WebDriver> driverPool = new InheritableThreadLocal<>();


   public static WebDriver getDriver(){
//
//       if(driverPool.get()==null){
//
//           String browserType = ConfigurationReader.getProperty("browser");
//
//           switch (browserType){
//
//               case "chrome":
//                  // WebDriverManager.chromedriver().setup();
//                   //driver = new ChromeDriver();  --> single run
//                   driverPool.set(new ChromeDriver());   // parallel run
//                  // driver.manage().window().maximize();
//                   driverPool.get().manage().window().maximize();
//                   driverPool.get().manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
//                   break;
//               case "firefox":
//                 //  WebDriverManager.firefoxdriver().setup();
//                  // driver = new FirefoxDriver();  ---> single run
//                   driverPool.set(new FirefoxDriver());
//                   driverPool.get().manage().window().maximize();
//                   driverPool.get().manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
//                   break;
//
//           }
//
//       }
//
//       return driverPool.get();


       if (driverPool.get() == null) {
           if (System.getProperty("BROWSER") == null) {   // DBROWSER (remote-chrome) - define System Property
               browser = ConfigurationReader.getProperty("browser");
           } else {
               browser = System.getProperty("BROWSER");
           }
           System.out.println("Browser: " + browser);
           switch (browser) {
               case "remote-chrome":
                   try {
                       // assign your grid server address
                       String gridAddress = "18.207.207.140";
                       URL url = new URL("http://" + gridAddress + ":4444/wd/hub");
                       DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
                       desiredCapabilities.setBrowserName("chrome");
                       driverPool.set(new RemoteWebDriver(url, desiredCapabilities));
                   } catch (Exception e) {
                       e.printStackTrace();
                   }
                   break;
               case "remote-firefox":
                   try {
                       // assign your grid server address
                       String gridAddress = "174.129.57.20";
                       URL url = new URL("http://" + gridAddress + ":4444/wd/hub");
                       DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
                       desiredCapabilities.setBrowserName("firefox");
                       driverPool.set(new RemoteWebDriver(url, desiredCapabilities));
                   } catch (Exception e) {
                       e.printStackTrace();
                   }
                   break;
               case "chrome":
                   driverPool.set(new ChromeDriver());
                   break;
               case "chrome-headless":
                   driverPool.set(new ChromeDriver(new ChromeOptions().setHeadless(true)));
                   break;
               case "firefox":
                   driverPool.set(new FirefoxDriver());

                   break;
               case "firefox-headless":
                   driverPool.set(new FirefoxDriver(new FirefoxOptions().setHeadless(true)));
                   break;

               case "edge":
                   if (System.getProperty("os.name").toLowerCase().contains("mac")) {
                       throw new WebDriverException("Your operating system does not support the requested browser");
                   }
                   driverPool.set(new EdgeDriver());

                   break;

               case "safari":
                   if (System.getProperty("os.name").toLowerCase().contains("windows")) {
                       throw new WebDriverException("Your operating system does not support the requested browser");
                   }
                   driverPool.set(new SafariDriver());
           }
       }

       return driverPool.get();
   }




    public static void closeDriver(){

        if(driverPool.get()!=null){
            driverPool.get().quit();
           // driver=null;  --> single run  reasigning to null
            driverPool.remove(); // --> parallel run doing the same thing
        }

    }



}
