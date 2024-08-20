package xmlXsdValidator;

import java.io.File;
import java.io.IOException;
import java.io.StringReader;
import java.nio.file.Files;
import java.nio.file.Paths;

import javax.xml.XMLConstants;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;

import org.xml.sax.SAXException;

public class SOAPValidator {

    public static void main(String[] args) {
        
        String soapRequest = "src/resources/soap-request.xml";
        String xsdFile = "src/resources/xsdfile.xsd";
        
        try {
            // Read the XML content into a String
            String xmlContent = new String(Files.readAllBytes(Paths.get(soapRequest)));

            // Perform validation
            if (validateXMLSchema(xsdFile, xmlContent)) {
                System.out.println("SOAP XML is valid against the XSD.");
            } else {
                System.out.println("SOAP XML is NOT valid against the XSD.");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static boolean validateXMLSchema(String xsdPath, String xmlContent) {
        try {
            SchemaFactory factory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
            Schema schema = factory.newSchema(new File(xsdPath));
            Validator validator = schema.newValidator();

            // Validate the XML content from the String
            validator.validate(new StreamSource(new StringReader(xmlContent)));
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