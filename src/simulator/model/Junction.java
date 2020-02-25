package simulator.model;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;

public class Junction extends SimulatedObject {

	
	protected List<Road> enterRoads;
	protected Map<Junction,Road> exitRoads;
	protected List<List<Vehicle>> queueList;
	protected int currGreen;
	protected int lastSwitchingTime;
	protected LightSwitchingStrategy lsStrategy;
	protected DequeuingStrategy dqStrategy;
	protected int xCoor, yCoor;
	//protected Map<Road,List<Vehicle>> roadsQueue;
	
	
	Junction(String id, LightSwitchingStrategy lsStrategy, DequeuingStrategy
			dqStrategy, int xCoor, int yCoor) {
		super(id);
		
		if(dqStrategy != null) {
			this.dqStrategy = dqStrategy;
		}else {
			//Excepcion
		}
		if(lsStrategy != null) {
			this.lsStrategy = lsStrategy;
		}else {
			//Excepcion
		}
		
		if(xCoor >= 0) {
			this.xCoor = xCoor;
		}else {
			//Excepcion
		}
		if(yCoor >= 0) {
			this.yCoor = yCoor;
		}else {
			//Excepcion
		}
		
		this._id = id;
		
	}

	
	void addIncommingRoad(Road r) {
		
		if(r.destJunc.equals(this)) {
			enterRoads.add(r);
			List<Vehicle> cola = r.vehicles; //No sé si se hace así
			queueList.add(cola);
		}else {
			//Excepcion
		}
		
	}
	
	void addOutGoingRoad(Road r) {
		
		if(r.srcJunc.equals(this) && exitRoads.containsKey(r.destJunc)) {
			exitRoads.put(r.destJunc, r);
		}else {
			//Excepcion
		}
		
	}
	
	void enter(Vehicle v) {
		v.road.enter(v);
	}
	
	
	Road roadTo(Junction j) {
		
		if(exitRoads.containsKey(j)) {
			return exitRoads.get(j);
		}else {
			return null;
		}
		
	}
	
	@Override
	void advance(int time) {
		List<Vehicle> exitVehicles = dqStrategy.dequeue(enterRoads.get(currGreen).vehicles);
		
		for(int i = 0; i < exitVehicles.size(); i++) {
			exitVehicles.get(i).advance(time);
			exitVehicles.get(i).moveToNextRoad();
			enterRoads.get(currGreen).exit(exitVehicles.get(i));
		}
		
		int nextGreen = lsStrategy.chooseNextGreen(enterRoads, queueList, currGreen, lastSwitchingTime, time);
		
		if(nextGreen != currGreen) {
			currGreen = nextGreen;
		}
		
	}

	@Override
	public JSONObject report() {
		JSONObject aux = new JSONObject();
		aux.put("id", _id);
		if(currGreen == -1) {
			aux.put("green", "none");
		}else {
			aux.put("green", currGreen);
		}
		
		JSONArray enterRoadsQueue = new JSONArray();
		for(int i = 0; i < enterRoads.size(); i++) {
			JSONObject queue = new JSONObject();
			JSONArray vehicles = new JSONArray();
			for(int j = 0; j < enterRoads.get(i).vehicles.size(); j++) {
				vehicles.put(enterRoads.get(i).vehicles.get(j)._id);
			}
			queue.put("road", enterRoads.get(i)._id);
			queue.put("vehicles", vehicles);
			enterRoadsQueue.put(queue);
		}
		
		aux.put("queues", enterRoadsQueue);
		
		
		return aux;
	}

}
