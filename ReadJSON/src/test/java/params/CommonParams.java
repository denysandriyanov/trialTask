package params;
/*
 * /------------------------------------------------------------------------\
 * |               Copyright 2021 by Avid Technology, Inc.                  |
 * \------------------------------------------------------------------------/
 */
/**
 * Param class stores values that are used for tests
 * 
 */
public class CommonParams
{
    private String pathToFile;
    private int expectedFieldsCount;
    private int expectedValuesCount;
    private String expectedOutput;
    private String fieldToSearch;
    private String valueToSearch;

    /**
     * @param pathToFile the path to tested file parameter
     * @param fieldToSearch the field to search parameter
     * @param valueToSearch the value to search parameter
     * @param expectedFieldsCount the expected count of fields found parameter
     * @param expectedValuesCount the expected found of values found parameter
     */
    public CommonParams (String pathToFile, String fieldToSearch, String valueToSearch, int expectedFieldsCount, int expectedValuesCount)
    {
        this.pathToFile = pathToFile;
        this.fieldToSearch = fieldToSearch;
        this.valueToSearch = valueToSearch;
        this.expectedFieldsCount = expectedFieldsCount;
        this.expectedValuesCount = expectedValuesCount;
    }

    /**
     * @param pathToFile the path to tested file parameter
     * @param expectedErrorMessage the expected error message parameter
     */
    public CommonParams (String pathToFile, String expectedErrorMessage)
    {
        this(pathToFile, null, null, 0, 0);
        this.expectedOutput = expectedErrorMessage;
    }

    /**
     * @return the fieldToSearch
     */
    public String getFieldToSearch()
    {
        return this.fieldToSearch;
    }

    /**
     * @return the valueToSearch
     */
    public Object getValueToSearch()
    {
        return this.valueToSearch;
    }

    /**
     * @return the pathToFile
     */
    public String getExpectedOutput()
    {
        if (this.expectedOutput == null)
            return("-found " + this.expectedFieldsCount +" objects with field \"" + this.fieldToSearch + "\"\r\n"
                            + "-found " + this.expectedValuesCount +" objects where field \"" 
                            + this.fieldToSearch + "\" equals \"" + this.valueToSearch + "\"\r\n");
        else
            return this.expectedOutput;
    }


    /**
     * @return the pathToFile
     */
    public String getPathToFile()
    {
        return this.pathToFile;
    }

    /**
     * @return the expectedFieldsCount
     */
    public int getExpectedFieldsCount()
    {
        return this.expectedFieldsCount;
    }

    /**
     * @return the expectedValuesCount
     */
    public int getExpectedValuesCount()
    {     
        return this.expectedValuesCount;
    }

    /**
     * @return the true if param contains expected error message
     */
    public boolean getIsErrorMessage()
    {     
        return this.expectedOutput != null;
    }
}
