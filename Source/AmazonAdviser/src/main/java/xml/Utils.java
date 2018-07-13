package xml;

import domain.MailDetails;
import domain.Query;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;

public class Utils {

    public static LinkedList<Query> fetchQueries() {
        return parseQueriesOnDocument(getDocument());
    }

    public static MailDetails fetchMailDetails() {
        return parseMailDetailsOnDocument(getDocument());
    }

    private static Document getDocument() {
        File file = new File("C:/Users/fsolano/Documents/GitHub/AmazonAdviser/Query Samples/Sample2.xml");
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory
                .newInstance();
        DocumentBuilder documentBuilder = null;
        Document document = null;
        try {
            documentBuilder = documentBuilderFactory.newDocumentBuilder();
            document = documentBuilder.parse(file);
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return document;
    }

    private static LinkedList<Query> parseQueriesOnDocument(Document document) {

        LinkedList<Query> queries = new LinkedList<Query>();

        NodeList queriesRawList = document.getElementsByTagName("query");
        int queriesQuantity = queriesRawList.getLength();
        for (int i = 0; i < queriesQuantity; i++) {
            Node rawQuery = queriesRawList.item(i);
            if (rawQuery.getNodeType() == Node.ELEMENT_NODE) {
                Element queryElement = (Element) rawQuery;
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

    private static MailDetails parseMailDetailsOnDocument(Document document) {
        MailDetails mailDetails = null;

        Node node = document.getElementsByTagName("email").item(0);
        if (node.getNodeType() == Node.ELEMENT_NODE) {
            Element queryElement = (Element) node;
            //Values retrieval
            NodeList usernameList = queryElement.getElementsByTagName("username");
            NodeList passwordList = queryElement.getElementsByTagName("password");
            NodeList fromList = queryElement.getElementsByTagName("from");
            NodeList toList = queryElement.getElementsByTagName("to");
            //Values Assignment
            String username = usernameList.item(0).getChildNodes().item(0)
                    .getNodeValue();
            String password = passwordList.item(0).getChildNodes().item(0)
                    .getNodeValue();
            String from = fromList.item(0).getChildNodes().item(0)
                    .getNodeValue();
            String to = toList.item(0).getChildNodes().item(0)
                    .getNodeValue();
            mailDetails = new MailDetails(username, password, from, to);
        }
        return mailDetails;
    }

}
