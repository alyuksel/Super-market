package Test;

import Produits.Nutella;
import Produits.Promotion;
import Supermarket.Entreprise;
import Vue.CommandeView;
import Vue.EmployersView;
import Vue.Fenetre;
import Vue.Market;
import Vue.MarketsVue;
import Vue.SalesView;


public class Main {
	
	public static void main(String[] args) {
		Entreprise adamAlpi = Entreprise.getMySuperMarkets();
		Fenetre frame = new Fenetre();
		frame.addPane(new MarketsVue(adamAlpi));
		frame.addPane(new Market(adamAlpi));
		frame.addPane(new CommandeView(adamAlpi));
		frame.addPane(new SalesView(adamAlpi));
		frame.addPane(new EmployersView(adamAlpi));
	}
	
}
