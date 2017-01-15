package Strategy;

import java.util.Map;

import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import Factory.NoSuchProductException;
import Factory.ProductFactory;
import Produits.Produit;

public class Name extends Choice {

	public Name() {
		super("Nom");
	}

	@Override
	public void eval(DefaultTableModel model, Map<String, Integer> allProduct,JTextField text) {
		ProductFactory fact = new ProductFactory();
		Produit p;
		try {
			p = fact.createProduct(text.getText());
			model.addRow(new Object[]{p.getProductType(),p.getLabel(),allProduct.get(text.getText()),p.getPrice()});
		} catch (NoSuchProductException e) {}
		
	}

}
