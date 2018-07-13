package selenium;

import domain.Query;

import java.util.LinkedList;

public interface QueryInterface {

    void getProductsFromQueries(LinkedList<Query> queries);

    void searchFor(Query query);

}
