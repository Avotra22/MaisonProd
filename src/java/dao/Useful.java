package dao;

import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class Useful {
	///////////////////////verification if null data////////////////////////
	public static boolean isNull(Object obj) {
		if(obj==null)return true;
		return false;
	}
	public static boolean isNullString(String obj) {
		obj=obj.replace(" ","");
		boolean bol= Useful.isNull(obj)||(obj=="");
		
		return bol;
	}
	
	//////////////////////////traitement numeric data //////////////////////////////
	public static boolean isNumeric(Object obj) {
		if(Useful.isNull(obj))return false;
		if(Number.class.isAssignableFrom(obj.getClass())) return true;
		return false;
	}
	public static boolean isNumericable(String obj) {
		try {
			Double a=new Double(obj);
			return true;
		}
		catch(Exception e) {
			return false;
		}
	}
	public static boolean ifPositifNumber(String obj) {
		return Useful.isNumericable(obj)&&(Double.parseDouble(obj)>=0);
	}
	
	//////////////////////traitement date data ////////////////
	public static Date stringToDate(String sdate) throws Exception {
		try {
			return Date.valueOf(sdate);
		}
		catch(Exception e) {
			 SimpleDateFormat formatter= new SimpleDateFormat ("dd/MM/yyyy");
			 java.util.Date rep=formatter.parse(sdate);
			 return new Date(rep.getTime());
		}
		
	}
	public static Date getNextDateOfTheMounth(int month) throws Exception {
		java.util.Date now=new java.util.Date();
		java.util.Date dateEstimated=(java.util.Date) now.clone();
		dateEstimated.setMonth(month);
		if(dateEstimated.before(now)) {
			dateEstimated.setYear(dateEstimated.getYear()+1);
		}
		
		return new Date(dateEstimated.getTime());
	}
	public static boolean isJourFiasana(Date date) {
		int andro=date.getDay();
		int[] nontrav= {2,4};
		for(int i=0;i<(nontrav.length);i++) 
			if(andro==nontrav[i])return false;
		return true;
	}
	public static int getDayWorkOfTheMonth(Date date) {
		Date dt=(Date) date.clone();
		dt.setDate(1);
		int nombreJour=0;
		int month=dt.getMonth();
		while(dt.getMonth()==month) {
			if(isJourFiasana(dt)) {
				nombreJour++;
			}
			dt.setDate(dt.getDate()+1);
		}
		return nombreJour;
	}
	public static Timestamp stringToDateLong(String sdate) throws Exception {
		 SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		    java.util.Date parsedDate = dateFormat.parse(sdate);
		    Timestamp timestamp = new java.sql.Timestamp(parsedDate.getTime());
		    return timestamp;
	}
	public static Time stringToTime(String sdate) throws Exception {
		 SimpleDateFormat dateFormat = new SimpleDateFormat("hh:mm:ss");
		    java.util.Date parsedDate = dateFormat.parse(sdate);
		    Time timestamp = new java.sql.Time(parsedDate.getTime());
		    return timestamp;
	}
	public static double dureeTime(Time tmp) {
			return 3600*tmp.getHours()+60*tmp.getMinutes()+tmp.getSeconds();
	}
        //difference date en jour
        public static Integer getDifferenceDate(Date da,Date dB){
        long time=da.getTime()-dB.getTime();
        time/=(1000*3600*24);
        return new Integer((int) time);
        }
        //difference dateseconde
        public static Double getDifferenceDate(Timestamp da,Timestamp dB){
        long time=da.getTime()-dB.getTime();
        time/=1000;
        return new Double((int) time);
        }
        
     //////////verifier si la date est � l'interieur d'un intervalle
        public static boolean isInsideIntervalle(Date date,Date debut,Date fin) {
        	int comparaison1=date.compareTo(debut);
        	int comparaison2=date.compareTo(fin);
        	return (comparaison1>=0&&comparaison2<=0);
        }
	 ///gettion d'exception
    public static void notNull(Object obj,String message) throws Exception{
        
        if(Useful.isNull(obj))
            throw new Exception(message);
    }
    public static void notNullString(String obj,String message) throws Exception{
        if(Useful.isNullString(obj))
            throw new Exception(message);
    }
    public static void  isNumericable(String obj,String message) throws Exception{
        if(!Useful.isNumericable(obj))
            throw new Exception(message);
    }
    public static Date getDateWithAdditionalMonth(int month) throws Exception {
		java.util.Date now=new java.util.Date();
        now.setMonth(now.getMonth()+month);
		return new Date(now.getTime());
	}
    public static Boolean isExpiredInOneMonth(Date date) throws Exception {
    	Date debut=Useful.getDateWithAdditionalMonth(0);
    	Date fin=Useful.getDateWithAdditionalMonth(1);
    	return Useful.isInsideIntervalle(date,debut,fin);
    }
    public static Boolean isExpiredInThreeMonth(Date date) throws Exception {
    	Date debut=Useful.getDateWithAdditionalMonth(1);
    	Date fin=Useful.getDateWithAdditionalMonth(3);
    	return Useful.isInsideIntervalle(date,debut,fin);
    }
	public static void main(String [] args) throws Exception {
		Date  a=Useful.getDateWithAdditionalMonth(1);
		System.out.println(a.toLocaleString());
	}
	
}
