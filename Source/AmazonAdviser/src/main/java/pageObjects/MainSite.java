package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import selenium.CommonDriver;

public class MainSite {

    public static void goTo(){
        CommonDriver.webDriver.get("https://www.amazon.com/");
    }

    public static void searchFor(String searchValue) {
        WebElement searchBar = CommonDriver.webDriver.findElement(By.id("twotabsearchtextbox"));
        searchBar.sendKeys(searchValue);
        searchBar.submit();
    }
}
