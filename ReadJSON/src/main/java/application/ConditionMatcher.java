package application;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;


/**
 * This class is for checking that field matches expectedField and if value matches expectedValue
 * Increments corresponding counters if match is true 
 * 
 */
public final class ConditionMatcher
{
    private static int foundFieldsCount;
    private static int foundValuesCount;
    private static String expectedValue;
    private static String expectedField;
    private static ObjectMapper mapper = new ObjectMapper();

    /**
     * Don't let anyone instantiate this class.
     */
    private ConditionMatcher()
    {
    }

    /**
     * Initializes matcher, setting expectedField and expectedValue values
     * 
     * @param expectedField the expected field value
     * @param expectedValue the expected value value
     */
    public static void init(String expectedField, String expectedValue)
    {
        ConditionMatcher.expectedField = expectedField;
        ConditionMatcher.expectedValue = expectedValue;
        foundFieldsCount = 0;
        foundValuesCount = 0;

    }

    /**
     * Checks if field matches expectedField and if value matches expectedValue
     * Increments corresponding counters if match is true 
     * 
     * @param field the field to check
     * @param value the value to check
     * @throws JsonProcessingException 
     * @throws JsonMappingException 
     */
    public static void checkFieldValue(String field, Object value) throws JsonProcessingException
    {
        if (field.equals(expectedField))
        {
            foundFieldsCount++;

            if (value.equals(mapper.readTree(String.valueOf(expectedValue))))
                foundValuesCount++;
        }
    }

    /**
     * @return the foundFieldsCount value
     */
    public static int getFoundFieldsCount()
    {
        return foundFieldsCount;
    }

    /**
     * @return the foundValuesCount value
     */
    public static int getFoundValuesCount()
    {
        return foundValuesCount;
    }
}
