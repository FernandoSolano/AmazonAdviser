package xml;

import domain.Query;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.util.LinkedList;

public class ParseXML {

    public static LinkedList<Query> parseDocument(Document document) {

        LinkedList<Query> queries = new LinkedList<Query>();

        NodeList queriesRawList = document.getElementsByTagName("query");
        int queriesQuantity = queriesRawList.getLength();
        for (int i = 0; i < queriesQuantity; i++) {
            Node rawQuery = queriesRawList.item(i);
            if(rawQuery.getNodeType() == Node.ELEMENT_NODE){
                Element queryElement = (Element)rawQuery;
                //Values retrieval
                NodeList keywordsList = queryElement.getElementsByTagName("keywords");
                NodeList queryLimitList = queryElement.getElementsByTagName("queryLimit");
                //Values Assignment
                String keywords = keywordsList.item(0).getChildNodes().item(0)
                        .getNodeValue();
                int queryLimit = Integer.parseInt(queryLimitList.item(0).getChildNodes().item(0)
                        .getNodeValue());
                Query query = new Query(keywords, queryLimit);
                queries.add(query);
            }
        }
        return queries;
    }

}
