package simulator.model;

import java.util.ArrayList;
import java.util.List;

public class NewVehicleEvent extends Event {
	
	private String id;
	private int maxSpeed, contClass;
	private List<String> itinerary;

	public NewVehicleEvent(int time, String id, int maxSpeed, int
			contClass, List<String> itinerary) {
			super(time);
			this.id = id;
			this.contClass = contClass;
			this.maxSpeed = maxSpeed;
			this.itinerary = itinerary;
	}

	@Override
	void execute(RoadMap map) {
		
		List<Junction> itinerario = new ArrayList<>();
		for(int i = 0; i < itinerary.size(); i++) {
			itinerario.add(map.getJunction(itinerary.get(i)));
		}
		
		map.addVehicle(new Vehicle(id,maxSpeed,contClass,itinerario));
		map.getVehicle(id).moveToNextRoad();
	}

}
