package databaseService.beans;

public class Class implements java.io.Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long id;
	private Long faculty_fk;
	private Integer group;
	private String free_time;
	private String color;
	
	// for select faculty name from class
	private String class_faculty_name;
	
	//Constructeur pour le test de la requette DELETE et SELECT (on n'a besoin que du ID)
		public Class(Long id) {
		this.id = id;
	}
			
	//Constructeur pour le test de la requette INSERT (on n'a pas besoin de ID car il est auto incrementer)
	public Class(Long faculty_fk, Integer group, String free_time, String color) {
		this.faculty_fk = faculty_fk;
		this.group = group;
		this.free_time = free_time;
		this.color = color;
	}	
				
	//Constructeur pour le test de la requette UPDATE 
	public Class(Long id, Long faculty_fk, Integer group, String free_time, String color) {
		this.id = id;
		this.faculty_fk = faculty_fk;
		this.group = group;
		this.free_time = free_time;
		this.color = color;
	}
	// Constructeur vide pour avoir la list des resultats de la requete SELECT
	public Class() {
		// TODO Auto-generated constructor stub
	}
	
	public Long getClassId() {
		return id;
	}
	public void setClassId(Long id) {
		this.id = id;
	}
	public Long getClassFacultyFk() {
		return faculty_fk;
	}
	public void setClassFacultyFk(Long faculty_fk) {
		this.faculty_fk = faculty_fk;
	}
	public int getClassGroup() {
		return group;
	}
	public void setClassGroup(Integer group) {
		this.group = group;
	}
	public String getClassFreeTime() {
		return free_time;
	}
	public void setClassFreeTime(String free_time) {
		this.free_time = free_time;
	}
	public String getClassColor() {
		return color;
	}
	public void setClassColor(String color) {
		this.color = color;
	}
	
	// for select faculty name from class

	public String getClassFacultyName() {
		return class_faculty_name;
	}

	public void setClassFacultyName(String class_faculty_name) {
		this.class_faculty_name = class_faculty_name;
	}

}
