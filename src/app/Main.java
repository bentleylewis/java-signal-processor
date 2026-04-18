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
		

		
		Signal signal1 = new Signal("Radar", 75, "ACTIVE");
		Signal signal2 = new Signal("Radar", 77, "ACTIVE");
		Signal signal3 = new Signal("COMM", 63, "ACTIVE");
		Signal signal4 = new Signal("COMM", 84, "INACTIVE");
		ArrayList<Signal> signalList = new ArrayList<Signal>();
		
		//Add signals to signalList(ArrayList)
		signalList.add(signal1);
		signalList.add(signal2);
		signalList.add(signal3);
		signalList.add(signal4);
		
		for(Signal signal: signalList) {
			db.insertSignal(signal);
		}
	
	
		
		// For-each loop to iterate through signalList
		for(Signal signal: signalList) {
			System.out.println(signal);
		}
		
		//create instance of SignalService
		SignalService service = new SignalService();
		
		//print All Active signals using SignalService Method
		ArrayList<Signal> activeSignals = service.getActiveSignals(signalList);
		
		for(Signal signal: activeSignals) {
			System.out.println(signal);
		}
		
		//print strongest signal using SignalService Method
		Signal strongestSignal = service.getStrongestSignal(signalList);
		System.out.println("This is the strongest Signal:" + strongestSignal);
		
		//print grouped, contains all signals from signalList sorted by type (ie. COMMS, RADAR)
		Map<String, ArrayList<Signal>> grouped = service.groupByType(signalList);
		
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
