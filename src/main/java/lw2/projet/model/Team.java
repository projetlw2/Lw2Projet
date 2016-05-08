package lw2.projet.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlElement;

@XmlRootElement (name = "Team")
@XmlAccessorType(XmlAccessType.NONE)
public class Team {
	
	@XmlElement
	private String name;
	
	@XmlElement
	private String firstName;
	
	@XmlElement
	private boolean gender;
	
	public Team(String name, String firstName, boolean gender) {
		this.name = name;
		this.firstName = firstName;
		this.gender = gender;
	}
	
	public Team() {
		
	}
	
	public String getName() {
		return name;
	}
	
	public String getFirstName() {
		return firstName;
	}
	
	public boolean getGender() {
		return gender;
	}
}