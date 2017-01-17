package Vue;

import java.awt.FlowLayout;
import java.util.ArrayList;
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

import Produits.Produit;
import Supermarket.Entreprise;

public class GestionView extends JPanel implements Observer{
	private Entreprise entreprise;
	JComboBox<String> combo;
	
	public GestionView(Entreprise e) {
		this.entreprise= e;
		this.entreprise.addObserver(this);
		this.setSize(400,200);
		this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		this.add(addPromForm());
		this.setVisible(true);
	}
	
	public JPanel addPromForm(){
		JPanel form = new JPanel();
		JTextField pourcentage = new JTextField(2);
		JButton ok = new JButton("Ok");
		ok.addActionListener(l->entreprise.setProductOnPromo(combo.getSelectedItem().toString(),
				Integer.valueOf(pourcentage.getText())));
		form.setLayout(new FlowLayout(FlowLayout.LEFT));
		form.add(new JLabel("Promotion : "));
		combo = new JComboBox<String>();
		form.add(combo);
		form.add(new JLabel("pourcentage : "));
		form.add(pourcentage);
		form.add(ok);
		return form;
	}
	
	@Override
	public String getName() {
		return "gestion";
	}

	@Override
	public void update(Observable o, Object arg) {
		combo.removeAllItems();
		Map<String,ArrayList<Produit>> map = entreprise.getCurrentMarket().flatRays();
		map.keySet().forEach(s->combo.addItem(s));
	}
	
	
}
