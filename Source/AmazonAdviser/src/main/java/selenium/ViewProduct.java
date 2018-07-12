package selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class ViewProduct {

    public static void clickOnItem(int itemNumber) {
        WebElement item = CommonDriver.webDriver.findElement(By.xpath("//*[@id=\"result_"+itemNumber+"\"]/div/div/div/div[1]/div/div/a"));
        item.click();
    }

}
