package simulator.factories;

import java.util.List;

import org.json.JSONObject;

import simulator.misc.Pair;
import simulator.model.Event;
import simulator.model.SetWeatherEvent;
import simulator.model.Weather;

public class SetWeatherEventBuilder extends Builder<Event> {

	SetWeatherEventBuilder(String type) {
		super(type);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected Event createTheInstance(JSONObject data) {
		Event aux;
		
		if(data.has("time") && data.has("info")) {
			aux = new SetWeatherEvent(data.getInt("time"), (List<Pair<String,Weather>>) data.get("info"));
		}else {
			aux = null;
		}
		
		return aux;
	}

}
