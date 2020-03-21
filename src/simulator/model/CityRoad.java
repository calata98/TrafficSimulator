package simulator.model;

public class CityRoad extends Road {

	protected CityRoad(String id, Junction srcJunc, Junction destJunc, int maxSpeed, int contLimit, int length,
			Weather weather) {
		super(id, srcJunc, destJunc, maxSpeed, contLimit, length, weather);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void reduceTotalContamination() {

		if(weather.equals(Weather.WINDY) || weather.equals(Weather.STORM)) {
			contTotal -= 10;
		}else {
			contTotal -= 2;
		}
		
		if(contTotal < 0) {
			contTotal = 0;
		}

	}

	

	@Override
	protected int calculateVehicleSpeed(Vehicle v) {
		
		return (int)Math.ceil((((11.0-v.contClass)/11.0)*currSpeedLimit));
	}

	@Override
	void updateSpeedLimit() {
	}

}
