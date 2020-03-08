package simulator.factories;

import org.json.JSONObject;

import simulator.model.DequeuingStrategy;
import simulator.model.Event;
import simulator.model.LightSwitchingStrategy;
import simulator.model.NewJunctionEvent;

public class NewJunctionEventBuilder extends Builder<Event> {

	NewJunctionEventBuilder(String type) {
		super(type);
		// TODO Auto-generated constructor stub
	}
	
	/*public NewJunctionEventBuilder(Factory<LightSwitchingStrategy>
	lssFactory, Factory<DequeuingStrategy> dqsFactory) {
		
	}*/

	@Override
	protected Event createTheInstance(JSONObject data) {
		
		Event aux;

		if(data.has("time") && data.has("id") && data.has("coor") && data.has("ls_strategy") && data.has("dq_strategy")) {
			aux = new NewJunctionEvent(data.getInt("time"),data.getString("id"), (LightSwitchingStrategy) data.get("ls_strategy"),(DequeuingStrategy) data.get("dq_strategy"), (int) data.getJSONArray("coor").get(0), (int) data.getJSONArray("coor").get(1));
		}else {
			aux = null;
		}
		
		return aux;
	}

}
