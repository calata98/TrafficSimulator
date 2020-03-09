package simulator.model;

public class NewCityRoad extends NewRoadEvent {

	public NewCityRoad(int time, String id, String srcJun, String destJunc, int length, int co2Limit, int maxSpeed,
			Weather weather) {
		super(time, id, srcJun, destJunc, length, co2Limit, maxSpeed, weather);
	}
	

	
	@Override
	void execute(RoadMap map) {
		map.addRoad(new CityRoad(id,map.getJunction(srcJunc),map.getJunction(destJunc),length,co2Limit,maxSpeed,weather));
	}

}
