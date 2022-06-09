package NFLSchedule;
import java.util.Random;
import java.util.*;

public class Season{
	Week[] schedule = new Week[18];

	public Season(League league) {
		Confrence AFC = league.getConfrence(0);
		Confrence NFC = league.getConfrence(1);
		selectInConfrenceRivals(AFC);
		selectInConfrenceRivals(NFC);
		selectIntraConfrenceRivals(AFC, NFC);
		fillTeamPools(AFC, NFC);
		assignByeWeeks(AFC, NFC);
		selectIntraConfrenceOpponent(AFC, NFC);
		updateTeamPools(AFC, NFC);
	}
	
	


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
	 */
	private void assignByeWeeks(Confrence AFC, Confrence NFC) {
		List<Team> teamList = new ArrayList<Team>();
		teamList.addAll(AFC.getAllTeams());
		teamList.addAll(NFC.getAllTeams());
		
	}





	/*
	 * Will generate each teams pool
	 * 
	 * Confrence AFC, NFC : Confrences of the NFL
	 */
	/*
	 * Will generate each teams intraleague opponent and put them into the pool of teams they can play
	 * 
	 * 
	 * Confrence AFC, NFC : Confrences of the NFL
	 */
	private void selectIntraConfrenceOpponent(Confrence AFC, Confrence NFC) {
		
	}
	
	/*
	 * Add their in division rivals to their team pool again as they play them twice.  
	 */
	private void updateTeamPools(Confrence AFC, Confrence NFC) {
		// TODO Auto-generated method stub
		
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
	


	public Week[] getSchedule(){
		return schedule;
	}
}
