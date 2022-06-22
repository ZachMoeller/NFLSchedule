package NFLSchedule;
import java.util.*;

public class Season{
	
	List<Week> schedule = new ArrayList<Week>();

	public Season(League league) {
		initalizeSchedule();
		Confrence AFC = league.getConfrence(0);
		Confrence NFC = league.getConfrence(1);
		selectInConfrenceRivals(AFC);
		selectInConfrenceRivals(NFC);
		selectIntraConfrenceRivals(AFC, NFC);
		fillTeamPools(AFC, NFC);
		assignByeWeeks(AFC, NFC);
		selectIntraConfrenceOpponent(AFC, NFC);
		updateTeamPools(AFC, NFC);
		createSchedule(AFC, NFC);
	}
	
	


	/*
	 * Will randomly assign in League Rivals
	 * 
	 * Confrence confrence: Either AFC or NFC
	 * using the provided confrence it will first randomly assign their in confrence rival 
	 * choosing 1 to 3 
	 * 
	 * 1: South
	 * 2: East
	 * 3: West 
	 * 
	 * and then assign the other 2 to each other
	 * 
	 * ex. The selection is 2
	 * North and East are set as Rivals
	 * then South and West are set as Rivals.
	 * */
	
	public void selectInConfrenceRivals(Confrence confrence) {
		Random rand = new Random();
		int selection = rand.nextInt(3)+1;
		confrence.getDivision(0).setInConfrenceRival(confrence.getDivision(selection));
		confrence.getDivision(selection).setInConfrenceRival(confrence.getDivision(0));
		if(selection == 1) {
			confrence.getDivision(2).setInConfrenceRival(confrence.getDivision(3));
			confrence.getDivision(3).setInConfrenceRival(confrence.getDivision(2));
		}
		else if(selection == 2) {
			confrence.getDivision(1).setInConfrenceRival(confrence.getDivision(3));
			confrence.getDivision(3).setInConfrenceRival(confrence.getDivision(1));
		}
		else {
			confrence.getDivision(2).setInConfrenceRival(confrence.getDivision(1));
			confrence.getDivision(1).setInConfrenceRival(confrence.getDivision(2));
		}
	}
	/*
	 *Will randomly assign intraleague rivals
	 *
	 * Confrence AFC, NFC: Each confrence in the NFL
	 * 
	 * create a list and populate it with each NFC Division.
	 * 0: North
	 * 1: South
	 * 2: East
	 * 3: West
	 * Then shuffle the list to randomize it
	 * 
	 * Using the same order as before it will assign each division in the AFC a different rival from the NFC
	 */
	public void selectIntraConfrenceRivals(Confrence AFC, Confrence NFC) {
			List<Division> nfcDivisions = new ArrayList<Division>();
			nfcDivisions.addAll(NFC.getDivisions());
			Collections.shuffle(nfcDivisions); //Randomize the order
			for(int i = 0; i < 4; i++) {
				AFC.getDivision(i).setIntraConfrenceRival(nfcDivisions.get(i));
				nfcDivisions.get(i).setIntraConfrenceRival(AFC.getDivision(i));
			}
	}
	/*
	 * Fills in each teams Team Pool
	 * 
	 * Confrence AFC, NFC : Confrences of the NFL
	 * 
	 * Takes all teams and creates a list then with the paramaters of the teams creates a list of the teams that team can play.
	 * This does not include Intraconfrence opponent as this is first for determining bye week.
	 */
	private void fillTeamPools(Confrence AFC, Confrence NFC) {
		List<Team> teamList = new ArrayList<Team>();
		teamList.addAll(AFC.getAllTeams());
		teamList.addAll(NFC.getAllTeams());
//		System.out.print(teamList);
		
		for(int i = 0; i < teamList.size(); i++) {
			Team team = teamList.get(i);
			int seed = i%4; //Seed in their Division. Since the List is sorted by Division, and Each division is sorted by Seed every 4th team is the first seed in their division.
			List<Team> opponentList = new ArrayList<Team>();
			Division division = team.getDivision();
			Confrence confrence = division.getConfrence();
			//Adding In division rivals
			for(int y = 0; y < 4; y++) {
				if(division.getTeam(y) != team) {
					opponentList.add(division.getTeam(y));
				}
				
				opponentList.add(division.getInConfrenceRival().getTeam(y)); //In Confrence Rivals
				
				opponentList.add(division.getIntraConfrenceRival().getTeam(y));// Intra Confrence Rivals
				
				//Other teams in their confrence that match their seed.
				if(confrence.getDivision(y) != division && confrence.getDivision(y) != division.getInConfrenceRival()) {
					opponentList.add(confrence.getDivision(y).getTeam(seed));
				}
				team.setTeamPool(opponentList);
			}
		}
	}
	
	
	/*
	 * Assigns each team's bye week in pairs
	 * 
	 * Takes each team's teamPool and assigns them a partner from that pool at random.
	 * Assigns the opps partner to the team. 
	 * From tests this has about a 1/10 to a 1/15 chance to fail so if it does it just runs again. 
	 * 
	 * After this it will put all the teams into a list and assign them at random starting at week 6 going until week 14
	 * 
	 */
	private void assignByeWeeks(Confrence AFC, Confrence NFC) {
		List<Team> teamList = new ArrayList<Team>();
		teamList.addAll(AFC.getAllTeams());
		teamList.addAll(NFC.getAllTeams());
		int week = 4; // Because bye weeks start at week 6.
		boolean teamFinished = true;
		
			while(teamList.size() > 0) {
				
				if(!teamFinished) {
					teamList.clear();
					teamList.addAll(AFC.getAllTeams());
					teamList.addAll(NFC.getAllTeams());
					teamFinished= true;
				}
				else {
					Team team = teamList.get(0);
					List<Team> teamPool = team.getTeamPool();
					Collections.shuffle(teamPool);
					for(int y = 0; y < teamPool.size(); y++) {
						Team selectedTeam = teamPool.get(y);
						if(teamList.contains(selectedTeam)) {
							team.setSharedByeWeek(selectedTeam);
							selectedTeam.setSharedByeWeek(team);
							teamList.remove(selectedTeam);
							teamList.remove(team);
							teamFinished= true;
						}
						else {
							teamFinished = false;
						}
					}
				}
			}
			
			teamList.addAll(AFC.getAllTeams());
			teamList.addAll(NFC.getAllTeams());
			Collections.shuffle(teamList);
			while(teamList.size() > 0) {
				if(teamList.size()%4 == 0) { // 4 teams per bye week
					week++;
				}
				Team team = teamList.get(0);
				Team opp = team.getSharedByeWeek();
				team.setWeekOfBye(week);
				opp.setWeekOfBye(week);
				Week byeWeek = schedule.get(week);
				byeWeek.getTeamsOnBye().add(team);
				byeWeek.getTeamsOnBye().add(opp);
				teamList.remove(team);
				teamList.remove(opp);
			}

			
			System.out.println("Bye Weeks Assigned");
		}
	/*
	 * Will generate each teams intraleague opponent and put them into the pool of teams they can play
	 * 
	 * 
	 * Confrence AFC, NFC : Confrences of the NFL
	 * 
	 * This will create a sorted list of the teams based on Confrence, Division, and Seed within the division
	 * The sort looks like this.
	 * AFC
	 * 	North
	 * 		1st seed
	 * 		2nd Seed
	 * 		3rd Seed
	 * 		4th Seed
	 * 	South
	 * 	East
	 * 	West
	 * NFC
	 * 	North
	 * 	South
	 * 	East
	 * 	West
	 * 
	 * since each division has their opponents selected the algorithim 
	 * will just choose one team that shares a seed from the other confrence that they're not already playing
	 * then both teams are added to the Finished teams list so they're not picked again. 
	 * run this 16 times and all teams are done. 
	 * 
	 */
	private void selectIntraConfrenceOpponent(Confrence AFC, Confrence NFC) {
		List<Team> teamList = new ArrayList<Team>();
		teamList.addAll(AFC.getAllTeams());
		teamList.addAll(NFC.getAllTeams());
		boolean teamFinished = true;
		
		while(teamFinished) {
			List<Team> finishedTeams = new ArrayList<Team>();
			for(int i = 0; i < teamList.size(); i++) {
				int seed = i%4; //The list is organized so I am allowed to do this 
				Team team = teamList.get(i);
				List<Team> possibleOpps = new ArrayList<Team>();
				//You only have to do one side as you're choosing 1 from each side of the league.
				//so I chose the AFC
				if(i < 16) {
					for(int y = 0; y < 4; y++) {
						possibleOpps.add(NFC.getDivision(y).getTeam(seed));
					}
						Collections.shuffle(possibleOpps);
					for(int y = 0; y < 4; y++) {
						// Team is not already playing them and they're not already done. 
						if(!team.getTeamPool().contains(possibleOpps.get(y)) && !finishedTeams.contains(possibleOpps.get(y))) {
							Team opp = possibleOpps.get(y);
							team.setIntraConfrenceRivalTeam(opp);
							List<Team> newTeamPool = team.getTeamPool();
							newTeamPool.add(opp);
							team.setTeamPool(newTeamPool);
							opp.setIntraConfrenceRivalTeam(team);
							newTeamPool = opp.getTeamPool();
							newTeamPool.add(team);
							opp.setTeamPool(newTeamPool);
							teamFinished = true;
							finishedTeams.add(team);
							finishedTeams.add(opp);
						}
						else {
							teamFinished = false;
						}
					}
				}
			}
		}
		System.out.println("Intraleague Opponents Selected");
	}
	
