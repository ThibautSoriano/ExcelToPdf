package main.java.datasdownloading;

import javax.xml.parsers.DocumentBuilderFactory;

import java.io.File;
import java.util.Date;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

import main.java.datasdownloading.entities.CampaignHeader;

import org.w3c.dom.Node;
import org.w3c.dom.Element;

public class XmlReader {

	public static void main(String args[]) {

	    try {

		File fXmlFile = new File("campaigns.xml");
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
		Document doc = dBuilder.parse(fXmlFile);
				
		//optional, but recommended
		//read this - http://stackoverflow.com/questions/13786607/normalization-in-dom-parsing-with-java-how-does-it-work
		doc.getDocumentElement().normalize();

		System.out.println("Root element :" + doc.getDocumentElement().getNodeName());
				
		NodeList nList = doc.getElementsByTagName("campaign");
				
		System.out.println("----------------------------");

		for (int temp = 0; temp < nList.getLength(); temp++) {

			Node nNode = nList.item(temp);
					
			System.out.println("\nCurrent Element :" + nNode.getNodeName());
					
			if (nNode.getNodeType() == Node.ELEMENT_NODE) {

				Element eElement = (Element) nNode;
				
				System.out.println("Campaign name : " + eElement.getElementsByTagName("name").item(0).getTextContent());
				System.out.println("Client name : " + eElement.getElementsByTagName("clientName").item(0).getTextContent());
				System.out.println("Status : " + eElement.getElementsByTagName("status").item(0).getTextContent());
				Date date = new Date(Integer.parseInt(eElement.getElementsByTagName("creationTS").item(0).getTextContent()));
				System.out.println("Creation TS : " + date);
				System.out.println("Start TS : " + eElement.getElementsByTagName("startTS").item(0).getTextContent());
				System.out.println("End TS : " + eElement.getElementsByTagName("endTS").item(0).getTextContent());

			}
		}
	    } catch (Exception e) {
		e.printStackTrace();
	    }
	  }
	
	public List<CampaignHeader> getHeaderList(String xmlDatas) {
		
		return null;
	}
}
