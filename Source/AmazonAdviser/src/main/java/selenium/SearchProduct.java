package selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class SearchProduct {
    public static void execute(String searchValue) {
        WebElement searchBar = CommonDriver.webDriver.findElement(By.id("twotabsearchtextbox"));
        searchBar.sendKeys(searchValue);
        searchBar.submit();
    }
}
