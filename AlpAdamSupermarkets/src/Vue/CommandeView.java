package Vue;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.util.Observable;
import java.util.Observer;
import java.util.stream.Stream;

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
import Produits.AllProduct;
import Produits.ProductType;
import Produits.Produit;
import Supermarket.Entreprise;

public class CommandeView extends JPanel implements Observer  {
	private static final long serialVersionUID = 1L;
	private Entreprise entreprise;
	private JTable table;
	private JComboBox<String> combo = new JComboBox<String>();
	private String[] product;
	private DefaultTableModel model = new DefaultTableModel();
	
	public CommandeView(Entreprise e) {
		this.entreprise=e;
		this.setLayout(new BorderLayout());
		this.setSize(300, 300);
		model.addColumn("Id"); model.addColumn("Nom"); model.addColumn("Quantité"); model.addColumn("Type");
		table = new JTable(model);
		this.add(addForm("commande :"),BorderLayout.CENTER);
		this.add(new JPanel().add(new JScrollPane(table)), BorderLayout.SOUTH);
	}
	
	private JPanel addForm(String label){
		entreprise.addObserver(this);
		JPanel formPanel = new JPanel();
		formPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
		formPanel.setMaximumSize(new Dimension(300,300));
		JTextField quantite = new JTextField(2);
		JButton addButton = new JButton("Add");
		addButton.addActionListener(e->{model.addRow(new Object[]{model.getRowCount()+1,combo.getSelectedItem(),
											quantite.getText(), combo.getSelectedItem()});});
		JButton clear = new JButton("clear");
		clear.addActionListener(l->model.setNumRows(0));
		formPanel.add(new JLabel(label));
		formPanel.add(combo);
		formPanel.add(new JLabel("quantité :"));	formPanel.add(quantite);
		formPanel.add(addButton);
		formPanel.add(clear);
		
		
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
			ProductFactory factory = new ProductFactory();
			product = AllProduct.getStrings();
			if(entreprise.getCurrentMarket()!=null &&entreprise.getCurrentMarket().getType().equals("Alimentary")){
				product = Stream.of(AllProduct.getStrings()).filter((String s)->factory.isAlimentary(s)).toArray(String[]::new);
			}
			for(String s : product)combo.addItem(s);
		}
	}

}