	/*
	 * Confrence AFC, NFC : Confrences of the NFL
	 * 
	 * Add their in division rivals to their team pool again as they play them twice.  
	 * We do this so we can use the team pool to make the schedule. 
	 */
	private void updateTeamPools(Confrence AFC, Confrence NFC) {
		List<Team> teamList = new ArrayList<Team>();
		teamList.addAll(AFC.getAllTeams());
		teamList.addAll(NFC.getAllTeams());
		for(int i = 0; i < teamList.size(); i++) {
			Team team = teamList.get(i);
			Division division = team.getDivision();
			for(int y = 0; y < 4; y++) {
				Team opp = division.getTeam(y);
				if(opp != team) { // Not allowed to play against yourself. Something about competitive integrity??? i dont know...
					team.getTeamPool().add(opp);
				}
			}
		}
//		System.out.print(teamList.get(0).getTeamPool());
		System.out.println("Team Pools Updated");
	}

	/*
	 * Confrence AFC, NFC : Confrences of the NFL
	 * 
	 * will execute the createTeamSchedule() method for each team.
	 */
	private void createSchedule(Confrence AFC, Confrence NFC) {
		List<Team> teamList = new ArrayList<Team>();
		teamList.addAll(AFC.getAllTeams());
		teamList.addAll(NFC.getAllTeams());
		Collections.shuffle(teamList); // Adds more variety by not doing the bengals first everytime. Joe Burrow looking pretty hot right now. made it to the superbowl and all that. 
		
		for(int i = 0; i < teamList.size(); i++) {
			Team team = teamList.get(i);
//			System.out.println(team);
			createTeamSchedule(team);
		}
	}



