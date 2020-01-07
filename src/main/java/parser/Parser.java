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
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

/**
 * This is a DOM parser class.
 * Its purpose is to extract Action
 * subclass instances from XML file.
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
     * @throws FileNotFoundException if XML file is absent
     */
    public ActionDeque parseXML() throws FileNotFoundException {
        ActionDeque actionDeque = new ActionDeque();
        File xmlFile = new File(pathToXML);
        if (!xmlFile.exists()) {  //if file does not exist
            System.out.println("XML file does not exist, exit");
            throw new FileNotFoundException("XML file by path \"" + pathToXML + "\" does not exist");
        }

        //get child nodes of a root element
        NodeList nodeList = retrieveNodeList();
        //get all elements with tag name Action
        ArrayList<Element> arrayListWithActionElements = retrieveActionElements(nodeList);
        //assemble collection with collections of all parameters of Action elements
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

    /**
     * Method to get all elements with tag name Action
     *
     * @param parentNodeList node list
     * @return ArrayList<Element> with elements that have tag name Action
     */
    private ArrayList<Element> retrieveActionElements(NodeList parentNodeList) {
        ArrayList<Element> arrayListWithActNodes = new ArrayList<>();
        analyseNode(arrayListWithActNodes, parentNodeList);
        return arrayListWithActNodes;
    }

    /**
     * Helper recursive method
     * for method retrieveActionElements(NodeList parentNodeList)
     *
     * @param parentNodeList        parentNodeList node list
     * @param arrayListWithActNodes array list that stores all
     *                              elements with tag Action
     */
    private static void analyseNode(ArrayList<Element> arrayListWithActNodes, NodeList parentNodeList) {
        for (int i = 0; i < parentNodeList.getLength(); i += 1) {
            Node node = parentNodeList.item(i);
            if (node.getNodeType() == Node.ELEMENT_NODE) {
                Element element = (Element) node;
                if (element.getNodeName().toLowerCase().equals("action")) {
                    arrayListWithActNodes.add(element); //if current elem has name action, then add it
//                    retrieveParametersOfActions();
                } else { //
//                    System.out.println("Element (getNodeName)" + element.getNodeName() + " HAS child nodes");
//                    System.out.println("Child tag val: " + element.getChildNodes().item(0).getNodeValue());
                    analyseNode(arrayListWithActNodes, element.getChildNodes()); //parse it further
                }
            }
        }
    }//end of method

    /**
     * Processes array list of action element tags in order to extract all
     * parameters of Action elements
     *
     * @param elementArrayList ArrayList that stores all Action elements
     * @return ArrayList<ArrayList       <       String>> with all parameters of
     * all Action elements
     */
    private ArrayList<ArrayList<String>> retrieveParametersOfActions(
            ArrayList<Element> elementArrayList) {

        ArrayList<ArrayList<String>> allActionParams
                = new ArrayList<>(); //parameters of all Action elements

        ArrayList<String> eachActionParams; //parameters of each Action element

        for (Element tmp : elementArrayList) {
            NodeList nodeList = tmp.getChildNodes(); //child nodelist of each Action element
            eachActionParams = new ArrayList<>();
            //analyse child nodelist of each Action element
            for (int i = 0; i < nodeList.getLength(); i++) {
                Node node = nodeList.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) node;
                    String s = element.getChildNodes().item(0).getNodeValue(); //get its parameter
//                    System.out.println("Received string: " + s);
                    eachActionParams.add(s);
                }
            }//end of inner loop for analysing each Action elem

            /*an output to verify correctness of processing
            System.out.println("========Here are parameters: ");
            for(String param: eachActionParams){
                System.out.println(param);
            }
            System.out.println("=========");
            */

            allActionParams.add(eachActionParams); //add assembled params of an Action elem
        }//end of outer loop

        return allActionParams;
    }//end of method retrieveParametersOfActions()

}//end of class
