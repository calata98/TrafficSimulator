package simulator.model;

import java.util.List;

import org.json.JSONObject;

import simulator.misc.SortedArrayList;

public class TrafficSimulator {

	private RoadMap roadMap; 
	private List<Event> eventList;
	private int time;
	
	
	public TrafficSimulator() {
		eventList = new SortedArrayList<Event>();
		time = 0;
		roadMap = new RoadMap();
	}
	
	
	public void addEvent(Event e) {
		eventList.add(e);
	}
	
	public void advance() {
		
		time++;
		while(eventList.size() > 0 && eventList.get(0)._time == time) {  //Se comprueba que haya eventos por ejecutar y que se ejecuten en el tick correspondiente
				eventList.get(0).execute(roadMap);
				eventList.remove(0);
		}
		List<Junction> junctions = roadMap.getJunctions();
		for(int i = 0; i < junctions.size(); i++) {
			junctions.get(i).advance(time);
		}
		
		
		List<Road> roads = roadMap.getRoads();
		for(int i = 0; i < roads.size(); i++) {
			roads.get(i).advance(time);
		}
		
	}
	
	public void reset() {
		roadMap.reset();
		eventList.clear();
		time = 0;
	}
	
	public JSONObject report() {
		
		JSONObject aux = new JSONObject();
		aux.put("time", time);
		aux.put("state", roadMap.report());
		
		return aux;
	}
	
	
}
