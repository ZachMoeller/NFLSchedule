package NFLSchedule;
import java.util.*;

public class Week {
	List<Team> teamsOnBye = new ArrayList<Team>();
	List<Game> weekSchedule = new ArrayList<Game>();
	int weekNumber;
	
	
	public Week(int weekNumber_c) {
		weekNumber = weekNumber_c;
	}
	
	public boolean containsTeam(Team team) {
		for(int i = 0; i < weekSchedule.size(); i++) {
			Game game = weekSchedule.get(i);
			if(game.containsTeam(team)) {
				return true;
			}
		}
		return false;
	}
	public int getWeekNumber() {
		return weekNumber;
	}
	
	public void setWeekNumber(int weekNumber) {
		this.weekNumber = weekNumber;
	}
	
	
	public List<Team> getTeamsOnBye() {
		return teamsOnBye;
	}

	public void setTeamsOnBye(List<Team> teamsOnBye) {
		this.teamsOnBye = teamsOnBye;
	}

	public List<Game> getWeekSchedule() {
		return weekSchedule;
	}

	public void setWeekSchedule(List<Game> weekSchedule) {
		this.weekSchedule = weekSchedule;
	}

	public void printWeek() {
		System.out.println(weekNumber + ": ");
		for(int i = 0; i < 16; i++) {
			System.out.print("| " + weekSchedule.get(i).getHomeTeam() + " vs " + weekSchedule.get(i).getAwayTeam()+ " |");
		}
	}
}
