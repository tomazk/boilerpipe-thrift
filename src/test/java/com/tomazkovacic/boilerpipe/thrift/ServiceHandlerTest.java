package com.tomazkovacic.boilerpipe.thrift;

import org.apache.thrift.TException;

import com.tomazkovacic.boilerpipe.thrift.gen.ExtractorException;
import com.tomazkovacic.boilerpipe.thrift.gen.ExtractorType;

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
    
    private ServiceHandler handler;
    
    public void setUp(){
    	handler = new ServiceHandler();
    }
    
    public void testPing(){

    	try {
			assertEquals(handler.ping(""), "pong");
		} catch (TException e) {
			e.printStackTrace();
			fail();
		}
    }
    
    public void testExtractStringSimple(){
    	try {
			String result = handler.extract_string("<html><body>this is some text</body></html>", ExtractorType.DEBUG);
			assert result.matches("this is some text");
			
		} catch (ExtractorException e) {
			e.printStackTrace();
			fail();
		} catch (TException e) {
			e.printStackTrace();
			fail();
		}
    	
    	
    }

}
