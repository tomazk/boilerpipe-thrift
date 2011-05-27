package com.tomazkovacic.boilerpipe.thrift;

import org.apache.commons.cli.ParseException;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TCompactProtocol;
import org.apache.thrift.protocol.TProtocolFactory;
import org.apache.thrift.transport.TFramedTransport;
import org.apache.thrift.transport.TNonblockingServerSocket;
import org.apache.thrift.transport.TServerSocket;
import org.apache.thrift.transport.TServerTransport;
import org.apache.thrift.transport.TTransportException;
import org.apache.thrift.transport.TTransportFactory;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import com.tomazkovacic.boilerpipe.thrift.ServerSettings;

public class ServerSettingsTest extends TestCase {
	
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public ServerSettingsTest( String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( ServerSettingsTest.class );
    }
	
    private ServerSettings getSettingsInstance(String[] args){
    	ServerSettings s = null;
    	try {
			s = ServerSettings.parseArgs(args);
    	} catch (ParseException e) {
			e.printStackTrace();
			fail();
		}
    	return s;
    }
    
    public void testPort(){
    	ServerSettings s =  getSettingsInstance(new String[]{"","-p","9090"});
    	assertEquals(s.getListeningPort(), 9090);

    }
    
    public void testDefaultPort(){
		ServerSettings s = getSettingsInstance(new String[]{"",});
		assertEquals(s.getListeningPort(), ServerSettings.DEFAULT_PORT);
    }
    
    
    public void testFalsePort(){
    	ServerSettings s = getSettingsInstance(new String[]{"","-p","a"});
		assertEquals(s.getListeningPort(), ServerSettings.DEFAULT_PORT);
    }
    
    public void testProtocolFactory(){
    	
		{
			ServerSettings s =getSettingsInstance(new String[]{"","-c"});
			TProtocolFactory factory = s.getProtocolFactory();
			Class c1 = factory.getClass().getEnclosingClass();
			assertEquals(c1, TCompactProtocol.class);
		}
		
		{
			ServerSettings s =getSettingsInstance(new String[]{"",});
			TProtocolFactory factory = s.getProtocolFactory();
			Class c1 = factory.getClass().getEnclosingClass();
			assertEquals(c1, TBinaryProtocol.class);
		}
    }
    
    public void testTransportFactory(){
    	
		{
			ServerSettings s =getSettingsInstance(new String[]{"","-f"});
			TTransportFactory factory = s.getTransportFactory();
			Class c1 = factory.getClass().getEnclosingClass();
			if (c1 != null){				
				assertEquals(c1, TFramedTransport.class);
			}
			else{
				fail();
			}
		}
		{
			ServerSettings s =getSettingsInstance(new String[]{"","-hsha"});
			TTransportFactory factory = s.getTransportFactory();
			Class c1 = factory.getClass().getEnclosingClass();
			if (c1 != null){				
				assertEquals(c1, TFramedTransport.class);
			}
			else{
				fail();
			}
		}
		
		{
			ServerSettings s =getSettingsInstance(new String[]{"","-nonblocking"});
			TTransportFactory factory = s.getTransportFactory();
			Class c1 = factory.getClass().getEnclosingClass();
			if (c1 != null){				
				assertEquals(c1, TFramedTransport.class);
			}
			else{
				fail();
			}
		}
		
		{
			ServerSettings s =getSettingsInstance(new String[]{"",});
			TTransportFactory factory = s.getTransportFactory();
			Class c1 = factory.getClass();
			if(c1.getEnclosingClass() != null){
				fail();
			}	
			assertEquals(c1, TTransportFactory.class);
		}
    } 
    
    public void testHelp(){
    	ServerSettings s =getSettingsInstance(new String[]{"",});
    	s.printHelp();
    	
    }
    
 
    
    

}
