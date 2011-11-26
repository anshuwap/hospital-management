package ece651.unittest.dao;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import junit.framework.TestCase;
import static org.easymock.classextension.EasyMock.*;
import org.easymock.classextension.IMocksControl;

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
	
	public void testSearchUser2() throws DAOException{
		SystemUserDaoImpl userdao = new SystemUserDaoImpl();
		SystemUser user = userdao.searchUserBySystemUserId(1);
		assertNotNull(user);
		assertEquals("jzhang", user.getUsername());
	}
	
	public void testSearchUserByRole() throws DAOException{
		SystemUserDaoImpl userdao = new SystemUserDaoImpl();
		List<SystemUser> userList = new ArrayList<SystemUser>();
		userList = userdao.SearchUserByRole("D");
		assertNotSame(0, userList.size());
	}
	
	public void testSearchAllUser() throws DAOException{
		SystemUserDaoImpl userdao = new SystemUserDaoImpl();
		List<SystemUser> userList = new ArrayList<SystemUser>();
		userList = userdao.searchAllUser();
		assertNotSame(0, userList.size());
	}
	
	public void testSaveUser() throws DAOException{
		SystemUser user = new SystemUser();
		user.setUsername("w1Zheng");
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
		SystemUser userdb = userdao.searchUserByUsername("w1Zheng");
		assertNotNull(userdb);
		assertEquals("w1Zheng", userdb.getUsername());		
	}
	
	public void testUpdateUser() throws DAOException{	
		SystemUserDaoImpl userdao = new SystemUserDaoImpl();
		SystemUser userdb = userdao.searchUserByUsername("w1Zheng");
		
		assertNotNull(userdb);
		Date birthday = Date.valueOf("1985-02-02");
		userdb.setBirthday(birthday);
		userdao.updateUser(userdb);
		
		SystemUser userdb2 = userdao.searchUserByUsername("w1Zheng");
		assertNotNull(userdb2);
		assertEquals(birthday.toString(), userdb2.getBirthday().toString());
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

	public void testSearchUserFail() {
		SystemUserDaoImpl userdao = new SystemUserDaoImpl();
		try {			
			userdao.getSession().close();
			SystemUser user = userdao.searchUserByUsername("jzhang");
			fail();
		} catch (DAOException e) {
			assertNotNull(e.getMessage());
		}
	}	
	
	public void testSearchUser2Fail(){
		SystemUserDaoImpl userdao = new SystemUserDaoImpl();
		try {			
			userdao.getSession().close();
			SystemUser user = userdao.searchUserBySystemUserId(1);
			fail();
		} catch (DAOException e) {
			assertNotNull(e.getMessage());
		}
	}

	public void testSaveUserFail() {
		SystemUser user = new SystemUser();
		user.setUsername("jzhang");
		user.setFirstName("Jianxin");
		user.setLastName("Zhang");
		user.setPassword("1234");
		user.setGender("M");
		user.setRoleType("D");
		Date birthday = Date.valueOf("1900-01-01");
		user.setBirthday(birthday);
		user.setEmail("wyzhang@uwaterloo.ca");
		user.setPhone("519-8888888");
		user.setSin("123-888-999");
		user.setActive("A");		
		SystemUserDaoImpl userdao = new SystemUserDaoImpl();
		try {
			userdao.saveUser(user);
			fail();
		} catch (DAOException e) {
			assertNotNull(e.getMessage());
		}
	}
	
	public void testUpdateUserFail(){	
		SystemUserDaoImpl userdao = new SystemUserDaoImpl();
		SystemUser user = new SystemUser();
		user.setUsername("jzhang");
		user.setFirstName("Jianxin");
		user.setLastName("Zhang");
		user.setPassword("1234");
		user.setGender("M");
		user.setRoleType("D");
		Date birthday = Date.valueOf("1900-01-01");
		user.setBirthday(birthday);
		user.setEmail("wyzhang@uwaterloo.ca");
		user.setPhone("519-8888888");
		user.setSin("123-888-999");
		user.setActive("A");
		try {
			birthday = Date.valueOf("1900-01-02");
			user.setBirthday(birthday);
			userdao.updateUser(user);
			fail();
		} catch (DAOException e) {
			assertNotNull(e.getMessage());
		}		
	}

	public void testDeleteUser() throws DAOException{	
		SystemUserDaoImpl userdao = new SystemUserDaoImpl();
		SystemUser userdb = userdao.searchUserByUsername("w1Zheng");
		
		assertNotNull(userdb);
		userdao.deleteUser(userdb);
		
		SystemUser userdb2 = userdao.searchUserByUsername("w1Zheng");
		assertNull(userdb2);
	}
	
	
	public void testDeleteUserFail(){	
		SystemUserDaoImpl userdao = new SystemUserDaoImpl();
		SystemUser user = new SystemUser();
		user.setUsername("jzhang");
		user.setFirstName("Jianxin");
		user.setLastName("Zhang");
		user.setPassword("1234");
		user.setGender("M");
		user.setRoleType("D");
		Date birthday = Date.valueOf("1900-01-01");
		user.setBirthday(birthday);
		user.setEmail("wyzhang@uwaterloo.ca");
		user.setPhone("519-8888888");
		user.setSin("123-888-999");
		user.setActive("A");
		//create mock objects
		IMocksControl control;
		control = createNiceControl();
		Session session = control.createMock(Session.class);
		Transaction tran = control.createMock(Transaction.class);
		//training mock objects
		session.beginTransaction();
		expectLastCall().andReturn(tran);
		tran.begin();
		expectLastCall();
		session.delete((SystemUser) notNull());
		expectLastCall();
		tran.commit();
		expectLastCall().andThrow(new HibernateException("mockTest"));
		tran.rollback();
		expectLastCall();
		//replay
		control.replay();
		//call Dao method
		try {			
			userdao.setSession(session);
			userdao.deleteUser(user);
			fail();
		} catch (DAOException e) {
			assertNotNull(e.getMessage());
			assertEquals("mockTest", e.getMessage());
		}
	}
	
	public void testSearchUserByRoleFail(){	
		SystemUserDaoImpl userdao = new SystemUserDaoImpl();
		//create mock objects
		IMocksControl control;
		control = createNiceControl();
		Session session = control.createMock(Session.class);
		//training mock objects
		session.createQuery((String) notNull());
		expectLastCall().andThrow(new HibernateException("mockTest"));
		//replay
		control.replay();
		//call Dao method
		try {			
			userdao.setSession(session);
			userdao.SearchUserByRole("D");
			fail();
		} catch (DAOException e) {
			assertNotNull(e.getMessage());
			assertEquals("mockTest", e.getMessage());
		}
	}
	
	public void testSearchAllUserFail(){	
		SystemUserDaoImpl userdao = new SystemUserDaoImpl();
		//create mock objects
		IMocksControl control;
		control = createNiceControl();
		Session session = control.createMock(Session.class);
		//training mock objects
		session.createQuery((String) notNull());
		expectLastCall().andThrow(new HibernateException("mockTest"));
		//replay
		control.replay();
		//call Dao method
		try {			
			userdao.setSession(session);
			userdao.searchAllUser();
			fail();
		} catch (DAOException e) {
			assertNotNull(e.getMessage());
			assertEquals("mockTest", e.getMessage());
		}
	}
}
