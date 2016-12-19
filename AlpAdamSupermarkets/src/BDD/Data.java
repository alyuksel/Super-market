package BDD;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class Data {
	private Connection connection;
	private Statement statement;
	private ResultSet res;

	
	public Data() throws ClassNotFoundException{
		Class.forName("org.sqlite.JDBC");
		try {
			  this.connection = DriverManager.getConnection("jdbc:sqlite:base.db");
		      this.statement = connection.createStatement();
		      
			
		}catch(SQLException e){
			System.err.println("Erreur ici : " + e.getMessage());
		}
		
	}
	
	public ResultSet requete(String req){
		try {
			res = statement.executeQuery(req);
			
		} catch (SQLException e) {
			System.err.println(e.getMessage() + e.getCause());
		}
		return res;
	}
	
	public boolean insert(String sql){
		boolean res = false;
		try {
			res = statement.execute(sql);
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return res;
	}
	
	public static void main(String[] args) throws ClassNotFoundException  {
		Data data = new Data();

		data.insert("insert into Produit(name,number) values ('yves rocher',20);");
		
	}
	
	
}
