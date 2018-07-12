package selenium;

import domain.Query;

import java.util.LinkedList;

public class SeleniumController {

    public static void executeQueries(LinkedList<Query> queries) {
        //Browse
        BrowseToAmazon.browse();
        for (Query query : queries
                ) {
            ExecuteSingleQuery.execute(query);
        }
        //Close
        CommonDriver.webDriver.close();
    }
}
