package Vue;

import java.awt.BorderLayout;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import Employers.Employer;
import Supermarket.Entreprise;

public class EmployersView extends JPanel implements Observer{
	private static final long serialVersionUID = 1L;
	private Entreprise entreprise;
	private JLabel actual = new JLabel();
	private DefaultTableModel model = new DefaultTableModel();
	
	public EmployersView(Entreprise e) {
		entreprise = e;
		entreprise.addObserver(this);
		this.setSize(400,200);
		this.setLayout(new BorderLayout());
		this.actual.setText("selectionner un magasin dans Select Market");
		this.add(this.actual,BorderLayout.NORTH);
		model.addColumn("Nom");model.addColumn("Prenom");model.addColumn("Poste");model.addColumn("Manage");model.addColumn("Salaire");;
		this.add(new JPanel().add(new JScrollPane(new JTable(model))), BorderLayout.SOUTH);		
		this.setVisible(true);	
	}
	
	
	
	@Override
	public String getName(){
		return "employerView";
	}
	
	
	
	@Override
	public void update(Observable o, Object arg) {
		this.actual.setText(entreprise.getCurrentMarket().toString());
		if(o.getClass().getSimpleName().equals("Entreprise")){
			entreprise.getCurrentMarket().addObserver(this);
		}
		model.setNumRows(0);
		for(Employer e : entreprise.getCurrentMarket().getEmployer()){
			model.addRow(new Object[]{e.getName(),e.getLast(),e.getClass().getName(),e.toString(),e.salary()});
		}
			
	}

}
