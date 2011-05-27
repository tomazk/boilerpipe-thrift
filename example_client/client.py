from thrift.transport import TTransport, TSocket
from thrift.protocol import TCompactProtocol
from thrift import Thrift

# this is the code thrift generates for us 
# gen-py directory was renamed to thriftgen
from thriftgen.BoilerpipeThriftService import ExtractorService
from thriftgen.BoilerpipeThriftService import ttypes

try:
    transport = TSocket.TSocket('localhost',9988)
    transport = TTransport.TFramedTransport(transport)
    protocol = TCompactProtocol.TCompactProtocol(transport)
    client = ExtractorService.Client(protocol)
    
    transport.open()
    
    # debug
    p = client.ping(unicode('PING')) # works
    print p # prints PONG
    
   
    transport.close()
    
except Thrift.TException as e:
    print e
    raise
except Exception as e:
    print e
    raise