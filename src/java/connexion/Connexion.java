package connexion;

import java.sql.*;

public class Connexion{
	private Connection connexion=null;
	public Connection getConnexion(){
		return this.connexion;
	}
	public Connexion(String base,String usr,String m)throws Exception{
	    try{
                Class.forName("org.postgresql.Driver");
	        Connection c = DriverManager.getConnection("jdbc:postgresql://localhost:5432/"+base,usr,m);
                this.connexion = c; 
	    }catch (Exception e) {
	    	e.printStackTrace();
	    }
	}
	public void close()throws Exception{
		if(this.connexion!=null){
			this.connexion.close();
		}
	}



}