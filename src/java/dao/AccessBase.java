package dao;

import java.sql.*;
import java.lang.reflect.*;
import java.util.ArrayList;


public class AccessBase {
	
	///////////////////////////////////////////////////useful//////////////////////////////////////////////////
	//////////////////////////////////////////////////////////////////////////////////////////////////////////
	public String[]getStringValues() throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException{
		Field[] field=this.getFieldAnnoted();
		String[] rep=new String[field.length];
		for(int i=0;i<rep.length;i++) {
			try {
				rep[i]=this.getAttributeValue(field[i].getName()).toString();
			}
			catch(Exception e) {
				rep[i]="";
			}
			
		}
		return rep;
 	}
	public String getStringOneValues(String name) throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchFieldException{
		Field field=this.getClass().getDeclaredField(name);
	return this.getAttributeValue(field.getName()).toString();
	
 	}
	public String[] getFieldName() {
		Field[] fld=this.getFieldAnnoted();
		String[] rep=new String[fld.length];
		for(int i=0;i<rep.length;i++) {
			rep[i]=fld[i].getName();
		}
		return rep;
	}
	public static String[][] getStringAttribute(AccessBase[] a) throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException{
		String[][] rep=new String[a.length][];
		for(int i=0;i<a.length;i++) {
			rep[i]=a[i].getStringValues();
		}
		return rep;
	}
	public static String[][] getStringAttribute(ArrayList a) throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException{
		AccessBase[] rep=(AccessBase[])a.toArray();
		return AccessBase.getStringAttribute(rep);
	}
	public static String[] getStringOneAttribute(AccessBase[] a,String name) throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchFieldException{
		String[] rep=new String[a.length];
		for(int i=0;i<a.length;i++) {
			rep[i]=a[i].getStringOneValues( name);
		}
		return rep;
	}
	//fonction permettant d'avoir les attributs ayant des annotations
	public Field[] getFieldAnnoted() {
		Field[] field=this.getClass().getDeclaredFields();
		ArrayList<Field> fields=new ArrayList<Field>();
		for(Field f : field) if(f.isAnnotationPresent(Attribute.class)) fields.add(f);
		return fields.toArray(new Field[fields.size()]);
	}
	public Field[] getFieldAnnotedWithoutPrimaryKey() {
		Field[] field=this.getClass().getDeclaredFields();
		ArrayList<Field> fields=new ArrayList<Field>();
		for(Field f : field) {
			if(f.isAnnotationPresent(Attribute.class)) {
				String id=AccessBase.IsIdAttribute(f) ;
				if(id.compareTo("yes")!=0) {
					fields.add(f);
				}
			
					
			}
				
				
		} 
		return fields.toArray(new Field[fields.size()]);
	}
	///fonction permettant d'avoir les attributs non null sur celui qui est annot�
	public  Field[] getNonNullAnnotedField(Field[] field) throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		ArrayList<Field> notnullField=new ArrayList<Field>();
		for(Field f:field) {
			Object attrVal=getAttributeValue(f.getName());
			if(attrVal!=null)notnullField.add(f);
		}
		return notnullField.toArray(new Field[notnullField.size()]);
	}
	
	public  Field[] getNonNullAnnotedField() throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		Field[] field=this.getFieldAnnoted();
		return getNonNullAnnotedField(field);
	}
	
	//fonction permettant de recuperer la valeur d'un attribur
		public  Object getAttributeValue(String attributeName) throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
			Method getter=AccessBase.getGetter(attributeName,this.getClass());
			return getter.invoke(this);	
		}
		//permet d'avoir le type de notre arguments
		public static  Class getClassArg(Object arg) {
			if(new String("").getClass().isInstance(arg))
				return new String("").getClass();
			if(new Integer(0).getClass().isInstance(arg)) 
				return new Integer(0).getClass();
			if(new Double(0).getClass().isInstance(arg))
				return new Double(0).getClass();
			if(new Float(0).getClass().isInstance(arg)) 
				return new Float(0).getClass();
			if(new Long(0).getClass().isInstance(arg)) 
				return new Long(0).getClass();
			if(Date.class.isInstance(arg)) 
				return Date.class;
			else return null;
		}
		
		//fonction permettant de transformer le string en objet
				public static Object getObjetArg(Field f,String arg) throws Exception {
					if(arg==null||arg=="") {
						return null;
					}
					if(new String("").getClass().equals(f.getType()))
						return 	arg;
					if(new Integer(0).getClass().equals(f.getType())) 
						return Integer.parseInt(arg);
					if(new Double(0).getClass().equals(f.getType()))
						return Double.parseDouble(arg);
					if(new Float(0).getClass().equals(f.getType())) 
						return Float.parseFloat(arg);
					if(new Long(0).getClass().equals(f.getType())) 
						return Long.parseLong(arg);
					if(Date.class.equals(f.getType())) 
						return Useful.stringToDate(arg);
					if(Timestamp.class.equals(f.getType())) {
						return arg;
					}
					if(Time.class.equals(f.getType())) 
						return arg;
					
					else return null;
				}
				
	//Nb le premier Libellee sera toujours l'idn dans notre libelles
		public  Method  getSetter(String libellee,Object arg) throws NoSuchMethodException, SecurityException {
			String getterName=AccessBase.getSetterName(libellee);
			Class cla=this.getClass();
			Class[] args= {getClassArg(arg)};
			return cla.getMethod(getterName,args);
	 	}
		
		public static String getSetterName(String libellee) {
			String deb=libellee.substring(0,1);
			String tohiny=libellee.substring(1);
			return "set"+deb.toUpperCase()+tohiny;
			
		}
		public static  String[] getTypeAttr(Field[]fields) {
			ArrayList<String> rep=new ArrayList<String>();
			for(Field f:fields) {
				Attribute attr=(Attribute)f.getAnnotation(Attribute.class);
				rep.add(attr.attrType());
			}
			return rep.toArray(new String[rep.size()]);
		}
		public static  Field getPrimaryKey(Field[]fields) {
			for(Field f:fields) {
				String id=AccessBase.IsIdAttribute(f) ;
				if(id!=null&&id!="") {
					return f;
				}
			}
			return null;
		}
		public static  String getAttrName(Field f) {
				Attribute attr=(Attribute)f.getAnnotation(Attribute.class);
				return attr.attrName();
		}
		public static  String IsIdAttribute(Field f) {
			Attribute attr=(Attribute)f.getAnnotation(Attribute.class);
			return attr.idPrimaryKey();
		}	
		public  String getTableName() {
			TableName table=getTable();
			return table.table();
		}
		public  String getView() {
			TableName table=getTable();
			return table.view();
		}
		public String getTables(){
			String view=this.getView();
			String table=getTableName();
			if(view.length()>1)return view;
			else return table;
		}
		public TableName getTable() {
			return (TableName)this.getClass().getAnnotation(TableName.class);
		}
		public  PreparedStatement setPreparedStatement(String query,Field[] attribute,Connection con) throws SQLException, NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
			PreparedStatement stmt=con.prepareStatement(query);
			int taille=attribute.length;
			for(int i =0;i<taille;i++) {
				AccessBase.configureStatement(i+1,getAttributeValue(attribute[i].getName()),stmt); 
			}
			return stmt;
		}
		public static void configureStatement(int pos,Object attribut,PreparedStatement stmt) throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, SQLException {
			
			if(attribut==null)
				stmt.setString(pos,null);
			else if(String.class.isInstance(attribut))
				stmt.setString(pos,attribut.toString());
			else if(Integer.class.isInstance(attribut)) 
				stmt.setInt(pos,new Integer(attribut.toString()));
			else if(Double.class.isInstance(attribut))
				stmt.setDouble(pos,new Double(attribut.toString()));
			else if(Float.class.isInstance(attribut))
				stmt.setFloat(pos,new Float(attribut.toString()));
			else if(Long.class.isInstance(attribut)) 
				stmt.setLong(pos,new Long(attribut.toString()));
			else if(Date.class.isInstance(attribut)) 
				stmt.setDate(pos,((Date)attribut));
			else if(Timestamp.class.isInstance(attribut)) 
				stmt.setTimestamp(pos,((Timestamp)attribut));
			else 				
				stmt.setString(pos,null);
		}
		public static Method  getGetter(String libellee,Class cla) throws NoSuchMethodException, SecurityException {
			String getterName=AccessBase.getGetterName(libellee);
			return cla.getMethod(getterName);
	 	}
		public static String getGetterName(String libellee) {
 			String deb=libellee.substring(0,1);
			String tohiny=libellee.substring(1);
			return "get"+deb.toUpperCase()+tohiny;
			
		}
		//permettant d'ajouter un element dans un tableau d'attribut
		public static Field[] addnewField(Field[] field,Field newF) {
			Field[] rep=new Field[field.length+1];
			for(int s=0;s<field.length;s++) {
				rep[s]=field[s];
			}
			rep[field.length]=newF;
			return rep;
		}
		public static String conditionId(Field id) {
			String rep="";
			if(id!=null) {
				rep=" where "+AccessBase.getAttrName(id)+" = ?";
			}
			return rep;
		} 
		public  Object getId() throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
			Field[] notNullField=getNonNullAnnotedField();
			Field id=AccessBase.getPrimaryKey(notNullField);
			return getAttributeValue(id.getName());
		}
	////////////////////////////////////////////////////find//////////////////////////////////////////////////////
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////	
		
	public static String generateCondition(Field[] notNullAnnotedField) {
		String condition=" where ";
		for(int i=0;i<notNullAnnotedField.length-1;i++) {
			condition+=AccessBase.getAttrName(notNullAnnotedField[i])+" = ? and ";
		}
		condition+=AccessBase.getAttrName(notNullAnnotedField[notNullAnnotedField.length-1])+" = ?";
		return condition;
		
	}
	

	
	//generation du requete sql
	public  String generateSql(Field[] activefield) {
		String table=this.getTables();	
		String query="Select ";
		for(int i=0;i<activefield.length-1;i++) {
			query+=AccessBase.getAttrName(activefield[i])+",";
		}
		System.out.println(table);
		query+=AccessBase.getAttrName(activefield[activefield.length-1])+" from "+table;
		System.out.println(query);
		return query;
	}

	public ArrayList find() throws Exception {
		Connection con=ConnectionBase.getCon();
		try {
			return find(con);
		}
		catch(Exception e) {
			throw(e);
		}
		finally {
			if(con!=null)con.close();
		}	
	}
	public ArrayList find(Connection con) throws Exception {
		try {
			Field[] activefield=getFieldAnnoted();
			Field[] notNullfield=getNonNullAnnotedField(activefield);
			String query=generateSql(activefield);
			String condition="";
			if(notNullfield.length>0)condition=AccessBase.generateCondition(notNullfield);
			query+=condition;		
			return find(con,query,notNullfield,activefield);
		}
		catch(Exception e) {
			throw(e);
		}
		finally {
		}	
	}
	public  ArrayList find(Connection con,String query,Field[]notNullfield,Field[] attribute)throws Exception {
		PreparedStatement stmt=null;
		 ArrayList val = new ArrayList();
        try {
        	stmt=setPreparedStatement(query,notNullfield,con);
        	ResultSet res=stmt.executeQuery();
        	while(res.next()){
        		ArrayList<String> arguments=new ArrayList();
        		for(Field f: attribute) {
        			String libelle=AccessBase.getAttrName(f);
        			arguments.add(res.getString(libelle));
        		}
        		val.add(createNewObjectLikeBase(arguments.toArray(new String[arguments.size()]),attribute));
        	}
        	return val;
        }
        catch(Exception e){
        	throw e;
        }
        finally {
        	if(stmt!=null)stmt.close();
        }
	}
	//fonction creant les nouveau object avec les aruments � l'int�rieurs
	public  Object createNewObjectLikeBase(String[] arguments,Field[] libellee) throws Exception {
		AccessBase reponse=(AccessBase)getClass().newInstance();
		int taille=arguments.length;
		Method setter=null;
		reponse.chargeObject(arguments, libellee);
		return reponse;
	}
	public void  chargeObject(String[] arguments,Field[] libellee) throws Exception {
		int taille=arguments.length;
		Method setter=null;
		for(int i=0;i<taille;i++) {
			if(arguments[i]==null||arguments[i]=="");	
			else {
				 Object arg=getObjetArg(libellee[i],arguments[i]);
				 setAttribute(libellee[i].getName(),arg);	
			}
			
		}
	}
	public void  chargeObject(String[] arguments) throws Exception {
		Field[] attribut=this.getFieldAnnotedWithoutPrimaryKey();
		this.chargeObject(arguments, attribut);
	}
	
	public void setAttribute(String attrName ,Object args) throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		
		Method setter =this.getSetter(attrName,args);
		Object[] arg= {args};
		setter.invoke(this,arg);	
	}
	
	///////////////////////////////////insert///////////////////////////////////////
	//////////////////////////////insert with anotation/////////////////////////////
	
	//fonction permettant de generer la requete sql()
			//set query
			public static String getQueryInsert(String table,Field[] notNullAnnotedField) {
				String rep="insert into "+table;
				String insertLibellee="(";
				String fanampiny="(";
				int taille=notNullAnnotedField.length-1;
				for(int i=0;i<taille;i++) {
					fanampiny+="?,";
					insertLibellee+=AccessBase.getAttrName(notNullAnnotedField[i])+",";
				}
				fanampiny+="?)";
				if(taille>=0)insertLibellee+=AccessBase.getAttrName(notNullAnnotedField[taille])+")";
				return rep+insertLibellee+" values "+fanampiny;
			}

	public  void insertBase(Connection con) throws Exception {
		try {
			Field[] allfield=this.getFieldAnnoted();
			String table=getTableName();	
	    	Field[] notNullField=getNonNullAnnotedField(allfield);
			
			insertionBase(con,table,notNullField);
		}
		catch(Exception e) {
			throw(e);
		}
		finally {
		}
	}
	public  void insertBase() throws Exception {
		Connection con=ConnectionBase.getCon();
		try {
			this.insertBase(con);
			con.commit();
		}
		catch(Exception e) {
			con.rollback();
			throw(e);
		}
		finally {
			if(con!=null)con.close();
		}
	
	}
	public  void insertionBase(Connection con,String table,Field[] notNullField) throws Exception{
		PreparedStatement stmt=null;
       try {
    	String query=AccessBase.getQueryInsert(table,notNullField);
       	stmt= setPreparedStatement(query,notNullField,con);
        stmt.executeUpdate();
       }
       catch(Exception e){
       	throw e;
       }
       finally {
       	if(stmt!=null)stmt.close();
       }
	}

	///////////////////////////////////////////////////////update////////////////////////////////////////////////
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	//fonction permettant de generer la requete sql()
			//set query
		
			public static String getQueryUpdate(String table,Field[] notNullAnnotedField,Field id) {
				String rep="Update "+table+" set ";
				String insertLibellee="";
				int taille=notNullAnnotedField.length-1;
				for(int i=0;i<taille;i++) insertLibellee+=AccessBase.getAttrName(notNullAnnotedField[i])+"=?,";
				if(taille>=0)insertLibellee+=AccessBase.getAttrName(notNullAnnotedField[taille])+"=?";
				return rep+insertLibellee+AccessBase.conditionId(id);
			}
	public  void updateBase(Connection con) throws Exception {
		try {
			Field[] notNullField=getNonNullAnnotedField();
			Field id=AccessBase.getPrimaryKey(notNullField);
			String table=this.getTableName();	
			updateBase(con,table,notNullField,id);
		}
		catch(Exception e) {
			throw(e);
		}
		finally {
		}
	
	}
	public  void updateBase() throws Exception {
		Connection con=null;
		try {
			con=ConnectionBase.getCon();
			updateBase(con);
			con.commit();
		}
		catch(Exception e) {
			con.rollback();
			throw(e);
		}
		finally {
			if(con!=null)con.close();
		}
	
	}
	public  void updateBase(Connection con,String table,Field[] notNullField,Field id) throws Exception{
		PreparedStatement stmt=null;
       try {
    	String query=AccessBase.getQueryUpdate(table, notNullField, id);
    	Field[] allattr=AccessBase.addnewField(notNullField,id);
       	stmt= setPreparedStatement(query,allattr,con);
        stmt.executeUpdate();

       }
       catch(Exception e){
       	throw e;
       }
       finally {
       	if(stmt!=null)stmt.close();
       }
	}
	/////////////////////////////////////delete/////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////////////
	//set query
	public static String getQueryDelete(String table,Field[] field) {
		String rep="Delete  from "+table+" ";
		return rep+AccessBase.generateCondition(field);
	}
	public  void deleteBase(Connection con) throws Exception {
		try {
			Field[] notNullField=getNonNullAnnotedField();
			String table=getTableName();	
			deleteBase(con,table,notNullField);
		}
		catch(Exception e) {
			throw(e);
		}
		finally {
		}
	
	}
	public  void deleteBase() throws Exception {
		Connection con=null;
		try {
			con=ConnectionBase.getCon();
			deleteBase(con);
			con.commit();
		}
		catch(Exception e) {
			con.rollback();
			throw(e);
		}
		finally {
			if(con!=null)con.close();
		}
	
	}
	public  void deleteBase(Connection con,String table,Field[] notNullField) throws Exception{
		PreparedStatement stmt=null;
       try {
    	   
    	String query=AccessBase.getQueryDelete(table, notNullField);
       	stmt= setPreparedStatement(query,notNullField,con);
        stmt.executeUpdate();
       }
       catch(Exception e){
       	throw e;
       }
       finally {
       	if(stmt!=null)stmt.close();
       }
	}
	public static void main(String []arg) throws Exception {
			double a=23.298268263383;
			int b=2;
			System.out.println(a/b);
		}
}
