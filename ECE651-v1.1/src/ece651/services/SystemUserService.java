package ece651.services;

import java.util.ArrayList;
import java.util.List;


import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;


import ece651.dao.DAOException;
import ece651.dao.SystemUserDaoImpl;
import ece651.model.SystemUser;

public class SystemUserService {

	private static SystemUserService systemUserService = null;
	private static CacheManager singletonManager;
	private static Cache userCache;
	
	static {
		try {
			singletonManager = CacheManager.create();
			Cache memoryOnlyCache = new Cache("userCache", 100, false, false, 6000, 6000);
			singletonManager.addCache(memoryOnlyCache);
			userCache = singletonManager.getCache("userCache");

			SystemUserDaoImpl userdao = new SystemUserDaoImpl();
			List<SystemUser> userList = new ArrayList<SystemUser>();
			userList = userdao.searchAllUser();

			for (SystemUser user : userList) {
				if (user != null) {
					userCache.put(new Element(user.getUsername(), user));
					System.out.println("Put username: "+user.getUsername()+" in Cache");
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
			throw new ExceptionInInitializerError(e);
		}
	}

	private SystemUserService() {

	}

	public static synchronized SystemUserService getInstance() {
		if (null == systemUserService) {
			systemUserService = new SystemUserService();
		}
		return systemUserService;
	}

	public SystemUser searchUserByUsername(String username) throws DAOException {

		// get from Cache first
		Element element;
		System.out.println("Get username: " +username +" from Cache...");
		if ((element = userCache.get(username)) != null) {
			return (SystemUser) element.getValue();
		}

		// if not in Cache, get from database
		SystemUser user = null;
		SystemUserDaoImpl userdao = new SystemUserDaoImpl();
		System.out.println("Get username: "+ username +" from Database...");
		try {			
			if ((user = userdao.searchUserByUsername(username)) != null) {
				write2Cache(user);
			}
		} catch (Exception e) {
			throw new DAOException(e.getMessage());
		}

		return user;
	}
	
	private synchronized void write2Cache(SystemUser user) {
		if (user != null) {
			System.out.println("Put username: "+user.getUsername()+" in Cache...");
			userCache.put(new Element(user.getUsername(), user));			
		}		
	}
	public static void main(String[] args) {
		//for testing 
		try {
			SystemUser user= SystemUserService.getInstance().searchUserByUsername("David");
			System.out.println("User name: "+user.getUsername());
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
