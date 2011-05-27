package com.tomazkovacic.boilerpipe.thrift;

import org.apache.commons.cli.ParseException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.thrift.transport.TTransportException;

public class Server {
	
	public static Log LOG = LogFactory.getLog(Server.class);

    /**
     * Main endpoint
     * 
     * @param args
     * @throws TTransportException 
     * @throws ParseException 
     */
    public static void init(String[] args) throws TTransportException, ParseException {
 
    	ServerSettings settings = ServerSettings.parseArgs(args);
    	if(settings.cmd.hasOption("help")){
    		settings.printHelp();
    		System.exit(-1);
    	}
    	
    }
    
    public static void main(String[] args){
    	try {
			init(args);
		} catch (TTransportException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
    	
    }

}
