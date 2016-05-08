package lw2.projet.model;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlAttribute;

@XmlRootElement(name="functionality")
@XmlAccessorType(XmlAccessType.NONE)
public class Functionality {
	@XmlElement
	private String description;
	
	@XmlAttribute
	private int priority;
	
	@XmlElement(name="requirements")
	private List<Requirement> requirements;

	public Functionality(String description, int priority, List<Requirement> requirements) {
		this.description = description;
		this.priority = priority;
		this.requirements = new ArrayList<Requirement>(requirements);
	}

	public Functionality() {
		
	}
	
	public String getDescription() {
		return description;
	}
	
	public int getPriority() {
		return priority;
	}
	
	public List<Requirement> getRequirements() {
		return requirements;
	}
}
