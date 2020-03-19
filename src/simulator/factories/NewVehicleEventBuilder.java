package simulator.factories;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import simulator.model.Event;
import simulator.model.NewVehicleEvent;

public class NewVehicleEventBuilder extends Builder<Event> {

	public NewVehicleEventBuilder() {
		super("new_vehicle");
		// TODO Auto-generated constructor stub
	}

	@Override
	protected Event createTheInstance(JSONObject data) {
		Event aux;
		
		
		if(data.has("time") && data.has("id") && data.has("maxspeed") && data.has("class") && data.has("itinerary")) {
			
			List<String> itinerario = new ArrayList<String>();     
			JSONArray jArray = (JSONArray) data.get("itinerary"); 
			for (int i=0;i<jArray.length();i++){ 
				itinerario.add(jArray.getString(i));
			} 
			
			aux = new NewVehicleEvent(data.getInt("time"), data.getString("id"),data.getInt("maxspeed"),data.getInt("class"),itinerario);
			
		}else {
			aux = null;
		}
		
		return aux;
	}

}
