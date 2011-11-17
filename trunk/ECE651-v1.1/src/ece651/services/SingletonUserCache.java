package ece651.services;

import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;


import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;

import ece651.dao.SystemUserDaoImpl;
import ece651.model.SystemUser;
//
//First implementation in Singleton Pattern 
//
public class SingletonUserCache {
	private static final Logger log = Logger.getLogger(SingletonUserCache.class.getName());
	private static CacheManager singletonManager;
	
	static {
		try {
			log.info("Init Cache...");
			singletonManager = CacheManager.create();
			Cache memoryOnlyCache = new Cache("userCache", 100, false, false, 6000, 6000);
			singletonManager.addCache(memoryOnlyCache);
			Cache userCache = singletonManager.getCache("userCache");

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

	public static CacheManager getSingletonManager() {
		log.info("Return singletonManager");
		return singletonManager;
	}

	public static void main(String[] args) {
		//for testing 
		log.info("calling SingletonUserCache.getInstance()");
		SingletonUserCache.getSingletonManager();
	}
}
