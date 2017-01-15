package Test;

import javax.swing.table.DefaultTableModel;

public abstract class Choice {
	String s;
	public Choice(String s) {
		this. s = s;
	}
	
	public abstract void eval(DefaultTableModel model);
}
