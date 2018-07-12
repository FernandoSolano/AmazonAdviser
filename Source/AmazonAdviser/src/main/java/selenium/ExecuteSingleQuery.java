package selenium;

import domain.Query;

public class ExecuteSingleQuery {

    public static void execute(Query query){
        SearchProduct.execute(query.getKeywords());
        //ClickProduct
        for (int i=2; i<query.getQueryLimit()+2; i++){
            ViewProduct.clickOnItem(i);
            CommonDriver.webDriver.navigate().back();
        }
        //Browse
        BrowseToAmazon.browse();
    }

}
