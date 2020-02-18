package simulator.model;

import java.util.List;

public class RoundRobinStrategy implements LightSwitchingStrategy{

	private int timeSlot;
	
	protected RoundRobinStrategy(int timeSlot) {
		this.timeSlot = timeSlot;
	}

	@Override
	public int chooseNextGreen(List<Road> roads, List<List<Vehicle>> qs, int currGreen, int lastSwitchingTime,
			int currTime) {

		int salida = 0;
		
		if(roads.isEmpty()) {
			salida = -1;
		}else if(currGreen == -1) {
				salida = 0;
		}else if(currTime-lastSwitchingTime < timeSlot){
				salida = currGreen;
		}else {
			salida = (currGreen + 1) % roads.size();
		}
			
		return salida;
	}
	
	
	
	
	
	
	
}
