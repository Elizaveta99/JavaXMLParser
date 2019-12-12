package XML;

import Model.ColdSteelCollection;
import Model.ColdSteel;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.io.SyncFailedException;
/**
 * SAX parser class
 * @author Elizaveta Rudko
 * @version 1.0.0
 */
public class ColdSteelCollectionSAXParser implements ColdSteelCollectionParser{

    /**
     * Parse XML file to collective using SAX parser
     * @param fileName name of the file that contains collective stored in XML format
     * @return parsed collective object
     * @throws Exception if some error occurred while parsing XML file
     * */
    @Override
    public ColdSteelCollection parse(String fileName) throws Exception
    {
        File inputFile = new File(fileName);
        SAXParserFactory factory = SAXParserFactory.newInstance();
        javax.xml.parsers.SAXParser saxParser = factory.newSAXParser();
        ColdSteelCollection cl = new ColdSteelCollection();
        Handler handler = new Handler(cl);
        saxParser.parse(inputFile, handler);
        return cl;
    }

    /**
     * Tags handler
     */
    private class Handler extends DefaultHandler {

        private String currentElement;
        boolean isTitleSet = false;
        private ColdSteel weapon;
        private ColdSteelCollection collection;
        private boolean parsed;

        /**
         * Constructor specifying list to which will be added new cars
         * @param collection list to which will be added new person
         */
        public Handler(ColdSteelCollection collection) {
            this.currentElement = null;
            this.weapon = null;
            this.parsed = true;
            this.collection = collection;
        }

        @Override
        public void startElement(String uri, String localName, String qName, Attributes attributes)
                throws SAXException
        {
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
        }

        @Override
        public void endElement(String uri, String localName, String qName) throws SAXException {
            if (qName.equals(ColdSteelCollection.Fields.WEAPONS.getName())){
                collection.addWeapon(weapon);
            }
        }

        @Override
        public void characters(char ch[], int start, int length) throws SAXException {
            String textValue = new String(ch, start, length);
            if (collection.getName() == null && isTitleSet)
            {
                collection.setName(textValue);
            }
            else
            if (!parsed)
            {
                if (currentElement.equals(ColdSteel.Fields.TYPE.getName())) {
                    weapon.setType(textValue);
                } else if (currentElement.equals(ColdSteel.Fields.TITLE.getName())) {
                    weapon.setTitle(textValue);
                } else if (currentElement.equals(ColdSteel.Fields.PRICE.getName())){
                    weapon.setPrice(Double.parseDouble(textValue.toUpperCase()));
                }

                parsed = true;
            }
        }
    }
}

