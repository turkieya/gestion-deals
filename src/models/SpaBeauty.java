package models;

public class SpaBeauty implements Deal {
	private int id;
	private String fournisseur;
	private String nom;
	private float prix_initial;
	private float prix_deal;
	private String date_deb;
	private String date_fin;
	private String description;
	private String image;
	
	public SpaBeauty(int id,String fournisseur, String nom, float prix_initial,float prix_deal, String date_deb, String date_fin,String description,String image) {
		this.id=id;
		this.fournisseur=fournisseur;
		this.nom=nom; 
		this.prix_initial=prix_initial;
		this.prix_deal=prix_deal;
		this.date_deb=date_deb;
		this.date_fin=date_fin;
		this.description=description;
		this.image=image;
	}
	public SpaBeauty(String nom,String fournisseur,float prix_deal,String date_fin,String description)
	{
		this.fournisseur=fournisseur;
		this.nom=nom; 
		this.prix_deal=prix_deal;
		this.date_fin=date_fin;
		this.description=description;
	}

	public String getFournisseur() {
		return fournisseur;
	}

	public void setFournisseur(String fournisseur) {
		this.fournisseur = fournisseur;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public float getPrix_initial() {
		return prix_initial;
	}

	public void setPrix_initial(float prix_initial) {
		this.prix_initial = prix_initial;
	}

	public float getPrix_deal() {
		return prix_deal;
	}

	public void setPrix_deal(float prix_deal) {
		this.prix_deal = prix_deal;
	}

	public String getDate_deb() {
		return date_deb;
	}

	public void setDate_deb(String date_deb) {
		this.date_deb = date_deb;
	}

	public String getDate_fin() {
		return date_fin;
	}

	public void setDate_fin(String date_fin) {
		this.date_fin = date_fin;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	
	

}
