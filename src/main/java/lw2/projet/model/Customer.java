package lw2.projet.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlElement;

@XmlRootElement (name = "customer")
@XmlAccessorType(XmlAccessType.NONE)
public class Customer {
	
	@XmlElement
	private String organization;
	
	@XmlElement
	private String contact;
	
	@XmlElement
	private int postalCode;
	
	public Customer(String organization, String contact, int postalCode) {
		this.organization = organization;
		this.contact = contact;
		this.postalCode = postalCode;
	}
	
	public Customer() {
		
	}
	
	public String getOrganization() {
		return organization;
	}
	
	public String getContact() {
		return contact;
	}
	
	public int getPostalCode() {
		return postalCode;
	}
}