package Strategy;

import java.util.ArrayList;
import java.util.Map;

import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import Produits.Produit;

public abstract class Choice {
	String s;
	public Choice(String s) {
		this. s = s;
	}
	
	public abstract void eval(DefaultTableModel model, Map<String,ArrayList<Produit>> allProduct,JTextField text);
	
	@Override
	public String toString() {
		return s;
	}
}
