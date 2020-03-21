package simulator.model;

public class NewInterCityRoad extends NewRoadEvent {

	public NewInterCityRoad(int time, String id, String srcJun, String destJunc, int maxSpeed, int co2Limit, int length,
			Weather weather) {
		super(time, id, srcJun, destJunc, maxSpeed, co2Limit, length, weather);
		
	}

	@Override
	void execute(RoadMap map) {
		map.addRoad(new InterCityRoad(id,map.getJunction(srcJunc),map.getJunction(destJunc),maxSpeed,co2Limit,length,weather));
	}

}
