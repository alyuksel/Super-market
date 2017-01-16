package Strategy;

import java.util.ArrayList;
import java.util.Map;

import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import Produits.Produit;
import Tools.GenericClass;

public class Superieur extends Choice {

	public Superieur() {
		super("Prix sup. à");
	}

	@Override
	public void eval(DefaultTableModel model, Map<String, ArrayList<Produit>> allProduct,JTextField text) {
		GenericClass.getFiltredProd(allProduct,(Produit p)-> p.getPrice()>Double.valueOf(text.getText()))
		.forEach(p->model.addRow(new Object[]{p.getProductType(),p.getLabel(),allProduct.get(p.getLabel()).size(),p.getPrice()}));

	}

}
