package dao;
import java.lang.reflect.Field;
import java.sql.*;

public class ConnectionBase
{
	public static Connection establishConnection(String driver,String url,String user,String pass) throws Exception
	{
		Class.forName(driver);
		Connection c = DriverManager.getConnection(url,user,pass);
		c.setAutoCommit(false);
		return c;
	}
        public static Connection getCon() throws Exception {
            Connection rep= ConnectionBase.establishConnection(
                "org.postgresql.Driver",
                "jdbc:postgresql://localhost:5432/maisonproduction",
                "maisonproduction",
                "123456"
            );
            rep.setAutoCommit(false);
            return rep;
        }
        public static int getSequence(String nomTable)throws Exception{
            Connection con=null;
            java.sql.Statement stmt=null;
            ResultSet res=null;
            try {
                con=ConnectionBase.getCon();
                stmt=con.createStatement();
                res=stmt.executeQuery("select nextval('seq"+nomTable+"') seq");
                res.next();
                return res.getInt("seq");
            } catch (Exception e) {
                throw e;
            }
            finally{
                if(res!=null)res.close();
                if(stmt!=null)stmt.close();
                if(con!=null)con.close();
            }

        }
        public static int getSequence(String nomTable,Connection con)throws Exception{
            java.sql.Statement stmt=null;
            ResultSet res=null;
            try {
                con=ConnectionBase.getCon();
                stmt=con.createStatement();
                res=stmt.executeQuery("select nextval('seq"+nomTable+"') seq");
                res.next();
                return res.getInt("seq");
            } catch (Exception e) {
                throw e;
            }
            finally{
                if(res!=null)res.close();
                if(stmt!=null)stmt.close();
            }

        }
        public static void main(String[]arg) throws Exception {
        	Connection con =ConnectionBase.getCon();
        	System.out.println("connected");
        	System.out.println("ok");
        }
}