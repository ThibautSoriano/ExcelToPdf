package main.java.datasdownloading;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import main.java.datasdownloading.entities.CampaignHeader;
import main.java.datasdownloading.entities.CampaignStatus;

import org.w3c.dom.Node;
import org.w3c.dom.Element;

public class XmlReader {

	public static void main(String args[]) {

		try {

			File fXmlFile = new File("session.xml");
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(fXmlFile);

			// optional, but recommended
			// read this -
			// http://stackoverflow.com/questions/13786607/normalization-in-dom-parsing-with-java-how-does-it-work
			doc.getDocumentElement().normalize();

			System.out.println("Root element :" + doc.getDocumentElement().getNodeName());

			NodeList nList = doc.getElementsByTagName("OpenSession");

			Node nNode = nList.item(0);

			if (nNode.getNodeType() == Node.ELEMENT_NODE) {

				Element eElement = (Element) nNode;

				System.out.println("session = " + eElement.getElementsByTagName("sessionID").item(0).getTextContent());
			}

			System.out.println("----------------------------");

			// for (int temp = 0; temp < nList.getLength(); temp++) {
			//
			// Node nNode = nList.item(temp);
			//
			// System.out.println("\nCurrent Element :" + nNode.getNodeName());
			//
			// if (nNode.getNodeType() == Node.ELEMENT_NODE) {
			//
			// Element eElement = (Element) nNode;
			//
			// System.out.println("Campaign name : " +
			// eElement.getElementsByTagName("name").item(0).getTextContent());
			// System.out.println("Client name : " +
			// eElement.getElementsByTagName("clientName").item(0).getTextContent());
			// System.out.println("Status : " +
			// eElement.getElementsByTagName("status").item(0).getTextContent());
			// Date date = new
			// Date(Integer.parseInt(eElement.getElementsByTagName("creationTS").item(0).getTextContent()));
			// System.out.println("Creation TS : " + date);
			// System.out.println("Start TS : " +
			// eElement.getElementsByTagName("startTS").item(0).getTextContent());
			// System.out.println("End TS : " +
			// eElement.getElementsByTagName("endTS").item(0).getTextContent());
			//
			// }
			// }
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String getSessionID(String xmlDatas) {
		String sessionID = "";
		
		
		try {
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(new InputSource(new ByteArrayInputStream(xmlDatas.getBytes("utf-8"))));

			doc.getDocumentElement().normalize();
			NodeList nList = doc.getElementsByTagName("OpenSession");

			Node nNode = nList.item(0);

			if (nNode.getNodeType() == Node.ELEMENT_NODE) {

				Element eElement = (Element) nNode;
				sessionID = eElement.getElementsByTagName("sessionID").item(0).getTextContent();

				System.out.println("session = " + sessionID);
			}
		} catch (ParserConfigurationException | SAXException | IOException e) {
			e.printStackTrace();
		}

		return sessionID;
	}

	public List<CampaignHeader> getHeaderList(String xmlDatas) {
		List<CampaignHeader> headerList = new ArrayList<>();

		try {
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(new InputSource(new ByteArrayInputStream(xmlDatas.getBytes("utf-8"))));

			doc.getDocumentElement().normalize();

			NodeList nList = doc.getElementsByTagName("campaign");

			for (int i = 0; i < nList.getLength(); i++) {

				Node nNode = nList.item(i);

				System.out.println("\nCurrent Element :" + nNode.getNodeName());

				if (nNode.getNodeType() == Node.ELEMENT_NODE) {

					Element eElement = (Element) nNode;

					String campaignName = eElement.getElementsByTagName("name").item(0).getTextContent();
					String clientName = eElement.getElementsByTagName("clientName").item(0).getTextContent();
					CampaignStatus campaignStatus = CampaignStatus
							.valueOf(eElement.getElementsByTagName("status").item(0).getTextContent().toUpperCase());
					Date creationDate = new Date(
							Integer.parseInt(eElement.getElementsByTagName("creationTS").item(0).getTextContent()));
					Date startDate = new Date(
							Integer.parseInt(eElement.getElementsByTagName("startTS").item(0).getTextContent()));
					Date endDate = new Date(
							Integer.parseInt(eElement.getElementsByTagName("endTS").item(0).getTextContent()));

					System.out.println("Campaign name : " + campaignName);
					System.out.println("Client name : " + clientName);
					System.out.println("Status : " + campaignStatus);
					System.out.println("Creation TS : " + creationDate);
					System.out.println("Start TS : " + startDate);
					System.out.println("End TS : " + endDate);

					headerList.add(new CampaignHeader(campaignName, clientName, campaignStatus, creationDate, startDate,
							endDate));

				}
			}
		} catch (ParserConfigurationException | SAXException | IOException e) {
			e.printStackTrace();
		}

		return headerList;
	}
}
