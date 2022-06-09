package NFLSchedule;
import java.util.*;

public class Team {
	String location;
	String name;
	Division division;
	int homeGames = 0;
	int awayGames = 0;
	Team intraConfrenceRivalTeam;
	List<Team> teamPool = new ArrayList<Team>();
	Team SharedByeWeek;
	int weekOfBye;
	
	
	public Team(String location_c, String name_c, Division division_c) {
		location = location_c;
		name = name_c;
		division = division_c;
	}
	
	
	
	public List<Team> getTeamPool() {
		return teamPool;
	}



	public void setTeamPool(List<Team> teamPool) {
		this.teamPool = teamPool;
	}



	public Division getDivision() {
		return division;
	}

	public void setDivision(Division division) {
		this.division = division;
	}

	public int getWeekOfBye() {
		return weekOfBye;
	}

	public void setWeekOfBye(int weekOfBye) {
		this.weekOfBye = weekOfBye;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public int getHomeGames() {
		return homeGames;
	}

	public void setHomeGames(int homeGames) {
		this.homeGames = homeGames;
	}

	public int getAwayGames() {
		return awayGames;
	}

	public void setAwayGames(int awayGames) {
		this.awayGames = awayGames;
	}

	public Team getIntraConfrenceRivalTeam() {
		return intraConfrenceRivalTeam;
	}

	public void setIntraConfrenceRivalTeam(Team intraConfrenceRivalTeam) {
		this.intraConfrenceRivalTeam = intraConfrenceRivalTeam;
	}

	public Team getSharedByeWeek() {
		return SharedByeWeek;
	}

	public void setSharedByeWeek(Team sharedByeWeek) {
		SharedByeWeek = sharedByeWeek;
	}
	
	public String toString() {
		return location + " " + name;
	}
}
