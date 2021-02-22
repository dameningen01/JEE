package databaseService.beans;

public class Timetable implements java.io.Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long id;
	private Long user_fk;
	private int nb_days;
	private int nb_periods ;
	private int  hours_per_period;
	private String description;
	private String free_time;
	private String summary;
	
	//Constructeur pour le test de la requette DELETE et SELECT (on n'a besoin que du ID)
			public Timetable(Long id) {
				this.id = id;
			}
			
			//Constructeur pour le test de la requette INSERT (on n'a pas besoin de ID car il est auto incrementer)
			public Timetable(String description, Long user_fk, String free_time, String summary, int nb_days, int nb_periods, int  hours_per_period) {
				this.description = description;
				this.user_fk = user_fk;
				this.free_time = free_time;
				this.summary = summary;
				this.nb_days = nb_days;
				this.nb_periods = nb_periods;
				this.hours_per_period = hours_per_period;
			}	
				
			//Constructeur pour le test de la requette UPDATE 
			public Timetable(Long id,String description, Long user_fk, String free_time, String summary, int nb_days, int nb_periods, int  hours_per_period) {
				this.id = id;
				this.description = description;
				this.user_fk = user_fk;
				this.free_time = free_time;
				this.summary = summary;
				this.nb_days = nb_days;
				this.nb_periods = nb_periods;
				this.hours_per_period = hours_per_period;
			}
			// Constructeur vide pour avoir la list des resultats de la requete SELECT
			public Timetable() {
				// TODO Auto-generated constructor stub
			}
	
	public Long getTimetableId() {
		return id;
	}
	public void setTimetableId(Long id) {
		this.id = id;
	}
	public Long getTimetableUserFk() {
		return user_fk;
	}
	public void setTimetableUserFk(Long user_fk) {
		this.user_fk = user_fk;
	}
	public int getTimetableNbDays() {
		return nb_days;
	}
	public void setTimetableNbDays(int nb_days) {
		this.nb_days = nb_days;
	}
	public int getTimetableNbPeriods() {
		return nb_periods;
	}
	public void setTimetableNbPeriods(int nb_periods) {
		this.nb_periods = nb_periods;
	}
	public int getTimetableHoursPerPeriod() {
		return hours_per_period;
	}
	public void setTimetableHoursPerPeriod(int hours_per_period) {
		this.hours_per_period = hours_per_period;
	}
	public String getTimetableFreeTime() {
		return free_time;
	}
	public void setTimetableFreeTime(String free_time) {
		this.free_time = free_time;
	}
	public String getTimetableDescription() {
		return description;
	}
	public void setTimetableDescription(String description) {
		this.description = description;
	}
	public String getTimetableSummary() {
		return summary;
	}
	public void setTimetableSummary(String summary) {
		this.summary = summary;
	}
	
}
