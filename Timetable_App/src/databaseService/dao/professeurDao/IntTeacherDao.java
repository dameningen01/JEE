package databaseService.dao.professeurDao;

import java.util.List;

import databaseService.beans.Teacher;

public interface IntTeacherDao {
	public boolean insertTeacher(Teacher p);
	public boolean deleteTeacher(Teacher p);
	public boolean updateTeacher(Teacher p);
	public List<Teacher> selectTeacher(Teacher p);

}
