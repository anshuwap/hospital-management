package ece651.dao;

import ece651.model.SystemUser;
import ece651.dao.DAOException;

public interface SystemUserDao {
	public void cleanup();
	public SystemUser searchUserBySystemUserId (int SystemUserId ) throws DAOException;
	public SystemUser searchUserByUsername ( String Username ) throws DAOException;
}
