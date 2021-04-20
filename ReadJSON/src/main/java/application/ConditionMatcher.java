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
    private static int expecedFieldsFound;
    private static int expectedValuesFound;
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
        expecedFieldsFound = 0;
        expectedValuesFound = 0;

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
            expecedFieldsFound++;

            if (value.equals(mapper.readTree(String.valueOf(expectedValue))))
                expectedValuesFound++;
        }
    }

    /**
     * @return the expecedFieldsFound value
     */
    public static int getExpecedFieldsFound()
    {
        return expecedFieldsFound;
    }

    /**
     * @return the expectedValuesFound value
     */
    public static int getExpectedValuesFound()
    {
        return expectedValuesFound;
    }
}
