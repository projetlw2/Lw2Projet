package lw2.projet.controller;

import java.beans.XMLDecoder;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import javax.xml.XMLConstants;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import lw2.projet.model.Model;
import lw2.projet.model.Stb;

@Controller
public class StbController {

	private static final String XSD_PATH = "src/main/resources/stb.xsd";
	private Model model;

	public StbController() {
		model = new Model();
	}

	// HOME
	@RequestMapping()
	@ResponseBody
	public String accueil() {
		String message = "<html><body><h1>Projet de Langages Web 2</h1><h3>Gestionnaire de STB</h3>"
				+ "<ul><li>/resume : Affiche sous forme r&eacute;sum&eacute;e, la liste des STB enregistr&eacute;es</li>"
				+ "<li>/resume/n : Affiche le contenu complet de la STB dont l'identifiant est \"n\"</li>"
				+ "<li>/depot : D&eacute;pose une nouvelle STB apr&egrave;s validation XSD par le service REST</li></ul>"
				+ "<br /><p>Anthony Br&eacute;ant - Cl&eacute;ment Poinsot</p>"
				+ "<p>Nombre de STBs : " + model.getStbList().size() + "</p></body></html>";
		return message;
	}
	
	// RESUME
	@RequestMapping(value = "resume")
	@ResponseBody
    public ResponseEntity<Model> resume() { 
		if (model.getStbList().size() > 0) {
			Model m = new Model(model.getShortStbList());
            return new ResponseEntity<Model>(m, HttpStatus.OK);
        }
        return new ResponseEntity<Model>(HttpStatus.NOT_FOUND);
    }
	
	// RESUME/n
	@RequestMapping(value = "resume/{n}")
	@ResponseBody
	public ResponseEntity<Stb> resume(@PathVariable("n") int n) { 
		if (n < model.getStbList().size()) {
			Stb stb = model.getStbList().get(n);
			return new ResponseEntity<Stb>(stb, HttpStatus.OK);
		}
		return new ResponseEntity<Stb>(HttpStatus.NOT_FOUND);
	}

	// DEPOT
	@RequestMapping("depot")
	@ResponseBody
	public String depot() {
		if (validateXML("src/main/resources/stb1.xml")){
			return "XML valide";
		}
		return "XML non valide";
	}
	
	@RequestMapping(value="depot", method = RequestMethod.POST)
	@ResponseBody 
	public int depot(@RequestBody Stb stb) {
		if (validateXML("stb.xml")) {
			model.getStbList().add(stb);
			return (model.getStbList().size() - 1);
		}
		return -1;
	}
	
	@RequestMapping(value = "depot/{xmlPath}")
	@ResponseBody
	public ResponseEntity<Stb> depot(@PathVariable("xmlPath") String xmlPath) {
		String path = xmlPath + ".xml";
		if (validateXML(path)) {
			Stb stb = (Stb) decodeFromFile(path);
			model.getStbList().add(stb);
			return new ResponseEntity<Stb>(stb, HttpStatus.OK);
		}
		return new ResponseEntity<Stb>(HttpStatus.NOT_FOUND);
	}
	
	// VALIDATION DU XML PAR XSD
	public static boolean validateXML(String xmlPath) {
		try {
			SchemaFactory factory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
            Schema schema = factory.newSchema(new File(XSD_PATH));
            Validator validator = schema.newValidator();
            validator.validate(new StreamSource(new File (xmlPath)));
		} catch (Exception e) {
			 System.out.println("Exception : " + e.getMessage());
			 return false;
		}
		return true;
	}
	
	// Fonction : XML -> Object
	public static Object decodeFromFile(String fileName) {
	    Object object = null;
	    XMLDecoder decoder = null;
		try {
			// Ouverture du décodeur
			decoder = new XMLDecoder(new FileInputStream(fileName));
			
			// Désérialisation de l'objet
			object = decoder.readObject();
		} catch (Exception e) {
			System.out.println("Exception : " + e.getMessage());
		} finally {
			// Fermeture du décodeur
		    decoder.close();
		}
	    return object;
	}
	
	// Fonction : XML -> Object
	@SuppressWarnings("null")
	public Object convertFromXMLToObject(String xmlfile) throws IOException, JAXBException {
		Unmarshaller unmarshaller = null;
		FileInputStream is = null;
		try {
			is = new FileInputStream(xmlfile);
			return unmarshaller.unmarshal(new StreamSource(is));
		} finally {
			if (is != null) {
				is.close();
			}
		}
	}
}