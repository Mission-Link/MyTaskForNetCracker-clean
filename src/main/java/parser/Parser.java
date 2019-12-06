package parser;

import actions.helpers.ActionDeque;
import actions.helpers.ActionFactory;
import logger.SimpleLogger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import strikepackage.Browser;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

/**
 * This is DOM parser class extracts Action subclass instances from
 * XML file
 * parsed Action subclass instances
 */
public class Parser {
    private String pathToXML;
    private ActionFactory actionFactory;
    private SimpleLogger simpleLogger;

    public Parser(String pathToXML, Browser browser) {
        this.pathToXML = pathToXML;
        actionFactory = new ActionFactory(browser);
        simpleLogger = new SimpleLogger();
    }

    /**
     * Method parseXML parses XML file and returns deque
     * with Action subclasses
     *
     * @return ActionDeque instance where all actions are stored
     */
    public ActionDeque parseXML() {
        ActionDeque actionDeque = new ActionDeque();
        File xmlFile = new File(pathToXML);
        if (!xmlFile.exists()) {  //if file does not exist
            System.out.println("XML file does not exist, exit");
            ArrayList<String> params = new ArrayList<>();
            params.add("Scenario XML file does not exist");
            actionDeque.putAction(actionFactory.createAction(params, simpleLogger));
            return actionDeque;
        }

        //get child nodes of a root element
        NodeList nodeList = retrieveNodeList();
        //get all elements with tag name Action
        ArrayList<Element> arrayListWithActionElements = retrieveActionElements(nodeList);
        //assemble collection with collection of all parameters of an Action element
        ArrayList<ArrayList<String>> allReceivedParams =
                retrieveParametersOfActions(arrayListWithActionElements);

//        System.out.println("Size of big coll is: " + allReceivedParams.size());

        for (ArrayList<String> tmp : allReceivedParams) {
            actionDeque.putAction(actionFactory.createAction(tmp, simpleLogger));
        }

        return actionDeque;
    }

    /**
     * A helper method that parses XML file and returns all child nodes
     * of a root node of an XML DOM structure
     *
     * @return (Nodelist) all child nodes of a root element
     */
    private NodeList retrieveNodeList() {
        File xmlFile = new File(pathToXML);
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db = null;
        try {
            db = dbf.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            System.out.println("Inappropriate configuration: \n" + e.getMessage());
            e.printStackTrace();
        }
        Document doc = null;
        try {
            assert db != null;
            doc = db.parse(xmlFile);
        } catch (SAXException | IOException e) {
            System.out.println("Exception occurred while parsing: \n" + e.getMessage());
            e.printStackTrace();
        }

        if (doc != null) {
            doc.getDocumentElement().normalize(); //normalization
        }

        assert doc != null;
        Element root = doc.getDocumentElement();
        return root.getChildNodes();
    }

    private ArrayList<Element> retrieveActionElements(NodeList parent) {
        ArrayList<Element> arrayListWithActNodes = new ArrayList<>();
        analyseNode(arrayListWithActNodes, parent);
        return arrayListWithActNodes;
    }

    /**
     * Helper recursive method
     * for method retrieveActionElements(NodeList parent)
     *
     * @param parent parent node
     */
    private static void analyseNode(ArrayList<Element> nodeListArrayList, NodeList parent) {
        for (int i = 0; i < parent.getLength(); i += 1) {
            Node node = parent.item(i);
            if (node.getNodeType() == Node.ELEMENT_NODE) {
                Element element = (Element) node;
                if (element.getNodeName().toLowerCase().equals("action")) {
                    nodeListArrayList.add(element); //if current elem has name action, then add it
//                    retrieveParametersOfActions();
                } else { //
//                    System.out.println("Element (getNodeName)" + element.getNodeName() + " HAS child nodes");
//                    System.out.println("Child tag val: " + element.getChildNodes().item(0).getNodeValue());
                    analyseNode(nodeListArrayList, element.getChildNodes()); //parse it further
                }
            }
        }
    }//end of method

    /**
     * Processes action element tags to create an Action subclass obj and
     * to store it in deque
     */
    private ArrayList<ArrayList<String>> retrieveParametersOfActions(
            ArrayList<Element> elementArrayList) {
        ArrayList<ArrayList<String>> paramsForActions = new ArrayList<>();
        ArrayList<String> actionArrayList;

        for (Element tmp : elementArrayList) {
            NodeList nodeList = tmp.getChildNodes();
            actionArrayList = new ArrayList<>();
            for (int i = 0; i < nodeList.getLength(); i++) {//analyse each Action Element
                Node node = nodeList.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) node;
                    String s = element.getChildNodes().item(0).getNodeValue(); //get its parameter
//                    System.out.println("Received string: " + s);
                    actionArrayList.add(s);
                }
            }//end of inner loop for analysing each Action elem

            /*an output to verify correctness of processing
            System.out.println("========Here are parameters: ");
            for(String param: actionArrayList){
                System.out.println(param);
            }
            System.out.println("=========");
            */

            paramsForActions.add(actionArrayList); //add assembled params of an Action elem
        }//end of outer loop

        return paramsForActions;
    }//end of method retrieveParametersOfActions()

}//end of class
