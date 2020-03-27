import java.io.Serializable;

public class Edible implements Serializable{
	
	
	private String food;
	private double portion;
	private String unit;
	private int cal;
	private int fat;
	private int carbo;
	private int protein;
	
	public Edible() {
		this.food = "";
		this.portion = 0;
		this.unit = "";
		this.cal = 0;
		this.fat = 0;
		this.carbo = 0;
		this.protein = 0;
	}
	
	public Edible(String food, double portion, String unit, int cal, int fat, int carbo, int protein) {
		this.food = food;
		this.portion = portion;
		this.unit = unit;
		this.cal = cal;
		this.fat = fat;
		this.carbo = carbo;
		this.protein = protein;
	}

	public String getFood() {
		return food;
	}

	public void setFood(String food) {
		this.food = food;
	}

	public double getPortion() {
		return portion;
	}

	public void setPortion(double portion) {
		this.portion = portion;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}
		
	

	public int getCal() {
		return cal;
	}

	public void setCal(int cal) {
		this.cal = cal;
	}

	public int getFat() {
		return fat;
	}

	public void setFat(int fat) {
		this.fat = fat;
	}

	public int getCarbo() {
		return carbo;
	}

	public void setCarbo(int carbo) {
		this.carbo = carbo;
	}

	public int getProtein() {
		return protein;
	}

	public void setProtein(int protein) {
		this.protein = protein;
	}

	@Override
	public String toString() {
		return "Edible [food=" + food + ", portion=" + portion +  ", cal=" + cal + ", fat=" + fat
				+ ", carbo=" + carbo + ", protein=" + protein + "]";
	}
	
	
	
	
}
