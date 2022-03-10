package fonctions;


import java.lang.reflect.Field;
import java.sql.*;
import java.util.Vector; 
import java.lang.reflect.*;
import java.beans.*;
import connexion.*;
import java.util.Date;



public class Fonctions{
    public String retNomClass(Object o){
    	Class c = o.getClass();
        String val = c.getSimpleName();
        return val;
      }

	 public  int countAtt(Object o){
		Class c = o.getClass();
		Field [] attributes = c.getDeclaredFields();
		//Field [] superAtt = supClass.getDeclaredFields();
		int a;
		a = attributes.length;
		return a;
	}

	public Field[] getField(Object obj){
		int nb = countAtt(obj);
		String nomAtt[] = new String[nb];
		Class c = obj.getClass();
		Class superClass = c.getSuperclass();

		//Field[] superAtt = superClass.getDeclaredFields();
		Field[] attributes = c.getDeclaredFields();
		//Field[] attributesTot = new Field[nb];

		return attributes;
	}


	public String[] retNomAtt(Object o){
		int nb = countAtt(o);
		String nomAtt[] = new String[nb];
		Field attributesTot[] = getField(o);		
		for(int i=0;i<nb;i++){
			nomAtt[i] = (String)attributesTot[i].getName();
		}
		return nomAtt;
	}


	public Method[] setters(Object obj){
		String nomAtt[] = retNomAtt(obj);
		int length = nomAtt.length;
		Method allSetters[] = new Method[length];
		for(int i=0;i<length;i++){
			try{
				PropertyDescriptor pd = new PropertyDescriptor(nomAtt[i], obj.getClass()); 	
				allSetters[i] = pd.getWriteMethod();
			} catch (IntrospectionException e) {
	            e.printStackTrace();
	        }		
		}
		return allSetters;
	}

	public Method getSet(Object obj,String att){
		String nomAtt[] = retNomAtt(obj);
		int length = nomAtt.length;
		Method set = null;
		for(int i=0;i<length;i++){
			if((nomAtt[i].toUpperCase()).equals(att.toUpperCase())){
				try{
					PropertyDescriptor pd = new PropertyDescriptor(nomAtt[i], obj.getClass()); 	
					set = pd.getWriteMethod();
				} catch (IntrospectionException e) {
		            e.printStackTrace();
		        }		
			}
		}
		return set;
	}

