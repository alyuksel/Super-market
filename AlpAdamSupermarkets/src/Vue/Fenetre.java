package Vue;

import java.awt.CardLayout;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;


public class Fenetre extends JFrame {

	private static final long serialVersionUID = -1497362708336802538L;
	JPanel panel = new JPanel();
	CardLayout layout = new CardLayout();
	
	public Fenetre() {
		this.setTitle("alpAdam entreprise");
		this.setSize(600,600);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		panel.setLayout(layout);
		
		JMenuBar menu = new JMenuBar();
		
		JMenu panels = new JMenu("Menu");
		
		JMenuItem market = new JMenuItem("Market");
		market.addActionListener(e->layout.show(panel, "market"));
		panels.add(market);
		
		JMenuItem selectMarkets = new JMenuItem("Select Market");
		selectMarkets.addActionListener(e->layout.show(panel, "marketview"));
		panels.add(selectMarkets);
		
		JMenuItem commande = new JMenuItem("Commandes");
		commande.addActionListener(e->layout.show(panel, "commande"));
		panels.add(commande);
		
		JMenuItem ventes = new JMenuItem("Ventes");
		ventes.addActionListener(e->layout.show(panel,"salesView"));
		panels.add(ventes);
		
		menu.add(panels);
		this.setJMenuBar(menu);
		
		this.add(panel);
		this.setVisible(true);
	}
	
	
	public void addPane(JPanel pane){
		panel.add(pane,pane.getName());
	}
	
}
