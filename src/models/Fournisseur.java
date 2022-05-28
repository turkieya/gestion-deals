package models;

public class Fournisseur extends User {
	private  int id ;
	private  String nom_commercial ;
	private  String horraire ;

	public Fournisseur (int id,String nom_commercial , String horraire , String email ,String mdp, int tel , String adresse) {
		super(email,mdp,tel,adresse);
		this.id=id;
		this.nom_commercial=nom_commercial;
		this.horraire=horraire;
	}
	public int getId() {
		return id;
	}
	public String getNom_commercial() {
		return nom_commercial;
	}
	public String getHorraire() {
		return horraire; 
	}
	public void setId(int id) {
		this.id=id;
	}
	public void setNom_commercial(String nom_commercial) {
		this.nom_commercial=nom_commercial;
	}
	public void setHorraire(String horraire) {
		this.horraire=horraire;
	}
	
}
