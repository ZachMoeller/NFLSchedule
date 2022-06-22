package NFLSchedule;
import java.util.*;
public class createSchedule {

	public static void main(String[] args) {
		
		Confrence AFC = new Confrence();
		Confrence NFC = new Confrence();
	
		Division AFCNorth = new Division(AFC);
		Team bengals = new Team("Cincinnati","Bengals", AFCNorth);
		Team steelers = new Team("Pittsburgh", "Steelers", AFCNorth);
		Team browns = new Team("Cleaveland", "Browns", AFCNorth);
		Team ravens = new Team("Baltimore", "Ravens", AFCNorth);
		Team[] AFCN = {bengals, steelers, browns, ravens};
		AFCNorth.setTeams(AFCN);
	
		Division AFCSouth = new Division(AFC);
		Team titans = new Team("Tennessee", "Titans", AFCSouth);
		Team colts = new Team("Indianapolis", "Colts", AFCSouth);
		Team texans = new Team("Houstan", "Texans", AFCSouth);
		Team jags = new Team("Jacksonville", "Jaguars", AFCSouth);
		Team[] AFCS = {titans, colts, texans, jags};
		AFCSouth.setTeams(AFCS);
	
		Division AFCEast = new Division(AFC);
		Team bills = new Team("Buffalo", "Bills", AFCEast);
		Team pats = new Team("New England", "Patriots", AFCEast);
		Team dolphins = new Team("Miami", "Dolphins", AFCEast);
		Team jets = new Team("New York", "Jets", AFCEast);
		Team[] AFCE = {bills, pats, dolphins, jets};
		AFCEast.setTeams(AFCE);
	
		Division AFCWest = new Division(AFC);
		Team chiefs = new Team("Kansas City", "Chiefs", AFCWest);
		Team raiders = new Team("Las Vegas", "Raiders", AFCWest);
		Team chargers = new Team("Las Angeles", "Chargers", AFCWest);
		Team broncos = new Team("Denver", "Broncos", AFCWest);
		Team[] AFCW = {chiefs, raiders, chargers, broncos};
		AFCWest.setTeams(AFCW);
	
	
		Division NFCNorth = new Division(NFC);
		Team packers = new Team("Green Bay", "Packers", NFCNorth);
		Team vikings = new Team("Minnesota","Vikings", NFCNorth);
		Team bears = new Team("Chicago", "Bears", NFCNorth);
		Team lions = new Team("Detriot", "Lions", NFCNorth);
		Team[] NFCN = {packers, vikings, bears, lions};
		NFCNorth.setTeams(NFCN);
		
		Division NFCSouth = new Division(NFC);
		Team buccs = new Team("Tampa Bay", "Buccaneers", NFCSouth);
		Team saints = new Team("New Orleans", "Saints", NFCSouth);
		Team falcons = new Team("Atlanta", "Falcons", NFCSouth);
		Team panthers = new Team("Carolina", "Panthers",NFCSouth);
		Team[] NFCS = {buccs, saints, falcons, panthers};
		NFCSouth.setTeams(NFCS);
		
		Division NFCEast = new Division(NFC);
		Team cowboys = new Team("Dallas", "Cowboys", NFCEast);
		Team eagles = new Team("Philadelphia", "Eagles", NFCEast);
		Team commies = new Team("Washington", "Commanders", NFCEast);
		Team giants = new Team("New York", "Giants", NFCEast);
		Team[] NFCE = {cowboys, eagles, commies, giants};
		NFCEast.setTeams(NFCE);
	
		Division NFCWest = new Division(NFC);
		Team rams = new Team("Los Angeles", "Rams", NFCWest);
		Team cards = new Team("Arizona", "Cardinals", NFCWest);
		Team sanFran = new Team("San Francisco", "49ers", NFCWest);
		Team seahawks = new Team("Seattle", "Seahawks", NFCWest);
		Team[] NFCW = {rams, cards, sanFran, seahawks};
		NFCWest.setTeams(NFCW);
	
		ArrayList<Division> AFCdivs = new ArrayList<Division>();
		AFCdivs.add(AFCNorth);
		AFCdivs.add(AFCSouth);
		AFCdivs.add(AFCEast);
		AFCdivs.add(AFCWest);
		ArrayList<Division> NFCdivs = new ArrayList<Division>();
		NFCdivs.add(NFCNorth);
		NFCdivs.add(NFCSouth);
		NFCdivs.add(NFCEast);
		NFCdivs.add(NFCWest);
		AFC.setDivisions(AFCdivs);
		NFC.setDivisions(NFCdivs);

		
		
		
		League nfl = new League(AFC, NFC);
		
		Game game = new Game(giants, eagles);
		

		
		Season season = new Season(nfl);
		
		season.printSchedule();
		
		
//		System.out.println(game.containsTeam(giants));
//		System.out.println(game.containsTeam(chiefs));
//		
//		Week week = new Week(1);
//		week.addGame(game);
//		
//		System.out.println(week.containsTeam(eagles));
//		System.out.println(week.containsTeam(chiefs));
//		System.out.println(chiefs.getSharedByeWeek().toString());

//		nfl.check();
		
//		nfl.printConfrences();
	}

}
