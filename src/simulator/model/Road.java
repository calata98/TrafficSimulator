package simulator.model;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

public abstract class Road extends SimulatedObject {

	
	protected Junction srcJunc, destJunc;
	protected int length;
	protected int maxSpeed;
	protected int currSpeedLimit;
	protected int contLimit;
	protected Weather weather;
	protected int contTotal;
	protected List<Vehicle> vehicles;
	
	
	
	Road(String id, Junction srcJunc, Junction destJunc, int maxSpeed,
			int contLimit, int length, Weather weather) {
			super(id);
			
			vehicles = new ArrayList<Vehicle>();
			
			if(maxSpeed <= 0) {
				throw new IllegalArgumentException("La velocidad maxima debe ser mayor a 0");
			}else {
				this.maxSpeed = maxSpeed;
				currSpeedLimit = maxSpeed;
			}
			
			if(contLimit < 0) {
				throw new IllegalArgumentException("El limite de contaminacion debe ser mayor a 0");
			}else {
				this.contLimit = contLimit;
			}
			
			if(length <= 0) {
				throw new IllegalArgumentException("La longitud de la carretera debe ser mayor a 0");
			}else {
				this.length = length;
			}
			
			if(weather == null) {
				throw new IllegalArgumentException("El tiempo no puede ser null");
			}else {
				this.weather = weather;
			}
			
			if(destJunc == null) {
				throw new IllegalArgumentException("Cruce destino no puede ser null");
			}else {
				this.destJunc = destJunc;
				this.destJunc.addIncommingRoad(this);
			}
			
			if(srcJunc == null) {
				throw new IllegalArgumentException("Cruce origen no pueder ser null");
			}else {
				this.srcJunc = srcJunc;
				this.srcJunc.addOutGoingRoad(this);
			}
			
			
	}
	
	void enter(Vehicle v) {
		
		if(v.location != 0 || v.speed != 0) {
			throw new IllegalArgumentException("No se ha podido añadir el vehiculo");
		}else {
			vehicles.add(v);
		}
	}
	
	void exit(Vehicle v) {
		vehicles.remove(v);
	}
	
	void setWeather(Weather w) {
		if(w == null) {
			throw new IllegalArgumentException("El tiempo w no puede ser null");
		}else {
			weather = w;
		}
	}
	
	void addContamination(int c) {
		if(c < 0) {
			throw new IllegalArgumentException("La contaminacion c debe ser mayor a 0");
		}else {
			contTotal += c;
		}
	}
	
	abstract void reduceTotalContamination();
	abstract void updateSpeedLimit();
	abstract int calculateVehicleSpeed(Vehicle v);

	@Override
	protected void advance(int time) {

		reduceTotalContamination();
		updateSpeedLimit();
		
		for(int i = 0; i < vehicles.size(); i++) {
			Vehicle aux = vehicles.get(i);
			aux.setSpeed(calculateVehicleSpeed(aux));
			aux.advance(time);
		}
	}

	@Override
	public JSONObject report() {

		JSONObject aux = new JSONObject();
		
		aux.put("id", _id);
		aux.put("speedlimit", currSpeedLimit);
		aux.put("weather", weather);
		aux.put("co2", contTotal);
		JSONArray carIds = new JSONArray();
		for(int i = 0; i < vehicles.size(); i++) {
			carIds.put(vehicles.get(i).getId());
		}
		aux.put("vehicles", carIds);
		
		
		return aux;
	}

}
