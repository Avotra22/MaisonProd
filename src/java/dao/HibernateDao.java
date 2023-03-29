package dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import org.hibernate.criterion.Example;
import org.hibernate.criterion.Order;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import javax.persistence.Id;
import org.hibernate.Criteria;
import org.hibernate.criterion.Conjunction;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;

//Hibernate 3.0
public class HibernateDao {

    private SessionFactory sessionFactory;

    public <T> T create(T entity){
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.save(entity);
        transaction.commit();
        session.close();
        return entity;
    }

    public <T> T findById(Class<T> clazz,Serializable id){
        Session session = sessionFactory.openSession();
        T entity = (T) session.get(clazz, id);
        session.close();
        return entity;
    }

    public <T> List<T> findAll(Class<T> tClass){
        Session session = sessionFactory.openSession();
        List<T> results = session.createCriteria(tClass).list();
        session.close();
        return results;
    }
    public <T> List<T> findWhere(T entity){
        Session session = sessionFactory.openSession();
        Example example = Example.create(entity).ignoreCase();
        List<T> results = session.createCriteria(entity.getClass()).add(example).list();
        session.close();
        return results;
    }
    public <T> List<T> findWhereV2(T entity) throws Exception{
        Session session = sessionFactory.openSession();
        Example example = Example.create(entity).ignoreCase();
        Criterion idCritere=getCritereId(entity);
        Criterion[] tab={example,idCritere};
        Criterion[] tab1={example};
        if(idCritere!=null) 
           tab1=tab;
        Conjunction restr= Restrictions.and(tab1);    
        List<T> results = session.createCriteria(entity.getClass()).add(restr).list();
        session.close();
        return results;
    }
    
    public <T> Criterion getCritereId(T entity) throws Exception{  
       Object idValue=getAttributeValue("id",entity);
       if(idValue!=null){
          return Restrictions.idEq(idValue);
       }
       return null;
    }
    public <T> List<T> paginateWhere (T entity, int offset, int size){
        Session session = sessionFactory.openSession();
        Example example = Example.create(entity).ignoreCase();
        List<T> results = session.createCriteria(entity.getClass())
                .add(example)
                .setFirstResult(offset)
                .setMaxResults(offset+size).list();
        session.close();
        return results;
    }

    public <T> List<T> paginate(Class<T> clazz, int offset, int size){
        Session session = sessionFactory.openSession();
        List<T> results = session.createCriteria(clazz)
                            .setFirstResult(offset)
                            .setMaxResults(offset+size).list();
        session.close();
        return results;
    }

    public <T> List<T> paginate(Class<T> clazz, int offset, int size, String orderBy, boolean orderAsc){
        Session session = sessionFactory.openSession();
        Order order = (orderAsc) ? Order.asc(orderBy) : Order.desc(orderBy);
        List<T> results = session.createCriteria(clazz)
                .addOrder(order)
                .setFirstResult(offset)
                .setMaxResults(offset+size) .list();
        session.close();
        return results;
    }

    public void deleteById(Class tClass, Serializable id){
        delete(findById(tClass, id));
    }

    public void delete(Object entity){
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.delete(entity);
        transaction.commit();
        session.close();
    }

    public <T> T update(T entity){
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.saveOrUpdate(entity);
        transaction.commit();
        session.close();
        return entity;
    }


    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
    public<T> List<T> recherche(T entity,String mot){
        Session session=null;
        List<T> list=null;
        try{
           session = sessionFactory.openSession();
           list=session.createCriteria(entity.getClass())
                   .add(
                           Restrictions.or(
                                   Restrictions.ilike("titre",mot, MatchMode.ANYWHERE),
                                   Restrictions.ilike("description",mot, MatchMode.ANYWHERE)
                           )
                   ).list();
        }
        catch(Exception e){
         e.printStackTrace();
        }
        return list;
    }
    public<T> List<T> recherchePublication(T entity){
        Timestamp now=Timestamp.valueOf(LocalDateTime.now());
        Session session=null;
        List<T> list=null;
        try{
           session = sessionFactory.openSession();
           list=session.createCriteria(entity.getClass())
                   .add(
                           Restrictions.and(
                                   Restrictions.lt("datePublication",now)
                           )
                   ).list();
        }
        catch(Exception e){
         e.printStackTrace();
        }
        return list;
    }
    public <T> List<T> paginatecherchePublication(T entity, int offset, int size){
        Timestamp now=Timestamp.valueOf(LocalDateTime.now());
        Session session=null;
        List<T> list=null;
        try{
           session = sessionFactory.openSession();
           list=session.createCriteria(entity.getClass())
                   .add(
                           Restrictions.and(
                                   Restrictions.lt("datePublication",now)          
                           )
                           
                   ).setFirstResult(offset)
                            .setMaxResults(offset+size).list();;
        }
        catch(Exception e){
         e.printStackTrace();
        }
        
        return list;
    }
    /////////////////////////////recherche uniquement conçu pour l'information/////
    public<T> List<T> findPublication(T entity,String attribute){
        Session session=null;
        List<T> list=null;
        try{
           session = sessionFactory.openSession();
           Criteria criteria = session.createCriteria(entity.getClass());
           criteria.addOrder(Order.desc(attribute));
           list=criteria.list();
                  
        }
        catch(Exception e){
         e.printStackTrace();
        }
        return list;
    }
    /////////////////////////////////////Indisponibilite acteur////////////////////////////////////////
     public<T> List<T> findByDate(T entity,Timestamp debut,Timestamp fin){
        Session session=null;
        List<T> list=null;
        try{
           session = sessionFactory.openSession();
           Criteria criteria = session.createCriteria(entity.getClass());
           criteria.add(
                           Restrictions.and(
                                   Restrictions.gt("debut", debut),
                                   Restrictions.lt("fin", fin)
                           )
                   );
           list=criteria.list();
                  
        }
        catch(Exception e){
         e.printStackTrace();
        }
        return list;
    }
    ///////////////////////////////quelques reflections////////////////////:
     public <T> Field getprimaryKey(T entity) {
		Field[] field=this.getClass().getDeclaredFields();
		for(Field f : field) if(f.isAnnotationPresent(Id.class)) return f;
		return null;
	}
     public <T> Object getAttributeValue(String attributeName,T entity) throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException{
	Method getter=HibernateDao.getGetter(attributeName,entity.getClass());
        try{
            return getter.invoke(entity);	
        }
        catch(Exception e){
            return null;
        }
	
    }
    public static Method  getGetter(String libellee,Class cla) throws NoSuchMethodException, SecurityException {
	String getterName=HibernateDao.getGetterName(libellee);
	return cla.getMethod(getterName);
    }
    public static String getGetterName(String libellee) {
 	String deb=libellee.substring(0,1);
	String tohiny=libellee.substring(1);
	return "get"+deb.toUpperCase()+tohiny;
			
    }
}
