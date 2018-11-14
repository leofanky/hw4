import java.io.StringReader;
import javax.json.*;

public class Movie implements Jsonable 
{
    public String title;
    public int year;
    public String released;
    public int runtime;
    public String[] genres;
    public Director director;
    public Writers[] writers;
    public Actor[] actors;
    public String plot;
    public String[] languages;
    public String[] countries;
    public String awards;
    public String poster;
    public Ratings[] ratings;

    @Override
    public String toJsonString()
    {
        return toJsonObject().toString();
    }
    public JsonArrayBuilder stringBuilder(String[] S) 
    {
    	JsonArrayBuilder strBuilder = Json.createArrayBuilder();
    	for(int i=0;i<=S.length;i++)
    	{
    		strBuilder.add(S[i]); 		
    	}
		return strBuilder;   	
    }
    public JsonArrayBuilder classBuilder(Writers[] O)
    {
    	JsonArrayBuilder objBuilder = Json.createArrayBuilder();
    	for(int i=0;i<=O.length;i++)
    	{
    		objBuilder.add(O[i].toJsonObject()); 		
    	}
    	return objBuilder;  
    }
    public JsonArrayBuilder classBuilder(Actor[] O)
    {
    	JsonArrayBuilder objBuilder = Json.createArrayBuilder();
    	for(int i=0;i<=O.length;i++)
    	{
    		objBuilder.add(O[i].toJsonObject()); 		
    	}
    	return objBuilder;  
    }
    public JsonArrayBuilder classBuilder(Ratings[] O)
    {
    	JsonArrayBuilder objBuilder = Json.createArrayBuilder();
    	for(int i=0;i<=O.length;i++)
    	{
    		objBuilder.add(O[i].toJsonObject()); 		
    	}
    	return objBuilder;  
    }
    

    @Override
    public JsonObject toJsonObject()
    {
        JsonArrayBuilder genresBuilder = stringBuilder(genres);
        JsonArrayBuilder writersBuilder = classBuilder(writers);
        JsonArrayBuilder actorsBuilder = classBuilder(actors);
        JsonArrayBuilder languagesBuilder = stringBuilder(languages);       
        JsonArrayBuilder countriesBuilder = stringBuilder(countries);
        JsonArrayBuilder ratingsBuilder = classBuilder(ratings);
		JsonObjectBuilder letsbuild= Json.createObjectBuilder()
								   	 .add("Title", title)
								   	 .add("Year", year)
								   	 .add("Released", released)
								   	 .add("Runtime", runtime)
								   	 .add("Genres", genresBuilder)
								   	 .add("Director", director.toJsonObject())
								   	 .add("Writers", writersBuilder)
								   	 .add("Actors", actorsBuilder)
								   	 .add("Plot", plot)
								   	 .add("Languages", languagesBuilder)
								   	 .add("Countries", countriesBuilder)
								   	 .add("Awards", awards)
								   	 .add("Poster", poster)
								   	 .add("Ratings", ratingsBuilder);
		return letsbuild.build();               
	}
    

	public String[] stringGetter(JsonArray J)
    {
    	String container[] = new String[J.size()];
        for(int i = 0; i < J.size(); i ++) {
            container[i] = J.getString(i);
        }
    	return container;
    }
	
	public Object[] readJsonObj(JsonArray J, String className)
	{
		Object array[]= new Object[J.size()];
		if (className=="Writer")
		{
			array = new Writers[J.size()];
	        for(int i = 0; i < J.size(); i ++)
	        {
	            array[i] = new Writers();
	            ((Writers) array[i]).fromJsonObject(J.getJsonObject(i));
	        }
		}
		else if(className=="Actor")
		{
			array = new Actor[J.size()];
	        for(int i = 0; i < J.size(); i ++)
	        {
	            array[i] = new Actor();
	            ((Actor) array[i]).fromJsonObject(J.getJsonObject(i));
	        }
			
		}
		else if (className=="Ratings")
		{
			array = new Ratings[J.size()];
	        for(int i = 0; i < J.size(); i ++)
	        {
	            array[i] = new Ratings();
	            ((Ratings) array[i]).fromJsonObject(J.getJsonObject(i));
	        }
		}
		
		return array;
	}
	
    public void fromJson(String json) 
    {
        JsonReader reader = Json.createReader(new StringReader(json));
        JsonObject object = reader.readObject();
        this.title = object.getString("Title");
        this.year = object.getInt("Year");
        this.released = object.getString("Released");
        this.runtime = object.getInt("Runtime");
        JsonArray array = object.getJsonArray("Genres");
        this.genres = stringGetter(array);     
        this.director = new Director();
        this.director.fromJsonObject(object.getJsonObject("Director"));
        array = object.getJsonArray("Writers");
        this.writers = (Writers[]) readJsonObj(array,"Writer");
        array = object.getJsonArray("Actors");
        this.actors = (Actor[]) readJsonObj(array,"Actor");
        this.plot = object.getString("Plot");
        array = object.getJsonArray("Languages");
        this.languages = stringGetter(array);
        array = object.getJsonArray("Countries");
        this.countries = stringGetter(array);
        this.awards = object.getString("Awards");
        this.poster = object.getString("Poster");
        array = object.getJsonArray("Ratings");
        this.ratings =(Ratings[]) readJsonObj(array,"Ratings");
    }
    public static void main(String... args)
    {
        Movie value = new Movie();
        System.out.println(value.toJsonString());
    }
    
}
   