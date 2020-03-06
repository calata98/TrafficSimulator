package simulator.factories;

import org.json.JSONObject;

import simulator.model.LightSwitchingStrategy;
import simulator.model.MostCrowdedStrategy;

public class MostCrowdedStrategyBuilder extends Builder<LightSwitchingStrategy> {

	private final int defaultTimeslot = 1;
	
	MostCrowdedStrategyBuilder(String type) {
		super(type);
	}

	@Override
	protected LightSwitchingStrategy createTheInstance(JSONObject data) {
		MostCrowdedStrategy aux;
		
		if(data.has("timeslot")) {
			aux = new MostCrowdedStrategy((int)data.get("timeslot"));
		}else {
			aux = new MostCrowdedStrategy(defaultTimeslot);
		}
		
		return aux;
	}

}
