package models;

public class Client extends User {
	private  int CIN  ;
	private  String nom ;
	private  String prenom ;
/*	private  int age ;
	private  int tel ;
	private  String adresse ;*/
	
	public Client (int CIN,String nom , String prenom , String email,String mdp , int tel , String adresse)
	{
		super(email,mdp,tel,adresse);
		this.CIN=CIN;
		this.nom=nom;
		this.prenom=prenom;
		/*this.age=age;
		this.tel=tel;
		this.adresse=adresse;*/
	}
	public int getCIN() {
		return CIN;
	}
	
	public String getNom() {
		return nom;
	}
	public String getPrenom() {
		return prenom;
	}
	/*public int getAge() {
		return age;
	}
	public int getTel() {
		return tel;
	}
	public String getAdresse() {
		return adresse;
	}*/
	public void setCin(int CIN) {
		this.CIN=CIN;
	}
	public void setNom(String nom) {
		this.nom=nom;
	}
	public void setPrenom(String prenom) {
		this.prenom=prenom;
	}
	/*public void setAge(int age) {
		this.age=age;
	}
	public void setTel(int tel) {
		this.tel=tel;
	}
	public void setAdresse(String adresse) {
		this.adresse=adresse;
	}*/
}
