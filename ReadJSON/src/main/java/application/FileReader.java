package application;

import java.io.File;
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
 * Reads JSON file and go through the it travers to access each field/value pairs
 * 
 */
public class FileReader
{   
    private String filePath;

    /**
     * Constructor
     * 
     * @param filePath the path to JSON file
     */
    public FileReader(String filePath)
    {
        File file = new File(filePath);        
        if (file.exists() &&  file.isFile())
            this.filePath = filePath;   
        else
            System.err.print("File does not exist");
    }

    /**
     * Reads JSON file structure and parses it
     * 
     * @return true if analyzed successfully
     */
    public boolean analyze()
    {
        if (this.filePath != null)
        {
            try 
            {
                ObjectMapper mapper = new ObjectMapper();
                JsonNode rootNode = mapper.readTree(Paths.get(this.filePath).toFile());
                parseNode(null, rootNode);
                return true;
            } 
            catch (Exception ex)
            {
                System.err.print("Failed to parse JSON file");
            }
        }
        return false;
    }

    /**
     * Gets the list of keys and vales contained in the node
     * Calls matcher on each key/value pair
     * 
     * @throws IllegalStateException
     */
    private static void parseNode(String key, final JsonNode node) throws IOException 
    {       
        if (node.isArray())
        {
            if (key != null)
                ConditionMatcher.checkFieldValue(key, node);

            Iterator<JsonNode> fieldsIterator = node.elements();
            while (fieldsIterator.hasNext())
            {
                JsonNode field = fieldsIterator.next();
                parseNode(key, field);
            }
        }
        else            
        {
            Iterator<Map.Entry<String, JsonNode>> fieldsIterator = node.fields();

            while (fieldsIterator.hasNext()) 
            {
                Map.Entry<String, JsonNode> field = fieldsIterator.next();
                String nodeKey = field.getKey();
                JsonNode value = field.getValue();

                if (value.isContainerNode()) 
                    parseNode(nodeKey, value);
                else
                    ConditionMatcher.checkFieldValue(nodeKey, value);
            }
        }
    }
}
