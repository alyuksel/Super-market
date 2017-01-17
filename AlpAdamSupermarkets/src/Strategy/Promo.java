package Strategy;

import java.util.ArrayList;
import java.util.Map;

import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import Produits.Produit;

public class Promo extends Choice {

	public Promo() {
		super("Promotion");
	}

	@Override
	public void eval(DefaultTableModel model, Map<String, ArrayList<Produit>> allProduct, JTextField text) {
		allProduct.values().stream()
		.filter(l->!l.isEmpty())
		.filter(l->l.get(0).getClass().getSimpleName().equals("Promotion"))
		.forEach(l->{
			Produit p = l.get(0);
			model.addRow(new Object[]{p.getProductType(),p.getLabel(),allProduct.get(p.getLabel()).size(),p.getPrice()});
			});
	}

}
