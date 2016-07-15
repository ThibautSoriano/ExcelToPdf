package main.java.datasdownloading;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import main.java.datasdownloading.entities.Campaign;
import main.java.datasdownloading.entities.CampaignHeader;
import main.java.datasdownloading.entities.CampaignStatus;
import main.java.excelreader.entities.CampaignRow;
import main.java.utils.Percentage;

import org.w3c.dom.Node;
import org.w3c.dom.Element;

public class XmlReader {
	
	private Map<String, String> placementsNames = new HashMap<>();
	
	private List<CampaignHeader> allHeaders = new ArrayList<>();
	
	public XmlReader(String xmlHeaderDatas) {
		allHeaders = getHeaderList(xmlHeaderDatas);
	}

	public List<CampaignHeader> getAllHeaders() {
		return allHeaders;
	}

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

	public static String getSessionID(String xmlDatas) {
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

	private List<CampaignHeader> getHeaderList(String xmlDatas) {
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

					String campaignID = eElement.getElementsByTagName("campaignID").item(0).getTextContent();
					String campaignName = eElement.getElementsByTagName("name").item(0).getTextContent();
					String clientName = eElement.getElementsByTagName("clientName").item(0).getTextContent();
					CampaignStatus campaignStatus = CampaignStatus
							.valueOf(eElement.getElementsByTagName("status").item(0).getTextContent().toUpperCase());
					Date creationDate = new Date(
							Long.parseLong(eElement.getElementsByTagName("creationTS").item(0).getTextContent()) * 1000);
					Date startDate = new Date(
							Long.parseLong(eElement.getElementsByTagName("startTS").item(0).getTextContent()) * 1000);
					Date endDate = new Date(
							Long.parseLong(eElement.getElementsByTagName("endTS").item(0).getTextContent()) * 1000);

					System.out.println("Campaign ID : " + campaignID);
					System.out.println("Campaign name : " + campaignName);
					System.out.println("Client name : " + clientName);
					System.out.println("Status : " + campaignStatus);
					System.out.println("Creation TS : " + creationDate);
					System.out.println("Start TS : " + startDate);
					System.out.println("End TS : " + endDate);

					headerList.add(new CampaignHeader(campaignID, campaignName, clientName, campaignStatus, creationDate, startDate,
							endDate));

				}
			}
		} catch (ParserConfigurationException | SAXException | IOException e) {
			e.printStackTrace();
		}

		return headerList;
	}
	
	public void fillMapPlacementsNames(String xmlPlacementList) {		
		try {
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(new InputSource(new ByteArrayInputStream(xmlPlacementList.getBytes("utf-8"))));

			doc.getDocumentElement().normalize();
			NodeList nList = doc.getElementsByTagName("placement");

			for (int i = 0; i < nList.getLength(); i++) {

				Node nNode = nList.item(i);

				if (nNode.getNodeType() == Node.ELEMENT_NODE) {
	
					Element eElement = (Element) nNode;
					placementsNames.put(eElement.getElementsByTagName("placementID").item(0).getTextContent(), eElement.getElementsByTagName("name").item(0).getTextContent());
					System.out.println("placement ID = " + eElement.getElementsByTagName("placementID").item(0).getTextContent());
					System.out.print("placementName = " + eElement.getElementsByTagName("name").item(0).getTextContent());
				}
			}
		} catch (ParserConfigurationException | SAXException | IOException e) {
			e.printStackTrace();
		}
	}
	
	public Campaign getCampaign(String campaignID, String xmlCampaignDatas, String xmlPlacementList) {
		Campaign c;
		List<CampaignRow> rows = new ArrayList<>();
		
		try {
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(new InputSource(new ByteArrayInputStream(xmlPlacementList.getBytes("utf-8"))));

			doc.getDocumentElement().normalize();
			NodeList nList = doc.getElementsByTagName("statisticsRecord");

			for (int i = 0; i < nList.getLength(); i++) {

				Node nNode = nList.item(i);

				if (nNode.getNodeType() == Node.ELEMENT_NODE) {
	
					Element eElement = (Element) nNode;
					String placementID = eElement.getElementsByTagName("placementID").item(0).getTextContent();
					
					String placementName = placementsNames.get(placementID);
					int impressions = Integer.parseInt(eElement.getElementsByTagName("impressions").item(0).getTextContent());
					int reach = Integer.parseInt(eElement.getElementsByTagName("reach").item(0).getTextContent());
					float frequency = Float.parseFloat(eElement.getElementsByTagName("frequency").item(0).getTextContent());
					int clicks = Integer.parseInt(eElement.getElementsByTagName("clicks").item(0).getTextContent());
					int userClicks = Integer.parseInt(eElement.getElementsByTagName("userClicks").item(0).getTextContent());
					float clickThroughRate = Float.parseFloat(eElement.getElementsByTagName("CTR").item(0).getTextContent());
					float uniqueCTR = Float.parseFloat(eElement.getElementsByTagName("UCTR").item(0).getTextContent());
					
					CampaignRow currentRow = new CampaignRow(placementName, impressions, frequency, clicks, userClicks, new Percentage(clickThroughRate), new Percentage(uniqueCTR));
					currentRow.setReach(reach);
					rows.add(currentRow);
				}
			}
		} catch (ParserConfigurationException | SAXException | IOException e) {
			e.printStackTrace();
		}
		
		c = new Campaign(getHeaderByID(campaignID), rows);
		
		return c;
	}
	
	public CampaignHeader getHeaderByID(String headerID) {
		for (int i = 0; i < allHeaders.size(); i++) {
			if (allHeaders.get(i).getCampaignID().equals(headerID)) {
				return allHeaders.get(i);
			}
		}
		
		return null;
	}
	
	
}
