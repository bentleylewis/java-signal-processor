package app;

import app.model.Signal;
import java.util.ArrayList; // Import ArrayList class
import java.util.Map;

import app.service.SignalService; // Import methods from SignalService class

import app.data.SignalDatabase; // Import database connection

public class Main {

	public static void main(String[] args) {

		SignalDatabase db = new SignalDatabase();
		db.createTable();
		
		 //create new Signal object, instance of Signal class
//		Signal signal = new Signal("Radar", 75, "ACTIVE");
		//insert new signal into DB
		//db.insertSignal(signal5);	
		//create instance of SignalService
		
		SignalService service = new SignalService();		
		

		//select all from dbSignals
		ArrayList<Signal> dbSignals = db.getAllSignals();
		
		
		ArrayList<Signal> activeSignals = service.getActiveSignals(dbSignals);
		
		for (Signal signal : activeSignals) {
		    System.out.println(signal);
		}
		
		System.out.println("----------------------------------------------");
		
		//print strongest signal using SignalService Method
		Signal strongestSignal = service.getStrongestSignal(dbSignals);
		System.out.println("This is the strongest Signal:" + strongestSignal);
		
		//print grouped, contains all signals from signalList sorted by type (ie. COMMS, RADAR)
		Map<String, ArrayList<Signal>> grouped = service.groupByType(dbSignals);
		
		//print type (key)
		for(String type : grouped.keySet()) {
			System.out.println("Type: " + type);
		
			//print signal (value)
			for(Signal signal: grouped.get(type)) {
				System.out.println(signal);
			}
		}
	}
}
