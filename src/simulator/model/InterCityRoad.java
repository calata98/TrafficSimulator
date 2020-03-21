package simulator.model;

public class InterCityRoad extends Road {

	protected InterCityRoad(String id, Junction srcJunc, Junction destJunc, int maxSpeed, int contLimit, int length,
			Weather weather) {
		super(id, srcJunc, destJunc, maxSpeed, contLimit, length, weather);
	}
	
	

	@Override
	void reduceTotalContamination() {
		int x = 0;
		
		switch(weather) {
		case SUNNY: x = 2;
		break;
		case CLOUDY: x = 3;
		break;
		case RAINY: x = 10;
		break;
		case WINDY: x = 15;
		break;
		case STORM: x = 20;
		break;
			
		}
		int aux = (int) (((100.0 - x)/100.0) * contTotal);
		contTotal = aux;
	}

	@Override
	void updateSpeedLimit() {

		if(contTotal > contLimit) {
			currSpeedLimit = (int)(maxSpeed*0.5);
		}else {
			currSpeedLimit = maxSpeed; 
		}

	}

	@Override
	protected int calculateVehicleSpeed(Vehicle v) {
		int speed;
		if(weather.equals(Weather.STORM)) {
			speed = (int) Math.ceil((currSpeedLimit*0.8)); //Redondeado hacia arriba
		}else {
			speed = currSpeedLimit;
		}
		
		return speed;
	}

}
