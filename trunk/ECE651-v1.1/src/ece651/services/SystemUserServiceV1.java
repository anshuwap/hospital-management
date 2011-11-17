package ece651.services;

import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;


import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;

import ece651.dao.DAOException;
import ece651.dao.SystemUserDaoImpl;
import ece651.model.SystemUser;
//
//First implementation in Singleton Pattern 
//
public class SystemUserServiceV1 {
	private static final Logger log = Logger.getLogger(SystemUserServiceV1.class.getName());
	private static SystemUserServiceV1 systemUserService = null;
	private static CacheManager singletonManager;
	private static Cache userCache;
	
	static {
		try {
			log.info("Init Cache...");
			singletonManager = CacheManager.create();
			Cache memoryOnlyCache = new Cache("userCache", 100, false, false, 6000, 6000);
			singletonManager.addCache(memoryOnlyCache);
			userCache = singletonManager.getCache("userCache");

			SystemUserDaoImpl userdao = new SystemUserDaoImpl();
			List<SystemUser> userList = new ArrayList<SystemUser>();
			userList = userdao.searchAllUser();

			log.info("Load all users into Cache...");
			for (SystemUser user : userList) {
				if (user != null) {
					log.info("Put username: "+user.getUsername()+" in Cache...");
					userCache.put(new Element(user.getUsername(), user));					
				}
			}
			log.info("Done");
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new ExceptionInInitializerError(e);
		}
	}

	private SystemUserServiceV1() {
		log.info(SystemUserServiceV1.class.getName());
	}

	public static synchronized SystemUserServiceV1 getInstance() {
		if (null == systemUserService) {
			systemUserService = new SystemUserServiceV1();
		}
		return systemUserService;
	}

	public SystemUser searchUserByUsername(String username) throws DAOException {

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
		} catch (Exception e) {
			throw new DAOException(e.getMessage());
		}

		return user;
	}
	
	private synchronized void write2Cache(SystemUser user) {
		if (user != null) {
			log.info("Put username: "+user.getUsername()+" in Cache...");
			userCache.put(new Element(user.getUsername(), user));			
		}		
	}
	public static void main(String[] args) {
		//for testing 
		try {
			SystemUser user= SystemUserServiceV1.getInstance().searchUserByUsername("David");
			log.info("User name: "+user.getUsername());
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
