package Vue;



import java.util.ArrayList;
import java.util.Map.Entry;
import java.util.Observable;
import java.util.Observer;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import Produits.Produit;
import Rayons.Rayon;
import Supermarket.Entreprise;


public class Market extends JPanel implements Observer {
	private static final long serialVersionUID = 1L;
	private Entreprise entreprise;
	private DefaultTableModel model = new DefaultTableModel();
	
	public Market(Entreprise e) {
		entreprise = e;
		entreprise.addObserver(this);
		this.setSize(400,200);
		this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		this.add(new JLabel("resumé du magasin : "));
		this.add(new JScrollPane(new JTable(model)));
		model.addColumn("Type"); model.addColumn("Nom"); model.addColumn("Quantité"); model.addColumn("Prix/u");
		this.setVisible(true);
	}
	
	
	public String getName(){
		return "market";
	}


	@Override
	public void update(Observable o, Object arg) {
		if(o.getClass().getSimpleName().equals("Entreprise")){
			entreprise.getCurrentMarket().addObserver(this);
			model.setNumRows(0);
			for(Rayon r :entreprise.getCurrentMarket().getRays().values()){
				for(Entry<String,ArrayList<Produit>> e : r.getMapProduct().entrySet()){
					if(!e.getValue().isEmpty())
					model.addRow(new Object[]{r.getProductType(),e.getKey(),e.getValue().size(),e.getValue().get(0).getPrice()});
				}
			}
		
		}
	}
}
