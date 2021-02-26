package databaseService.beans;

public class Subject implements java.io.Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long id;
	private String module;
	private String submodule;
	private String type;
	private String abrev;
	private String color;
	
	//Constructeur pour le test de la requette DELETE et SELECT (on n'a besoin que du ID)
			public Subject(Long id) {
				this.id = id;
			}
			
			//Constructeur pour le test de la requette INSERT (on n'a pas besoin de ID car il est auto incrementer)
			public Subject(String module, String submodule,String abrev, String color) {
				this.module = module;
				this.submodule = submodule;
				this.abrev = abrev;
				this.color = color;
			}	
				
			//Constructeur pour le test de la requette UPDATE 
			public Subject(Long id,String module, String submodule,String type,String abrev, String color) {
				this.id = id;
				this.module = module;
				this.submodule = submodule;
				this.abrev = abrev;
				this.color = color;
				this.type = type;
			}
			// Constructeur vide pour avoir la list des resultats de la requete SELECT
			public Subject() {
				// TODO Auto-generated constructor stub
			}
	
	public Long getSubjectId() {
		return id;
	}
	public void setSubjectId(Long id) {
		this.id = id;
	}
	public String getSubjectModule() {
		return module;
	}
	public void setSubjectModule(String module) {
		this.module = module;
	}
	public String getSubjectSubmodule() {
		return submodule;
	}
	public void setSubjectSubmodule(String submodule) {
		this.submodule = submodule;
	}
	public String getSubjectType() {
		return type;
	}
	public void setSubjectType(String type) {
		this.type = type;
	}
	public String getSubjectAbrev() {
		return abrev;
	}
	public void setSubjectAbrev(String abrev) {
		this.abrev = abrev;
	}
	public String getSubjectColor() {
		return color;
	}
	public void setSubjectColor(String color) {
		this.color = color;
	}
}
