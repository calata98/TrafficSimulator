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
				throw new IllegalArgumentException("La velocidad maxima debe ser mayor a 0");
			}else {
				this.maxSpeed = maxSpeed;
			}
			
			if(contClass < 0 || contClass > 10) {
				throw new IllegalArgumentException("El grado de contaminacion debe estar entre 0 y 10");
			}else {
				this.contClass = contClass;
			}
			if(itinerary.size() < 2) {
				throw new IllegalArgumentException("La longitud de la lista del itinerario debe ser al menos 2");
			}else {
				readItinerary = Collections.unmodifiableList(new ArrayList<>(itinerary));
			}
			
			estado = VehicleStatus.PENDING;
	}
	
	void setSpeed(int s) {
		if(s < 0) {
			throw new IllegalArgumentException("La velocidad s debe ser positiva");
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
			throw new IllegalArgumentException("El grado de contaminacion c debe estar entre 0 y 10");
		}else {
			contClass = c;
		}
	}
	
	
	
	@Override
	void advance(int time) {
		
		if(estado.equals(VehicleStatus.TRAVELING)) {

			prevLocation = location;
			
			if((location + speed) < road.length) {
				location = location + speed;
			}else {
				location = road.length;
				estado = VehicleStatus.WAITING;
				speed = 0;
				road.destJunc.enter(this);
			}
			
			distanciaR += (location - prevLocation);
			int cont = (location - prevLocation) * contClass;
			
			contTotal += cont;
			road.addContamination(cont);
		}else {
			speed = 0;
		}
		

	}
	
	void moveToNextRoad() {
		if(estado.equals(VehicleStatus.WAITING) || estado.equals(VehicleStatus.PENDING)) {
			if(estado.equals(VehicleStatus.PENDING)) {
				road = readItinerary.get(0).exitRoads.get(readItinerary.get(1));
				System.out.println(_id + " entra 1º en " + road + " desde " + readItinerary.get(0));
				road.enter(this);
				estado = VehicleStatus.TRAVELING;
			}else {
				road.exit(this);
				if(readItinerary.indexOf(road.destJunc) >= readItinerary.size() - 1){
					estado = VehicleStatus.ARRIVED;
				}else {
					road = readItinerary.get(readItinerary.indexOf(road.destJunc)).roadTo(readItinerary.get(readItinerary.indexOf(road.destJunc) + 1));
					this.location = 0;
					road.enter(this);
					System.out.println(_id + " entra en " + road );
					estado = VehicleStatus.TRAVELING;
				}
			}
			
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
		if(estado != VehicleStatus.ARRIVED) {
			aux.put("road", road._id);
			aux.put("location", location);
		}
		
		return aux;
	}

}
