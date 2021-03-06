package ece651.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Collections;

import org.apache.log4j.Logger;

import net.sf.ehcache.Cache;
import net.sf.ehcache.Element;

import ece651.dao.DAOException;
import ece651.dao.SystemUserDao;
import ece651.dao.SystemUserDaoImpl;
import ece651.model.SystemUser;

public class SystemUserService {
	private static final Logger log = Logger.getLogger(SystemUserService.class);
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
		SystemUserDao userdao = new SystemUserDaoImpl();
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
		finally{
			userdao.cleanup();
		}

		return user;
	}
	
	public void saveUser (SystemUser user) throws Exception {
		SystemUserDao userdao = new SystemUserDaoImpl();
		try {			
			userdao.saveUser(user);
			log.info("Put username: "+user.getUsername()+" in Cache...");
			userCache.put(new Element(user.getUsername(), user));
		} catch (DAOException e) {
			throw new DAOException(e.getMessage());
		}
		catch(Exception ex){
			throw new Exception(ex.getMessage());
		}
		finally{
			userdao.cleanup();
		}
	}
	
	public void updateUser(SystemUser user) throws Exception {
		SystemUserDao userdao = new SystemUserDaoImpl();
		try {				
			userdao.updateUser(user);
			log.info("Put username: "+user.getUsername()+" in Cache...");
			userCache.put(new Element(user.getUsername(), user));
		} catch (DAOException e) {
			throw new DAOException(e.getMessage());
		}
		catch(Exception ex){
			throw new Exception(ex.getMessage());
		}
		finally{
			userdao.cleanup();
		}
	}

	public SystemUser searchUserBySystemUserId(int systemUserId) throws Exception {

		// get from Cache first
		log.info("Search systemUserId: " +systemUserId +" from Cache...");
		List<String> keyList = new ArrayList<String>();
		keyList = userCache.getKeys();
		for (String meKey: keyList) {
			Element element;
			if ((element = userCache.get(meKey)) != null && 
				((SystemUser)element.getValue()).getSystemUserId()==systemUserId) {
				return (SystemUser)element.getValue();
			}
		}
		
		// if not in Cache, get from database
		log.info("Can't get systemUserId: " +systemUserId +" from Cache");
		SystemUser user = null;
		SystemUserDao userdao = new SystemUserDaoImpl();
		log.info("Search systemUserId: "+ systemUserId +" from Database...");
		try {			
			if ((user = userdao.searchUserBySystemUserId(systemUserId)) != null) {
				log.info("Put username: "+user.getUsername()+" in Cache...");
				userCache.put(new Element(user.getUsername(), user));	
			}
		} catch (DAOException e) {
			throw new DAOException(e.getMessage());
		}
		catch(Exception ex){
			throw new Exception(ex.getMessage());
		}
		finally{
			userdao.cleanup();
		}

		return user;
	}
	
	public List<SystemUser> searchAllUser() throws Exception {
		
		// get from Cache 
		log.info("Search all users from Cache...");
		List<SystemUser> userList = new ArrayList<SystemUser>();
		List<String> keyList = new ArrayList<String>();
		keyList = userCache.getKeys();
		
		for (String eKey: keyList) {
			Element element;
			if ((element = userCache.get(eKey)) != null) {
				userList.add((SystemUser)element.getValue());
			}
		}	
		UserComparator comparator=new UserComparator();
		Collections.sort(userList, comparator);
		return userList;
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
