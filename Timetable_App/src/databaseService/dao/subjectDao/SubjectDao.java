package databaseService.dao.subjectDao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import databaseService.beans.Subject;
import databaseService.dao.user.dao.ConnectionManager;

public class SubjectDao implements IntSubjectDao{

	@Override
	public boolean insertSubject(Subject sb) {
		ConnectionManager cm = ConnectionManager.getInstance();
		Connection c = null;
		try {
			c = cm.openConnection();
			String sql = "INSERT INTO SUBJECT (module,sub_module,type,abrev,color) VALUES ('"+sb.getSubjectModule()+"','"+sb.getSubjectSubmodule()+"','"+sb.getSubjectType()+"','"+sb.getSubjectAbrev()+"','"+sb.getSubjectColor()+"')";
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
	public boolean deleteSubject(Subject sb) {
		ConnectionManager cm = ConnectionManager.getInstance();
		Connection c = null;
		try {
			c = cm.openConnection();
			String sql = "DELETE FROM SUBJECT WHERE ID = '"+sb.getSubjectId()+"' ";
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
	public boolean updateSubject(Subject sb) {
		ConnectionManager cm = ConnectionManager.getInstance();
		Connection c = null;
		try {
			c = cm.openConnection();
			String sql = "UPDATE SUBJECT SET MODULE = '"+sb.getSubjectModule()+"' , SUB_MODULE = '"+sb.getSubjectSubmodule()+"' , TYPE = '"+sb.getSubjectType()+"' , ABREV = '"+sb.getSubjectAbrev()+"' , COLOR = '"+sb.getSubjectColor()+"' WHERE ID = '"+sb.getSubjectId()+"' ";
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
	public List<Subject> selectSubject(Subject sb) {
		ConnectionManager cm = ConnectionManager.getInstance();
		Connection c = null;
		List<Subject> list = new ArrayList<Subject>();
		try {
			c = cm.openConnection();
			String sql = "SELECT * FROM SUBJECT WHERE 1=1 ";
			if(sb.getSubjectId() != null) {
				sql+= " AND ID = " + sb.getSubjectId();
			}
			if(sb.getSubjectModule() != null) {
				sql+= " AND MODULE = '" + sb.getSubjectModule()+ "'";
			}
			if(sb.getSubjectSubmodule() != null) {
				sql+= " AND SUB_MODULE = '" + sb.getSubjectSubmodule()+ "'";
			}
			if(sb.getSubjectType() != null) {
				sql+= " AND TYPE = '" + sb.getSubjectType()+ "'";
			}
			if(sb.getSubjectAbrev() != null) {
				sql+= " AND ABREV = '" + sb.getSubjectAbrev()+ "'";
			}
			if(sb.getSubjectColor() != null) {
				sql+= " AND COLOR = '" + sb.getSubjectColor()+ "'";
			}
			
			Statement st = c.createStatement();
			ResultSet resultats = st.executeQuery(sql);
			while(resultats.next()) {
				Subject sbl = new Subject();
				sbl.setSubjectId(resultats.getLong("Id"));
				sbl.setSubjectModule(resultats.getString("module"));
				sbl.setSubjectSubmodule(resultats.getString("sub_module"));
				sbl.setSubjectType(resultats.getString("type"));
				sbl.setSubjectAbrev(resultats.getString("abrev"));
				sbl.setSubjectColor(resultats.getString("color"));
				
				list.add(sbl);
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
