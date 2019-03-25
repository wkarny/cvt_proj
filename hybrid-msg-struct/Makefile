# See README.txt.

all: java

java:   add_addressbook 


protoc_middleman: hybridaddressbook.proto
	protoc $$PROTO_PATH --java_out=. hybridaddressbook.proto
	@touch protoc_middleman


javac_middleman: AddPerson.java protoc_middleman
	javac -cp $$CLASSPATH AddPerson.java com/grpc/hybrid/HybridAddressBookProtos.java
	@touch javac_middleman

add_addressbook: javac_middleman
	@echo "Writing shortcut script add_addressbook..."
	@echo '#! /bin/sh' > add_addressbook
	@echo 'java -classpath .:$$CLASSPATH AddPerson "$$@"' >> add_addressbook
	@chmod +x add_addressbook

