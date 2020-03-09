package simulator.model;
import java.util.List;
import simulator.misc.Pair;

public class NewSetContClassEvent extends Event {

	private List<Pair<String,Integer>> cs;
	
	public NewSetContClassEvent(int time, List<Pair<String,Integer>> cs) {
		super(time);
		if(!cs.equals(null)) {
			this.cs = cs;
		}else {
			throw new IllegalArgumentException("cs no puede ser null");
		}
	}

	@Override
	void execute(RoadMap map) {

		for(int i = 0; i < cs.size(); i++) {
			if(!map.getVehicle(cs.get(i).getFirst()).equals(null)) {
				map.getVehicle(cs.get(i).getFirst()).setContaminationClass(cs.get(i).getSecond());
			}else {
				throw new IllegalArgumentException("El vehiculo no existe en el mapa de carreteras");
			}
		}

	}

}
