package db.fennec.api.proto;

import static io.grpc.MethodDescriptor.generateFullMethodName;
import static io.grpc.stub.ClientCalls.asyncBidiStreamingCall;
import static io.grpc.stub.ClientCalls.asyncClientStreamingCall;
import static io.grpc.stub.ClientCalls.asyncServerStreamingCall;
import static io.grpc.stub.ClientCalls.asyncUnaryCall;
import static io.grpc.stub.ClientCalls.blockingServerStreamingCall;
import static io.grpc.stub.ClientCalls.blockingUnaryCall;
import static io.grpc.stub.ClientCalls.futureUnaryCall;
import static io.grpc.stub.ServerCalls.asyncBidiStreamingCall;
import static io.grpc.stub.ServerCalls.asyncClientStreamingCall;
import static io.grpc.stub.ServerCalls.asyncServerStreamingCall;
import static io.grpc.stub.ServerCalls.asyncUnaryCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedStreamingCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall;

/**
 * <pre>
 * SERVICE
 * </pre>
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.17.1)",
    comments = "Source: FennecService.proto")
public final class FennecServiceGrpc {

  private FennecServiceGrpc() {}

  public static final String SERVICE_NAME = "db.fennec.api.proto.FennecService";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<db.fennec.api.proto.FQueryReq,
      db.fennec.api.proto.FQueryResp> getQueryMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "query",
      requestType = db.fennec.api.proto.FQueryReq.class,
      responseType = db.fennec.api.proto.FQueryResp.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<db.fennec.api.proto.FQueryReq,
      db.fennec.api.proto.FQueryResp> getQueryMethod() {
    io.grpc.MethodDescriptor<db.fennec.api.proto.FQueryReq, db.fennec.api.proto.FQueryResp> getQueryMethod;
    if ((getQueryMethod = FennecServiceGrpc.getQueryMethod) == null) {
      synchronized (FennecServiceGrpc.class) {
        if ((getQueryMethod = FennecServiceGrpc.getQueryMethod) == null) {
          FennecServiceGrpc.getQueryMethod = getQueryMethod = 
              io.grpc.MethodDescriptor.<db.fennec.api.proto.FQueryReq, db.fennec.api.proto.FQueryResp>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "db.fennec.api.proto.FennecService", "query"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  db.fennec.api.proto.FQueryReq.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  db.fennec.api.proto.FQueryResp.getDefaultInstance()))
                  .setSchemaDescriptor(new FennecServiceMethodDescriptorSupplier("query"))
                  .build();
          }
        }
     }
     return getQueryMethod;
  }

  private static volatile io.grpc.MethodDescriptor<db.fennec.api.proto.FWriteReq,
      db.fennec.api.proto.FStatus> getInsertMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "insert",
      requestType = db.fennec.api.proto.FWriteReq.class,
      responseType = db.fennec.api.proto.FStatus.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<db.fennec.api.proto.FWriteReq,
      db.fennec.api.proto.FStatus> getInsertMethod() {
    io.grpc.MethodDescriptor<db.fennec.api.proto.FWriteReq, db.fennec.api.proto.FStatus> getInsertMethod;
    if ((getInsertMethod = FennecServiceGrpc.getInsertMethod) == null) {
      synchronized (FennecServiceGrpc.class) {
        if ((getInsertMethod = FennecServiceGrpc.getInsertMethod) == null) {
          FennecServiceGrpc.getInsertMethod = getInsertMethod = 
              io.grpc.MethodDescriptor.<db.fennec.api.proto.FWriteReq, db.fennec.api.proto.FStatus>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "db.fennec.api.proto.FennecService", "insert"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  db.fennec.api.proto.FWriteReq.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  db.fennec.api.proto.FStatus.getDefaultInstance()))
                  .setSchemaDescriptor(new FennecServiceMethodDescriptorSupplier("insert"))
                  .build();
          }
        }
     }
     return getInsertMethod;
  }

  private static volatile io.grpc.MethodDescriptor<db.fennec.api.proto.FWriteReq,
      db.fennec.api.proto.FStatus> getUpsertMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "upsert",
      requestType = db.fennec.api.proto.FWriteReq.class,
      responseType = db.fennec.api.proto.FStatus.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<db.fennec.api.proto.FWriteReq,
      db.fennec.api.proto.FStatus> getUpsertMethod() {
    io.grpc.MethodDescriptor<db.fennec.api.proto.FWriteReq, db.fennec.api.proto.FStatus> getUpsertMethod;
    if ((getUpsertMethod = FennecServiceGrpc.getUpsertMethod) == null) {
      synchronized (FennecServiceGrpc.class) {
        if ((getUpsertMethod = FennecServiceGrpc.getUpsertMethod) == null) {
          FennecServiceGrpc.getUpsertMethod = getUpsertMethod = 
              io.grpc.MethodDescriptor.<db.fennec.api.proto.FWriteReq, db.fennec.api.proto.FStatus>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "db.fennec.api.proto.FennecService", "upsert"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  db.fennec.api.proto.FWriteReq.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  db.fennec.api.proto.FStatus.getDefaultInstance()))
                  .setSchemaDescriptor(new FennecServiceMethodDescriptorSupplier("upsert"))
                  .build();
          }
        }
     }
     return getUpsertMethod;
  }

  private static volatile io.grpc.MethodDescriptor<db.fennec.api.proto.FRemoveReq,
      db.fennec.api.proto.FStatus> getRemoveMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "remove",
      requestType = db.fennec.api.proto.FRemoveReq.class,
      responseType = db.fennec.api.proto.FStatus.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<db.fennec.api.proto.FRemoveReq,
      db.fennec.api.proto.FStatus> getRemoveMethod() {
    io.grpc.MethodDescriptor<db.fennec.api.proto.FRemoveReq, db.fennec.api.proto.FStatus> getRemoveMethod;
    if ((getRemoveMethod = FennecServiceGrpc.getRemoveMethod) == null) {
      synchronized (FennecServiceGrpc.class) {
        if ((getRemoveMethod = FennecServiceGrpc.getRemoveMethod) == null) {
          FennecServiceGrpc.getRemoveMethod = getRemoveMethod = 
              io.grpc.MethodDescriptor.<db.fennec.api.proto.FRemoveReq, db.fennec.api.proto.FStatus>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "db.fennec.api.proto.FennecService", "remove"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  db.fennec.api.proto.FRemoveReq.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  db.fennec.api.proto.FStatus.getDefaultInstance()))
                  .setSchemaDescriptor(new FennecServiceMethodDescriptorSupplier("remove"))
                  .build();
          }
        }
     }
     return getRemoveMethod;
  }

  private static volatile io.grpc.MethodDescriptor<db.fennec.api.proto.FRemoveNSReq,
      db.fennec.api.proto.FStatus> getRemoveNSMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "removeNS",
      requestType = db.fennec.api.proto.FRemoveNSReq.class,
      responseType = db.fennec.api.proto.FStatus.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<db.fennec.api.proto.FRemoveNSReq,
      db.fennec.api.proto.FStatus> getRemoveNSMethod() {
    io.grpc.MethodDescriptor<db.fennec.api.proto.FRemoveNSReq, db.fennec.api.proto.FStatus> getRemoveNSMethod;
    if ((getRemoveNSMethod = FennecServiceGrpc.getRemoveNSMethod) == null) {
      synchronized (FennecServiceGrpc.class) {
        if ((getRemoveNSMethod = FennecServiceGrpc.getRemoveNSMethod) == null) {
          FennecServiceGrpc.getRemoveNSMethod = getRemoveNSMethod = 
              io.grpc.MethodDescriptor.<db.fennec.api.proto.FRemoveNSReq, db.fennec.api.proto.FStatus>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "db.fennec.api.proto.FennecService", "removeNS"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  db.fennec.api.proto.FRemoveNSReq.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  db.fennec.api.proto.FStatus.getDefaultInstance()))
                  .setSchemaDescriptor(new FennecServiceMethodDescriptorSupplier("removeNS"))
                  .build();
          }
        }
     }
     return getRemoveNSMethod;
  }

  private static volatile io.grpc.MethodDescriptor<db.fennec.api.proto.FListReq,
      db.fennec.api.proto.FListResp> getListMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "list",
      requestType = db.fennec.api.proto.FListReq.class,
      responseType = db.fennec.api.proto.FListResp.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<db.fennec.api.proto.FListReq,
      db.fennec.api.proto.FListResp> getListMethod() {
    io.grpc.MethodDescriptor<db.fennec.api.proto.FListReq, db.fennec.api.proto.FListResp> getListMethod;
    if ((getListMethod = FennecServiceGrpc.getListMethod) == null) {
      synchronized (FennecServiceGrpc.class) {
        if ((getListMethod = FennecServiceGrpc.getListMethod) == null) {
          FennecServiceGrpc.getListMethod = getListMethod = 
              io.grpc.MethodDescriptor.<db.fennec.api.proto.FListReq, db.fennec.api.proto.FListResp>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "db.fennec.api.proto.FennecService", "list"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  db.fennec.api.proto.FListReq.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  db.fennec.api.proto.FListResp.getDefaultInstance()))
                  .setSchemaDescriptor(new FennecServiceMethodDescriptorSupplier("list"))
                  .build();
          }
        }
     }
     return getListMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static FennecServiceStub newStub(io.grpc.Channel channel) {
    return new FennecServiceStub(channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static FennecServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    return new FennecServiceBlockingStub(channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static FennecServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    return new FennecServiceFutureStub(channel);
  }

  /**
   * <pre>
   * SERVICE
   * </pre>
   */
  public static abstract class FennecServiceImplBase implements io.grpc.BindableService {

    /**
     */
    public void query(db.fennec.api.proto.FQueryReq request,
        io.grpc.stub.StreamObserver<db.fennec.api.proto.FQueryResp> responseObserver) {
      asyncUnimplementedUnaryCall(getQueryMethod(), responseObserver);
    }

    /**
     */
    public void insert(db.fennec.api.proto.FWriteReq request,
        io.grpc.stub.StreamObserver<db.fennec.api.proto.FStatus> responseObserver) {
      asyncUnimplementedUnaryCall(getInsertMethod(), responseObserver);
    }

    /**
     */
    public void upsert(db.fennec.api.proto.FWriteReq request,
        io.grpc.stub.StreamObserver<db.fennec.api.proto.FStatus> responseObserver) {
      asyncUnimplementedUnaryCall(getUpsertMethod(), responseObserver);
    }

    /**
     */
    public void remove(db.fennec.api.proto.FRemoveReq request,
        io.grpc.stub.StreamObserver<db.fennec.api.proto.FStatus> responseObserver) {
      asyncUnimplementedUnaryCall(getRemoveMethod(), responseObserver);
    }

    /**
     */
    public void removeNS(db.fennec.api.proto.FRemoveNSReq request,
        io.grpc.stub.StreamObserver<db.fennec.api.proto.FStatus> responseObserver) {
      asyncUnimplementedUnaryCall(getRemoveNSMethod(), responseObserver);
    }

    /**
     */
    public void list(db.fennec.api.proto.FListReq request,
        io.grpc.stub.StreamObserver<db.fennec.api.proto.FListResp> responseObserver) {
      asyncUnimplementedUnaryCall(getListMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getQueryMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                db.fennec.api.proto.FQueryReq,
                db.fennec.api.proto.FQueryResp>(
                  this, METHODID_QUERY)))
          .addMethod(
            getInsertMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                db.fennec.api.proto.FWriteReq,
                db.fennec.api.proto.FStatus>(
                  this, METHODID_INSERT)))
          .addMethod(
            getUpsertMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                db.fennec.api.proto.FWriteReq,
                db.fennec.api.proto.FStatus>(
                  this, METHODID_UPSERT)))
          .addMethod(
            getRemoveMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                db.fennec.api.proto.FRemoveReq,
                db.fennec.api.proto.FStatus>(
                  this, METHODID_REMOVE)))
          .addMethod(
            getRemoveNSMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                db.fennec.api.proto.FRemoveNSReq,
                db.fennec.api.proto.FStatus>(
                  this, METHODID_REMOVE_NS)))
          .addMethod(
            getListMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                db.fennec.api.proto.FListReq,
                db.fennec.api.proto.FListResp>(
                  this, METHODID_LIST)))
          .build();
    }
  }

  /**
   * <pre>
   * SERVICE
   * </pre>
   */
  public static final class FennecServiceStub extends io.grpc.stub.AbstractStub<FennecServiceStub> {
    private FennecServiceStub(io.grpc.Channel channel) {
      super(channel);
    }

    private FennecServiceStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected FennecServiceStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new FennecServiceStub(channel, callOptions);
    }

    /**
     */
    public void query(db.fennec.api.proto.FQueryReq request,
        io.grpc.stub.StreamObserver<db.fennec.api.proto.FQueryResp> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getQueryMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void insert(db.fennec.api.proto.FWriteReq request,
        io.grpc.stub.StreamObserver<db.fennec.api.proto.FStatus> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getInsertMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void upsert(db.fennec.api.proto.FWriteReq request,
        io.grpc.stub.StreamObserver<db.fennec.api.proto.FStatus> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getUpsertMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void remove(db.fennec.api.proto.FRemoveReq request,
        io.grpc.stub.StreamObserver<db.fennec.api.proto.FStatus> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getRemoveMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void removeNS(db.fennec.api.proto.FRemoveNSReq request,
        io.grpc.stub.StreamObserver<db.fennec.api.proto.FStatus> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getRemoveNSMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void list(db.fennec.api.proto.FListReq request,
        io.grpc.stub.StreamObserver<db.fennec.api.proto.FListResp> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getListMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   * <pre>
   * SERVICE
   * </pre>
   */
  public static final class FennecServiceBlockingStub extends io.grpc.stub.AbstractStub<FennecServiceBlockingStub> {
    private FennecServiceBlockingStub(io.grpc.Channel channel) {
      super(channel);
    }

    private FennecServiceBlockingStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected FennecServiceBlockingStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new FennecServiceBlockingStub(channel, callOptions);
    }

    /**
     */
    public db.fennec.api.proto.FQueryResp query(db.fennec.api.proto.FQueryReq request) {
      return blockingUnaryCall(
          getChannel(), getQueryMethod(), getCallOptions(), request);
    }

    /**
     */
    public db.fennec.api.proto.FStatus insert(db.fennec.api.proto.FWriteReq request) {
      return blockingUnaryCall(
          getChannel(), getInsertMethod(), getCallOptions(), request);
    }

    /**
     */
    public db.fennec.api.proto.FStatus upsert(db.fennec.api.proto.FWriteReq request) {
      return blockingUnaryCall(
          getChannel(), getUpsertMethod(), getCallOptions(), request);
    }

    /**
     */
    public db.fennec.api.proto.FStatus remove(db.fennec.api.proto.FRemoveReq request) {
      return blockingUnaryCall(
          getChannel(), getRemoveMethod(), getCallOptions(), request);
    }

    /**
     */
    public db.fennec.api.proto.FStatus removeNS(db.fennec.api.proto.FRemoveNSReq request) {
      return blockingUnaryCall(
          getChannel(), getRemoveNSMethod(), getCallOptions(), request);
    }

    /**
     */
    public db.fennec.api.proto.FListResp list(db.fennec.api.proto.FListReq request) {
      return blockingUnaryCall(
          getChannel(), getListMethod(), getCallOptions(), request);
    }
  }

  /**
   * <pre>
   * SERVICE
   * </pre>
   */
  public static final class FennecServiceFutureStub extends io.grpc.stub.AbstractStub<FennecServiceFutureStub> {
    private FennecServiceFutureStub(io.grpc.Channel channel) {
      super(channel);
    }

    private FennecServiceFutureStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected FennecServiceFutureStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new FennecServiceFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<db.fennec.api.proto.FQueryResp> query(
        db.fennec.api.proto.FQueryReq request) {
      return futureUnaryCall(
          getChannel().newCall(getQueryMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<db.fennec.api.proto.FStatus> insert(
        db.fennec.api.proto.FWriteReq request) {
      return futureUnaryCall(
          getChannel().newCall(getInsertMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<db.fennec.api.proto.FStatus> upsert(
        db.fennec.api.proto.FWriteReq request) {
      return futureUnaryCall(
          getChannel().newCall(getUpsertMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<db.fennec.api.proto.FStatus> remove(
        db.fennec.api.proto.FRemoveReq request) {
      return futureUnaryCall(
          getChannel().newCall(getRemoveMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<db.fennec.api.proto.FStatus> removeNS(
        db.fennec.api.proto.FRemoveNSReq request) {
      return futureUnaryCall(
          getChannel().newCall(getRemoveNSMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<db.fennec.api.proto.FListResp> list(
        db.fennec.api.proto.FListReq request) {
      return futureUnaryCall(
          getChannel().newCall(getListMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_QUERY = 0;
  private static final int METHODID_INSERT = 1;
  private static final int METHODID_UPSERT = 2;
  private static final int METHODID_REMOVE = 3;
  private static final int METHODID_REMOVE_NS = 4;
  private static final int METHODID_LIST = 5;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final FennecServiceImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(FennecServiceImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_QUERY:
          serviceImpl.query((db.fennec.api.proto.FQueryReq) request,
              (io.grpc.stub.StreamObserver<db.fennec.api.proto.FQueryResp>) responseObserver);
          break;
        case METHODID_INSERT:
          serviceImpl.insert((db.fennec.api.proto.FWriteReq) request,
              (io.grpc.stub.StreamObserver<db.fennec.api.proto.FStatus>) responseObserver);
          break;
        case METHODID_UPSERT:
          serviceImpl.upsert((db.fennec.api.proto.FWriteReq) request,
              (io.grpc.stub.StreamObserver<db.fennec.api.proto.FStatus>) responseObserver);
          break;
        case METHODID_REMOVE:
          serviceImpl.remove((db.fennec.api.proto.FRemoveReq) request,
              (io.grpc.stub.StreamObserver<db.fennec.api.proto.FStatus>) responseObserver);
          break;
        case METHODID_REMOVE_NS:
          serviceImpl.removeNS((db.fennec.api.proto.FRemoveNSReq) request,
              (io.grpc.stub.StreamObserver<db.fennec.api.proto.FStatus>) responseObserver);
          break;
        case METHODID_LIST:
          serviceImpl.list((db.fennec.api.proto.FListReq) request,
              (io.grpc.stub.StreamObserver<db.fennec.api.proto.FListResp>) responseObserver);
          break;
        default:
          throw new AssertionError();
      }
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public io.grpc.stub.StreamObserver<Req> invoke(
        io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        default:
          throw new AssertionError();
      }
    }
  }

  private static abstract class FennecServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    FennecServiceBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return db.fennec.api.proto.FennecServiceProto.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("FennecService");
    }
  }

  private static final class FennecServiceFileDescriptorSupplier
      extends FennecServiceBaseDescriptorSupplier {
    FennecServiceFileDescriptorSupplier() {}
  }

  private static final class FennecServiceMethodDescriptorSupplier
      extends FennecServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    FennecServiceMethodDescriptorSupplier(String methodName) {
      this.methodName = methodName;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.MethodDescriptor getMethodDescriptor() {
      return getServiceDescriptor().findMethodByName(methodName);
    }
  }

  private static volatile io.grpc.ServiceDescriptor serviceDescriptor;

  public static io.grpc.ServiceDescriptor getServiceDescriptor() {
    io.grpc.ServiceDescriptor result = serviceDescriptor;
    if (result == null) {
      synchronized (FennecServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new FennecServiceFileDescriptorSupplier())
              .addMethod(getQueryMethod())
              .addMethod(getInsertMethod())
              .addMethod(getUpsertMethod())
              .addMethod(getRemoveMethod())
              .addMethod(getRemoveNSMethod())
              .addMethod(getListMethod())
              .build();
        }
      }
    }
    return result;
  }
}
