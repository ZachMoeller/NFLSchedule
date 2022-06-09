package NFLSchedule;
import java.util.*;

public class League {
	List<Confrence> confrences = new ArrayList<Confrence>();

	public League(Confrence AFC, Confrence NFC) {
		confrences.add(AFC);
		confrences.add(NFC);

	}
	
	public Confrence getConfrence(int n) {
		return confrences.get(n);
	}
	public void setConfrences(List<Confrence> confrences) {
		this.confrences = confrences;
	}

	public List<Confrence> getConfrences() {
		return confrences;
	}

	public void printConfrences() {

	}

}
