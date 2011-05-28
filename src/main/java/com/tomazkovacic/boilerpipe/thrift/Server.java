package com.tomazkovacic.boilerpipe.thrift;

import org.apache.commons.cli.ParseException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.thrift.transport.TNonblockingServerTransport;
import org.apache.thrift.transport.TServerSocket;
import org.apache.thrift.transport.TTransportException;
import org.apache.thrift.server.THsHaServer;
import org.apache.thrift.server.TNonblockingServer;
import org.apache.thrift.server.TServer;
import org.apache.thrift.server.TThreadPoolServer;

import com.tomazkovacic.boilerpipe.thrift.gen.ExtractorService;

public class Server {
    
    private static Log LOG = LogFactory.getLog(Server.class);

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
        
        // Set processor
        ServiceHandler handler = new ServiceHandler();
        ExtractorService.Processor processor = new ExtractorService.Processor(handler);
        
        // Set server
        TServer server = null;
        if(settings.cmd.hasOption("hsha")){
            TNonblockingServerTransport transport = 
                (TNonblockingServerTransport) settings.getServerTransport();
            THsHaServer.Args sArgs = new THsHaServer.Args(transport);
            sArgs.processor(processor);
            sArgs.transportFactory(settings.getTransportFactory());
            sArgs.protocolFactory(settings.getProtocolFactory());
            server = new THsHaServer(sArgs);
            LOG.info("started hsha server");
        }else if (settings.cmd.hasOption("nonblocking")){
            TNonblockingServerTransport transport = 
                (TNonblockingServerTransport) settings.getServerTransport();
            TNonblockingServer.Args sArgs = new TNonblockingServer.Args(transport);
            sArgs.processor(processor);
            sArgs.transportFactory(settings.getTransportFactory());
            sArgs.protocolFactory(settings.getProtocolFactory());
            server = new TNonblockingServer(sArgs);
            LOG.info("started nonblocking server");
        }else{
            TServerSocket transport = (TServerSocket) settings.getServerTransport();
            TThreadPoolServer.Args sArgs = new TThreadPoolServer.Args(transport);
            sArgs.processor(processor);
            sArgs.transportFactory(settings.getTransportFactory());
            sArgs.protocolFactory(settings.getProtocolFactory());
            server = new TThreadPoolServer(sArgs);
            LOG.info("started thread pool server");
        }
        // start the server and get things going
        server.serve();
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
