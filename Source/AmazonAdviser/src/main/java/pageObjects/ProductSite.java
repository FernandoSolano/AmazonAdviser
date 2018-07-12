package pageObjects;

import selenium.CommonDriver;

public class ProductSite {

    public static void goBack(){
        CommonDriver.webDriver.navigate().back();
    }

}
