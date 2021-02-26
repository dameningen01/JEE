package databaseService.dao.lessonDao;

import java.util.ArrayList;
import java.util.List;
import java.util.ArrayList;

import databaseService.beans.Lesson;

public interface IntLessonDao {
	public boolean insertLesson(Lesson ls);
	public boolean deleteLesson(Lesson ls);
	public boolean updateLesson(Lesson ls);
	public List<Lesson> selectLesson(Lesson ls);
	public List<Lesson> selectUniqueLessonTimetable(Lesson ls);
	public ArrayList<String> selectDetailsLesson(Lesson ls);
}
