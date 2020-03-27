import java.io.Serializable;
import java.util.ArrayList;
import java.io.Serializable;


public class Day implements Serializable {
	private ArrayList<Edible> brkfst = new ArrayList<>();
	private ArrayList<Edible> lunch = new ArrayList<>();
	private ArrayList<Edible> dinner = new ArrayList<>();
	
	
	public Day(ArrayList<Edible> brkfst, ArrayList<Edible> lunch, ArrayList<Edible> dinner) {
		this.brkfst = brkfst;
		this.lunch = lunch;
		this.dinner = dinner;
	}


	public ArrayList<Edible> getBrkfst() {
		return brkfst;
	}


	public void setBrkfst(ArrayList<Edible> brkfst) {
		this.brkfst = brkfst;
	}


	public ArrayList<Edible> getLunch() {
		return lunch;
	}


	public void setLunch(ArrayList<Edible> lunch) {
		this.lunch = lunch;
	}


	public ArrayList<Edible> getDinner() {
		return dinner;
	}


	public void setDinner(ArrayList<Edible> dinner) {
		this.dinner = dinner;
	}
	
	
	public int dailyCalory () {
		int dailyCal = 0;
		for (Edible e: brkfst) {
			dailyCal += e.getCal();
		}
		for (Edible e: lunch) {
			dailyCal += e.getCal();
		}
		for (Edible e: dinner) {
			dailyCal += e.getCal();
		}
		return dailyCal;
	}
	
	
	public int dailyProtein() {
		int dailyProtein = 0;
		for (Edible e: brkfst) {
			dailyProtein += e.getProtein();
		}
		for (Edible e: lunch) {
			dailyProtein += e.getProtein();
		}
		for (Edible e: dinner) {
			dailyProtein += e.getProtein();
		}
		return dailyProtein;
	}
	
	public int dailyCarbo() {
		int dailyCarbo = 0;
		for (Edible e: brkfst) {
			dailyCarbo += e.getCarbo();
		}
		for (Edible e: lunch) {
			dailyCarbo += e.getCarbo();
		}
		for (Edible e: dinner) {
			dailyCarbo += e.getCarbo();
		}
		return dailyCarbo;
	}
	
	
	public int dailyFats() {
		int dailyFats = 0;
		for (Edible e: brkfst) {
			dailyFats += e.getFat();
		}
		for (Edible e: lunch) {
			dailyFats += e.getFat();
		}
		for (Edible e: dinner) {
			dailyFats += e.getFat();
		}
		return dailyFats;
	}
	
	public double dailyBeverages() {
		
		double dailyBeverages = 0;
		for (Edible e: brkfst) {
			if (e.getUnit().equals("ml"))
				dailyBeverages += e.getPortion();
		}
		for (Edible e: lunch) {
			if (e.getUnit().equals("ml"))
				dailyBeverages += e.getPortion();
		}
		for (Edible e: dinner) {
			if (e.getUnit().equals("ml"))
				dailyBeverages += e.getPortion();
		}
		return dailyBeverages;
	}
	
	
	
	
}
