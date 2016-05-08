package lw2.projet.model;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlElement;

@XmlRootElement (name = "stb")
@XmlAccessorType(XmlAccessType.NONE)
public class Stb {
	// STATIC COUNTER
	private static int ID = 0;
	
	// ATTRIBUTES
	@XmlElement
	private int id;
	
	@XmlElement
	private String title;
	
	@XmlElement
	private String version;
	
	@XmlElement
	private String date;
	
	@XmlElement
	private String description;
	
	@XmlElement
	private Customer customer;
	
	@XmlElement (name = "member")
	private List<Team> team;
	
	@XmlElement (name = "functionality")
	private List<Functionality> functionalities;
	
	@XmlElement (name = "comment")
	private List<String> comments;
	
	
	// CONSTRUCTOR
	public Stb(int id, String title, String version, String date, String description, Customer customer,
			List<Team> team, List<Functionality> functionalities, List<String> comments) {
		
		if (id == -1) {
			this.id = ID;
			ID++;
		} else {
			this.id = id;
		}
		
		this.title = title;
		this.version = version;
		this.date = date;
		this.description = description;
		this.customer = customer;
		if (team != null) {
			this.team = new ArrayList<Team>(team);
		}
		if (functionalities != null) {
			this.functionalities = new ArrayList<Functionality>(functionalities);
		}
		if (comments != null) {
			this.comments = new ArrayList<String>(comments);
		}
	}
	
	public Stb() {
		this.id = ID;
		ID++;
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
        this.id = id;
    }
	
	public String getTitle() {
		return title;
	}
	
	public String getVersion() {
		return version;
	}
	
	public String getDate() {
		return date;
	}
	
	public String getDescription() {
		return description;
	}
	
	public Customer getCustomer() {
		return customer;
	}
	
	public List<Team> getTeam() {
		return team;
	}
	
	public List<Functionality> getFunctionalities() {
		return functionalities;
	}
	
	public List<String> getComments() {
		return comments;
	}
}
