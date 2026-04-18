package app.service;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import app.model.Signal;

//Get all active signals
public class SignalService {

	//method to return all active signals in ArrayList
	public ArrayList<Signal> getActiveSignals(ArrayList<Signal> signalList) {
		
		//create ArrayList "activeSignals" that will be returned
		ArrayList<Signal> activeSignals = new ArrayList<>();
		
		//loop through given ArrayList, append to activeSignals if ACTIVE status
		for(Signal signal: signalList) {
			if(signal.getStatus().equals("ACTIVE")) {
				activeSignals.add(signal);
			}
		}
		return activeSignals;
	}
	
	//method to find strongest signal in ArrayList
	public Signal getStrongestSignal(ArrayList<Signal> signalList) {
		
		//Strongest begins as first within ArrayList
		Signal strongest = signalList.get(0);
		
		//For each compare strength
		for(Signal signal: signalList) {
			if(signal.getStrength() > strongest.getStrength()) {
				strongest = signal;
			}
		}
		
		return strongest;
	}
	
	// Method groupByType takes Array List of Signal and returns map
	public Map<String, ArrayList<Signal>> groupByType(ArrayList<Signal> signalList) {
		
		//create Map "typeMap" that will be returned
		Map<String, ArrayList<Signal>> typeMap = new HashMap<>();
		
		for(Signal signal: signalList) {
			String type = signal.getType();
			if(!typeMap.containsKey(type)) {
				typeMap.put(type, new ArrayList<>());
			}
			typeMap.get(type).add(signal);
		}
		return typeMap;
	}
}
