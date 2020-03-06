package simulator.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.json.JSONObject;

import simulator.misc.SortedArrayList;

public class Simulator {

	private RoadMap roadMap; 
	private List<Event> eventList;
	private int time;
	
	
	public Simulator() {
		eventList = new SortedArrayList<Event>();
		time = 0;
		roadMap = new RoadMap(new ArrayList<>(),new ArrayList<>(),new ArrayList<>(),new HashMap<>(),new HashMap<>(),new HashMap<>());
	}
	
	
	public void addEvent(Event e) { ///Sin acabar
		eventList.add(e);
	}
	
	public void advance() {
		
		time++;
		for(int i = 0; i < eventList.size(); i++) {
			if(eventList.get(i)._time == time) {
				eventList.get(i).execute(roadMap);
				eventList.remove(i);
			}
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
