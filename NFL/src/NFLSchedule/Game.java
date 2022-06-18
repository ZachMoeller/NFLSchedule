package NFLSchedule;
public class Game {
	Team homeTeam;
	Team awayTeam;
	
	
	public Game(Team homeTeam_c, Team awayTeam_c) {
		homeTeam = homeTeam_c;
		awayTeam = awayTeam_c;
	}
	
	public boolean containsTeam(Team team) {
		if(team == homeTeam || team == awayTeam) {
			return true;
		}
		else {
			return false;
		}
	}
	public Team getHomeTeam() {
		return homeTeam;
	}
	public void setHomeTeam(Team homeTeam) {
		this.homeTeam = homeTeam;
	}
	public Team getAwayTeam() {
		return awayTeam;
	}
	public void setAwayTeam(Team awayTeam) {
		this.awayTeam = awayTeam;
	}
}
