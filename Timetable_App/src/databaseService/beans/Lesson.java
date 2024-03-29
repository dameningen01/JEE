package databaseService.beans;

public class Lesson implements java.io.Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long id;
	private Long teacher_fk;
	private Long class_fk;
	private Long room_fk ;
	private Long  subject_fk;
	private Long timetable_fk;
	private int total_lessons;
	private String lesson_occ;
	private String lesson_link;
	private String color;
	
	//Details select fields
	private String teacher_name;
	private String info;
	private String room_abrev;
	private String subject_abrev;
	private String class_freetime;
	private String teacher_freetime;
	private String class_color;
	
	
	//Constructeur pour le test de la requette DELETE et SELECT (on n'a besoin que du ID)
	public Lesson(Long id) {
		this.id = id;
	}
	
	//Constructeur pour le test de la requette INSERT (on n'a pas besoin de ID car il est auto incrementer)
	public Lesson(Long teacher_fk, Long class_fk, Long room_fk, Long subject_fk, Long timetable_fk, int total_lessons, String lesson_occ, String lesson_link, String color) {
		this.teacher_fk = teacher_fk;
		this.class_fk = class_fk;
		this.room_fk = room_fk;
		this.subject_fk = subject_fk;
		this.timetable_fk = timetable_fk;
		this.total_lessons = total_lessons;
		this.lesson_occ = lesson_occ;
		this.lesson_link = lesson_link;
		this.color = color;
	}	
		
	//Constructeur pour le test de la requette UPDATE 
	public Lesson(Long id, Long teacher_fk, Long class_fk, Long room_fk, Long subject_fk, Long timetable_fk, int total_lessons, String lesson_occ, String lesson_link, String color) {
		this.id = id;
		this.teacher_fk = teacher_fk;
		this.class_fk = class_fk;
		this.room_fk = room_fk;
		this.subject_fk = subject_fk;
		this.timetable_fk = timetable_fk;
		this.total_lessons = total_lessons;
		this.lesson_occ = lesson_occ;
		this.lesson_link = lesson_link;
		this.color = color;
	}
	// Constructeur vide pour avoir la list des resultats de la requete SELECT
	public Lesson() {
		// TODO Auto-generated constructor stub
	}
	
	public Long getLessonId() {
		return id;
	}
	public void setLessonId(Long id) {
		this.id = id;
	}
	public Long getLessonTeacherFk() {
		return teacher_fk;
	}
	public void setLessonTeacherFk(Long teacher_fk) {
		this.teacher_fk = teacher_fk;
	}
	public Long getLessonClassFk() {
		return class_fk;
	}
	public void setLessonClassFk(Long class_fk) {
		this.class_fk = class_fk;
	}
	public Long getLessonRoomFk() {
		return room_fk;
	}
	public void setLessonRoomFk(Long room_fk) {
		this.room_fk = room_fk;
	}
	public Long getLessonSubjectFk() {
		return subject_fk;
	}
	public void setLessonSubjectFk(Long subject_fk) {
		this.subject_fk = subject_fk;
	}
	public Long getLessonTimetableFk() {
		return timetable_fk;
	}
	public void setLessonTimetableFk(Long timetable_fk) {
		this.timetable_fk = timetable_fk;
	}
	public int getTotalLessons() {
		return total_lessons;
	}
	public void setTotalLessons(int total_lessons) {
		this.total_lessons = total_lessons;
	}
	public String getLessonOcc() {
		return lesson_occ;
	}
	public void setLessonOcc(String lesson_occ) {
		this.lesson_occ = lesson_occ;
	}
	public String getLessonLink() {
		return lesson_link;
	}
	public void setLessonLink(String lesson_link) {
		this.lesson_link = lesson_link;
	}
	public String getLessonColor() {
		return color;
	}
	public void setLessonColor(String color) {
		this.color = color;
	}
	
	//getters and setters for details select variables

	public String getLessonTeacherName() {
		return teacher_name;
	}

	public void setLessonTeacherName(String teacher_name) {
		this.teacher_name = teacher_name;
	}

	public String getLessonInfo() {
		return info;
	}

	public void setLessonInfo(String info) {
		this.info = info;
	}

	public String getLessonRoomAbrev() {
		return room_abrev;
	}

	public void setLessonRoomAbrev(String room_abrev) {
		this.room_abrev = room_abrev;
	}

	public String getLessonSubjectAbrev() {
		return subject_abrev;
	}

	public void setLessonSubjectAbrev(String subject_abrev) {
		this.subject_abrev = subject_abrev;
	}

	public String getLessonClassFreetime() {
		return class_freetime;
	}

	public void setLessonClassFreetime(String class_freetime) {
		this.class_freetime = class_freetime;
	}

	public String getLessonTeacherFreetime() {
		return teacher_freetime;
	}

	public void setLessonTeacherFreetime(String teacher_freetime) {
		this.teacher_freetime = teacher_freetime;
	}

	public String getLessonClassColor() {
		return class_color;
	}

	public void setLessonClassColor(String class_color) {
		this.class_color = class_color;
	}
	
}
