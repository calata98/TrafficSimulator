package simulator.factories;

import org.json.JSONObject;

import simulator.model.DequeuingStrategy;
import simulator.model.Event;
import simulator.model.LightSwitchingStrategy;
import simulator.model.NewJunctionEvent;

public class NewJunctionEventBuilder extends Builder<Event> {

	
	
	private Factory<DequeuingStrategy> dqsFactory;
	private Factory<LightSwitchingStrategy> lssFactory;
	
	
	public NewJunctionEventBuilder(Factory<LightSwitchingStrategy>
	lssFactory, Factory<DequeuingStrategy> dqsFactory) {
		super("new_junction");
		this.dqsFactory = dqsFactory;
		this.lssFactory = lssFactory;
	}

	@Override
	protected Event createTheInstance(JSONObject data) {
		
		Event aux;
		
		if(data.has("time") && data.has("id") && data.has("coor") && data.has("ls_strategy") && data.has("dq_strategy")) {
			aux = new NewJunctionEvent(data.getInt("time"),data.getString("id"), lssFactory.createInstance((JSONObject) data.get("ls_strategy")),dqsFactory.createInstance((JSONObject) data.get("dq_strategy")), (int) data.getJSONArray("coor").get(0), (int) data.getJSONArray("coor").get(1));
		}else {
			aux = null;
		}
		
		return aux;
	}

}
