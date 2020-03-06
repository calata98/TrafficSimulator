package simulator.factories;

import org.json.JSONObject;

import simulator.model.Event;
import simulator.model.NewInterCityRoad;
import simulator.model.Weather;

public class NewInterCityRoadEventBuilder extends Builder<Event> {

	NewInterCityRoadEventBuilder(String type) {
		super(type);
		// TODO Auto-generated constructor stub
	}

	
	@Override
	protected Event createTheInstance(JSONObject data) {
		Event aux;
		
		
		if(data.has("time") && data.has("id") && data.has("src") && data.has("dest") && data.has("length") && data.has("co2limit") && data.has("maxspeed") && data.has("weather")) {
			aux = new NewInterCityRoad(data.getInt("time"),data.getString("id"), data.getString("src"), data.getString("dest"),data.getInt("length"),data.getInt("co2limit"),data.getInt("maxspeed"), (Weather)data.get("weather"));
		}else {
			aux =  null;
		}
		
		return aux;
	}
	
	
	
}
