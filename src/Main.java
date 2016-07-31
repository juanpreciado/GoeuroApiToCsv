import goeurocustomexceptions.GoeuroReadingException;
import goeurocustomexceptions.GoeuroWritingException;
import goeuroreader.ApiGoeuroReader;
import goeurowriter.GoeuroWriter;
import org.json.JSONArray;

public class Main {

    public static void main(String[] args) {

        try {
            ApiGoeuroReader reader = new ApiGoeuroReader();
            reader.setCityName(args[0]);
            JSONArray jsonArray = reader.readFromApi();
            //Since the fields latitude and longitud are nested inside the object geo_position, we will the notation
            //parent.child, so for instance for the case "latitude", the column name will be "geo_position.latitude"
            String[] columnNames = {"_id", "name", "type", "geo_position.latitude", "geo_position.longitude"};
            GoeuroWriter writer = new GoeuroWriter();
            writer.fromJSONtoCSV(jsonArray, columnNames);

        }
        catch (ArrayIndexOutOfBoundsException e){
            System.out.println("Error: I donn't know which city are you refering to; USAGE: java -jar HelloWorldOpenjdk.jar city_name\n");
        }
        catch (GoeuroReadingException e) {
            e.printStackTrace();
        }
        catch (GoeuroWritingException e) {
            e.printStackTrace();
        }
    }
}
