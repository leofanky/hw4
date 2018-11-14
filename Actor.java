
import javax.json.*;
public class Actor implements Jsonable 
{
	public String name;
    public String character;
    public String Name1="Name";
    public String add2="As";
    

    public void fromJsonObject(JsonObject object)
    {
        this.name = object.getString("Name");
        this.character = object.getString("As");
    }

    @Override
    public String toJsonString() {
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
