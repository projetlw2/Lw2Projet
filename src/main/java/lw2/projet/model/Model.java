package lw2.projet.model;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlElement;

@XmlRootElement(name="stbList")
public class Model {
	
	@XmlElement(name="stb")
	private List<Stb> stbList;
	
	public Model() {
		stbList = new ArrayList<Stb>();
		createExemple();
	}
	
	public Model(List<Stb> stbList) {
        this.stbList = new ArrayList<Stb>(stbList);
    }
	
	public List<Stb> getStbList() {
		return stbList;
	}
	
	public List<Stb> getShortStbList() {
        List<Stb> list = new ArrayList<Stb>();
        for (Stb stb : stbList) {
            Stb tmp = new Stb(stb.getId(), stb.getTitle(), stb.getVersion(), stb.getDate(),
                    stb.getDescription(), null, null, null, null);
            list.add(tmp);
        }
        return list;
    }
	
	private void createExemple() {
		
		// Stb de test 1
		String title = "Collision-Code";
		String version = "0.1";
		String date = "2016";
		String description = "Stb de Collision";
		Customer customer = new Customer("CRIANN", "LT", 76);
		List<Team> team = new ArrayList<Team>();
		team.add(new Team("POINSOT", "Clement", true));
		team.add(new Team("BREANT", "Anthony", false));
		
		List<Requirement> requirements1 = new ArrayList<Requirement>();
		requirements1.add(new Requirement(0, "3 methodes", 1));
		requirements1.add(new Requirement(1, "3 clics", 2));
		
		List<Requirement> requirements2 = new ArrayList<Requirement>();
		requirements2.add(new Requirement(0, "calcul", 1));
		
		List<Functionality> functionalities= new ArrayList<Functionality>();
		functionalities.add(new Functionality("IHM", 2, requirements1));
		functionalities.add(new Functionality("Modele", 1, requirements2));
		
		List<String> comments = new ArrayList<String>();
		comments.add("Commentaire 1");
		
		Stb stb1 = new Stb(-1, title, version, date, description, customer, team, functionalities, comments);
		stbList.add(stb1);
		
		// Stb de test 2
		title = "Titre de la 2eme Stb";
		version = "0.07";
		date = "2012";
		description = "description de la 2eme Stb";
		customer = new Customer("CRIANN", "RT", 18);
		team = new ArrayList<Team>();
		team.add(new Team("BREANT", "Anthony", true));
		team.add(new Team("POINSOT", "Clement", false));
		
		requirements1 = new ArrayList<Requirement>();
		requirements1.add(new Requirement(0, "exigence Fonctionnelle1", 1));
		requirements1.add(new Requirement(1, "exigence Fonctionnelle2", 2));
		
		requirements2 = new ArrayList<Requirement>();
		requirements2.add(new Requirement(0, "exigence Fonctionnelle1 ", 1));
		
		functionalities = new ArrayList<Functionality>();
		functionalities.add(new Functionality("Intelligence artificielle", 2, requirements1));
		functionalities.add(new Functionality("Modele", 1, requirements2));
		
		Stb stb2 = new Stb(-1, title, version, date, description, customer, team, functionalities, null);
		stbList.add(stb2);
		
		// Stb de test 3
		title = "Titre de la 3eme Stb";
		version = "0.3";
		date = "2012";
		description = "description de la 3eme Stb";
		customer = new Customer("CRIANN", "RT", 18);
		team = new ArrayList<Team>();
		team.add(new Team("BREANT", "Anthony", true));
		team.add(new Team("POINSOT", "Clement", false));

		requirements1 = new ArrayList<Requirement>();
		requirements1.add(new Requirement(0, "exigence Fonctionnelle1 ", 1));
		
		requirements2 = new ArrayList<Requirement>();
		requirements2.add(new Requirement(0, "exigence Fonctionnelle1", 1));
		requirements2.add(new Requirement(1, "exigence Fonctionnelle2", 2));
		
		functionalities = new ArrayList<Functionality>();
		functionalities.add(new Functionality("Modele", 1, requirements1));
		functionalities.add(new Functionality("Intelligence artificielle", 2, requirements2));
		
		Stb stb3 = new Stb(-1, title, version, date, description, customer, team, functionalities, null);
		stbList.add(stb3);
	}
}
