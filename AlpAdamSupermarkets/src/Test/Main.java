package Test;

import java.util.ArrayList;
import java.util.HashSet;

import Produits.Produit;
import Rayons.AlimentaryRay;
import Rayons.BeautyRay;
import Rayons.Rayon;
import Supermarket.Alimentary;
import Supermarket.Arrivals;
import Supermarket.Generalist;
import Supermarket.SuperMarket;
import Vue.Vue;

public class Main {
	public static void main(String[] args) {
		Arrivals arrivals = new Arrivals();
		ArrayList<Produit> stocks = arrivals.getArrivals();
		Generalist generalist = new Generalist("U");
		Alimentary alimentaire = new Alimentary("Bio");
		AlimentaryRay ray1 = new AlimentaryRay();
		BeautyRay ray2 = new BeautyRay();
		ArrayList<Rayon> rayons = new ArrayList<>();
		HashSet<SuperMarket> smks = new HashSet<>();
		smks.add(generalist);
		smks.add(alimentaire);
		Vue v = new Vue(smks);
		rayons.add(ray1);
		rayons.add(ray2);
		generalist.setRayons(rayons);
		generalist.addProduct(stocks);
		alimentaire.setRayons(rayons);
		alimentaire.addProduct(stocks);
		System.out.println(smks.size());
		System.out.println(alimentaire.getNumberOfRayon());
		
		
	}
}
