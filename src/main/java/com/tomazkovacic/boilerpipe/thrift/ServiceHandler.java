package com.tomazkovacic.boilerpipe.thrift;

import java.nio.ByteBuffer;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.thrift.TException;

import com.tomazkovacic.boilerpipe.thrift.gen.ExceptionCode;
import com.tomazkovacic.boilerpipe.thrift.gen.ExtractorException;
import com.tomazkovacic.boilerpipe.thrift.gen.ExtractorType;

import de.l3s.boilerpipe.BoilerpipeExtractor;
import de.l3s.boilerpipe.BoilerpipeProcessingException;
import de.l3s.boilerpipe.extractors.ArticleExtractor;
import de.l3s.boilerpipe.extractors.CanolaExtractor;
import de.l3s.boilerpipe.extractors.DefaultExtractor;
import de.l3s.boilerpipe.extractors.KeepEverythingExtractor;

public class ServiceHandler 
implements com.tomazkovacic.boilerpipe.thrift.gen.ExtractorService.Iface
{
	private static Log LOG = LogFactory.getLog(ServerSettings.class);

	/**
	 * Get extractor instance based on the enum selector
	 * */
	private static BoilerpipeExtractor getExtractor(ExtractorType etype){
		BoilerpipeExtractor extractor = null;
		switch(etype){
			case DEFAULT: 
				extractor = new DefaultExtractor();
			break;
			
			case ARTICLE: 
				extractor = new ArticleExtractor();
			break;
				
			case CANOLA: 
				extractor = new CanolaExtractor();
			break;
				
			case DEBUG: 
				extractor = KeepEverythingExtractor.INSTANCE;
			break;
		}
		return extractor;
	}
	
	/**
	 * @param htmlData
	 * 		Binary html data 
	 * @param encoding
	 * 		Encoding of html data
	 * @param etype
	 * 		Extractor selector
	 * */
	public String extract_binary(ByteBuffer htmlData, String encoding,
			ExtractorType etype) throws ExtractorException, TException {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * @param htmlString
	 * 		Html data 
	 * @param etype
	 * 		Extractor selector
	 * */
	public String extract_string(String htmlString, ExtractorType etype)
			throws ExtractorException, TException {
		
		BoilerpipeExtractor extractor = getExtractor(etype);
		try {
			return extractor.getText(htmlString); 
		} catch (BoilerpipeProcessingException e) {
			throw new ExtractorException(ExceptionCode.PROCESSING, e.getMessage(),"");
		}
	}

	/**
	 * Debugging utility
	 * */
	public String ping(String input) throws TException {
		return "pong";
	}

}
