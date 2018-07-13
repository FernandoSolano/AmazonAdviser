package selenium;

import domain.Product;
import domain.Query;
import org.openqa.selenium.NoSuchElementException;
import pageObjects.MainSite;
import pageObjects.ProductSite;
import pageObjects.SearchResultsSite;

import java.util.LinkedList;

public class QueryController implements QueryInterface {

    public static LinkedList<Product> products;

    public QueryController() {
        products = new LinkedList<Product>();
    }

    public void getProductsFromQueries(LinkedList<Query> queries) {
        //Browse
        MainSite.goTo();
        for (Query query : queries
                ) {
            searchFor(query);
        }
        //Close
        CommonDriver.webDriver.close();
    }

    public void searchFor(Query query) {
        int queryLimit = query.getQueryLimit();
        MainSite.searchFor(query.getKeywords());
        //ClickProduct
        for (int i = 0; i < queryLimit; i++) {
            try {
                SearchResultsSite.clickOnItem(i);
                ProductSite.addItemToList();
                ProductSite.goBack();
            } catch (NoSuchElementException e) {
                System.out.print(e);
                queryLimit++;
            }
        }
        //Browse
        MainSite.goTo();
    }
}
