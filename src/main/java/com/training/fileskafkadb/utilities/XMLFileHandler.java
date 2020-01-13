package com.training.fileskafkadb.utilities;

import com.training.fileskafkadb.dta.Employee;
import org.w3c.dom.Document;
import org.xml.sax.*;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.util.*;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;

public class XMLFileHandler implements MyFileHandler
{
    private static int index = 0;
    private DocumentBuilderFactory documentBuilderFactory;
    private Document document;
    private DocumentBuilder documentBuilder;
    private Element root;
    public XMLFileHandler() {
        this.documentBuilderFactory = DocumentBuilderFactory.newInstance();
        try {
            documentBuilder = documentBuilderFactory.newDocumentBuilder();
            this.document = documentBuilder.newDocument();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }
        this.root = document.createElement("Employees");
        document.appendChild(root);
    }
    public static void main(String[] args) {
    }
    public Employee readEmployee() throws ParseException {
        Employee employe = new Employee();
        File objFile = new File("/Users/parastripathi/Downloads/employee.xml");
        DocumentBuilderFactory objFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder objBuilder = null;
        Document objDocument = null;
        try {
            objBuilder = objFactory.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }
        try {
            objDocument = objBuilder.parse(objFile);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        }
        objDocument.getDocumentElement().normalize();
        NodeList nList = objDocument.getElementsByTagName("employee");
        //  for (int i = 0; i < nList.getLength(); i++) {
        Node nNode = nList.item(index++);
        Element elem = (Element) nNode;
        Node node1 = elem.getElementsByTagName("firstName").item(0);
        String fname = node1.getTextContent();
        Node node2 = elem.getElementsByTagName("lastName").item(0);
        String lname = node2.getTextContent();
        Node node3 = elem.getElementsByTagName("dateOfBirth").item(0);
        String dob = node3.getTextContent();
        Node node4 = elem.getElementsByTagName("experience").item(0);
        String exp = node4.getTextContent();
        employe.setFirstName(fname);
        employe.setLastName(lname);
        employe.setDateOfBirth(new SimpleDateFormat("MM/dd/yy").parse(dob));
        employe.setExperience(new Double(exp));
        // }
        return employe;
    }
    public void writeEmployee(Employee obj) {
        try {
            String fName = obj.getFirstName();
            String lName = obj.getLastName();
            Date dateOne = obj.getDateOfBirth();
            Format formatter = new SimpleDateFormat("dd/MM/yy");
            String str = formatter.format(dateOne);
            double exp = obj.getExperience();
            String strexp = Double.toString(exp);
            Element employee = document.createElement("employee");
            root.appendChild(employee);
            Element firstName = document.createElement("firstName");
            firstName.appendChild(document.createTextNode(fName));
            employee.appendChild(firstName);
            Element lastname = document.createElement("lastName");
            lastname.appendChild(document.createTextNode(lName));
            employee.appendChild(lastname);
            Element dateOfBirth = document.createElement("dateOfBirth");
            dateOfBirth.appendChild(document.createTextNode(str));
            employee.appendChild(dateOfBirth);
            Element experience = document.createElement("experience");
            experience.appendChild(document.createTextNode(strexp + "\n"));
            employee.appendChild(experience);
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource domSource = new DOMSource(document);
            StreamResult streamResult = new StreamResult(new File("/Users/parastripathi/Downloads/employee_new.xml"));
            transformer.transform(domSource, streamResult);
//            System.out.println("Done");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
