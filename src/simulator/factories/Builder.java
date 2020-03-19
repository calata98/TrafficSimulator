package simulator.factories;

import org.json.JSONObject;

public abstract class Builder<T> {
	protected String _type;

	Builder(String type) {
		if (type == null)
			throw new IllegalArgumentException("Invalid type: " + type);
		else
			_type = type;
	}

	public T createInstance(JSONObject info) {

		T b = null;

		if (_type != null && _type.equals(info.getString("type"))) {
			b = createTheInstance(info.has("data") ? info.getJSONObject("data") : null);
			
			
			/*if(info.getJSONObject("data").has("id")) {
				System.out.println(info.getString("type") + " " + info.getJSONObject("data").getString("id"));
			}*/
			
		}
		
		return b;
	}

	protected abstract T createTheInstance(JSONObject data);
}
