package com.tomazkovacic.boilerpipe.thrift;

import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;

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
			String result = handler.extract_string("<html><body><p>this is some text</p></body></html>", ExtractorType.DEBUG);
			assertTrue(result.startsWith("this is"));
			
		} catch (ExtractorException e) {
			e.printStackTrace();
			fail();
		} catch (TException e) {
			e.printStackTrace();
			fail();
		}
    }
    
    public void testExtractBinarySimple(){
    	try {
    		String html = "<html><body><p>thiššššš is some text</p></body></html>";
    		byte [] htmlBinary = html.getBytes("utf8");
			String result = handler.extract_binary(ByteBuffer.wrap(htmlBinary), "utf8", ExtractorType.DEBUG);
			assertTrue(result.startsWith("thiššššš is"));
			
		} catch (ExtractorException e) {
			e.printStackTrace();
			fail();
		} catch (TException e) {
			e.printStackTrace();
			fail();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			fail();
		}
    }

}
