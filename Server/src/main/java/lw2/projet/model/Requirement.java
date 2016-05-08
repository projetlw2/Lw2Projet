package lw2.projet.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlAttribute;

@XmlRootElement(name = "requirement")
@XmlAccessorType(XmlAccessType.NONE)
public class Requirement {
	@XmlElement
	private int id;
	
	@XmlElement
	private String description;
	
	@XmlAttribute
	private int priority;

	public Requirement(int id, String description, int priority) {
		this.id = id;
		this.description = description;
		this.priority = priority;
	}
	
	public Requirement() {
		
	}
	
	public int getId() {
		return id;
	}
	
	public String getDescription() {
		return description;
	}
	
	public int getPriority() {
		return priority;
	}
}
