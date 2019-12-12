package XML;

import Model.ColdSteelCollection;
import Model.ColdSteel;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.events.EndElement;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;
import java.io.FileReader;
/**
 * StAX parser class
 * @author Elizaveta Rudko
 * @version 1.0.0
 */
public class ColdSteelCollectionStAXParser implements ColdSteelCollectionParser{

    /**
     * Parse XML file to collective using StAX parser
     * @param fileName name of the file that contains collective stored in XML format
     * @return parsed collective object
     * @throws Exception if some error occurred while parsing XML file
     * */
    @Override
    public ColdSteelCollection parse(String fileName) throws Exception
    {
        XMLInputFactory factory = XMLInputFactory.newInstance();
        XMLEventReader eventReader = factory.createXMLEventReader(new FileReader(fileName));

        ColdSteelCollection cl = new ColdSteelCollection();
        String currentElement = new String();
        boolean isTitleSet = false;
        ColdSteel weapon = null;
        ColdSteelCollection collection = new ColdSteelCollection();
        boolean parsed;
        parsed = true;

        while (eventReader.hasNext())
        {
            XMLEvent event = eventReader.nextEvent();
            String qName;

            switch (event.getEventType())
            {
                case XMLStreamConstants.START_ELEMENT:
                    StartElement startElement = event.asStartElement();
                    qName = startElement.getName().getLocalPart();

                    if (collection.getName() == null && !isTitleSet && qName.equals(ColdSteelCollection.Fields.NAME.getName())){
                        isTitleSet = true;
                    }
                    else
                        if (qName.equals(ColdSteelCollection.Fields.WEAPONS.getName())) {
                            weapon = new ColdSteel();
                        }
                        else
                            if (ColdSteel.Fields.TYPE.getName().equals(qName) ||
                                    ColdSteel.Fields.TITLE.getName().equals(qName) ||
                                    ColdSteel.Fields.PRICE.getName().equals(qName))
                            {
                                parsed = false;
                            }
                    currentElement = qName;
                    break;

                case XMLStreamConstants.CHARACTERS:
                    String textValue = event.asCharacters().getData();
                    if (collection.getName() == null && isTitleSet)
                    {
                        collection.setName(textValue);
                    }
                    else
                        if (!parsed)
                        {
                            if (currentElement.equals(ColdSteel.Fields.TYPE.getName())) {
                                weapon.setType(textValue);
                            }
                            else
                                if (currentElement.equals(ColdSteel.Fields.TITLE.getName())) {
                                    weapon.setTitle(textValue);
                                }
                                else
                                    if (currentElement.equals(ColdSteel.Fields.PRICE.getName())) {
                                        weapon.setPrice(Double.parseDouble(textValue.toUpperCase()));
                                    }
                            parsed = true;
                        }
                    break;

                case XMLStreamConstants.END_ELEMENT:
                    EndElement endElement = event.asEndElement();
                    qName = endElement.getName().getLocalPart();
                    if (qName.equals(ColdSteelCollection.Fields.WEAPONS.getName())){
                        collection.addWeapon(weapon);
                    }

                    break;
            }
        }

        return collection;
    }
}

