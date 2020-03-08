package simulator.model;

import java.util.List;

import simulator.misc.Pair;

public class SetWeatherEvent extends Event {

	private List<Pair<String,Weather>> ws;
	
	public SetWeatherEvent(int time, List<Pair<String,Weather>> ws) {
		super(time);
		if(!ws.equals(null)) {
			this.ws = ws;
		}else {
			throw new IllegalArgumentException("ws no ser null");
		}
		
	}
	
	@Override
	void execute(RoadMap map) {

		for(int i = 0; i < ws.size(); i++) {
			if(!map.getRoad(ws.get(i).getFirst()).equals(null)) {
				map.getRoad(ws.get(i).getFirst()).setWeather(ws.get(i).getSecond());
			}else {
				throw new IllegalArgumentException("La carretera no existe en el mapa de carreteras");
			}
			
		}

	}

}