	public Method[] getters(Object obj){
		String nomAtt[] = retNomAtt(obj);
		int length = nomAtt.length;
		Method allGetters[] = new Method[length];
		for(int i=0;i<length;i++){
			try{
				PropertyDescriptor pd = new PropertyDescriptor(nomAtt[i], obj.getClass()); 	
				allGetters[i] = pd.getReadMethod();
			} catch (IntrospectionException e) {
	            e.printStackTrace();
	        }		
		}
		return allGetters;
	}
	public Object[] invokeGetters(Object obj){
		Method allGetters[] = getters(obj);
		int length = allGetters.length;
		Object res[] = new Object[length];
		for(int i=0;i<length;i++){
			try {
				res[i] = allGetters[i].invoke(obj);
	        } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
	            e.printStackTrace();
	        }
		}
		return res;
	}

	public void inserer(Object o,String nomTab,Connexion connexion) throws SQLException{
                java.sql.Statement statement = null;
                String tabId = "id";
                String req="INSERT INTO "+nomTab+"(";
                String allTab;
                String att[] = retNomAtt(o);
                String attSansId[] = new String[att.length];
                Object values[] = invokeGetters(o);
                Object valuesSansId[] = new Object[values.length];
                for(int i=0;i<att.length;i++){
                    attSansId[i] = att[i];
                    valuesSansId[i] = values[i];
                    if((att[i].toUpperCase()).equals(tabId.toUpperCase())){
                        attSansId[i] = att[i+1];
                        valuesSansId[i] = values[i+1];
                        i = i+1;
                    }
                }
                String tab ="";
                String val ="";
                for(int i=0;i<attSansId.length;i++){
                    if(attSansId[i]!=null && valuesSansId[i]!=null){
                        String virg = "";
                        if(i != attSansId.length-1){
                            virg = ",";
                        } 
                        tab = tab +  attSansId[i] + virg;
                        val = val+ "'"+valuesSansId[i]+ "'" + virg;
                        
                    }    
                }
                req = req + tab + ") VALUES(" + val + ")";
                try{
                    System.out.println(req);
                    statement = connexion.getConnexion().createStatement();
                    statement.executeUpdate(req);
                }catch (SQLException e) {
                    e.printStackTrace();
                }finally{
 			if(connexion!=null){
 				connexion.getConnexion().close();
 			}
 			if(statement!=null){
 				statement.close();
 			}
 		}
        }

	public void insererSeq(Object o,String nomTab,String nomSeq,Connexion connexion) throws SQLException{
                java.sql.Statement statement = null;
                String tabId = "id";
                String req="INSERT INTO "+nomTab+"(";
                String allTab;
                String att[] = retNomAtt(o);
                String attSansId[] = new String[att.length];
                Object values[] = invokeGetters(o);
                Object valuesSansId[] = new Object[values.length];
                for(int i=0;i<att.length;i++){
                    attSansId[i] = att[i];
                    valuesSansId[i] = values[i];
                    if((att[i].toUpperCase()).equals(tabId.toUpperCase())){
                        attSansId[i] = null;
                    }
                    if((att[i].toUpperCase()).equals(nomSeq.toUpperCase())){
                        valuesSansId[i] = "test";
                    }
                }
                String tab ="";
                String val ="";
                for(int i=0;i<attSansId.length;i++){
                    if(attSansId[i]!=null && valuesSansId[i]!=null){
                        String virg = "";
                        if(i != attSansId.length-1){
                            virg = ",";
                        } 
                        tab = tab +  attSansId[i] + virg;
                        String pa = "'";
                        if((attSansId[i].toUpperCase()).equals(nomSeq.toUpperCase())){
                            valuesSansId[i] ="nextval('"+nomSeq+"')";
                            pa = "";
                        }
                        val = val+pa+valuesSansId[i]+pa+  virg;
                        
                    }    
                    System.out.println(attSansId[i]);
                }
                req = req + tab + ") VALUES(" + val + ")";
                try{
                    System.out.println(req);
                    statement = connexion.getConnexion().createStatement();
                    statement.executeUpdate(req);
                }catch (SQLException e) {
                    e.printStackTrace();
                }finally{
 			if(connexion!=null){
 				connexion.getConnexion().close();
 			}
 			if(statement!=null){
 				statement.close();
 			}
 		}
        }
        
        
	public Vector select(Object obj,String nomTab,Connexion connexion)throws Exception{
		Vector res = new Vector();
		//Object allGetters[] = getters(obj);
		String req = "SELECT * FROM "+nomTab;
		java.sql.Statement statement=null;
		ResultSet result = null;
		int nb = countAtt(obj);
		String nomAtt[] = retNomAtt(obj);
		Field att[] = getField(obj);

        int taille;
        try{
	    statement = connexion.getConnexion().createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            result = statement.executeQuery(req);
            taille = result.getMetaData().getColumnCount();
            String[] nomCol = new String[taille];
			String[] typeCol = new String[taille];
	        for(int i=0; i<taille; i++) {
	            nomCol[i] = result.getMetaData().getColumnName(i+1);
	            typeCol[i] = result.getMetaData().getColumnTypeName(i+1);
                    //System.out.println("Col "+ nomCol[i]);
                    //System.out.println("Type Col "+ typeCol[i]);
	        }
	        int id =0;


			while(result.next()) {
                Object objet=obj.getClass().newInstance();
                Method method;
	            for(int j=0; j<taille; j++) {
	                for(int i=0; i<nb; i++) {
	                    if(typeCol[j].equalsIgnoreCase("VARCHAR") == true && nomCol[j].equalsIgnoreCase(nomAtt[i].toUpperCase()) == true) {
	                        String r = result.getString(j+1);
	                        method = getSet(obj,nomCol[j]);
	                        method.invoke(objet, r);
	                    }
	                    if(typeCol[j].equalsIgnoreCase("SERIAL") == true && nomCol[j].equalsIgnoreCase(nomAtt[i].toUpperCase()) == true) {
	                        int r = result.getInt(j+1);
	                        method = getSet(obj,nomCol[j]);
	                        method.invoke(objet, r);
	                    }
	                    else if(typeCol[j].equalsIgnoreCase("NUMBER") == true && nomCol[j].equalsIgnoreCase(nomAtt[i].toUpperCase()) == true) {
	                        int r = result.getInt(j+1);
	                        method = getSet(obj,nomCol[j]);
	                        method.invoke(objet, r);
	                    }
	                    else if(typeCol[j].equalsIgnoreCase("DATE") == true && nomCol[j].equalsIgnoreCase(nomAtt[i].toUpperCase()) == true) {
	                        Date r = result.getDate(j+1);
	                        method = getSet(obj,nomCol[j]);
	                        method.invoke(objet, r);
	                    }  
	                }    
	            }
	            res.add(objet);
	            id = id + 1;
			} 
        }catch (Exception e) {
        	e.printStackTrace();
        }finally{
        	if(connexion!=null){
	        	connexion.close();
        	}
  			if (statement!=null) {
  				statement.close();
  			}
  			if (result!=null) {
  				result.close();
  			}
        } 
        return res;
	}
	
    public void update(Object obj,String nomTab,Connexion connexion) throws SQLException{
        java.sql.Statement statement = null;
        String tabId = "id";
        String req="UPDATE "+nomTab;
        String att[] = retNomAtt(obj);
        String attSansId[] = new String[att.length];
        Object values[] = invokeGetters(obj);
        Object valuesSansId[] = new Object[values.length];
        int id = 0;
        for(int i=0;i<att.length;i++){
            attSansId[i] = att[i];
            valuesSansId[i] = values[i];
            if((att[i].toUpperCase()).equals(tabId.toUpperCase())){
                id = id+i;
                attSansId[i] = att[i+1];
                valuesSansId[i] = values[i+1];
                i = i+1;
            }
        }
        String tab = " WHERE id="+values[0];
        String val =" SET ";
        for(int i=0;i<attSansId.length;i++){
            if(attSansId[i]!=null && valuesSansId[i]!=null){
                String virg = "";
                if(i != attSansId.length-1){
                    virg = ",";
                } 
//                tab = tab +   + virg;
                val = val+attSansId[i]+ "='"+valuesSansId[i]+ "'" + virg;
             //   tab  = tab+;

            }    
        }
        req = req +val + tab ;
        try{
            System.out.println(req);
            statement = connexion.getConnexion().createStatement();
            statement.executeUpdate(req);
        }catch (SQLException e) {
            e.printStackTrace();
        }finally{
            if(connexion!=null){
                    connexion.getConnexion().close();
            }
            if(statement!=null){
                    statement.close();
            }
        }        
            
    }

        
     public void delete(Object obj,String nomTab,Connexion connexion) throws SQLException{
        java.sql.Statement statement = null;
        String tabId = "id";
        Object values[] = invokeGetters(obj);
        String req="DELETE FROM "+nomTab+" WHERE id="+values[0];
        try{
            System.out.println(req);
            statement = connexion.getConnexion().createStatement();
            statement.executeUpdate(req);
        }catch (SQLException e) {
            e.printStackTrace();
        }finally{
            if(connexion!=null){
                    connexion.getConnexion().close();
            }
            if(statement!=null){
                    statement.close();
            }
        }        
         
     }   
}