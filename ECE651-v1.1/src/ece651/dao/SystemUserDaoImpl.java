package ece651.dao;

import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import ece651.dao.HibernateUtil;
import ece651.dao.DAOException;
import ece651.model.SystemUser;

public class SystemUserDaoImpl implements SystemUserDao {

	Logger log = Logger.getLogger(getClass().toString());
	
	private Session session; 
	
	public void setSession(Session session) {
		this.session = session;
	}

	public Session getSession()
	{
		return this.session;
	}

	public void cleanup(){
		if (session != null) session.close();
		HibernateUtil.shutdown();
	}

	public SystemUserDaoImpl(){
		log.info(getClass().toString());
		this.session = HibernateUtil.getSessionFactory().openSession(); 
	}

	public SystemUser searchUserBySystemUserId(int SystemUserId)
			throws DAOException {
		SystemUser user;
		try{
			user = (SystemUser)session.get(SystemUser.class, SystemUserId);
		}catch (HibernateException e) {
			throw new DAOException(e.getMessage());
		}

		return user;
	}

	public SystemUser searchUserByUsername(String Username) throws DAOException {
		SystemUser user = null;
		try{
			String hql ="select user from SystemUser as user where user.username=?";
			Query q =session.createQuery(hql);
			q.setString(0, Username);
			if( q.list().size()>0){
				user = (SystemUser)q.list().get(0);
			}
		}catch (HibernateException e) {
			throw new DAOException(e.getMessage());
		}
		return user;
	}

	public List<SystemUser> SearchUserByRole(String roleType) throws DAOException {
		List<SystemUser> userList = new ArrayList<SystemUser>();
		try{
			Query q = session.createQuery("select user from SystemUser as user where user.roleType=?");
			q.setString(0, roleType);
			userList = q.list();
		}catch (HibernateException e) {
			throw new DAOException(e.getMessage());
		}
		return userList;
	}
	
	public List<SystemUser> searchAllUser() throws DAOException {
		List<SystemUser> userList = new ArrayList<SystemUser>();
		try{
			Query q = session.createQuery("from SystemUser");
			userList = q.list();
		}catch (HibernateException e) {
			throw new DAOException(e.getMessage());
		}
		return userList;
	}
	
	public void saveUser (SystemUser user) throws DAOException {
		Transaction tran = null;
		try{
			tran = session.beginTransaction();
			tran.begin();
			session.save(user);
			tran.commit();
		}catch (HibernateException e) {
			tran.rollback();
			throw new DAOException(e.getMessage());
		}
	}
	
	public void updateUser(SystemUser user) throws DAOException {
		Transaction tran = null;
		try{
			tran = session.beginTransaction();
			tran.begin();
			session.update(user);
			tran.commit();
		}catch (HibernateException e) {
			tran.rollback();
			throw new DAOException(e.getMessage());
		}
	}
	
	public void deleteUser(SystemUser user) throws DAOException {
		Transaction tran = null;
		try{
			tran = session.beginTransaction();
			tran.begin();
			session.delete(user);
			tran.commit();
		}catch (HibernateException e) {
			tran.rollback();
			throw new DAOException(e.getMessage());
		}
	}

}
