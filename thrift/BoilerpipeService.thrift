namespace java com.tomazkovacic.boilerpipe.thrift.gen
namespace py BoilerpipeThriftService

enum ExceptionCode{
    PROCESSING = 1,
    ENCODING = 2,
    UNKNOWN = 3,
}

exception ExtractorException{
    1: ExceptionCode code,
    2: string message,
    3: string trace,
}

enum ExtractorType{
    DEFAULT = 1,
    ARTICLE = 2,
    CANOLA = 3,
    DEBUG = 4, // dummy keep everything extractor
}

service ExtractorService{
    
    // debugging utility
    string ping(1: string input),
    
    // service endpoint
    string extract_string(1: string html_string, 2: ExtractorType etype)
        throws (1: ExtractorException e),
    
    // service endpoint 
    string extract_binary(1: binary html_data, 2: string encoding, 3: ExtractorType etype)
        throws (1: ExtractorException e),
}
