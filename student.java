import java.util.*;

public class student {
	
	// Necessary Fields
	public int id;
	public String name;
	public int remainingDuration;
	public double requestedRating;
	
	TreeMap<Integer, student> studentTreeMap = new TreeMap<Integer, student>();
	TreeMap<Integer, student> allocatedTreeMap = new TreeMap<Integer, student>();
	TreeMap<Integer, student> StudentsToBeAllocated = new TreeMap<Integer, student>();
	TreeMap<Integer, student> outputTreeMap = new TreeMap<Integer, student>();
	
	// The student constructor
	public student(int id, String name, int remainingDuration, double requestedRating) {
		this.id = id;
		this.name = name;
		this.remainingDuration = remainingDuration;
		this.requestedRating = requestedRating;
	}
	
	// Getters and Setters
	public int getId() {
		return this.id;
	}
	
	public String getName() {
		return this.name;
	}
	
	public int getRemainingDuration() {
		return this.remainingDuration;
	}
	
	public double getRequestedRating() {
		return this.requestedRating;
	}
	
	public void setDuration(int newRemainingDuration) {
		this.remainingDuration = newRemainingDuration;
	}
	
	
	
	
	
	
	
	
	
	
}
