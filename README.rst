boilerpipe-thrift
-----------------

This is a thrift server for some extractors in the Boilerpipe text extraction library.

* `Boilerpipe <http://code.google.com/p/boilerpipe/>`_
* `Apache Thrift <http://thrift.apache.org/>`_

Author
------

Tomaž Kovačič <tomaz.kovacic@gmail.com>

Thrift interface
----------------

see ``thrift/BoilerpipeService.thrift``

::

    enum ExceptionCode{
        PROCESSING = 1,
        ENCODING = 2,
        GENERIC = 3,
    }
    
    exception ExtractorException{
        1: ExceptionCode code,
        2: string message,
        3: string trace,
    }
    
    enum ExtractorType{
        DEFAULT = 1,
        ARTICLE = 2,
        ARTICLE_SENTENCE = 4,
        DEBUG = 5, // dummy keep everything extractor
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
    


Usage
-----

Use maven to generate java source files via thrift compiler

::

    mvn generate-sources
    
or just run the usual

::

    mvn install

Maven was also configured to produce a single ``.jar`` archive with a runnable 
endpoint indicated in its manifest file. 

Now we can take a look at the help text

::
    
    $ java -jar target/boilerpipe-thrift-x.x.x-jar-with-dependencies.jar -h
    
    usage: Thrift
     -c,--compact      Use the compact protocol
     -f,--framed       Use framed transport
     -h,--help         Print help information
     -threadpool       Use the TThreadPoolServer. This is the default.
     -hsha             Use the THsHaServer. This implies the framed transport.
     -nonblocking      Use the TNonblockingServer. This implies the framed
                       transport.
     -p,--port <arg>   Port to bind to [default: 9999]
    
Notice that you can use the command line flags to configure your server. 
This configuration mechanism currently supports (similar to the Hadoop HBase thrift service configuration):


**Servers**

* thread pool server
* non blocking server
* HsHa server (Half-Sync/Half-Async)

**Protocols**

* compact protocol
* binary protocol

**Transports**

* framed transport
* server dependent transport

*Example:* to fire up a nonblocking server on port ``9988`` using the required 
framed transport, do

::

    $ java -jar target/boilerpipe-thrift-x.x.x-jar-with-dependencies.jar -nonblocking -p 9988
    
This will imply the framed transport without using the additional flag. 

Licence
-------

Copyright 2011 Tomaz Kovacic

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
