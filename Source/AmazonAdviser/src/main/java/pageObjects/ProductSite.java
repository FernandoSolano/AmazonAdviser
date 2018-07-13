package pageObjects;

import domain.Product;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import selenium.CommonDriver;
import selenium.QueryController;

public class ProductSite {

    private static final String descriptionXpathRoute = "//*[@id='productDescription']/p";
    private static final String ourPriceIntegerXpathRoute = "//*[@id='priceblock_ourprice']/span[2]";
    private static final String ourPriceDecimalXpathRoute = "//*[@id='priceblock_ourprice']/span[3]";
    private static final String usedPriceIntegerXpathRoute = "//*[@id='priceblock_usedprice']/span[2]";
    private static final String usedPriceDecimalXpathRoute = "//*[@id='priceblock_usedprice']/span[3]";

    public static void goBack() {
        CommonDriver.webDriver.navigate().back();
    }

    public static void addItemToList() {
        String name = CommonDriver.webDriver.findElement(By.id("productTitle")).getText();
        String description = CommonDriver.webDriver.findElement(By.xpath(descriptionXpathRoute)).getText();
        Double price = getProductPrice();
        String url = CommonDriver.webDriver.getCurrentUrl();
        Product product = new Product(name, description, url, price);
        QueryController.products.add(product);
    }

    private static double getProductPrice() {
        if (existsElementByXpath(ourPriceIntegerXpathRoute)) {
            String priceIntegerValues = CommonDriver.webDriver.findElement(By.xpath(ourPriceIntegerXpathRoute)).getText();
            if (!priceIntegerValues.equals("")) {
                return Double.parseDouble(priceIntegerValues + "." + CommonDriver.webDriver.findElement(By.xpath(ourPriceDecimalXpathRoute)).getText());
            } else {
                return Double.parseDouble(CommonDriver.webDriver.findElement(By.xpath(usedPriceIntegerXpathRoute)).getText() + "." + CommonDriver.webDriver.findElement(By.xpath(usedPriceDecimalXpathRoute)).getText());
            }
        } else {
            return Double.parseDouble(getCleanedPriceString(CommonDriver.webDriver.findElement(By.id("priceblock_ourprice")).getText()));
        }
    }

    private static boolean existsElementByXpath(String xpath) {
        try {
            CommonDriver.webDriver.findElement(By.xpath(xpath));
        } catch (NoSuchElementException e) {
            return false;
        }
        return true;
    }

    private static String getCleanedPriceString(String rawString) {
        return rawString.substring(1);
    }

}
