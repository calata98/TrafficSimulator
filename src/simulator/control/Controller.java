package simulator.control;


import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

import simulator.factories.Factory;
import simulator.model.Event;
import simulator.model.TrafficSimulator;

public class Controller {
	
	
	private TrafficSimulator simulator;
	private Factory<Event> eventsFactory;
	
	public Controller(TrafficSimulator sim, Factory<Event> eventsFactory)
	{
		
		if(sim.equals(null)) {
			throw new IllegalArgumentException("TrafficSimulator no puede ser null");
		}else {
			this.simulator = sim;
		}
		
		if(eventsFactory.equals(null)) {
			throw new IllegalArgumentException("Events Factory no puede ser null");
		}else {
			this.eventsFactory = eventsFactory;
		}
		
		
	}
	
	
	public void loadEvents(InputStream in) {
		
		JSONObject jo = new JSONObject(new JSONTokener(in));
		
		if(jo.has("events")) {
			JSONArray eventos = jo.getJSONArray("events");
			
			for(int i = 0; i < eventos.length(); i++) {
				simulator.addEvent(eventsFactory.createInstance(eventos.getJSONObject(i)));
			}
			
		}else {
			throw new IllegalArgumentException("La estructura JSON de los eventos no es válida");
		}
	}
	
	
	
	public void run(int n, OutputStream out) {

		
		JSONArray estados = new JSONArray();
		JSONObject outputObject = new JSONObject();

		
		for(int i = n; i > 0; i--) {
			simulator.advance();
			estados.put(simulator.report());
		}
		
		
		
		outputObject.put("states", estados);
		
		
		PrintStream writer = new PrintStream(out);
		writer.print(outputObject.toString());
		
		
		
	}
	
	public void reset() {
		simulator.reset();
	}
	
	
}
