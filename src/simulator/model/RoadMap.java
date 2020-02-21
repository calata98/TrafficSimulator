package simulator.model;

import java.util.List;
import java.util.Map;

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
			//Excepcion
		}
			
	}
	
	protected void addVehicle(Vehicle v) {
		for(int i = 0; i < v.readItinerary.size(); i++) {
			for(int j = 0; j < roads.size(); j++) {
				if(!v.readItinerary.get(i).equals(roads.get(j).srcJunc)) {
					
				}
			}
			
			
		}
		//if(!vehicleMap.containsKey(v._id) || roadMap.containsValue(v.readItinerary))
		roadMap.containsValue(v.readItinerary);
	}
	
	
}
