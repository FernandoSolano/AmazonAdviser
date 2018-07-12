import domain.Query;
import selenium.SeleniumController;
import xml.ParseXML;
import xml.ReadXML;

import java.util.LinkedList;

public class MainClass {

    public static void main(String[] args) {
        LinkedList<Query> queries = ParseXML.parseDocument(new ReadXML().getDocument());
        SeleniumController.executeQueries(queries);
        //Send items by email
    }

}
