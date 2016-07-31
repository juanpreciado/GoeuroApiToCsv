# GoeuroApiToCsv
This small project seves to generate a CSV file from a given which returns a JSON array. It was tested with the Goeuro API, 
although it should work with generic APIs as well.
# Usage
Included, there is a jar file which can be executed from the console; th jar filehe jar file is located within the root of the project in 
GoeuroApiToCsv_jar/GoeuroApiToCsv.jar.
So in order to run it as an stand alone app, just download it from github, and run in a terminal:
<br/>
<i>
java -jar PATH_TO_YOUR_DIRECTORY/GoeuroApiToCsv.jar CITY_NAME
</i>
<br/>
So, for example let's say you save in your home directory, and you are there right now, it is enough to run: 
<br/>
<i>
java -jar GoeuroApiToCsv.jar berlin
</i>
<br/>
Notice that the parameter CITY_NAME has been replaced for "berlin", it means we wann export the info corresponding to Berlin from 
the Goeuro Api to the CSV file
#Output
After executing the JAR file, a CSV file called <i>goeuroApiToCsvOutput.csv</i> will be generated in the folder where you were located 
when you executed the jar (Be sure of give the right writing permissions to the directory)
#Entry Point to the code
If you wanna take a look of the code, you can start from src/Main.java, there you will be able to follow the logic and access the inner logic.
Notice that all of the logic is concentrated in 2 classes:<br/>
<i/>src/goeuroreader/ApiGoeuroReader.java</i> Contains the logic regarding the reading process from the API<br/>
<i>src/goeurowriter/GoeuroWriter.java</i> Contains the logic regarding the writing process into the CSV file <br/>
