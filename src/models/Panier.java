package models;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import Exceptions.ClientException;

public class Panier {

	private int id;
	private float total;
	private List<String> deals= new ArrayList <String>();
	
	public Panier(int id) {
		this.setId(id);
		this.setTotal(0);
		deals = new ArrayList <String>();
	}

	public float getTotal() {
		return total;
	}
	public void setTotal(float total) {
		this.total = total;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public List <String> getDeals(){
		return deals;}
	
	public void ajouterDeal(String deal) throws ClientException {
		if (!deals.add(deal)) {
				throw new ClientException("Le Deal est réservé déjà !");
		}
	}
	public void afficher(){
		Iterator <String> lt = deals.iterator();
		System.out.println("liste des deals:");
		while(lt.hasNext()){
			System.out.print(lt.next()+" ");}
		
		}
}
