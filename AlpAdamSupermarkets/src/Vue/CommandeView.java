package Vue;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.util.Observable;
import java.util.Observer;
import java.util.stream.Stream;

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
import Produits.AllProduct;
import Produits.ProductType;
import Supermarket.Commande;
import Supermarket.Entreprise;

public class CommandeView extends JPanel implements Observer  {
	private static final long serialVersionUID = 1L;
	private Entreprise entreprise;
	private JTable table;
	private JComboBox<String> combo = new JComboBox<String>();
	private DefaultTableModel model = new DefaultTableModel();
	private ProductFactory factory = new ProductFactory();
	private Commande commande;
	
	public CommandeView(Entreprise e) {
		this.entreprise=e;
		this.setLayout(new BorderLayout());
		this.setSize(300, 300);
		model.addColumn("Id"); model.addColumn("Nom"); model.addColumn("Quantité"); model.addColumn("Type");
		table = new JTable(model);
		this.add(addForm("commande :"),BorderLayout.CENTER);
		this.add(new JPanel().add(new JScrollPane(table)), BorderLayout.SOUTH);
		this.commande = new Commande();
	}
	
	private JPanel addForm(String label){
		entreprise.addObserver(this);
		JPanel formPanel = new JPanel();
		formPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
		formPanel.setMaximumSize(new Dimension(300,300));
		JTextField quantite = new JTextField(2);
		JButton addButton = new JButton("Add");
		addButton.addActionListener(e->{try {
			model.addRow(new Object[]{model.getRowCount()+1,combo.getSelectedItem(),
												quantite.getText(), factory.createProduct(combo.getSelectedItem().toString())
												.getProductType().toString()});
		} catch (NoSuchProductException e1) {e1.printStackTrace();
		}});
		JButton clear = new JButton("clear");
		JButton commander = new JButton("Commander");
		commander.addActionListener(l->{
			for(int i=0;i<model.getRowCount();i++){
				if(!entreprise.getCurrentMarket().getRays().containsKey(ProductType.valueOf(model.getValueAt(i,3).toString())))
					commande.setRayonForCommande(model.getValueAt(i,3),entreprise.getCurrentMarket().getName());
				commande.setCommande(model.getValueAt(i,1),Integer.valueOf(model.getValueAt(i,2).toString()),model.getValueAt(i,3),entreprise.getCurrentMarket().getName());
			}});
		clear.addActionListener(l->model.setNumRows(0));
		formPanel.add(new JLabel(label));
		formPanel.add(combo);
		formPanel.add(new JLabel("quantité :"));	formPanel.add(quantite);
		formPanel.add(addButton);
		formPanel.add(clear);
		formPanel.add(commander);
		
		return formPanel;
		}
	
	@Override
	public String getName() {
		return "commande";
	}

	@Override
	public void update(Observable o, Object arg) {
		if(o.getClass().getSimpleName().equals("Entreprise")){
			combo.removeAllItems();
			String[] product = AllProduct.getStrings();
			if(entreprise.getCurrentMarket()!=null &&entreprise.getCurrentMarket().getType().equals("Alimentary")){
				product = Stream.of(AllProduct.getStrings()).filter((String s)->factory.isAlimentary(s)).toArray(String[]::new);
			}
			for(String s : product)combo.addItem(s);
		}
	}

}
