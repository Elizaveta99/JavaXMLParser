package XML;

import javax.xml.XMLConstants;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.File;

/**
 * XML validator class
 * @author Elizaveta Rudko
 * @version 1.0.0
 */
public class ValidatorXML {
    /**
     * Check XML file using xsd file
     * @param xmlFileName file name of the XML file
     * @param xsdFileName file name of the xsd file
     * @return true if XML file has been validated successfully
     */
    public static boolean validate(String xmlFileName, String xsdFileName) {
        try {
            SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
            StreamSource xsdSources = new StreamSource(new File(xsdFileName));
            Schema schema = schemaFactory.newSchema(xsdSources);
            Validator validator = schema.newValidator();
            validator.validate(new StreamSource(new File(xmlFileName)));
        } catch (Exception e) {
            System.out.println("Validation failed because of: "+ e.getMessage());
            return false;
        }
        return true;
    }
}
