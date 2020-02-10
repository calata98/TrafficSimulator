package simulator.model;

import java.util.List;

import org.json.JSONObject;

public abstract class Road extends SimulatedObject {

	
	private Junction srcJunc, destJunc;
	protected int length;
	protected int maxSpeed;
	protected int speedLimit;
	protected int contLimit;
	protected Weather weather;
	protected int contTotal;
	private List<Vehicle> vehicles;
	
	
	
	protected Road(String id, Junction srcJunc, Junction destJunc, int maxSpeed,
			int contLimit, int length, Weather weather) {
			super(id);
			//La constructora debe añadir la carretera como carretera saliente a su cruce origen, y como carretera entrante a su cruce destino.
			
			if(maxSpeed <= 0) {
				//Excepcion
			}else {
				this.maxSpeed = maxSpeed;
				speedLimit = maxSpeed;
			}
			
			if(contLimit < 0) {
				//Excepcion
			}else {
				this.contLimit = contLimit;
			}
			
			if(length <= 0) {
				//Excepcion
			}else {
				this.length = length;
			}
			
			if(weather == null) {
				//Excepcion
			}else {
				this.weather = weather;
			}
			
			if(srcJunc == null) {
				//Excepcion
			}else {
				this.srcJunc = srcJunc;
			}
			
			if(destJunc == null) {
				//Excepcion
			}else {
				this.destJunc = destJunc;
			}
			
	}
	
	protected void enter(Vehicle v) {
		
		if(v.location != 0 || v.speed != 0) {
			//Excepcion
		}else {
			vehicles.add(v);
		}
	}
	
	protected void exit(Vehicle v) {
		vehicles.remove(v);
	}
	
	protected void setWeather(Weather w) {
		if(w == null) {
			//Excepcion
		}else {
			weather = w;
		}
	}
	
	protected void addContamination(int c) {
		if(c < 0) {
			//Excepcion
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
		
		//Ordenar por localizacion

	}

	@Override
	public JSONObject report() {
		// TODO Auto-generated method stub
		return null;
	}

}
