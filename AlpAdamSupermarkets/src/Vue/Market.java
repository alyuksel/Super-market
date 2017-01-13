package Vue;


import java.awt.Component;
import java.awt.FlowLayout;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import Factory.NoSuchProductException;
import Factory.ProductFactory;
import Produits.Produit;
import Supermarket.Entreprise;


public class Market extends JPanel implements Observer {
	private static final long serialVersionUID = 1L;
	private Entreprise entreprise;
	private JLabel actual = new JLabel();
	JTextField text = new JTextField(10);
	private DefaultTableModel model = new DefaultTableModel();
	
	public Market(Entreprise e) {
		entreprise = e;
		entreprise.addObserver(this);
		this.setSize(400,200);
		this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		this.actual.setText("selectionner un magasin dans Select Market");
		this.add(this.actual);
		this.add(new JScrollPane(new JTable(model)));
		this.add(addSort());
		model.addColumn("Type"); model.addColumn("Nom"); model.addColumn("Quantité"); model.addColumn("Prix/u");
		this.setVisible(true);
	}
	
	
	private Component addSort() {
		JPanel formPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JComboBox<String> combo = new JComboBox<String>(new String[]{"All","Nom","Inferieur à","Superieur à"});
		JButton ok = new JButton("Valider");
		ok.addActionListener(l->{
			clearTable();
			toDisplay(combo.getSelectedItem().toString());
		});
		formPanel.add(new JLabel("Afficher : ")); formPanel.add(combo);
		formPanel.add(text); formPanel.add(ok);
		return formPanel;
	}

	private void toDisplay(String val) {
		Map<String,Integer> allProduct = entreprise.getCurrentMarket().toStringInt();
		ProductFactory fact = new ProductFactory();
		try{
			switch (val) { 
			case "Nom": {
					Produit p = fact.createProduct(text.getText());
				model.addRow(new Object[]{p.getProductType(),p.getLabel(),allProduct.get(text.getText()),p.getPrice()});
			}break;
			
			case "All":{
				clearTable();
				for(String s : allProduct.keySet()){
					Produit p = fact.createProduct(s);
					model.addRow(new Object[]{p.getProductType(),p.getLabel(),allProduct.get(s),p.getPrice()});
				}
			}break;
			
			default:
				break;
			}
		} catch (NoSuchProductException e) {e.printStackTrace();} 
	}


	public String getName(){
		return "market";
	}
	
	private void clearTable(){
		model.setRowCount(0);
	}

	

	@Override
	public void update(Observable o, Object arg) {
		this.actual.setText(entreprise.getCurrentMarket().toString());
		if(o.getClass().getSimpleName().equals("Entreprise")){
			entreprise.getCurrentMarket().addObserver(this);
		}
			clearTable();
			toDisplay("All");
	}
	
	}

