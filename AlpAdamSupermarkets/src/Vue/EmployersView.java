package Vue;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;


import Employers.Employer;
import Supermarket.Entreprise;

public class EmployersView extends JPanel implements Observer{
	private static final long serialVersionUID = 1L;
	private Entreprise entreprise;
	private JLabel actual = new JLabel();
	private DefaultTableModel model = new DefaultTableModel();
	private JLabel management = new JLabel();
	
	public EmployersView(Entreprise e) {
		entreprise = e;
		entreprise.addObserver(this);
		this.setSize(400,200);
		this.setLayout(new BorderLayout());
		this.actual.setText("selectionner un magasin dans Select Market");
		this.add(this.actual,BorderLayout.NORTH);
		this.add(this.employeeChoice());
		model.addColumn("Nom");model.addColumn("Prenom");model.addColumn("Poste");model.addColumn("Salaire");;
		this.add(new JPanel().add(new JScrollPane(new JTable(model))), BorderLayout.SOUTH);	
		this.setVisible(true);	
	}
	
	private JPanel employeeChoice(){
		JPanel empl = new JPanel();
		JComboBox<String> poste = new JComboBox<>();
		JTextField name = new JTextField(8);
		JTextField firstname = new JTextField(8);
		JButton add = new JButton("Add");
		empl.setLayout(new FlowLayout(FlowLayout.LEFT));
		empl.add(new JLabel("Nom : "));empl.add(name);empl.add(new JLabel("Prenom : "));empl.add(firstname);
		empl.add(new JLabel("Poste : "));
		poste.addItem("Manager");poste.addItem("Manu");poste.addItem("Caissier");
		empl.add(poste);
		empl.add(add);
		empl.add(management);
		add.addActionListener(l-> {if(entreprise.isAlreadyAManager()&&poste.getSelectedItem().toString().equals("Manager"))
										{System.out.println("true");management.setText("Un seul Manager Possible !");management.setForeground(Color.red);}
								   else {entreprise.addEmployer(name.getText(),firstname.getText(),poste.getSelectedItem().toString());}
		});
		return empl;
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
			model.addRow(new Object[]{e.getName(),e.getLast(),e.getClass().getSimpleName(),e.salary()});
			if(e.isManager()){management.setText(e.toString());management.setForeground(Color.BLACK);};
		}
			
	}

}
