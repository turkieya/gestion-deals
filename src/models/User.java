package models;

public class User {
	
	private  String email ;
	private String mdp;
	private  int tel ;
	private  String adresse ;
	
	public User (String email,String mdp , int tel , String adresse)
	{
		this.email=email;
		this.mdp=mdp;
		this.tel=tel;
		this.adresse=adresse;
	}

	public String getEmail() { 
		return email;
	}
	public String getMdp() {
		return mdp;
	}
	public int getTel() {
		return tel;
	}
	public String getAdresse() {
		return adresse;
	}

	public void setEmail(String email) {
		this.email=email;
	}
	public void setMdp(String mdp) {
		this.mdp=mdp;
	}
	public void setTel(int tel) {
		this.tel=tel;
	}
	public void setAdresse(String adresse) {
		this.adresse=adresse;
	}
}
