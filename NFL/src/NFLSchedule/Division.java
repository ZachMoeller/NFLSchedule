package NFLSchedule;
import java.util.*;

public class Division {
	Division inConfrenceRival;
	Division intraConfrenceRival;
	Confrence confrence;
	Team[] teams = new Team[4];
	
	
	
	
	public Division(Confrence confrence_c) {
		this.confrence = confrence_c;
	}
	
	/*
	 * will randomly pick a team that is not the given team
	 */
	public Team getOtherTeam(Team team) { 
		Random rand = new Random();
		Team opp;
		do {
				opp = teams[rand.nextInt(4)];
		} while(opp == team);
		return opp;
	}
	
	public Confrence getConfrence() {
		return confrence;
	}


	public void setConfrence(Confrence confrence) {
		this.confrence = confrence;
	}


	public Division getInConfrenceRival() {
		return inConfrenceRival;
	}
	public void setInConfrenceRival(Division inConfrenceRival) {
		this.inConfrenceRival = inConfrenceRival;
	}
	public Division getIntraConfrenceRival() {
		return intraConfrenceRival;
	}
	public void setIntraConfrenceRival(Division intraConfrenceRival) {
		this.intraConfrenceRival = intraConfrenceRival;
	}
	public Team getTeam(int n) { // n represents the team in the array must be between 0-3
		return teams[n];
	}
	public void setTeam(int n, Team team) { // n represents the team in the array must be between 0-3
		teams[n] = team;
	}
	
	public Team[] getTeams() { //Returns whole team array
		return teams;
	}
	
	public void setTeams(Team[] teams) {
		this.teams = teams;
	}
	
	public void printDivision() {
		System.out.println(inConfrenceRival.divisionTeams());
		System.out.println(intraConfrenceRival.divisionTeams());
		for(int i = 0; i < 4; i++) {				
			System.out.print(teams[i].toString() + ", ");
		}
		System.out.println();
	}
	
	public String divisionTeams() {
		String teamStrings = teams[0].toString();
		for(int i = 1; i < 4; i++) {
			teamStrings = teamStrings + " " + teams[i].toString(); 
		}
		return teamStrings;
	}
}
