package databaseService.dao.facultyDao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import databaseService.beans.Faculty;
import databaseService.dao.user.dao.ConnectionManager;

public class FacultyDao implements IntFacultyDao{

	@Override
	public boolean insertFaculty(Faculty f) {
		ConnectionManager cm = ConnectionManager.getInstance();
		Connection c = null;
		try {
			c = cm.openConnection();
			String sql = "INSERT INTO FACULTY (name,abrev,year)VALUES ('"+f.getFacultyName()+"','"+f.getFacultyAbrev()+"','"+f.getFacultyYear()+"')";
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
	public boolean deleteFaculty(Faculty f) {
		ConnectionManager cm = ConnectionManager.getInstance();
		Connection c = null;
		try {
			c = cm.openConnection();
			String sql = "DELETE FROM FACULTY WHERE ID = '"+f.getFacultyId()+"' ";
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
	public boolean updateFaculty(Faculty f) {
		ConnectionManager cm = ConnectionManager.getInstance();
		Connection c = null;
		try {
			c = cm.openConnection();
			String sql = "UPDATE FACULTY SET NAME = '"+f.getFacultyName()+"' , ABREV = '"+f.getFacultyAbrev()+"' , YEAR = '"+f.getFacultyYear()+"' WHERE ID = '"+f.getFacultyId()+"' ";
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
	public List<Faculty> selectFaculty(Faculty f) {
		ConnectionManager cm = ConnectionManager.getInstance();
		Connection c = null;
		List<Faculty> list = new ArrayList<Faculty>();
		try {
			c = cm.openConnection();
			String sql = "SELECT * FROM FACULTY WHERE 1=1 ";
			if(f.getFacultyId() != null) {
				sql+= " AND ID = " + f.getFacultyId();
			}
			if(f.getFacultyName() != null) {
				sql+= " AND NAME = '" + f.getFacultyName()+ "'";
			}
			if(f.getFacultyAbrev() != null) {
				sql+= " AND ABREV = '" + f.getFacultyAbrev()+ "'";
			}
			if(f.getFacultyYear() != 0 ) {
				sql+= " AND Year = '" + f.getFacultyYear()+ "'";
			}
			
			Statement st = c.createStatement();
			ResultSet resultats = st.executeQuery(sql);
			while(resultats.next()) {
				Faculty fl = new Faculty();
				fl.setFacultyId(resultats.getLong("Id"));
				fl.setFacultyName(resultats.getString("name"));
				fl.setFacultyAbrev(resultats.getString("abrev"));
				fl.setFacultyYear(resultats.getInt("year"));
				
				list.add(fl);
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
