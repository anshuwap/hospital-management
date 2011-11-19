package ece651.dao;

import java.util.List;

import ece651.model.SystemUser;
import ece651.dao.DAOException;

public interface SystemUserDao {
	public void cleanup();
	public void saveUser (SystemUser user) throws DAOException;
	public void updateUser(SystemUser user) throws DAOException;
	public List<SystemUser> searchAllUser() throws DAOException;
	public SystemUser searchUserBySystemUserId (int SystemUserId ) throws DAOException;
	public SystemUser searchUserByUsername ( String Username ) throws DAOException;
}
