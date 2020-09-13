package helper;

import java.util.*;
import dto.*;
import javax.xml.parsers.*;
import javax.xml.xpath.*;

import org.w3c.dom.*;

import dao.ItemDAO;


public class RSSHelper {

	public static List<Item> read(){
		String url="https://europa.eu/newsroom/calendar.xml_en?field_nr_events_by_topic_tid=151";
		List<Item> items= new ArrayList<Item>();
		try {
			DocumentBuilderFactory factory=DocumentBuilderFactory.newInstance();
			DocumentBuilder builder=factory.newDocumentBuilder();
			Document document= builder.parse(url);
			XPath path= XPathFactory.newInstance().newXPath();
			NodeList nodeList=(NodeList) path.compile("//item").evaluate(document,XPathConstants.NODESET);
			for(int i=0;i<nodeList.getLength();i++) {
				Item item= new Item();
				item.setDescription(path.compile("./description").evaluate(nodeList.item(i), XPathConstants.STRING).toString());
				item.setLink(path.compile("./link").evaluate(nodeList.item(i), XPathConstants.STRING).toString());
				item.setPubDate(path.compile("./pubDate").evaluate(nodeList.item(i), XPathConstants.STRING).toString());
				item.setTitle(path.compile("./title").evaluate(nodeList.item(i), XPathConstants.STRING).toString());
				items.add(item);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		List<Item> dbItems=ItemDAO.select();
		for(int i=0;i<dbItems.size();i++) {
			Item item= dbItems.get(i);
			
		}
		
		items.addAll(dbItems);
		Collections.sort(items);
		
		return items;
	}
}
