package simulator.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;

public class RoadMap {
	
	protected List<Junction> junctions;
	protected List<Road> roads;
	protected List<Vehicle> vehicles;
	protected Map<String,Junction> junctionMap;
	protected Map<String,Road> roadMap;
	protected Map<String,Vehicle> vehicleMap;
	
	protected RoadMap(List<Junction> junctions, List<Road> roads, List<Vehicle> vehicles,
			Map<String, Junction> junctionMap, Map<String, Road> roadMap, Map<String, Vehicle> vehicleMap) {
		this.junctions = junctions;
		this.roads = roads;
		this.vehicles = vehicles;
		this.junctionMap = junctionMap;
		this.roadMap = roadMap;
		this.vehicleMap = vehicleMap;
	}
	
	protected void addJunction(Junction j) {
		if(!junctionMap.containsKey(j._id)) {
			junctions.add(j);
			junctionMap.put(j._id, j);
		}
	}
	
	protected void addRoad(Road r) {
		if(!roadMap.containsKey(r._id) || (junctionMap.containsValue(r.srcJunc) && junctionMap.containsValue(r.destJunc))) {
			roads.add(r);
			roadMap.put(r._id, r);
		}else {
			throw new IllegalArgumentException("No se ha podido añadir la carretera");
		}
			
	}
	
	
	
	protected void addVehicle(Vehicle v) {
		
		boolean itineraryOk = true;
		
		for(int i = 0; i < v.readItinerary.size() - 1; i++) {
			//System.out.println(v.readItinerary.get(i) + " " + v.readItinerary.get(i + 1));
			Road road = v.readItinerary.get(i).roadTo(v.readItinerary.get(i + 1));
			itineraryOk = road != null;
		}
		
		if(!vehicleMap.containsKey(v._id) && itineraryOk) {
			vehicles.add(v);
			vehicleMap.put(v._id, v);
		}else {
			throw new IllegalArgumentException("No se ha podido añadir el vehiculo");
		}
		
	}
	
	public Junction getJunction(String id) {
		if(junctionMap.containsKey(id)) {
			return junctionMap.get(id);
		}else {
			return null;
		}
	}
	
	
	public Road getRoad(String id) {
		if(roadMap.containsKey(id)) {
			return roadMap.get(id);
		}else {
			return null;
		}
	}
	
	public Vehicle getVehicle(String id) {
		if(vehicleMap.containsKey(id)) {
			return vehicleMap.get(id);
		}else {
			return null;
		}
	}
	
	public List<Junction>getJunctions(){
		return Collections.unmodifiableList(new ArrayList<>(junctions));
	}
	
	public List<Road>getRoads() {
		return Collections.unmodifiableList(new ArrayList<>(roads));
	}
	
	public List<Vehicle>getVehicles(){
		return Collections.unmodifiableList(new ArrayList<>(vehicles));
	}
	
	protected void reset() {
		junctions.clear();
		roads.clear();
		vehicles.clear();
		junctionMap.clear();
		roadMap.clear();
		vehicleMap.clear();
	}
	
	public JSONObject report() {
		JSONObject aux = new JSONObject();
		
		JSONArray junctionsReport = new JSONArray();
		for(int i = 0; i < junctions.size(); i ++) {
			junctionsReport.put(junctions.get(i).report());
		}
		aux.put("junctions", junctionsReport);
		
		JSONArray roadsReport = new JSONArray();
		for(int i = 0; i < roads.size(); i++) {
			roadsReport.put(roads.get(i).report());
		}
		aux.put("roads", roadsReport);
		
		JSONArray vehiclesReport = new JSONArray();
		for(int i = 0; i < vehicles.size(); i++) {
			vehiclesReport.put(vehicles.get(i).report());
		}
		aux.put("vehicles", vehiclesReport);
		
		
		return aux;
		
	}
	
	
	
	
}
