package Vue;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import Supermarket.Entreprise;
import Supermarket.Sales;

public class SalesView extends JPanel implements Observer {
	
	private static final long serialVersionUID = 1L;
	private Entreprise entreprise;
	private JLabel actuel = new JLabel();
	private JLabel recetteLabel= new JLabel();
	private JLabel erreur = new JLabel();
	private DefaultTableModel model = new DefaultTableModel();
	private Sales sales;
	
	public SalesView(Entreprise entreprise) {
		this.entreprise = entreprise;
		this.entreprise.addObserver(this);
		this.setLayout(new BorderLayout());
		this.setSize(400,200);
		this.actuel.setText("selectionner un magasin dans Select Market");
		this.add(this.actuel,BorderLayout.NORTH);
		this.add(addForm("ventes : "));
		model.addColumn("Produit");model.addColumn("Type");model.addColumn("Recette");model.addColumn("NbPrdVendus");
		this.add(new JPanel().add(new JScrollPane(new JTable(model))), BorderLayout.SOUTH);		
		this.setVisible(true);
	}
	
	private JPanel addForm(String label){
		JPanel formPanel = new JPanel();
		formPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
		formPanel.setMaximumSize(new Dimension(20,20));
		JTextField produit = new JTextField(20);
		JTextField quantite = new JTextField(2);
		JButton saleButton = new JButton("Sale");
		formPanel.add(new JLabel(label)); 
		formPanel.add(produit);formPanel.add(new JLabel("produit"));
		formPanel.add(quantite);formPanel.add(new JLabel("Qte"));
		formPanel.add(saleButton);
		formPanel.add(this.recetteLabel);
		this.erreur.setForeground(Color.RED);
		formPanel.add(this.erreur);
		saleButton.addActionListener(e-> {if((entreprise.getCurrentMarket().flatRays().containsKey(produit.getText()))&&(!Integer.valueOf(quantite.getText()).equals(0)))
			{sales.productSold(produit.getText().trim(),quantite.getText().trim());this.erreur.setText("");}
				else this.erreur.setText("erreur de saisie");;});
		sales = new Sales();
		return formPanel;
	}
	
	@Override
	public String getName(){
		return "salesView";
	}
	
	
	@Override
	public void update(Observable o, Object arg) {
		this.actuel.setText(entreprise.getCurrentMarket().toString());
		if(o.getClass().getSimpleName().equals("Entreprise")){
			entreprise.getCurrentMarket().addObserver(this);
		}
		sales.setMarket(entreprise.getCurrentMarket());
		model.setNumRows(0);		
		for(ArrayList<String> as : sales.getSalesDB()){
			model.addRow(new Object[]{as.get(0),as.get(1),as.get(2),as.get(3)});
		}
		recetteLabel.setText("recette du magasin " + sales.getBenefits() + " euros");
	}
}
