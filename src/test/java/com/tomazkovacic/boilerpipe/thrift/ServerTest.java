package com.tomazkovacic.boilerpipe.thrift;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import com.tomazkovacic.boilerpipe.thrift.Server;

public class ServerTest extends TestCase {
	
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public ServerTest( String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( ServerTest.class );
    }
	
	public void testServer(){
		//Server.main(new String[]{"","-p", "9988"});
	}

}
