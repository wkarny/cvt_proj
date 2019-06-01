helloworld:
	javac -cp $$CLASSPATH:/home/wyes/proj/cvt_proj/capnproto-java/runtime/target/classes:/home/wyes/proj/cvt_proj/capnproto-java/examples/target/classes:/home/wyes/proj/cvt_proj/grpc-java/core/build/classes/java/main:/home/wyes/proj/cvt_proj/grpc-java/stub/build/classes/java/main org/capnproto/test/HelloWorld.java

helloworldmain:
	javac -cp $$CLASSPATH:/home/wyes/proj/cvt_proj/capnproto-java/runtime/target/classes:/home/wyes/proj/cvt_proj/capnproto-java/examples/target/classes:/home/wyes/proj/cvt_proj/grpc-java/core/build/classes/java/main:/home/wyes/proj/cvt_proj/grpc-java/stub/build/classes/java/main:/home/wyes/proj/cvt_proj/hybrid-msg-struct/helloworld HelloWorldMain.java

runhello:
	@echo "Writing shortcut script run_hello..."
	@echo '#! /bin/sh' > run_hello
	@echo 'java -classpath .:$$CLASSPATH:/home/wyes/proj/cvt_proj/capnproto-java/runtime/target/classes:/home/wyes/proj/cvt_proj/capnproto-java/examples/target/classes:/home/wyes/proj/cvt_proj/grpc-java/core/build/classes/java/main:/home/wyes/proj/cvt_proj/grpc-java/stub/build/classes/java/main:/home/wyes/proj/cvt_proj/hybrid-msg-struct/helloworld HelloWorldMain "$$@"' >> run_hello
	@chmod +x run_hello


test:
	javac -cp $$CLASSPATH:/home/wyes/proj/cvt_proj/capnproto-java/runtime/target/classes:/home/wyes/proj/cvt_proj/capnproto-java/examples/target/classes:/home/wyes/proj/cvt_proj/grpc-java/core/build/classes/java/main:/home/wyes/proj/cvt_proj/grpc-java/stub/build/classes/java/main:/home/wyes/proj/cvt_proj/hybrid-msg-struct/helloworld:./jar/guava-15.0-rc1.jar HelloClient.java
	javac -cp $$CLASSPATH:/home/wyes/proj/cvt_proj/capnproto-java/runtime/target/classes:/home/wyes/proj/cvt_proj/capnproto-java/examples/target/classes:/home/wyes/proj/cvt_proj/grpc-java/core/build/classes/java/main:/home/wyes/proj/cvt_proj/grpc-java/stub/build/classes/java/main:/home/wyes/proj/cvt_proj/hybrid-msg-struct/helloworld:./jar/guava-15.0-rc1.jar HelloService.java
	javac -cp $$CLASSPATH:/home/wyes/proj/cvt_proj/capnproto-java/runtime/target/classes:/home/wyes/proj/cvt_proj/capnproto-java/examples/target/classes:/home/wyes/proj/cvt_proj/grpc-java/core/build/classes/java/main:/home/wyes/proj/cvt_proj/grpc-java/stub/build/classes/java/main:/home/wyes/proj/cvt_proj/hybrid-msg-struct/helloworld:./jar/guava-15.0-rc1.jar HelloRunner.java
