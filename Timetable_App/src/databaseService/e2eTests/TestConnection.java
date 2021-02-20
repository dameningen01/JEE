package databaseService.e2eTests;

import java.sql.Connection;

import databaseService.dao.user.dao.ConnectionManager;


public class TestConnection {
	public static void main(String[] args) {
		 ConnectionManager cm = ConnectionManager.getInstance();
		 Connection c = cm.openConnection();
		 System.out.println(c);
	}
}
