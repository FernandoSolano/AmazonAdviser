import domain.Query;
import mail.MailController;
import selenium.QueryController;
import xml.Utils;

import java.util.LinkedList;

public class MainClass {

    public static void main(String[] args) {
        LinkedList<Query> queries = Utils.fetchQueries();
        QueryController qc = new QueryController();
        qc.getProductsFromQueries(queries);
        MailController.sendNew();
    }

}
