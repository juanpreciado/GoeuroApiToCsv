package goeuroreader;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import goeurocustomexceptions.GoeuroReadingException;
import org.json.JSONArray;

/**
 * Serves for reading purposes regarding the GoEuro API
 *
 * Created by juan on 7/31/16.
 */
public class ApiGoeuroReader {
    private String apiUrl = "http://api.goeuro.com/api/v2/position/suggest/en/";
    private String cityName;

    /**
     * Queries the Goeuro Api and returns a JSONArray with the info from a given city
     *
     * @return JSONArray
     * @throws GoeuroReadingException
     */
    public JSONArray readFromApi() throws GoeuroReadingException{
        try {
            URL goeuroApiUrl = new URL(this.apiUrl+this.cityName);
            URLConnection connection = goeuroApiUrl.openConnection();
            BufferedReader bufferReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String inputLine = "";
            String inputAuxReader = "";
            while ((inputAuxReader =  bufferReader.readLine()) != null) {
                inputLine = inputLine + inputAuxReader;
            }
            bufferReader.close();
            JSONArray jsonArray = new JSONArray(inputLine);
            return jsonArray;

        } catch (MalformedURLException e) {
            throw new GoeuroReadingException("It seems like the URL for the API is malformed");
        } catch (IOException e) {
            throw new GoeuroReadingException("There was an error reading the info from the API");
        }

    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }
}
