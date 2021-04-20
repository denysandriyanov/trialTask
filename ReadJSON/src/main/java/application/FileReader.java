package application;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Reads JSON file and gets a list of map representations for each found object
 * 
 */
public class FileReader
{   
    private String filePath;

    /**
     * Constructor
     * 
     * @param filePath the path to JSON file
     * @throws FileNotFoundException
     */
    public FileReader(String filePath) throws FileNotFoundException
    {
        File file = new File(filePath);
        if (file.exists() &&  file.isFile())
            this.filePath = filePath;   
        else
            throw new FileNotFoundException("File does not exist " + filePath); 
    }


    /**
     * Reads JSON file structure and parses it
     * 
     * @throws IllegalStateException
     */
    public void analyzeFile() throws IllegalStateException
    {
        try 
        {
            ObjectMapper mapper = new ObjectMapper();
            JsonNode rootNode = mapper.readTree(Paths.get(filePath).toFile());
            parseNode(null, rootNode);
        } 
        catch (Exception ex)
        {
            throw new IllegalStateException("Failed to parse JSON file " + ex.getMessage());
        }
    }

    /**
     * Gets the list of keys and vales contained in the node
     * Calls matcher on each key/value pair
     * 
     * @return List if JSON objects in map representation
     * @throws IllegalStateException
     */
    private static Object parseNode(String key, final JsonNode node) throws IOException 
    {       
        if (node.isArray())
        {
            if (key != null)
                ConditionMatcher.checkFieldValue(key, node);
            
            List<Object> objectsList = new ArrayList<>();
            Iterator<JsonNode> fieldsIterator = node.elements();
            while (fieldsIterator.hasNext())
            {
                JsonNode field = fieldsIterator.next();
                objectsList.add(parseNode(key, field));
            }
            return objectsList;
        }
        else if (node.isValueNode())
            return node;
        else            
        {
            Iterator<Map.Entry<String, JsonNode>> fieldsIterator = node.fields();
            Map<String, Object> objectMap = new HashMap<>();

            while (fieldsIterator.hasNext()) 
            {
                Map.Entry<String, JsonNode> field = fieldsIterator.next();
                String nodeKey = field.getKey();
                JsonNode value = field.getValue();

                if (value.isContainerNode()) 
                    objectMap.put(nodeKey, parseNode(nodeKey, value));
                else
                {
                    ConditionMatcher.checkFieldValue(nodeKey, value);
                    objectMap.put(nodeKey, value);
                }
            }
            return objectMap;
        }
    }
}
