package models;

public class Vente {

	private int num;
	private int cin_clt;
	private String fournisseur;
	private String date;
	private String deal;
	private float prixdeal;
	public Vente(int num, int cin_clt, String fournisseur, String date, String deal, float prixdeal) {
		this.num = num;
		this.cin_clt = cin_clt;
		this.fournisseur = fournisseur;
		this.date = date;
		this.deal = deal; 
		this.prixdeal = prixdeal;
	}
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public int getCin_clt() {
		return cin_clt;
	}
	public void setCin_clt(int cin_clt) {
		this.cin_clt = cin_clt;
	}
	public String getFournisseur() {
		return fournisseur;
	}
	public void setFournisseur(String fournisseur) {
		this.fournisseur = fournisseur;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getDeal() {
		return deal;
	}
	public void setDeal(String deal) {
		this.deal = deal;
	}
	public float getPrixdeal() {
		return prixdeal;
	}
	public void setPrixdeal(float prixdeal) {
		this.prixdeal = prixdeal;
	}
	
	
}
