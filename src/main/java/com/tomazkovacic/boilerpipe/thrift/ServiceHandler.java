package com.tomazkovacic.boilerpipe.thrift;

import java.nio.ByteBuffer;

import org.apache.thrift.TException;
import com.tomazkovacic.boilerpipe.thrift.gen.ExtractorException;


public class ServiceHandler 
implements com.tomazkovacic.boilerpipe.thrift.gen.ExtractorService.Iface
{

	public String ping(String input) throws TException {
		System.out.println("ping");
		return "pong";
	}

	public String extract_binary(ByteBuffer htmlData, String encoding)
			throws ExtractorException, TException {
		// TODO Auto-generated method stub
		return null;
	}

	public String extract_string(String htmlString) throws ExtractorException,
			TException {
		// TODO Auto-generated method stub
		return null;
	}

}
