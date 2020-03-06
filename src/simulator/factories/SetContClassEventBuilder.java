package simulator.factories;

import java.util.List;

import org.json.JSONObject;

import simulator.misc.Pair;
import simulator.model.Event;
import simulator.model.NewSetContClassEvent;

public class SetContClassEventBuilder extends Builder<Event> {

	SetContClassEventBuilder(String type) {
		super(type);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected Event createTheInstance(JSONObject data) {
		Event aux;
		
		if(data.has("time") && data.has("info")) {
			aux = new NewSetContClassEvent(data.getInt("time"), (List<Pair<String,Integer>>) data.get("info"));
		}else {
			aux = null;
		}	
		
		
		return aux;
	}

}
