package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import selenium.CommonDriver;

public class SearchResultsSite {

    public static void clickOnItem(int itemNumber) throws NoSuchElementException {
        WebElement item = CommonDriver.webDriver.findElement(By.xpath("//*/li[@id='result_"+itemNumber+"'][@class='s-result-item celwidget  ']//*/div/a[@class='a-link-normal a-text-normal']"));
        item.click();
    }

}
