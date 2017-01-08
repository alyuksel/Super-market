package Vue;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
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

import Produits.ProductType;
import Supermarket.Entreprise;

public class CommandeView extends JPanel implements Observer  {
	private static final long serialVersionUID = 1L;
	private Entreprise entreprise;
	private JTable table;
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
		JPanel formPanel = new JPanel();
		formPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
		formPanel.setMaximumSize(new Dimension(300,300));
		JTextField text = new JTextField(15);
		JTextField quantite = new JTextField(2);
		JButton addButton = new JButton("Add");
		JComboBox<String> combo = new JComboBox<String>(ProductType.getStrings());
		addButton.addActionListener(e->{model.addRow(new Object[]{model.getRowCount()+1,text.getText(),
											quantite.getText(), combo.getSelectedItem()});});
		JButton clear = new JButton("clear");
		clear.addActionListener(l->model.setNumRows(0));
		formPanel.add(new JLabel(label));
		formPanel.add(text);	formPanel.add(combo);
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
		
	}

}
