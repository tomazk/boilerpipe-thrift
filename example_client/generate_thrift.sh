echo "generating python source ..." 
thrift  -r -v --gen py:utf8strings ../thrift/BoilerpipeService.thrift 
echo "renaming gen-py into thriftgen"
mv ./gen-py ./thriftgen
echo "done"