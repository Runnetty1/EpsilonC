package epsilonc;

/*
 ***************************************************************************
 * AUTHOR: AUDUN MOSENG & MATS HARWISS LAST EDITED: 15.12.2014 
 * LAST EDITED BY: MATS HARWISS
 *
 * CLASS PURPOSE: READ AND GET XML NODES FROM EXTERNAL FILE
 ***************************************************************************
 */

import java.io.File;
import javax.swing.JOptionPane;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class XMLReader {

    private String treeRoot;
    private int XMLNodeSize;
    private Document document;

    XMLReader() {
    }

    XMLReader(String xmlFile) {
        ///////////////////////////
        this.XMLNodeSize = 0;
        document = getXML(xmlFile);
        this.treeRoot = document.getFirstChild().getNodeName();
    }

    public String getTreeRoot() {
        return this.treeRoot;
    }

    public Document getXMLDoc() {
        return this.document;
    }

    private Document getXML(String filePath) {
        try {
            File fXmlFile;

            if (filePath.equalsIgnoreCase("")) {
                fXmlFile = new File(MainProgram.currXMLFilePath);
            } else {
                fXmlFile = new File(filePath);
            }

            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(fXmlFile);
            doc.getDocumentElement().normalize();
            return doc;
        } catch (Exception e) {
            System.err.println("ERROR: " + "R0 " + "- NO XML. @XMLREADER.GETXML, Line.60");
            JOptionPane.showMessageDialog(null, "Theres was a error when trying to read the domain.xml."
                    +"The document might not exist, or its not a propper XML Document!", 
                    "Warning", JOptionPane.PLAIN_MESSAGE);
        }
        return null;
    }

    public NodeList getNodeFromXML(String filePath, String nodeName) {
        try {
            File fXmlFile;

            if (filePath.equalsIgnoreCase("")) {
                fXmlFile = new File(MainProgram.currXMLFilePath);
            } else {
                fXmlFile = new File(filePath);
            }

            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(fXmlFile);
            doc.getDocumentElement().normalize();
            NodeList nList = doc.getElementsByTagName(nodeName);
            return nList;
        } catch (Exception e) {
            //Print error
            System.err.println("ERROR: " + "R0 " + "- NO XML NODES. @XMLREADER.GETNODEFROMXML, Line.62");
        }
        return null;
    }

    public void getNodeLengthFromXML(Node node) {
        this.XMLNodeSize++;
        NodeList nodeList = node.getChildNodes();
        for (int i = 0; i < nodeList.getLength(); i++) {
            Node currentNode = nodeList.item(i);
            if (currentNode.getNodeType() == Node.ELEMENT_NODE) {
                //calls this method for all the children which is Element
                getNodeLengthFromXML(currentNode);
            }
        }
    }

    public int getNodeSize() {
        getNodeLengthFromXML(document.getDocumentElement());
        return this.XMLNodeSize;
    }
}
