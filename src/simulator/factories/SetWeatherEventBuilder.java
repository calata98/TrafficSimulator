package simulator.factories;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import simulator.misc.Pair;
import simulator.model.Event;
import simulator.model.SetWeatherEvent;
import simulator.model.Weather;

public class SetWeatherEventBuilder extends Builder<Event> {

	public SetWeatherEventBuilder() {
		super("set_weather");
		// TODO Auto-generated constructor stub
	}

	@Override
	protected Event createTheInstance(JSONObject data) {
		Event aux;
		
		if(data.has("time") && data.has("info")) {
			
			List<Pair<String,Weather>> lista = new ArrayList<>();     
			JSONArray arrayP = (JSONArray) data.get("info"); 
			
			for (int i=0;i<arrayP.length();i++){ 
				lista.add(new Pair<String,Weather>(arrayP.getJSONObject(i).getString("road"), Weather.valueOf((String)arrayP.getJSONObject(i).get("weather"))));
			} 
			
			aux = new SetWeatherEvent(data.getInt("time"), lista);
		}else {
			aux = null;
		}
		
		return aux;
	}

}
