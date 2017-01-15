package Strategy;

import java.util.Map;

import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import Produits.Produit;
import Tools.GenericClass;

public class Inferieur extends Choice {

	public Inferieur() {
		super("Prix inf. à");
	}

	@Override
	public void eval(DefaultTableModel model, Map<String, Integer> allProduct, JTextField text) {
		GenericClass.getFiltredProd(allProduct.keySet().stream(),(Produit p)-> p.getPrice()<Double.valueOf(text.getText()))
		.forEach(p->model.addRow(new Object[]{p.getProductType(),p.getLabel(),allProduct.get(p.getLabel()),p.getPrice()}));
	}

	

}
