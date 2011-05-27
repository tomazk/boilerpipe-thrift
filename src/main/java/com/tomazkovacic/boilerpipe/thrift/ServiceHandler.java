package com.tomazkovacic.boilerpipe.thrift;

import java.nio.ByteBuffer;

import org.apache.thrift.TException;
import com.tomazkovacic.boilerpipe.thrift.gen.ExtractorException;
import com.tomazkovacic.boilerpipe.thrift.gen.ExtractorType;


public class ServiceHandler 
implements com.tomazkovacic.boilerpipe.thrift.gen.ExtractorService.Iface
{

	public String extract_binary(ByteBuffer htmlData, String encoding,
			ExtractorType etype) throws ExtractorException, TException {
		// TODO Auto-generated method stub
		return null;
	}

	public String extract_string(String htmlString, ExtractorType etype)
			throws ExtractorException, TException {
		// TODO Auto-generated method stub
		return null;
	}

	public String ping(String input) throws TException {
		// TODO Auto-generated method stub
		return "pong";
	}

}
