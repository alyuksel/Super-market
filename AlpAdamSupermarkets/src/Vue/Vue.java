package Vue;


import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashSet;
import java.util.stream.Collectors;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import Supermarket.Generalist;
import Supermarket.SuperMarket;

public class Vue extends JFrame implements ActionListener {
	
	private static final long serialVersionUID = 1L;
	private HashSet<SuperMarket> supermarkets;
	private SuperMarket market;
	private JPanel top,bot,right,left;
	private JLabel data;
	private JButton resume;
	
	
	public Vue(HashSet<SuperMarket> superMarkets){
		this.supermarkets = superMarkets;
		setTitle("Supermarkets");
		setSize(600, 500);
		setResizable(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new GridLayout(2, 4));
		setPanelsMarkets();
		add(top);
		add(bot);
		add(right);
		add(left);
		setVisible(true);
	}
	public Vue(SuperMarket market) {
		this.market = market;
		setTitle("Supermarket");
		setSize(600, 500);
		setResizable(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new GridLayout(2, 0));
		setPanels();
		add(top);
		add(bot);
		setVisible(true);
	}
	
	private void setPanelsMarkets(){
		top = new JPanel();
		top.setBackground(Color.white);
		top.setLayout(new FlowLayout(FlowLayout.LEFT));
		JButton b1 = new JButton("Infos");
		JLabel l1 = new JLabel("Generalist");
		JMenuBar menuBar = new JMenuBar();
		JMenu menu = new JMenu("jjj");
		menuBar.add(menu);
		top.add(menuBar);
		top.add(b1);
		top.add(l1);
		b1.addActionListener((a)-> l1.setText(getType(Generalist.class)));
		
		bot = new JPanel();
		bot.setLayout(new FlowLayout());
		bot.setBackground(Color.blue);
		right = new JPanel();
		right.setLayout(new FlowLayout());
		right.setBackground(Color.red);
		left = new JPanel();
		left.setBackground(Color.green);
		left.setLayout(new FlowLayout());
	}
	
	private void setPanels(){
		top = new JPanel();
		top.setBackground(Color.white);
		top.setLayout(new FlowLayout(FlowLayout.LEFT));
		data = new JLabel("Nothing to display");
		top.add(data);
		bot = new JPanel();
		bot.setLayout(new FlowLayout());
		resume = new JButton("resume");
		bot.add(resume); 
		resume.addActionListener((e)->data.setText(market.toString()));
	}
	private <T> String getType(Class<T> cla){
		return supermarkets.stream()
				.filter(e->e.getClass().equals(cla))
				.map(e-> e.toString())
				.collect(Collectors.toList())
				.get(0);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
	}

}
