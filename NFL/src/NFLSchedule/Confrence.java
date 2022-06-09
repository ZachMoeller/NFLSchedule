package NFLSchedule;

import java.util.*;
import java.util.Random;

public class Confrence {
	List<Division> divisions = new ArrayList<Division>();
	
	public Confrence() {
	
	}

	public List<Division> getDivisions() {
		return divisions;
	}

	public void setDivisions(List<Division> divisions) {
		this.divisions = divisions;
	}
	
	public Division getDivision(int n) {
		return divisions.get(n);
	}

	public void setDivision(int n, Division division) {
		this.divisions.set(n, division);
	}
	
	public Division getRandomDivision() {
		Random rand = new Random();
		return divisions.get(rand.nextInt(4));
	}
	
	//Returns list Sorted by division (North, South, East, West), each division sorted by standing (1st, 2nd, 3rd, 4th)
	public List<Team> getAllTeams(){
		List<Team> teamList = new ArrayList<Team>();
		for(int i = 0; i < 4; i++) {
			for(int y = 0; y < 4; y++) {
				teamList.add(divisions.get(i).getTeam(y));
			}
		}
		return teamList;
	}
	public void printConfrence() {
		for(int i = 0; i < 4; i++) {
			divisions.get(i).printDivision();
		}
		System.out.println();
	}
}
