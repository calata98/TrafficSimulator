package simulator.factories;

import java.util.List;

import org.json.JSONObject;

import simulator.model.Event;
import simulator.model.NewVehicleEvent;

public class NewVehicleEventBuilder extends Builder<Event> {

	NewVehicleEventBuilder(String type) {
		super(type);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected Event createTheInstance(JSONObject data) {
		Event aux;
		
		if(data.has("time") && data.has("id") && data.has("maxspeed") && data.has("class") && data.has("itinerary")) {
			aux = new NewVehicleEvent(data.getInt("time"), data.getString("id"),data.getInt("maxspeed"),data.getInt("class"),(List<String>) data.get("itinerary"));
		}else {
			aux = null;
		}
		
		return aux;
	}

}
