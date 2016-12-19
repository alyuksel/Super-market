package Vue;

import javax.swing.JFrame;

public class Fenetre extends JFrame {

	private static final long serialVersionUID = -1497362708336802538L;
	
	public Fenetre(Vue vue) {
		this.setTitle(vue.getName());
		this.setVisible(true);
		this.setSize(800,800);
		this.add(vue);
	}
	
}
