package NFLSchedule;

public class Week {
	Team[] teamsOnBye = new Team[4];
	Game[] weekSchedule = new Game[16];
	int weekNumber;
	
	public Week(int weekNumber_c) {
		weekNumber = weekNumber_c;
	}
	
	public int getWeekNumber() {
		return weekNumber;
	}
	
	public void setWeekNumber(int weekNumber) {
		this.weekNumber = weekNumber;
	}
	public Team[] getTeamsOnBye() {
		return teamsOnBye;
	}
	public void setTeamsOnBye(Team[] teamsOnBye) {
		this.teamsOnBye = teamsOnBye;
	}
	public Game[] getWeekSchedule() {
		return weekSchedule;
	}
	public void setWeekSchedule(Game[] weekSchedule) {
		this.weekSchedule = weekSchedule;
	}
	
	public void printWeek() {
		System.out.println(weekNumber + ": ");
		for(int i = 0; i < 16; i++) {
			System.out.print("| " + weekSchedule[i].getHomeTeam() + " vs " + weekSchedule[i].awayTeam + " |");
		}
			
		
	}
}
