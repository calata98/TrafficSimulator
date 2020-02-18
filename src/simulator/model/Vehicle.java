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
	private List<Junction> readItinerary;

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
		
		aux.append("id", _id);
		aux.append("speed", speed);
		aux.append("distance", distanciaR);
		aux.append("co2", contTotal);
		aux.append("class", contClass);
		aux.append("status", estado);
		aux.append("road", road._id);
		aux.append("location", location);
		
		return aux;
	}

}
