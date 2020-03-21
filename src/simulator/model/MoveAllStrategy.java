package simulator.model;

import java.util.List;

public class MoveAllStrategy implements DequeuingStrategy{

	@Override
	public List<Vehicle> dequeue(List<Vehicle> q) {

		List<Vehicle> lista;
		
		if(q.isEmpty()) {
			q.remove(0);
			lista = q;
		}else {
			lista = null;
		}
		
		
		
		return lista;
	}

}
