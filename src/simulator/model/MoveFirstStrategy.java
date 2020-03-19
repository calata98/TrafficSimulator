package simulator.model;

import java.util.ArrayList;
import java.util.List;

public class MoveFirstStrategy implements DequeuingStrategy{

	@Override
	public List<Vehicle> dequeue(List<Vehicle> q) {

		List<Vehicle> lista = new ArrayList<Vehicle>();
		System.out.println(q);
		lista.add(q.get(0));
		
		return lista;
	}

}
