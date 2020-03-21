package simulator.factories;

import org.json.JSONObject;

import simulator.model.Event;
import simulator.model.NewCityRoad;
import simulator.model.NewInterCityRoad;
import simulator.model.Weather;

public class NewCityRoadEventBuilder extends Builder<Event> {

	public NewCityRoadEventBuilder() {
		super("new_city_road");
	}

	
	@Override
	protected Event createTheInstance(JSONObject data) {
		Event aux;
		
		
		if(data.has("time") && data.has("id") && data.has("src") && data.has("dest") && data.has("length") && data.has("co2limit") && data.has("maxspeed") && data.has("weather")) {
			aux = new NewCityRoad(data.getInt("time"),data.getString("id"), data.getString("src"), data.getString("dest"),data.getInt("maxspeed"),data.getInt("co2limit"),data.getInt("length"), (Weather) Weather.valueOf((String) data.get("weather")));
		}else {
			aux =  null;
		}
		
		return aux;
	}
	
	
	
}
