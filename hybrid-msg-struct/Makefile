# See README.txt.

all: java

java:   add_addressbook 


protoc_middleman: hybridaddressbook.proto
	protoc $$PROTO_PATH --java_out=. hybridaddressbook.proto
	@touch protoc_middleman


javac_middleman: AddPerson.java protoc_middleman
	javac -cp $$CLASSPATH:/home/wyes/proj/cvt_proj/capnproto-java/runtime/target/classes:/home/wyes/proj/cvt_proj/capnproto-java/examples/target/classes AddPerson.java com/grpc/hybrid/HybridAddressBookProtos.java
	@touch javac_middleman

add_addressbook: javac_middleman
	@echo "Writing shortcut script add_addressbook..."
	@echo '#! /bin/sh' > add_addressbook
	@echo 'java -classpath .:$$CLASSPATH:/home/wyes/proj/cvt_proj/capnproto-java/runtime/target/classes:/home/wyes/proj/cvt_proj/capnproto-java/examples/target/classes AddPerson "$$@"' >> add_addressbook
	@chmod +x add_addressbook

/home/wyes/proj/cvt_proj/capnproto-java/runtime/target/classes:/home/wyes/proj/cvt_proj/capnproto-java/examples/target/classes


kvstore:
	javac -cp $$CLASSPATH:/home/wyes/proj/cvt_proj/capnproto-java/runtime/target/classes:/home/wyes/proj/cvt_proj/capnproto-java/examples/target/classes:/home/wyes/proj/cvt_proj/grpc-java/core/build/classes/java/main:/home/wyes/proj/cvt_proj/grpc-java/stub/build/classes/java/main kvstore/KeyValues.java 