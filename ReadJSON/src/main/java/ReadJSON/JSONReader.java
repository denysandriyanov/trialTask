package ReadJSON;

import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.type.TypeReference;

/**
 * Reads JSON file and gets a list of map representations for each found object
 * 
 */
public class JSONReader
{   
    private String filePath;

    /**
     * Constructor
     * 
     * @param filePath the path to JSON file
     * @throws FileNotFoundException 
     */
    public JSONReader(String filePath) throws FileNotFoundException
    {
        if (new File(filePath).exists() &&  new File(filePath).isFile())
            this.filePath = filePath;   
        else
            throw new FileNotFoundException(this.filePath + " File does not exist"); 
    }


    /**
     * Gets the list of JSON objects contained in the file
     * 
     * @return List if JSON objects in map representation
     * @throws IllegalStateException
     */
    public List<Map<String, Object>> getListOfObjects() throws IllegalStateException
    {
        try 
        {
            ObjectMapper mapper = new ObjectMapper();
            return mapper.readValue(Paths.get(filePath).toFile(), new TypeReference<List<Map<String, Object>>>(){});
        } 
        catch (Exception ex)
        {
            throw new IllegalStateException("Failed to parse JSON file " + ex.getMessage());
        }
    }

}
