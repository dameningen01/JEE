package databaseService.dao.lessonDao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import databaseService.beans.Lesson;
import databaseService.dao.user.dao.ConnectionManager;

public class LessonDao implements IntLessonDao{

	@Override
	public boolean insertLesson(Lesson ls) {
		ConnectionManager cm = ConnectionManager.getInstance();
		Connection c = null;
		try {
			c = cm.openConnection();
			String sql = "INSERT INTO LESSON (teacher_id, class_id, room_id, subject_id, timetable_id, total_lessons, lesson_occ, lesson_link, color) VALUES ('"+ls.getLessonTeacherFk()+"','"+ls.getLessonClassFk()+"','"+ls.getLessonRoomFk()+"','"+ls.getLessonSubjectFk()+"','"+ls.getLessonTimetableFk()+"','"+ls.getTotalLessons()+"','"+ls.getLessonOcc()+"','"+ls.getLessonLink()+"','"+ls.getLessonColor()+"')";
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
	public boolean deleteLesson(Lesson ls) {
		ConnectionManager cm = ConnectionManager.getInstance();
		Connection c = null;
		try {
			c = cm.openConnection();
			String sql = "DELETE FROM LESSON WHERE ID = '"+ls.getLessonId()+"' ";
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
	public boolean updateLesson(Lesson ls) {
		ConnectionManager cm = ConnectionManager.getInstance();
		Connection c = null;
		try {
			c = cm.openConnection();
			String sql = "UPDATE LESSON SET TEACHER_ID = '"+ls.getLessonTeacherFk()+"' , CLASS_ID = '"+ls.getLessonClassFk()+"' , ROOM_ID = '"+ls.getLessonRoomFk()+"' , SUBJECT_ID = '"+ls.getLessonSubjectFk()+"' , TIMETABLE_ID = '"+ls.getLessonTimetableFk()+"' , TOTAL_LESSONS = '"+ls.getTotalLessons()+"' , LESSON_OCC = '"+ls.getLessonOcc()+"' , LESSON_LINK = '"+ls.getLessonLink()+"' , COLOR = '"+ls.getLessonColor()+"' WHERE ID = '"+ls.getLessonId()+"' ";
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
	public List<Lesson> selectLesson(Lesson ls) {
		ConnectionManager cm = ConnectionManager.getInstance();
		Connection c = null;
		List<Lesson> list = new ArrayList<Lesson>();
		try {
			c = cm.openConnection();
			String sql = "SELECT * FROM LESSON WHERE 1=1 ";
			if(ls.getLessonId() != null) {
				sql+= " AND ID = '" + ls.getLessonId()+ "'";
			}
			if(ls.getLessonTeacherFk() != null) {
				sql+= " AND TEACHER_ID = '" + ls.getLessonTeacherFk()+ "'";
			}
			if(ls.getLessonClassFk() != null) {
				sql+= " AND CLASS_ID = '" + ls.getLessonClassFk()+ "'";
			}
			if(ls.getLessonRoomFk() != null) {
				sql+= " AND ROOM_ID = '" + ls.getLessonRoomFk()+ "'";
			}
			if(ls.getLessonSubjectFk() != null) {
				sql+= " AND SUBJECT_ID = '" + ls.getLessonSubjectFk()+ "'";
			}
			if(ls.getLessonTimetableFk() != null) {
				sql+= " AND TIMETABLE_ID = '" + ls.getLessonTimetableFk()+ "'";
			}
			if(ls.getTotalLessons() != 0) {
				sql+= " AND TOTAL_LESSONS = '" + ls.getTotalLessons()+ "'";
			}
			if(ls.getLessonOcc() != null) {
				sql+= " AND LESSON_OCC = '" + ls.getLessonOcc()+ "'";
			}
			if(ls.getLessonLink() != null) {
				sql+= " AND LESSON_Link = '" + ls.getLessonLink()+ "'";
			}
			if(ls.getLessonColor() != null) {
				sql+= " AND COLOR = '" + ls.getLessonColor()+ "'";
			}
			sql+= ";";
			System.out.println(sql);
			Statement st = c.createStatement();
			ResultSet resultats = st.executeQuery(sql);
			while(resultats.next()) {
				Lesson lsl = new Lesson();
				lsl.setLessonId(resultats.getLong("Id"));
				lsl.setLessonTeacherFk(resultats.getLong("teacher_id"));
				lsl.setLessonClassFk(resultats.getLong("class_id"));
				lsl.setLessonRoomFk(resultats.getLong("room_id"));
				lsl.setLessonSubjectFk(resultats.getLong("subject_id"));
				lsl.setLessonTimetableFk(resultats.getLong("timetable_id"));
				lsl.setTotalLessons(resultats.getInt("total_lessons"));
				lsl.setLessonOcc(resultats.getString("lesson_occ"));
				lsl.setLessonLink(resultats.getString("lesson_link"));
				lsl.setLessonColor(resultats.getString("color"));
				
				list.add(lsl);
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

	@Override
	public List<Lesson> selectUniqueLessonTimetable(Lesson ls) {
		ConnectionManager cm = ConnectionManager.getInstance();
		Connection c = null;
		List<Lesson> list = new ArrayList<Lesson>();
		try {
			c = cm.openConnection();
			String sql = "SELECT DISTINCT TIMETABLE_ID FROM LESSON WHERE 1=1 ";
			if(ls.getLessonTeacherFk() != null) {
				sql+= " AND TEACHER_ID = " + ls.getLessonTeacherFk();
			}
			if(ls.getLessonClassFk() != null) {
				sql+= " AND CLASS_ID = " + ls.getLessonClassFk();
			}
			
			Statement st = c.createStatement();
			ResultSet resultats = st.executeQuery(sql);
			while(resultats.next()) {
				Lesson lsl = new Lesson();
				lsl.setLessonTimetableFk(resultats.getLong("timetable_id"));
				
				list.add(lsl);
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
	
	

	@SuppressWarnings("null")
	@Override
	public ArrayList<String> selectDetailsLesson(Lesson ls) {
		String s1 = null;
		String s2 = null;
		String s3 = null;
		String s4 = null;
		String s5 = null;
		String s6 = null;
		String s7 = null;
		
		ConnectionManager cm = ConnectionManager.getInstance();
		Connection c = null;
		ArrayList<String> lr = new ArrayList<String>();
		try {
			c = cm.openConnection();
			String sql = "SELECT l.id, l.teacher_id, t.name, l.class_id, concat (f.year, f.abrev, c.group_num) as info , l.room_id, r.abrev, l.subject_id, s.abrev, l.total_lessons, l.timetable_id, l.lesson_occ, l.lesson_link, l.color, c.free_time, t.free_time, c.color FROM  lesson l, teacher t, class c, faculty f, room r, subject s, timetable tb WHERE  l.teacher_id = t.id AND l.class_id = c.id AND c.faculty_id = f.id AND  l.room_id = r.id AND l.subject_id = s.id AND l.timetable_id = tb.id ; ";
			
			Statement st = c.createStatement();
			ResultSet resultats = st.executeQuery(sql);
			
			while(resultats.next()) {
				s1.substring((int) resultats.getLong("l.id"));
				s2.substring((int) resultats.getLong("l.teacher_id"));
				s3.substring((int)resultats.getLong("l.class_id"));
				s4.substring((int)resultats.getLong("l.room_id"));
				s5.substring((int)resultats.getLong("l.subject_id"));
				s6.substring((int)resultats.getLong("l.timetable_id"));
				s7.substring((int)resultats.getInt("l.total_lessons"));
				
				lr.add(s1);
				lr.add(s2);
				lr.add(s3);
				lr.add(s4);
				lr.add(s5);
				lr.add(s6);
				lr.add(s7);
				lr.add(resultats.getString("l.lesson_occ"));
				lr.add(resultats.getString("l.lesson_link"));
				lr.add(resultats.getString("l.color"));
				lr.add(resultats.getString("t.name"));
				lr.add(resultats.getString("r.abrev"));
				lr.add(resultats.getString("s.abrev"));
				lr.add(resultats.getString("info"));
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("statement failed to parse");
			e.printStackTrace();
		}finally {
			cm.closeConnection(c);
		}
		return lr;
	}

}
