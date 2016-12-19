package Vue;



import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import Supermarket.Entreprise;
import Supermarket.SuperMarket;


public class Vue extends JPanel implements ActionListener {
	
	private static final long serialVersionUID = 1L;
	private String name;
	private String numberOfRay="";
	
	public Vue(Entreprise entreprise) {
		this.name = entreprise.getName();
		this.setSize(200,200);
		this.setVisible(true);
		this.setBackground(Color.gray);
		JLabel label = new JLabel(entreprise.getSupermarkets().toString());
		this.add(label);
		JButton button = new JButton("NumerOfRayon");
		for(SuperMarket sm : entreprise.getSupermarkets()){
			numberOfRay = numberOfRay + ", " + sm.getNumberOfRayon();
		}
		button.addActionListener(l-> label.setText(numberOfRay));
		this.add(button);
	}
	
	
	public String getName(){
		return this.name;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		
	}

}
