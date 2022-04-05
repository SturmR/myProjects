import java.util.*;

public class house {
	
	// Necessary Fields
	public int id;
	public int fullDuration;
	public double rating;
	
	TreeMap<Integer, house> houseTreeMap = new TreeMap<Integer, house>();
	TreeMap<Integer, house> occupiedTreeMap = new TreeMap<Integer, house>();
	
	// The house constructor
	public house(int id, int fullDuration, double rating) {
		this.id = id;
		this.fullDuration = fullDuration;
		this.rating = rating;
	}
	
	// Getters and Setters
	public int getId() {
		return this.id;
	}
	
	public int getFullDuration() {
		return this.fullDuration;
	}
	
	public double getRating() {
		return this.rating;
	}
	
	public void setFullDuration(int newFullDuration) {
		this.fullDuration = newFullDuration;
	}
	
	
	
	
}
