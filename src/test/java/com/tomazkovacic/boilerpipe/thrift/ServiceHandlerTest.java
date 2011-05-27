package com.tomazkovacic.boilerpipe.thrift;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class ServiceHandlerTest extends TestCase {
	
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public ServiceHandlerTest( String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( ServiceHandlerTest.class );
    }

}
