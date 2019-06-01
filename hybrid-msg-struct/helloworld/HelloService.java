//package io.grpc.examples;

import io.grpc.Status;
import io.grpc.stub.StreamObserver;
import java.nio.ByteBuffer;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.TimeUnit;

import org.capnproto.test.HelloWorld.HelloRequest;
import org.capnproto.test.HelloWorld.HelloResponce;


/**
 * This is the actual server logic.  It includes a basic key value store, as well as implements
 * thread safe methods for creating, retrieving, updating, and deleting values.  (These are
 * commonly known as "CRUD" operations.)
 */
final class HelloService extends HelloWorld.HelloWorldImplBase {


  @Override
  public void greet(
      HelloRequest.Reader req, StreamObserver<HelloResponce.Reader> responseObserver) {
    org.capnproto.MessageBuilder message = new org.capnproto.MessageBuilder();
    HelloResponce.Builder hr = message.initRoot(HelloRequest.factory);
    hr.setName("Hello, " + req.getName().toString());
    responseObserver.onNext(hr);
    responseObserver.onCompleted();
  }

}
