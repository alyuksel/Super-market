package Test;

import java.util.ArrayList;

import javax.swing.JPanel;

import Produits.Produit;
import Rayons.AlimentaryRay;
import Rayons.BeautyRay;
import Rayons.Rayon;
import Supermarket.Alimentary;
import Supermarket.Arrivals;
import Supermarket.Entreprise;
import Supermarket.Generalist;
import Vue.Fenetre;
import Vue.Market;
import Vue.MarketsVue;


public class Main {
	public static void main(String[] args) {
		
		Entreprise adamAlpi = Entreprise.getMySuperMarkets();
		Generalist generalist = new Generalist("superU");
		Alimentary epicerie = new Alimentary("Rachid epicerie");
		Generalist auchan = new Generalist("auchan");
		
		Rayon ray1 = new AlimentaryRay();
		Rayon ray2 = new BeautyRay();
		generalist.setRayon(ray1);
		generalist.setRayon(ray2);
		Arrivals arrivee = new Arrivals();
		ArrayList<Produit> stock = arrivee.getArrivals();
		generalist.addProduct(stock);
		
		adamAlpi.addSuperMarket(generalist);
		adamAlpi.addSuperMarket(epicerie);
		adamAlpi.addSuperMarket(auchan);
		
		Fenetre frame = new Fenetre();
		frame.addPane(new MarketsVue(adamAlpi));
		frame.addPane(new Market(adamAlpi));
		
	}
}
