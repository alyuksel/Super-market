package Vue;

import java.util.Observable;
import java.util.Observer;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import Supermarket.Entreprise;

public class CommandeView extends JPanel implements Observer  {
	private Entreprise entreprise;
	private JTable table;
	private DefaultTableModel model = new DefaultTableModel();
	
	public CommandeView(Entreprise e) {
		this.entreprise=e;
		model.addColumn("Id"); model.addColumn("Nom"); model.addColumn("Quantité");
		table = new JTable(model);
		this.add(new JScrollPane(table));
	}

	@Override
	public void update(Observable o, Object arg) {
		
	}

}
