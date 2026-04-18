package app.model;

public class Signal {
// Define attributes
	int id;
	String type;
	int strength;
	String status;
	
	//Constructor for reading from DB
	public Signal(int id, String type, int strength, String status) {
		this.id = id;
		this.type = type;
		this.strength = strength;
		this.status = status;
	}

	//For creating new signals // utilize constructor overloading
	public Signal(String type, int strength, String status) {
		this.type = type;
		this.strength = strength;
		this.status = status;
	}
	
	// Override toString method to accurately display object
	@Override
	public String toString() {
		return "Signal [id=" + id + ", type=" + type + ", strength=" + strength + ", status=" + status + "]";
	}
	
	
	//Getter method to return status from Signal object
	public String getStatus() {
		return status;
	}
	
	//Getter method to return strength (int) from Signal object
	public int getStrength() {
		return strength;
	}
	
	public String getType() {
		return type;
	}
}
