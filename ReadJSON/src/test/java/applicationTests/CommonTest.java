package applicationTests;

import org.junit.After;
import org.junit.Before;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public abstract class CommonTest
{
    protected final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    protected final ByteArrayOutputStream errContent = new ByteArrayOutputStream();     
  
    /**
     * Setting up output streams
     */
    @Before
    public void setUpStreams()
    {
        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));
    }

    /**
     * Restoring output streams to initial values
     */
    @After
    public void restoreStreams()
    {
        System.setOut(System.out);
        System.setErr(System.err);
    }

}
