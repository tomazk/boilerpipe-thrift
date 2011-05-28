from thrift.transport import TTransport, TSocket
from thrift.protocol import TBinaryProtocol

from thriftgen.BoilerpipeThriftService import ExtractorService

def connect(host = 'localhost', port = 9999):
    transport = TSocket.TSocket(host,port)
    transport = TTransport.TFramedTransport(transport)
    protocol = TBinaryProtocol.TBinaryProtocol(transport)
    client = ExtractorService.Client(protocol)
    
    return client, transport