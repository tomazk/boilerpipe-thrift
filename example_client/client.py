from thrift.transport import TTransport, TSocket
from thrift.protocol import TBinaryProtocol
from thrift import Thrift

# this is the code thrift generates for us 
# gen-py directory was renamed to thriftgen
from thriftgen.BoilerpipeThriftService import ExtractorService
from thriftgen.BoilerpipeThriftService import ttypes

try:
    transport = TSocket.TSocket('localhost',9999)
    transport = TTransport.TFramedTransport(transport)
    protocol = TBinaryProtocol.TBinaryProtocol(transport)
    client = ExtractorService.Client(protocol)
    
    transport.open()
    
    # debug
    p = client.ping(unicode('PING')) # works
    print p # prints PONG
    
    with open("example_document.html",'r') as f:
        p = client.extract_string(unicode(f.read(), 'utf8','ignore'), ttypes.ExtractorType.DEFAULT);
        print p
   
    transport.close()
    
except Thrift.TException as e:
    print e
    raise
except Exception as e:
    print e
    raise