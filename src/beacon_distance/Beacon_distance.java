package beacon_distance;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import java.io.File;
import java.io.IOException;
import org.xml.sax.SAXException;

public class Beacon_distance {
    
    public static double calculateDistance(int txPower, double rssi) {
  if (rssi == 0) {
    return -1.0; // if we cannot determine distance, return -1
  }

  double ratio = rssi*1.0/txPower;
  if (ratio < 1.0) {
    return Math.pow(ratio,10);
  }
  else {
    double distance =  (0.89976)*Math.pow(ratio,7.7095) + 0.111;   
    return distance;
  }
  
} 
    
    public static void main(String[] args) throws SAXException, IOException {
        double rssi;
        int txPower;
        
        try{
        File fXmlFile = new File("data.xml");
        
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        Document doc = dBuilder.parse(fXmlFile); 
        doc.getDocumentElement().normalize(); 
                System.out.println("Root element:"+ doc.getDocumentElement().getNodeName());
        NodeList nList = doc.getElementsByTagName("distance");
        System.out.println("----------------------------");
        for(int i=0;i<nList.getLength();i++)
        {
            Node nNode = nList.item(i);
            System.out.println("\nCurrent Element :" + nNode.getNodeName());
        
        
        if (nNode.getNodeType() == Node.ELEMENT_NODE) {
        Element eElement = (Element) nNode; 
        System.out.println("Distance id : " + eElement.getAttribute("id"));
        System.out.println("Rssi Value" + i +":" + eElement.getElementsByTagName("rssi").item(0).getTextContent());
        System.out.println("txPower Value" + i +":" + eElement.getElementsByTagName("txPower").item(0).getTextContent());
        System.out.println("----------------------------");
        
       rssi = Double.parseDouble(eElement.getElementsByTagName("rssi").item(0).getTextContent());
       txPower=Integer.parseInt(eElement.getElementsByTagName("txPower").item(0).getTextContent());
       System.out.println("Distance=" + Beacon_distance.calculateDistance(txPower, rssi)+"\n\n");
        }
        }
        }
        catch (Exception e) {
        e.printStackTrace();
        }
        }
        }
        


