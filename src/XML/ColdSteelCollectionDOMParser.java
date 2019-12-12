package XML;

import Model.ColdSteelCollection;
import Model.ColdSteel;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;

/**
 * DOM parser class
 * @author Elizaveta Rudko
 * @version 1.0.0
 */
public class ColdSteelCollectionDOMParser implements ColdSteelCollectionParser{

    /**
     * Parse XML file to collective using DOM parser
     * @param fileName name of the file that contains collective stored in XML format
     * @return parsed collective object
     * @throws Exception if some error occurred while parsing XML file
     * */
    @Override
    public ColdSteelCollection parse(String fileName) throws Exception
    {
        File inputFile = new File(fileName);
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        Document doc = dBuilder.parse(inputFile);
        doc.getDocumentElement().normalize();
        NodeList nodes = doc.getElementsByTagName(ColdSteelCollection.Fields.WEAPONS.getName());
        NodeList name = doc.getElementsByTagName(ColdSteelCollection.Fields.NAME.getName());
        ColdSteelCollection cl = new ColdSteelCollection(name.item(0).getTextContent());

        for (int i = 0; i < nodes.getLength(); i++)
        {
            Node node = nodes.item(i);
            if (node.getNodeType() == Node.ELEMENT_NODE)
            {
                Element element = (Element) node;
                String type = element.getElementsByTagName(ColdSteel.Fields.TYPE.getName()).item(0).getTextContent();
                String title = element.getElementsByTagName(ColdSteel.Fields.TITLE.getName()).item(0).getTextContent();
                Double price = Double.parseDouble(element.getElementsByTagName(ColdSteel.Fields.PRICE.getName()).item(0).getTextContent().toUpperCase());
                cl.addWeapon(new ColdSteel(type, title, price) {
                });
            }
        }
        return cl;
    }
}

