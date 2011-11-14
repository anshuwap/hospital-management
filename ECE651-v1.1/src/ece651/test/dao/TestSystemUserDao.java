package ece651.test.dao;

import java.sql.Date;

import junit.framework.TestCase;

import ece651.dao.DAOException;
import ece651.dao.SystemUserDaoImpl;
import ece651.model.SystemUser;

public class TestSystemUserDao extends TestCase {
	
	public void testSearchUser() throws DAOException{
		SystemUserDaoImpl userdao = new SystemUserDaoImpl();
		SystemUser user = userdao.searchUserByUsername("jzhang");
		assertNotNull(user);
		assertEquals("jzhang", user.getUsername());
		//System.out.println("searchUser is: "+user);
	}
	
	public void testSearchUserFail() {
		try {
			SystemUserDaoImpl userdao = new SystemUserDaoImpl();
			userdao.cleanup();		
			SystemUser user = userdao.searchUserByUsername("jzhang");
		} catch (DAOException e) {
			assertNotNull(e.getMessage());
		}

	}	

	public void testSearchUser2() throws DAOException{
		SystemUserDaoImpl userdao = new SystemUserDaoImpl();
		SystemUser user = userdao.searchUserBySystemUserId(1);
		assertNotNull(user);
		assertEquals("jzhang", user.getUsername());
	}
	
	public void testSaveUser() throws DAOException{
		SystemUser user = new SystemUser();
		user.setUsername("wZheng");
		user.setFirstName("Weiyi");
		user.setLastName("Zheng");
		user.setPassword("1234");
		user.setGender("M");
		user.setRoleType("D");
		Date birthday = Date.valueOf("1985-01-01");
		user.setBirthday(birthday);
		user.setEmail("wyzhang@uwaterloo.ca");
		user.setPhone("519-8888888");
		user.setSin("123-888-999");
		user.setActive("A");		
		SystemUserDaoImpl userdao = new SystemUserDaoImpl();
		userdao.saveUser(user);
		SystemUser userdb = userdao.searchUserByUsername("wZheng");
		assertNotNull(userdb);
		assertEquals("wZheng", userdb.getUsername());		
	}
	
	public void testUpdateUser() throws DAOException{	
		SystemUserDaoImpl userdao = new SystemUserDaoImpl();
		SystemUser userdb = userdao.searchUserByUsername("wZheng");
		
		assertNotNull(userdb);
		Date birthday = Date.valueOf("1985-02-02");
		userdb.setBirthday(birthday);
		userdao.updateUser(userdb);
		
		SystemUser userdb2 = userdao.searchUserByUsername("wZheng");
		assertNotNull(userdb2);
		assertEquals(birthday.toString(), userdb2.getBirthday().toString());
	}
	
	public void testDeleteUser() throws DAOException{	
		SystemUserDaoImpl userdao = new SystemUserDaoImpl();
		SystemUser userdb = userdao.searchUserByUsername("wZheng");
		
		assertNotNull(userdb);
		userdao.deleteUser(userdb);
		
		SystemUser userdb2 = userdao.searchUserByUsername("wZheng");
		assertNull(userdb2);
	}
	
	public void testGetSession() throws DAOException{	
		SystemUserDaoImpl userdao = new SystemUserDaoImpl();
		assertNotNull(userdao.getSession());
	}
	
	public void testCleanup() throws DAOException{	
		SystemUserDaoImpl userdao = new SystemUserDaoImpl();
		userdao.cleanup();
		assertFalse(userdao.getSession().isOpen());
	}
}
