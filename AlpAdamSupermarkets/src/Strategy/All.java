package Strategy;

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

	@Override
	public void eval(DefaultTableModel model, Map<String, Integer> allProduct,JTextField text) {
		ProductFactory fact = new ProductFactory();
		for(String s : allProduct.keySet()){
			Produit p = null;
			try {
				p = fact.createProduct(s);
				model.addRow(new Object[]{p.getProductType(),p.getLabel(),allProduct.get(s),p.getPrice()});
			} catch (NoSuchProductException e) {}
			
		}
	}
	
}
