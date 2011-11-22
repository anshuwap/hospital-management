package ece651.services;

import java.util.Comparator;

import ece651.model.SystemUser;

public class UserComparator implements Comparator<Object> {

	public int compare(Object arg0, Object arg1) {
		SystemUser user0 = (SystemUser) arg0;
		SystemUser user1 = (SystemUser) arg1;
		
		return user0.getSystemUserId().compareTo(user1.getSystemUserId());
		
	}

}