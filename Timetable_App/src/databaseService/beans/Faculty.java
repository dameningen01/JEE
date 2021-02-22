package databaseService.beans;

public class Faculty implements java.io.Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long id;
	private String name;
	private String abrev;
	private String year;
	
	//Constructeur pour le test de la requette DELETE et SELECT (on n'a besoin que du ID)
		public Faculty(Long id) {
			this.id = id;
		}
		
		//Constructeur pour le test de la requette INSERT (on n'a pas besoin de ID car il est auto incrementer)
		public Faculty(String name, String abrev, String year) {
			this.name = name;
			this.abrev = abrev;
			this.year = year;
		}	
			
		//Constructeur pour le test de la requette UPDATE 
		public Faculty(Long id,String name, String abrev, String year) {
			this.id = id;
			this.name = name;
			this.abrev = abrev;
			this.year = year;
		}
		// Constructeur vide pour avoir la list des resultats de la requete SELECT
		public Faculty() {
			// TODO Auto-generated constructor stub
		}
	
	public Long getFacultyId() {
		return id;
	}
	public void setFacultyId(Long id) {
		this.id = id;
	}
	public String getFacultyName() {
		return name;
	}
	public void setFacultyName(String name) {
		this.name = name;
	}
	public String getFacultyAbrev() {
		return abrev;
	}
	public void setFacultyAbrev(String abrev) {
		this.abrev = abrev;
	}

	public String getFacultyYear() {
		return year;
	}

	public void setFacultyYear(String year) {
		this.year = year;
	}
}
