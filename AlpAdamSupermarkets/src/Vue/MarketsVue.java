package Vue;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Supermarket.Alimentary;
import Supermarket.Entreprise;
import Supermarket.Generalist;

public class MarketsVue extends JPanel implements Observer {
	private static final long serialVersionUID = 1L;
	private Entreprise entreprise;
	private JLabel actuel = new JLabel();
	private JComboBox<String> combo = new JComboBox<String>();
	
	
	public MarketsVue(Entreprise e){
		this.entreprise = e;
		this.entreprise.addObserver(this);
		this.setLayout(new FlowLayout(FlowLayout.LEFT));
		this.setSize(400,200);
		this.actuel.setText("selectionner un magasin dans Select Market");
		this.add(this.actuel);
		addForm("Ajouter Market");
		setBox();
		this.combo.addActionListener(ev->{if(!this.entreprise.getSupermarkets().isEmpty())this.entreprise.setCurrent(this.combo.getSelectedItem().toString());});
		this.add(this.combo);
		JButton remove = new JButton("remove");
		remove.addActionListener(ev->{if(!this.entreprise.getSupermarkets().isEmpty()){
									this.entreprise.removeSupermarkets(entreprise.getCurrentMarket().getName());
									this.combo.removeItemAt(combo.getSelectedIndex());}});
		this.add(remove);
		this.setVisible(true);
	}
	
	private void setBox(){
		for(String s : this.entreprise.getSupermarkets().keySet()){
			this.combo.addItem(s);
		}
	}
	
	private void addForm(String label){
		JPanel formPanel = new JPanel();
		formPanel.setLayout(new FlowLayout());
		formPanel.setMaximumSize(new Dimension(400, 50));
		formPanel.add(new JLabel(label));
		JTextField text = new JTextField(15);
		JButton valide = new JButton("ok");
		JComboBox<String> type = new JComboBox<String>(); type.addItem("Alimentary"); type.addItem("Generalist");
		valide.addActionListener(ev->{if(type.getSelectedItem().equals("Generalist")){
				this.entreprise.addSuperMarket(new Generalist(text.getText()));this.entreprise.addSuperMarketOnDB(text.getText(),(String)type.getSelectedItem());}
				else {this.entreprise.addSuperMarket(new Alimentary(text.getText()));this.entreprise.addSuperMarketOnDB(text.getText(),(String)type.getSelectedItem());}
				this.combo.addItem(text.getText());});
		formPanel.add(type);
		formPanel.add(text);
		formPanel.add(valide);
		this.add(formPanel);
	}
	
	
	@Override
	public String getName() {
		return "marketview";
	}

	@Override
	public void update(Observable o, Object arg) {
		this.actuel.setText(entreprise.getCurrentMarket().toString());
	}
	
}
