package ece651.services;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;

import net.sf.ehcache.Cache;
import net.sf.ehcache.Element;

import ece651.dao.DAOException;
import ece651.dao.SystemUserDaoImpl;
import ece651.model.SystemUser;

public class SystemUserService {
	private Logger log = Logger.getLogger(SystemUserService.class.getName());
	private Cache userCache;
	
	public SystemUserService() {
		log.info(SystemUserService.class.getName());
		this.userCache = SingletonUserCache.getSingletonManager().getCache("userCache");
	}

	public SystemUser searchUserByUsername(String username) throws Exception {

		// get from Cache first
		Element element;
		log.info("Search username: " +username +" from Cache...");
		if ((element = userCache.get(username)) != null) {
			return (SystemUser) element.getValue();
		}
		log.info("Can't get username: " +username +" from Cache");
		
		// if not in Cache, get from database
		SystemUser user = null;
		SystemUserDaoImpl userdao = new SystemUserDaoImpl();
		log.info("Search username: "+ username +" from Database...");
		try {			
			if ((user = userdao.searchUserByUsername(username)) != null) {
				log.info("Put username: "+user.getUsername()+" in Cache...");
				userCache.put(new Element(user.getUsername(), user));	
			}
		} catch (DAOException e) {
			throw new DAOException(e.getMessage());
		}
		catch(Exception ex){
			throw new Exception(ex.getMessage());
		}

		return user;
	}
	
	public void saveUser (SystemUser user) throws Exception {
		try {	
			SystemUserDaoImpl userdao = new SystemUserDaoImpl();
			userdao.saveUser(user);
			userCache.put(new Element(user.getUsername(), user));
		} catch (DAOException e) {
			throw new DAOException(e.getMessage());
		}
		catch(Exception ex){
			throw new Exception(ex.getMessage());
		}
	}
	
	public void updateUser(SystemUser user) throws Exception {
		try {	
			SystemUserDaoImpl userdao = new SystemUserDaoImpl();
			userdao.updateUser(user);
			userCache.put(new Element(user.getUsername(), user));
		} catch (DAOException e) {
			throw new DAOException(e.getMessage());
		}
		catch(Exception ex){
			throw new Exception(ex.getMessage());
		}
	}
	public static void main(String[] args) {
		//for testing 
		try {
			SystemUser user= (new SystemUserService()).searchUserByUsername("David");
			System.out.print("User name: "+user.getUsername());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
