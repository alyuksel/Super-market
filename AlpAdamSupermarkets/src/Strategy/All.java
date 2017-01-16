package Strategy;

import java.util.ArrayList;
import java.util.Map;

import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import Factory.NoSuchProductException;
import Factory.ProductFactory;
import Produits.Produit;

public class All extends Choice {

	public All() {
		super("All");
	}

	public void evaal(DefaultTableModel model, Map<String, ArrayList<Produit>> allProduct,JTextField text) {
		ProductFactory fact = new ProductFactory();
		for(String s : allProduct.keySet()){
			Produit p = null;
			try {
				if(p.getClass().getSimpleName().equals("Promotion"))
				p = fact.createProduct(s);
				model.addRow(new Object[]{p.getProductType(),p.getLabel(),allProduct.get(s),p.getPrice()});
			} catch (NoSuchProductException e) {}
			
		}
	}

	@Override
	public void eval(DefaultTableModel model,Map<String, ArrayList<Produit>> allProduct, JTextField text) {
		allProduct.keySet().forEach(s->{
			if(!allProduct.get(s).isEmpty()){
				Produit p = allProduct.get(s).get(0);
				model.addRow(new Object[]{p.getProductType(),p.getLabel(),allProduct.get(s).size(),p.getPrice()});
			}
		});
		
	}
}
