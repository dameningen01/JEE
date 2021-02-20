package databaseService.dao.user.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import databaseService.beans.User;

public class UserDao implements IntUserDao{

	@Override
	public boolean insertUser(User u) {
		ConnectionManager cm = ConnectionManager.getInstance();
		Connection c = null;
		try {
			c = cm.openConnection();
			String sql = "INSERT INTO USERS (Username,password,user_type)VALUES ('"+u.getUsername()+"','"+u.getPassword()+"','"+u.getUserType()+"')";
			Statement st = c.createStatement();
			st.execute(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("statement failed to parse");
			e.printStackTrace();
		}finally {
			cm.closeConnection(c);
		}
		return false;
	}

	@Override
	public boolean deleteUser(User u) {
		ConnectionManager cm = ConnectionManager.getInstance();
		Connection c = null;
		try {
			c = cm.openConnection();
			String sql = "DELETE FROM USERS WHERE ID = '"+u.getUserID()+"' ";
			Statement st = c.createStatement();
			st.execute(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("statement failed to parse");
			e.printStackTrace();
		}finally {
			cm.closeConnection(c);
		}
		return false;
	}

	@Override
	public boolean updateUser(User u) {
		ConnectionManager cm = ConnectionManager.getInstance();
		Connection c = null;
		try {
			c = cm.openConnection();
			String sql = "UPDATE USERS SET USERNAME = '"+u.getUsername()+"' , USER_TYPE = '"+u.getUserType()+"' WHERE ID = '"+u.getUserID()+"' ";
			Statement st = c.createStatement();
			st.execute(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("statement failed to parse");
			e.printStackTrace();
		}finally {
			cm.closeConnection(c);
		}
		return false;
	}

	@Override
	public List<User> selectUser(User u) {
		ConnectionManager cm = ConnectionManager.getInstance();
		Connection c = null;
		List<User> list = new ArrayList<User>();
		try {
			c = cm.openConnection();
			String sql = "SELECT * FROM USERS WHERE 1=1 ";
			if(u.getUserID() != null) {
				sql+= " AND ID = " + u.getUserID();
			}
			if(u.getUsername() != null) {
				sql+= " AND USERNAME = '" + u.getUsername()+ "'";
			}
			if(u.getPassword() != null) {
				sql+= " AND PASSWORD = '" + u.getPassword()+ "'";
			}
			if(u.getUserType() != null) {
				sql+= " AND USER_TYPE = '" + u.getUserType()+ "'";
			}
			
			Statement st = c.createStatement();
			ResultSet resultats = st.executeQuery(sql);
			while(resultats.next()) {
				User ul = new User();
				ul.setId(resultats.getLong("Id"));
				ul.setUsername(resultats.getString("username"));
				ul.setPassword(resultats.getString("password"));
				ul.setUserType(resultats.getString("user_type"));
				
				list.add(ul);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("statement failed to parse");
			e.printStackTrace();
		}finally {
			cm.closeConnection(c);
		}
		return list;
	}

}
