
import javax.json.*;
public class Writers implements Jsonable 
{

	 public String name;
	 public String type;
	 public String Name1="Name";
	 public String add2="Type";
	 public String character = type;

	    public void fromJsonObject(JsonObject object) 
	    {
	        this.name = object.getString("Name");
	        this.type = object.getString("Type");
	    }

	    @Override
	    public String toJsonString()
	    {
	        return toJsonObject().toString();
	    }

	    @Override
	    public JsonObject toJsonObject()
	    {
	        return Json.createObjectBuilder()
	            .add(Name1, name)
	            .add(add2, character)
	            .build();
	    }
}
