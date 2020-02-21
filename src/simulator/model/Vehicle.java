package simulator.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.json.JSONObject;

public class Vehicle extends SimulatedObject {
	
	private List<Junction> itinerary;
	private int maxSpeed;
	protected int speed;
	private VehicleStatus estado;
	protected Road road;
	protected int location;
	private int prevLocation;
	protected int contClass;
	private int contTotal;
	private int distanciaR;
	protected List<Junction> readItinerary;

	Vehicle(String id, int maxSpeed, int contClass,
			List<Junction> itinerary) {
			super(id);
			
			if(maxSpeed <= 0) {
				//Excepcion
			}else {
				this.maxSpeed = maxSpeed;
			}
			
			if(contClass < 0 || contClass > 10) {
				//Excepcion
			}else {
				this.contClass = contClass;
			}
			if(itinerary.size() < 2) {
				//Excepcion
			}else {
				readItinerary = Collections.unmodifiableList(new ArrayList<>(itinerary));
			}
	}
	
	void setSpeed(int s) {
		if(s < 0) {
			//Excepcion
		}else {
			if(s <= maxSpeed) {
				speed = s;
			}else {
				speed = maxSpeed;
			}
		}
	}
	
	void setContaminationClass(int c) {
		if(c < 0 || c > 10) {
			//Excepcion
		}else {
			contClass = c;
		}
	}
	
	
	
	@Override
	void advance(int time) {
		
		if(estado.equals(VehicleStatus.TRAVELING)) {

			prevLocation = location;
			
			if((location + speed) <= road.length) {
				location = location + speed;
			}else {
				location = road.length;
			}
			
			contTotal = (location - prevLocation) * contClass;
			
			if(location == road.length) {
				estado = VehicleStatus.WAITING;
			}
		}
		

	}
	
	void moveToNextRoad() {
		
		if(!estado.equals(VehicleStatus.WAITING) && !estado.equals(VehicleStatus.PENDING)) {
			//Excepcion
		}
		
		
		
	}

	@Override
	public JSONObject report() {

		JSONObject aux = new JSONObject();
		
		aux.put("id", _id);
		aux.put("speed", speed);
		aux.put("distance", distanciaR);
		aux.put("co2", contTotal);
		aux.put("class", contClass);
		aux.put("status", estado);
		aux.put("road", road._id);
		aux.put("location", location);
		
		return aux;
	}

}
