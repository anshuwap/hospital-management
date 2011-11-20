package ece651.dao;

import java.util.List;

import org.hibernate.Session;

import ece651.model.SystemUser;
import ece651.dao.DAOException;

public interface SystemUserDao {
	public Session getSession();
	public void cleanup();
	public SystemUser searchUserBySystemUserId (int SystemUserId ) throws DAOException;
	public SystemUser searchUserByUsername ( String Username ) throws DAOException;
	public List<SystemUser> searchAllUser() throws DAOException;
	public List<SystemUser> SearchUserByRole(String roleType) throws DAOException;
	public void saveUser (SystemUser user) throws DAOException;
	public void updateUser(SystemUser user) throws DAOException;
	public void deleteUser(SystemUser user) throws DAOException;
}