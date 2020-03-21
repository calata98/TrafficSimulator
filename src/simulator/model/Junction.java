package simulator.model;

import java.util.ArrayList;
import java.util.HashMap;
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
	
	
	Junction(String id, LightSwitchingStrategy lsStrategy, DequeuingStrategy
			dqStrategy, int xCoor, int yCoor) {
		super(id);
		
		if(dqStrategy != null) {
			this.dqStrategy = dqStrategy;
		}else {
			throw new IllegalArgumentException("DequeuingStrategy no puede ser null");
		}
		if(lsStrategy != null) {
			this.lsStrategy = lsStrategy;
		}else {
			throw new IllegalArgumentException("LightSwitchingStrategy no puede ser null");
		}
		
		if(xCoor >= 0) {
			this.xCoor = xCoor;
		}else {
			throw new IllegalArgumentException("La coordenada x debe ser positiva");
		}
		if(yCoor >= 0) {
			this.yCoor = yCoor;
		}else {
			throw new IllegalArgumentException("La coordenada y debe ser positiva");
		}
		
		this._id = id;
		
		exitRoads = new HashMap<Junction,Road>();
		enterRoads = new ArrayList<Road>();
		queueList = new ArrayList<>();
		currGreen = -1;
	}

	
	void addIncommingRoad(Road r) {
		
		if(r.destJunc.equals(this)) {
			enterRoads.add(r);
			queueList.add(new LinkedList<>(r.vehicles));
		}else {
			throw new IllegalArgumentException("La carretera r debe ser entrante");
		}
		
	}
	
	void addOutGoingRoad(Road r) {
		
		if(r.srcJunc.equals(this) && !exitRoads.containsKey(r.destJunc)) {
			exitRoads.put(r.destJunc, r);
		}else {
			throw new IllegalArgumentException("La carretera r debe ser saliente");
		}
		
	}
	
	void enter(Vehicle v) {
		queueList.get(enterRoads.indexOf(v.road)).add(v);
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
		if(enterRoads.size() > 0) { 
			if(currGreen != -1 && queueList.size() > 0) { //Si no hay un semaforo en verde y la lista de colas está vacia los coches no avanzan
				List<Vehicle> exitVehicles = dqStrategy.dequeue(queueList.get(currGreen));
				if(exitVehicles != null) { //Si exitVehicles es null significa que no hay coches en la lista de colas que puedan salir del cruce
					for(int i = 0; i < exitVehicles.size(); i++) {
						exitVehicles.get(i).advance(time);
						exitVehicles.get(i).moveToNextRoad();
						queueList.get(currGreen).remove(exitVehicles.get(i)); //Se saca el coche de la lista de colas por haber salido del cruce
					}
				}
			}
			
			int nextGreen = lsStrategy.chooseNextGreen(enterRoads, queueList, currGreen, lastSwitchingTime, time);
			
			if(nextGreen != currGreen) {
				currGreen = nextGreen;
				lastSwitchingTime = time;
			}
		}
	}

	@Override
	public JSONObject report() {
		JSONObject aux = new JSONObject();
		aux.put("id", _id);
		if(currGreen == -1) {
			aux.put("green", "none");
		}else {
			aux.put("green", enterRoads.get(currGreen));
		}
		
		JSONArray enterRoadsQueue = new JSONArray();
		for(int i = 0; i < enterRoads.size(); i++) {  
			JSONObject queue = new JSONObject();
			JSONArray vehicles = new JSONArray();
			for(int j = 0; j < queueList.get(i).size(); j++) {
				vehicles.put(queueList.get(i).get(j));
			}
			queue.put("road", enterRoads.get(i)._id);
			queue.put("vehicles", vehicles);
			enterRoadsQueue.put(queue);
		}
		
		aux.put("queues", enterRoadsQueue);
		
		
		return aux;
	}

}
