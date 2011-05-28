import os
import re
import unittest
import client

from thriftgen.BoilerpipeThriftService import ttypes
from thriftgen import BoilerpipeThriftService

def get_data(filename):
    with open(os.path.join(os.getcwd(),'test_data',filename),'r') as f:
        return f.read()

class TestData(unittest.TestCase):
    
    def setUp(self):
        self.client, self.transport = client.connect()
        self.transport.open()
        
    def tearDown(self):
        self.transport.close()
        
    def test_example_string_1(self):
        doc = get_data('1.html')
        result = self.client.extract_string(unicode(doc,'utf8','ignore'), ttypes.ExtractorType.DEBUG)
        #print result[:300]
        if not re.search('Andrew Prunicki', result[:300]):
            self.fail()
        
    def test_example_binary_1(self):
        doc = get_data('1.html')
        result = self.client.extract_binary(unicode(doc,'utf8','ignore').encode('utf8')
                                            ,'utf8', ttypes.ExtractorType.DEBUG)
        #print result[:300]
        if not re.search('Andrew Prunicki', result[:300]):
            self.fail()
            
    def test_example_string_2(self):
        doc = get_data('2.html')
        result = self.client.extract_string(unicode(doc,'utf8','ignore'), ttypes.ExtractorType.DEBUG)
        #print result[:300]
        if not re.search('Climate Change', result[:300]):
            self.fail()
        
    def test_example_binary_2(self):
        doc = get_data('2.html')
        result = self.client.extract_binary(unicode(doc,'utf8','ignore').encode('utf8')
                                            ,'utf8', ttypes.ExtractorType.DEBUG)
        #print result[:300]
        if not re.search('Climate Change', result[:300]):
            self.fail()
            
    def test_example_string_3(self):
        doc = get_data('3.html')
        result = self.client.extract_string(unicode(doc,'utf8','ignore'), ttypes.ExtractorType.DEBUG)
        #print result[:300]
        if not re.search('There has been some recent', result[:300]):
            self.fail()
        
    def test_example_binary_3(self):
        doc = get_data('3.html')
        result = self.client.extract_binary(unicode(doc,'utf8','ignore').encode('utf8')
                                            ,'utf8', ttypes.ExtractorType.DEBUG)
        #print result[:300]
        if not re.search('There has been some recent', result[:300]):
            self.fail()
            
    def test_example_string_4(self):
        doc = get_data('4.html')
        result = self.client.extract_string(unicode(doc,'utf8','ignore'), ttypes.ExtractorType.DEBUG)
        #print result[:300]
        if not re.search('The four categories of NoSQL databases', result[:300]):
            self.fail()
        
    def test_example_binary_4(self):
        doc = get_data('4.html')
        result = self.client.extract_binary(unicode(doc,'utf8','ignore').encode('utf8')
                                            ,'utf8', ttypes.ExtractorType.DEBUG)
        #print result[:300]
        if not re.search('The four categories of NoSQL databases', result[:300]):
            self.fail()
            
    def test_example_string_5(self):
        doc = get_data('5.html')
        result = self.client.extract_string(unicode(doc,'utf8','ignore'), ttypes.ExtractorType.DEBUG)
        #print result[:300]
        if not re.search('SUBSCRIBE TO NEW SCIENTIST', result[:300]):
            self.fail()
        
    def test_example_binary_5(self):
        doc = get_data('5.html')
        result = self.client.extract_binary(unicode(doc,'utf8','ignore').encode('utf8')
                                            ,'utf8', ttypes.ExtractorType.DEBUG)
        #print result[:300]
        if not re.search('SUBSCRIBE TO NEW SCIENTIST', result[:300]):
            self.fail()
            
    def test_default(self):
        doc = get_data('2.html')
        result = self.client.extract_string(unicode(doc,'utf8','ignore'), ttypes.ExtractorType.DEFAULT)
        print result[:300]
        
    def test_article(self):
        doc = get_data('2.html')
        result = self.client.extract_string(unicode(doc,'utf8','ignore'), ttypes.ExtractorType.ARTICLE)
        print result[:300]
        
    def test_canola(self):
        doc = get_data('2.html')
        result = self.client.extract_string(unicode(doc,'utf8','ignore'), ttypes.ExtractorType.CANOLA)
        print result[:300]
        
    def test_failed_encoding(self):
        doc = get_data('2.html')
        try:
            self.client.extract_binary(unicode(doc,'utf8','ignore').encode('utf8')
                                            ,'jadajadajada', ttypes.ExtractorType.DEBUG)
        except BoilerpipeThriftService.ExtractorService.ExtractorException as e:
            self.assertEqual(e.message,'jadajadajada')
        else:
            self.fail()



def main():
    unittest.main()

if __name__ == '__main__':
    main()