package com.emart.session;

import java.util.ArrayList;
import java.util.List;
import com.emart.accessData.*;

public class EmartSession {
	
	private static EmartSession emartSession = new EmartSession();
	
	private List<User> activeUsers = new ArrayList<User>();
	
	public List<User> getActiveUsers() {
		return activeUsers;
	}

	public void setActiveUsers(List<User> activeUsers) {
		this.activeUsers = activeUsers;
	}

	private EmartSession() {
		
	};
	
	public static EmartSession getInstance() {
		return emartSession;
	}

}
