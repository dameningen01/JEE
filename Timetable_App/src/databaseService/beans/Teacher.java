package databaseService.beans;

public class Teacher {
	private Long id;
	private Long user_fk;
	private String name;
	private String free_time;
	private String color;
	
	//Constructeur pour le test de la requette DELETE et SELECT (on n'a besoin que du ID)
		public Teacher(Long id) {
			this.id = id;
		}
		
		//Constructeur pour le test de la requette INSERT (on n'a pas besoin de ID car il est auto incrementer)
		public Teacher(String name, String free_time, String couleur, Long user_fk) {
			this.name = name;
			this.free_time = free_time;
			this.color = couleur;
			this.user_fk = user_fk;
		}	
			
		//Constructeur pour le test de la requette UPDATE 
		public Teacher(Long id, Long user_fk,String name, String free_time, String couleur) {
			this.id = id;
			this.user_fk = user_fk;
			this.name = name;
			this.free_time = free_time;
			this.color = couleur;
		}
		// Constructeur vide pour avoir la list des resultats de la requete SELECT
		public Teacher() {
			// TODO Auto-generated constructor stub
		}
	public String getTeacherName() {
		return name;
	}
	public String getTeacherFreeTime(){
		return free_time;
	}
	public String getTeacherColor() {
		return color;
	}
	public void setTeacherName(String name) {
		this.name = name ;
	}
	public void setTeacherFreeTime(String free_time) {
		this.free_time = free_time;
	} 
	public void setTeacherColor(String couleur) {
		this.color = couleur;
	}
	public Long getTeacherId() {
		return id;
	}
	public void setTeacherId(Long id) {
		this.id = id;
	}
	public Long getTeacherUserFk() {
		return user_fk;
	}
	public void setTeacherUserFk(Long user_fk) {
		this.user_fk = user_fk;
	}
}
