package simulator.model;

public class NewJunctionEvent extends Event {

	
	
	private String id;
	private LightSwitchingStrategy lsStrategy;
	private DequeuingStrategy dqStrategy;
	private int xCoor, yCoor;
	
	public NewJunctionEvent(int time, String id, LightSwitchingStrategy
			lsStrategy, DequeuingStrategy dqStrategy, int xCoor, int yCoor) {
			super(time);
			this.id = id;
			this.dqStrategy = dqStrategy;
			this.lsStrategy = lsStrategy;
			this.xCoor = xCoor;
			this.yCoor = yCoor;
	}
	
	@Override
	void execute(RoadMap map) {
		map.addJunction(new Junction(id,lsStrategy,dqStrategy,xCoor,yCoor));
	}

}
