package Vue;

import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;


public class Fenetre extends JFrame {

	private static final long serialVersionUID = -1497362708336802538L;
	JPanel panel = new JPanel();
	CardLayout layout = new CardLayout();
	
	public Fenetre() {
		this.setTitle("alpAdam entreprise");
		this.setSize(550,400);
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
		
		menu.add(panels);
		this.setJMenuBar(menu);
		
		this.add(panel);
		this.setVisible(true);
	}
	
	
	public void addPane(JPanel pane){
		panel.add(pane,pane.getName());
	}
	
}
