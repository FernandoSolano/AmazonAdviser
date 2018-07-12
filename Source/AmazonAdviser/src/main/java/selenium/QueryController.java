package selenium;

import domain.Query;
import org.openqa.selenium.NoSuchElementException;
import pageObjects.MainSite;
import pageObjects.SearchResultsSite;

import java.util.LinkedList;

public class QueryController {

    public static void executeQueries(LinkedList<Query> queries) {
        //Browse
        MainSite.goTo();
        for (Query query : queries
                ) {
            searchFor(query);
        }
        //Close
        CommonDriver.webDriver.close();
    }

    public static void searchFor(Query query){
        int queryLimit = query.getQueryLimit();
        MainSite.searchFor(query.getKeywords());
        //ClickProduct
        for (int i=0; i<queryLimit; i++){
            try{
                SearchResultsSite.clickOnItem(i);
                CommonDriver.webDriver.navigate().back();
            }catch (NoSuchElementException e){
                System.out.print(e);
                queryLimit++;
            }
        }
        //Browse
        MainSite.goTo();
    }
}
