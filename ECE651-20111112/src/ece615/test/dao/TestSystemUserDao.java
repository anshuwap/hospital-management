package ece615.test.dao;

import junit.framework.TestCase;

import ece651.dao.DAOException;
import ece651.dao.SystemUserDaoImpl;
import ece651.model.SystemUser;

public class TestSystemUserDao extends TestCase {
	
	public void testsearchUser() throws DAOException{
		SystemUserDaoImpl userdao = new SystemUserDaoImpl();
		SystemUser user = userdao.searchUserByUsername("jzhang");
		System.out.println("searchUser is: "+user);
	}
	

	public void testsearchUser2() throws DAOException{
		SystemUserDaoImpl userdao = new SystemUserDaoImpl();
		SystemUser user = userdao.searchUserBySystemUserId(1);
		System.out.println("searchUser is: "+user);
	}
}
