package simulator.factories;

import org.json.JSONObject;
import simulator.model.LightSwitchingStrategy;
import simulator.model.RoundRobinStrategy;


public class RoundRobinStrategyBuilder extends Builder<LightSwitchingStrategy>{
	
	private final int defaultTimeslot = 1;

	public RoundRobinStrategyBuilder() {
		super("round_robin_lss");
	}

	@Override
	protected LightSwitchingStrategy createTheInstance(JSONObject data) {
		 RoundRobinStrategy aux;
		
		if(data.has("timeslot")) {
			aux = new RoundRobinStrategy((int)data.get("timeslot"));
		}else {
			aux = new RoundRobinStrategy(defaultTimeslot);
		}
		
		return aux;
	}

}
