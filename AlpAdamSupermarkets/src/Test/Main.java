package Test;

import java.util.ArrayList;

import Produits.Produit;
import Rayons.AlimentaryRay;
import Rayons.BeautyRay;
import Rayons.Rayon;
import Supermarket.Arrivals;
import Supermarket.Entreprise;
import Supermarket.Generalist;
import Vue.Fenetre;
import Vue.Vue;


public class Main {
	public static void main(String[] args) {
		
		Entreprise adamAlpi = Entreprise.getMySuperMarkets();
		Generalist generalist = new Generalist("U");
		Rayon ray1 = new AlimentaryRay();
		Rayon ray2 = new BeautyRay();
		generalist.setRayon(ray1);
		generalist.setRayon(ray2);
		Arrivals arrivee = new Arrivals();
		ArrayList<Produit> stock = arrivee.getArrivals();
		generalist.addProduct(stock);
		adamAlpi.addSuperMarket(generalist);
		Vue vue = new Vue(adamAlpi);
		Fenetre frame = new Fenetre(vue);
		
		
	}
}
