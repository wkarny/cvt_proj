//package io.grpc.examples;


import com.google.common.util.concurrent.FutureCallback;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.MoreExecutors;
import io.grpc.CallOptions;
import io.grpc.Channel;
import io.grpc.ClientCall;
import io.grpc.Status;
import io.grpc.Status.Code;
//import io.grpc.examples.KvGson.CreateRequest;
//import io.grpc.examples.KvGson.CreateResponse;
//import io.grpc.examples.KvGson.DeleteRequest;
// import io.grpc.examples.KvGson.DeleteResponse;
// import io.grpc.examples.KvGson.RetrieveRequest;
// import io.grpc.examples.KvGson.RetrieveResponse;
// import io.grpc.examples.KvGson.UpdateRequest;
// import io.grpc.examples.KvGson.UpdateResponse;
import io.grpc.stub.ClientCalls;
import java.nio.ByteBuffer;
import java.util.Random;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.capnproto.test.HelloWorld.HelloRequest;
import org.capnproto.test.HelloWorld.HelloResponce;

/**
 * Performs sample work load, by creating random keys and values, retrieving them, updating them,
 * and deleting them.  A real program would actually use the values, and they wouldn't be random.
 */
final class HelloClient {
  private static final Logger logger = Logger.getLogger(HelloClient.class.getName());

  private final Channel channel;

  private AtomicLong rpcCount = new AtomicLong();
  private final Semaphore limiter = new Semaphore(100);

  HelloClient(Channel channel) {
    this.channel = channel;
  }

  long getRpcCount() {
    return rpcCount.get();
  }

  /**
   * Does the client work until {@code done.get()} returns true.  Callers should set done to true,
   * and wait for this method to return.
   */
  void doClientWork(AtomicBoolean done) throws InterruptedException {
    Random random = new Random();
    AtomicReference<Throwable> errors = new AtomicReference<>();

    while (!done.get() && errors.get() == null) {
      // Pick a random CRUD action to take.
      int command = 0;
      if (command == 0) {
        doCreate(channel, errors);
        continue;
      }
    }
    if (errors.get() != null) {
      throw new RuntimeException(errors.get());
    }
  }

  /**
   * Creates a random key and value.
   */
  private void doCreate(Channel chan, AtomicReference<Throwable> error)
      throws InterruptedException {
    limiter.acquire();
    ClientCall<HelloRequest.Reader, HelloResponce.Reader> call =
        chan.newCall(HelloWorld.GREET_METHOD, CallOptions.DEFAULT);
    org.capnproto.MessageBuilder message = new org.capnproto.MessageBuilder();
    HelloRequest.Builder hr = message.initRoot(HelloRequest.factory);
    hr.setName("Wyes");

    ListenableFuture<HelloResponce.Reader> res = ClientCalls.futureUnaryCall(call, hr);
    res.addListener(() ->  {
      rpcCount.incrementAndGet();
      limiter.release();
    }, MoreExecutors.directExecutor());
    Futures.addCallback(res, new FutureCallback<HelloResponce.Reader>() {
      @Override
      public void onSuccess(HelloResponse.Reader result) {
       
      }

      @Override
      public void onFailure(Throwable t) {
        Status status = Status.fromThrowable(t);
        if (status.getCode() == Code.ALREADY_EXISTS) {
          
        }
      }
    });
  }

  
}
