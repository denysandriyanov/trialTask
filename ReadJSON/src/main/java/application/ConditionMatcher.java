package application;


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
    public static void init(String expectedField, Object expectedValue) 
    {
        ConditionMatcher.expectedField = expectedField;
        ConditionMatcher.expectedValue = String.valueOf(expectedValue);
    }

    /**
     * Checks if field matches expectedField and if value matches expectedValue
     * Increments corresponding counters if match is true 
     * 
     * @param field the field to check
     * @param value the value to check
     */
    public static void checkFieldValue(String field, Object value)
    {
        if (field.equals(expectedField))
        {
            expecedFieldsFound++;

            if (expectedValue != null && String.valueOf(value).equals(expectedValue))
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
