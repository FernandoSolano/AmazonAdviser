package selenium;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class BrowseToAmazon {

    public static void browse(){
        WebDriver driver = new ChromeDriver();
        driver.get("https://www.amazon.com/");
    }

}
