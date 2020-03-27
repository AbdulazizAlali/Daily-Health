import java.io.Serializable;
import java.util.ArrayList;
import java.io.Serializable;


public class Month implements Serializable{
	private ArrayList<Day> monthList= new ArrayList<>(31);

	public Month(ArrayList<Day> monthList) {
		this.monthList = monthList;
	}

	public ArrayList<Day> getMonthList() {
		return monthList;
	}

	public void setMonthList(ArrayList<Day> monthList) {
		this.monthList = monthList;
	}
	
	
	
	
}
