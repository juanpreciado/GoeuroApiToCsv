package goeurowriter;

import goeurocustomexceptions.GoeuroWritingException;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.regex.Pattern;

/**
 * Serves to write a CSV file from a given JSONArray
 *
 * Created by juan on 7/31/16.
 */
public class GoeuroWriter {

    /**
     * Receives a JSonArray, and an Array of Strings, creates a CSV file with the columns specified in the String of Arrays
     * from the JSONArray
     *
     * @param jsonArray Json Array
     * @param columnNames Column names for the CSV file, the column names must match the keys in the JSON array
     * @throws GoeuroWritingExceptionth
     */
    public void fromJSONtoCSV (JSONArray jsonArray, String[] columnNames) throws GoeuroWritingException{
        try {
            PrintWriter printWriter = new PrintWriter(new File(("goeuroApiToCsvOutput.csv")));
            StringBuilder stringBuilder = new StringBuilder();
            for (String columnName : columnNames) {
                stringBuilder.append(columnName);
                stringBuilder.append(";");
            }
            stringBuilder.append("\n");
            System.out.println(jsonArray.toString());
            for(int n = 0; n < jsonArray.length(); n++) {
                JSONObject jsonObject = jsonArray.getJSONObject(n);
                for (String columnName : columnNames) {
                    if(columnName.contains(".")) {
                        String[] parts = columnName.split(Pattern.quote("."));
                        if(parts.length != 2) {
                            throw new GoeuroWritingException("There was an error, for the moment the system allows only" +
                                    "one level of inner objects.");
                        }
                        JSONObject innerObject = (JSONObject) jsonObject.get(parts[0]);
                        stringBuilder.append(innerObject.get(parts[1]).toString());
                    } else {
                        stringBuilder.append(jsonObject.get(columnName).toString());
                    }
                        stringBuilder.append(";");
                }
                stringBuilder.append("\n");
            }
            printWriter.write(stringBuilder.toString());
            printWriter.close();


        } catch (FileNotFoundException e) {
            throw new GoeuroWritingException("There was an error, the file douldnt be generated");
        } catch (JSONException e) {
            throw new GoeuroWritingException("Checck the column names, one of them doesnt exist in the API");
        } catch (NullPointerException e) {
            throw new GoeuroWritingException("There is no info associated to the requested city");
        }
    }

}
