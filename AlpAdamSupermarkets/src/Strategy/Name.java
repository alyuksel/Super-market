package Strategy;

import java.util.ArrayList;
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
	public void eval(DefaultTableModel model, Map<String,ArrayList<Produit>> allProduct,JTextField text) {
		Produit p = null;
		if(!allProduct.get(text.getText()).isEmpty()){
			p = allProduct.get(text.getText()).get(0);
		}
		model.addRow(new Object[]{p.getProductType(),p.getLabel(),allProduct.get(text.getText()).size(),p.getPrice()});
	}
}
