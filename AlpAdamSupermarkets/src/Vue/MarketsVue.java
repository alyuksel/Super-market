package Vue;

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.Collection;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;
import java.util.Set;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.Spring;
import javax.swing.SpringLayout;
import javax.swing.SwingUtilities;

import Supermarket.Alimentary;
import Supermarket.Entreprise;
import Supermarket.Generalist;
import Supermarket.SuperMarket;

public class MarketsVue extends JPanel implements Observer {
	private static final long serialVersionUID = 1L;
	Entreprise entreprise;
	private JLabel actuel = new JLabel();
	private JComboBox<String> combo = new JComboBox<String>();
	
	
	public MarketsVue(Entreprise e) {
		entreprise = e;
		entreprise.addObserver(this);
		this.setLayout(new FlowLayout(FlowLayout.LEFT));
		this.setSize(400,200);
		actuel.setText("nom magasin : ");
		this.add(actuel);
		addForm("Ajouter Market");
		setBox();
		combo.addActionListener(ev->{if(!entreprise.getSupermarkets().isEmpty())entreprise.setCurrent(combo.getSelectedItem().toString());});
		this.add(combo);
		JButton remove = new JButton("remove");
		remove.addActionListener(ev->{if(!entreprise.getSupermarkets().isEmpty()){
									entreprise.removeSupermarkets(entreprise.getCurrentMarket().getName());
									combo.removeItemAt(combo.getSelectedIndex());}});
		this.add(remove);
		
		this.setVisible(true);
	}
	
	private void setBox(){
		for(String s : entreprise.getSupermarkets().keySet()){
			combo.addItem(s);
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
		valide.addActionListener(ev->{if(type.getSelectedItem().equals("Generalist"))
				entreprise.addSuperMarket(new Generalist(text.getText()));
				else entreprise.addSuperMarket(new Alimentary(text.getText()));
				combo.addItem(text.getText());});
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
		actuel.setText("nom magasin : "+ entreprise.getCurrentMarket().getName()
				+ " ,  type : "+ entreprise.getCurrentMarket().getType());
	}
	
}