	private void createTeamSchedule(Team team) {
		List<Team> teamPool = team.getTeamPool();
		int byeWeek = team.getWeekOfBye();
		Division div = team.getDivision();
		List<Team> divRivals = new ArrayList<Team>();
		Team opp = null;
		Week week = schedule.get(17);
		if(!week.containsTeam(team)) {
			for(int i = 0; i < 3; i++) {
				Team rival = div.getTeam(i);
				if(rival != team) {
					divRivals.add(rival);
				}
			}
		
			for(int i = 0; i < 2; i++) { //Last Week of the season
				opp = divRivals.get(i);
				if(teamPool.contains(opp) && !week.containsTeam(opp)) {
					Game game = new Game(team, opp);
					week.addGame(game);
					teamPool.remove(opp);
					opp.getTeamPool().remove(team);
//					System.out.println("Week 18 Finished");
					break;// once a game is made leave the loop
				}
			}
			
			for(int i = 16; i > -1; i--) { //Rest of the weeks
				week = schedule.get(i);
				if(week.containsTeam(team) || i == byeWeek) { //If the team is already playing or it's their bye week move to the next week. 
					continue;
				}
				
				else {
					
					for(int y = 0; y < teamPool.size(); y++) {// Selecting a team that doesn't play already
						opp = teamPool.get(y);
						if(!week.containsTeam(opp)) break; //Stop looking once the team is found. 
						}
					
					Game game = new Game(team, opp);
					week.addGame(game);
					teamPool.remove(opp);
					opp.getTeamPool().remove(team);
					}
//				System.out.println("Week " + (i+1) + " Finished");
				}
			}
		}



	public List<Week> getSchedule() {
		return schedule;
	}


	public void setSchedule(List<Week> schedule) {
		this.schedule = schedule;
	}

	//Sets the week Numbers
	public void initalizeSchedule() {
		for(int i = 0; i < 18; i++) {
			Week week = new Week(i);
			schedule.add(week);
		}
	}
}