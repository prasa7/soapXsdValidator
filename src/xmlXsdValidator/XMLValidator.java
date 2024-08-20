package xmlXsdValidator;

import java.io.File;
import javax.xml.XMLConstants;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import org.xml.sax.SAXException;
import java.io.IOException;

public class XMLValidator {

    public static void main(String[] args) {
        
        String xmlFile = "src/resources/xmlfile.xml";
        String xsdFile = "src/resources/xsdfile.xsd";

        if (validateXMLSchema(xsdFile, xmlFile)) {
            System.out.println("XML is valid against the XSD.");
        } else {
            System.out.println("XML is NOT valid against the XSD.");
        }
    }

    public static boolean validateXMLSchema(String xsdPath, String xmlPath) {
        try {
            // Create a SchemaFactory for the W3C XML Schema
            SchemaFactory factory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);

            // Load the XSD file as a schema
            Schema schema = factory.newSchema(new File(xsdPath));

            // Create a Validator from the schema
            Validator validator = schema.newValidator();

            // Validate the XML file against the schema
            validator.validate(new StreamSource(new File(xmlPath)));
        } catch (IOException e) {
            System.out.println("IOException: " + e.getMessage());
            return false;
        } catch (SAXException e) {
            System.out.println("SAXException: " + e.getMessage());
            return false;
        }

        return true;
    }
}