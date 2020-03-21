package simulator.model;

import java.util.List;

public class MostCrowdedStrategy implements LightSwitchingStrategy{

	private int timeSlot;
	
	public MostCrowdedStrategy(int timeSlot) {
		this.timeSlot = timeSlot;
	}
	
	@Override
	public int chooseNextGreen(List<Road> roads, List<List<Vehicle>> qs, int currGreen, int lastSwitchingTime,
			int currTime) {

		int salida = 0;
		
		if(roads.isEmpty()) {
			salida = -1;
		}else if(currGreen == -1){
			int maxQueue = 0;
			int roadMaxQueue = 0;
			for(int i = 0; i < qs.size(); i++) {
				if(qs.get(i).size() > maxQueue) {
					maxQueue = qs.get(i).size();
					roadMaxQueue = i;
				}
			}
			salida = roadMaxQueue;
		}else if(currTime-lastSwitchingTime < timeSlot) {
			salida = currGreen;
		}else {
			int maxQueue = 0;
			int roadMaxQueue = 0;
			for(int i = 0; i < ((currGreen + 1) % qs.size()); i++) {
				if(qs.get(i).size() > maxQueue) {
					maxQueue = qs.get(i).size();
					roadMaxQueue = i;
				}
			}
			salida = roadMaxQueue;
		}
		
		return salida;
	}

}
