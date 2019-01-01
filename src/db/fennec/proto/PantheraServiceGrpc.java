package db.fennec.proto;

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
    comments = "Source: resc/proto/PantheraService.proto")
public final class PantheraServiceGrpc {

  private PantheraServiceGrpc() {}

  public static final String SERVICE_NAME = "db.fennec.proto.PantheraService";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<db.fennec.proto.PantheraGetRequest,
      db.fennec.proto.PantheraGetResponse> getGetMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "get",
      requestType = db.fennec.proto.PantheraGetRequest.class,
      responseType = db.fennec.proto.PantheraGetResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<db.fennec.proto.PantheraGetRequest,
      db.fennec.proto.PantheraGetResponse> getGetMethod() {
    io.grpc.MethodDescriptor<db.fennec.proto.PantheraGetRequest, db.fennec.proto.PantheraGetResponse> getGetMethod;
    if ((getGetMethod = PantheraServiceGrpc.getGetMethod) == null) {
      synchronized (PantheraServiceGrpc.class) {
        if ((getGetMethod = PantheraServiceGrpc.getGetMethod) == null) {
          PantheraServiceGrpc.getGetMethod = getGetMethod = 
              io.grpc.MethodDescriptor.<db.fennec.proto.PantheraGetRequest, db.fennec.proto.PantheraGetResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "db.fennec.proto.PantheraService", "get"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  db.fennec.proto.PantheraGetRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  db.fennec.proto.PantheraGetResponse.getDefaultInstance()))
                  .setSchemaDescriptor(new PantheraServiceMethodDescriptorSupplier("get"))
                  .build();
          }
        }
     }
     return getGetMethod;
  }

  private static volatile io.grpc.MethodDescriptor<db.fennec.proto.PantheraGetKeysRequest,
      db.fennec.proto.PantheraGetKeysResponse> getGetKeysMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "getKeys",
      requestType = db.fennec.proto.PantheraGetKeysRequest.class,
      responseType = db.fennec.proto.PantheraGetKeysResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<db.fennec.proto.PantheraGetKeysRequest,
      db.fennec.proto.PantheraGetKeysResponse> getGetKeysMethod() {
    io.grpc.MethodDescriptor<db.fennec.proto.PantheraGetKeysRequest, db.fennec.proto.PantheraGetKeysResponse> getGetKeysMethod;
    if ((getGetKeysMethod = PantheraServiceGrpc.getGetKeysMethod) == null) {
      synchronized (PantheraServiceGrpc.class) {
        if ((getGetKeysMethod = PantheraServiceGrpc.getGetKeysMethod) == null) {
          PantheraServiceGrpc.getGetKeysMethod = getGetKeysMethod = 
              io.grpc.MethodDescriptor.<db.fennec.proto.PantheraGetKeysRequest, db.fennec.proto.PantheraGetKeysResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "db.fennec.proto.PantheraService", "getKeys"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  db.fennec.proto.PantheraGetKeysRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  db.fennec.proto.PantheraGetKeysResponse.getDefaultInstance()))
                  .setSchemaDescriptor(new PantheraServiceMethodDescriptorSupplier("getKeys"))
                  .build();
          }
        }
     }
     return getGetKeysMethod;
  }

  private static volatile io.grpc.MethodDescriptor<db.fennec.proto.PantheraGetKeySliceRequest,
      db.fennec.proto.PantheraGetKeysResponse> getGetKeySliceMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "getKeySlice",
      requestType = db.fennec.proto.PantheraGetKeySliceRequest.class,
      responseType = db.fennec.proto.PantheraGetKeysResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<db.fennec.proto.PantheraGetKeySliceRequest,
      db.fennec.proto.PantheraGetKeysResponse> getGetKeySliceMethod() {
    io.grpc.MethodDescriptor<db.fennec.proto.PantheraGetKeySliceRequest, db.fennec.proto.PantheraGetKeysResponse> getGetKeySliceMethod;
    if ((getGetKeySliceMethod = PantheraServiceGrpc.getGetKeySliceMethod) == null) {
      synchronized (PantheraServiceGrpc.class) {
        if ((getGetKeySliceMethod = PantheraServiceGrpc.getGetKeySliceMethod) == null) {
          PantheraServiceGrpc.getGetKeySliceMethod = getGetKeySliceMethod = 
              io.grpc.MethodDescriptor.<db.fennec.proto.PantheraGetKeySliceRequest, db.fennec.proto.PantheraGetKeysResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "db.fennec.proto.PantheraService", "getKeySlice"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  db.fennec.proto.PantheraGetKeySliceRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  db.fennec.proto.PantheraGetKeysResponse.getDefaultInstance()))
                  .setSchemaDescriptor(new PantheraServiceMethodDescriptorSupplier("getKeySlice"))
                  .build();
          }
        }
     }
     return getGetKeySliceMethod;
  }

  private static volatile io.grpc.MethodDescriptor<db.fennec.proto.PantheraGetMultiRequest,
      db.fennec.proto.PantheraGetMultiResponse> getGetMultiMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "getMulti",
      requestType = db.fennec.proto.PantheraGetMultiRequest.class,
      responseType = db.fennec.proto.PantheraGetMultiResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<db.fennec.proto.PantheraGetMultiRequest,
      db.fennec.proto.PantheraGetMultiResponse> getGetMultiMethod() {
    io.grpc.MethodDescriptor<db.fennec.proto.PantheraGetMultiRequest, db.fennec.proto.PantheraGetMultiResponse> getGetMultiMethod;
    if ((getGetMultiMethod = PantheraServiceGrpc.getGetMultiMethod) == null) {
      synchronized (PantheraServiceGrpc.class) {
        if ((getGetMultiMethod = PantheraServiceGrpc.getGetMultiMethod) == null) {
          PantheraServiceGrpc.getGetMultiMethod = getGetMultiMethod = 
              io.grpc.MethodDescriptor.<db.fennec.proto.PantheraGetMultiRequest, db.fennec.proto.PantheraGetMultiResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "db.fennec.proto.PantheraService", "getMulti"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  db.fennec.proto.PantheraGetMultiRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  db.fennec.proto.PantheraGetMultiResponse.getDefaultInstance()))
                  .setSchemaDescriptor(new PantheraServiceMethodDescriptorSupplier("getMulti"))
                  .build();
          }
        }
     }
     return getGetMultiMethod;
  }

  private static volatile io.grpc.MethodDescriptor<db.fennec.proto.PantheraOversertRequest,
      db.fennec.proto.PantheraOperationStatusResponse> getInsertMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "insert",
      requestType = db.fennec.proto.PantheraOversertRequest.class,
      responseType = db.fennec.proto.PantheraOperationStatusResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<db.fennec.proto.PantheraOversertRequest,
      db.fennec.proto.PantheraOperationStatusResponse> getInsertMethod() {
    io.grpc.MethodDescriptor<db.fennec.proto.PantheraOversertRequest, db.fennec.proto.PantheraOperationStatusResponse> getInsertMethod;
    if ((getInsertMethod = PantheraServiceGrpc.getInsertMethod) == null) {
      synchronized (PantheraServiceGrpc.class) {
        if ((getInsertMethod = PantheraServiceGrpc.getInsertMethod) == null) {
          PantheraServiceGrpc.getInsertMethod = getInsertMethod = 
              io.grpc.MethodDescriptor.<db.fennec.proto.PantheraOversertRequest, db.fennec.proto.PantheraOperationStatusResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "db.fennec.proto.PantheraService", "insert"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  db.fennec.proto.PantheraOversertRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  db.fennec.proto.PantheraOperationStatusResponse.getDefaultInstance()))
                  .setSchemaDescriptor(new PantheraServiceMethodDescriptorSupplier("insert"))
                  .build();
          }
        }
     }
     return getInsertMethod;
  }

  private static volatile io.grpc.MethodDescriptor<db.fennec.proto.PantheraOversertRequest,
      db.fennec.proto.PantheraOperationStatusResponse> getOverwriteMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "overwrite",
      requestType = db.fennec.proto.PantheraOversertRequest.class,
      responseType = db.fennec.proto.PantheraOperationStatusResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<db.fennec.proto.PantheraOversertRequest,
      db.fennec.proto.PantheraOperationStatusResponse> getOverwriteMethod() {
    io.grpc.MethodDescriptor<db.fennec.proto.PantheraOversertRequest, db.fennec.proto.PantheraOperationStatusResponse> getOverwriteMethod;
    if ((getOverwriteMethod = PantheraServiceGrpc.getOverwriteMethod) == null) {
      synchronized (PantheraServiceGrpc.class) {
        if ((getOverwriteMethod = PantheraServiceGrpc.getOverwriteMethod) == null) {
          PantheraServiceGrpc.getOverwriteMethod = getOverwriteMethod = 
              io.grpc.MethodDescriptor.<db.fennec.proto.PantheraOversertRequest, db.fennec.proto.PantheraOperationStatusResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "db.fennec.proto.PantheraService", "overwrite"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  db.fennec.proto.PantheraOversertRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  db.fennec.proto.PantheraOperationStatusResponse.getDefaultInstance()))
                  .setSchemaDescriptor(new PantheraServiceMethodDescriptorSupplier("overwrite"))
                  .build();
          }
        }
     }
     return getOverwriteMethod;
  }

  private static volatile io.grpc.MethodDescriptor<db.fennec.proto.PantheraDeleteTableRequest,
      db.fennec.proto.PantheraOperationStatusResponse> getDeleteTableMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "deleteTable",
      requestType = db.fennec.proto.PantheraDeleteTableRequest.class,
      responseType = db.fennec.proto.PantheraOperationStatusResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<db.fennec.proto.PantheraDeleteTableRequest,
      db.fennec.proto.PantheraOperationStatusResponse> getDeleteTableMethod() {
    io.grpc.MethodDescriptor<db.fennec.proto.PantheraDeleteTableRequest, db.fennec.proto.PantheraOperationStatusResponse> getDeleteTableMethod;
    if ((getDeleteTableMethod = PantheraServiceGrpc.getDeleteTableMethod) == null) {
      synchronized (PantheraServiceGrpc.class) {
        if ((getDeleteTableMethod = PantheraServiceGrpc.getDeleteTableMethod) == null) {
          PantheraServiceGrpc.getDeleteTableMethod = getDeleteTableMethod = 
              io.grpc.MethodDescriptor.<db.fennec.proto.PantheraDeleteTableRequest, db.fennec.proto.PantheraOperationStatusResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "db.fennec.proto.PantheraService", "deleteTable"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  db.fennec.proto.PantheraDeleteTableRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  db.fennec.proto.PantheraOperationStatusResponse.getDefaultInstance()))
                  .setSchemaDescriptor(new PantheraServiceMethodDescriptorSupplier("deleteTable"))
                  .build();
          }
        }
     }
     return getDeleteTableMethod;
  }

  private static volatile io.grpc.MethodDescriptor<db.fennec.proto.PantheraDeleteRowRequest,
      db.fennec.proto.PantheraOperationStatusResponse> getDeleteRowMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "deleteRow",
      requestType = db.fennec.proto.PantheraDeleteRowRequest.class,
      responseType = db.fennec.proto.PantheraOperationStatusResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<db.fennec.proto.PantheraDeleteRowRequest,
      db.fennec.proto.PantheraOperationStatusResponse> getDeleteRowMethod() {
    io.grpc.MethodDescriptor<db.fennec.proto.PantheraDeleteRowRequest, db.fennec.proto.PantheraOperationStatusResponse> getDeleteRowMethod;
    if ((getDeleteRowMethod = PantheraServiceGrpc.getDeleteRowMethod) == null) {
      synchronized (PantheraServiceGrpc.class) {
        if ((getDeleteRowMethod = PantheraServiceGrpc.getDeleteRowMethod) == null) {
          PantheraServiceGrpc.getDeleteRowMethod = getDeleteRowMethod = 
              io.grpc.MethodDescriptor.<db.fennec.proto.PantheraDeleteRowRequest, db.fennec.proto.PantheraOperationStatusResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "db.fennec.proto.PantheraService", "deleteRow"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  db.fennec.proto.PantheraDeleteRowRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  db.fennec.proto.PantheraOperationStatusResponse.getDefaultInstance()))
                  .setSchemaDescriptor(new PantheraServiceMethodDescriptorSupplier("deleteRow"))
                  .build();
          }
        }
     }
     return getDeleteRowMethod;
  }

  private static volatile io.grpc.MethodDescriptor<db.fennec.proto.PantheraSizeRequest,
      db.fennec.proto.PantheraSizeResponse> getSizeMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "size",
      requestType = db.fennec.proto.PantheraSizeRequest.class,
      responseType = db.fennec.proto.PantheraSizeResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<db.fennec.proto.PantheraSizeRequest,
      db.fennec.proto.PantheraSizeResponse> getSizeMethod() {
    io.grpc.MethodDescriptor<db.fennec.proto.PantheraSizeRequest, db.fennec.proto.PantheraSizeResponse> getSizeMethod;
    if ((getSizeMethod = PantheraServiceGrpc.getSizeMethod) == null) {
      synchronized (PantheraServiceGrpc.class) {
        if ((getSizeMethod = PantheraServiceGrpc.getSizeMethod) == null) {
          PantheraServiceGrpc.getSizeMethod = getSizeMethod = 
              io.grpc.MethodDescriptor.<db.fennec.proto.PantheraSizeRequest, db.fennec.proto.PantheraSizeResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "db.fennec.proto.PantheraService", "size"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  db.fennec.proto.PantheraSizeRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  db.fennec.proto.PantheraSizeResponse.getDefaultInstance()))
                  .setSchemaDescriptor(new PantheraServiceMethodDescriptorSupplier("size"))
                  .build();
          }
        }
     }
     return getSizeMethod;
  }

  private static volatile io.grpc.MethodDescriptor<db.fennec.proto.PantheraListTablesRequest,
      db.fennec.proto.PantheraListTablesResponse> getListMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "list",
      requestType = db.fennec.proto.PantheraListTablesRequest.class,
      responseType = db.fennec.proto.PantheraListTablesResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<db.fennec.proto.PantheraListTablesRequest,
      db.fennec.proto.PantheraListTablesResponse> getListMethod() {
    io.grpc.MethodDescriptor<db.fennec.proto.PantheraListTablesRequest, db.fennec.proto.PantheraListTablesResponse> getListMethod;
    if ((getListMethod = PantheraServiceGrpc.getListMethod) == null) {
      synchronized (PantheraServiceGrpc.class) {
        if ((getListMethod = PantheraServiceGrpc.getListMethod) == null) {
          PantheraServiceGrpc.getListMethod = getListMethod = 
              io.grpc.MethodDescriptor.<db.fennec.proto.PantheraListTablesRequest, db.fennec.proto.PantheraListTablesResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "db.fennec.proto.PantheraService", "list"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  db.fennec.proto.PantheraListTablesRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  db.fennec.proto.PantheraListTablesResponse.getDefaultInstance()))
                  .setSchemaDescriptor(new PantheraServiceMethodDescriptorSupplier("list"))
                  .build();
          }
        }
     }
     return getListMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static PantheraServiceStub newStub(io.grpc.Channel channel) {
    return new PantheraServiceStub(channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static PantheraServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    return new PantheraServiceBlockingStub(channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static PantheraServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    return new PantheraServiceFutureStub(channel);
  }

  /**
   * <pre>
   * SERVICE
   * </pre>
   */
  public static abstract class PantheraServiceImplBase implements io.grpc.BindableService {

    /**
     */
    public void get(db.fennec.proto.PantheraGetRequest request,
        io.grpc.stub.StreamObserver<db.fennec.proto.PantheraGetResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getGetMethod(), responseObserver);
    }

    /**
     */
    public void getKeys(db.fennec.proto.PantheraGetKeysRequest request,
        io.grpc.stub.StreamObserver<db.fennec.proto.PantheraGetKeysResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getGetKeysMethod(), responseObserver);
    }

    /**
     */
    public void getKeySlice(db.fennec.proto.PantheraGetKeySliceRequest request,
        io.grpc.stub.StreamObserver<db.fennec.proto.PantheraGetKeysResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getGetKeySliceMethod(), responseObserver);
    }

    /**
     */
    public void getMulti(db.fennec.proto.PantheraGetMultiRequest request,
        io.grpc.stub.StreamObserver<db.fennec.proto.PantheraGetMultiResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getGetMultiMethod(), responseObserver);
    }

    /**
     */
    public void insert(db.fennec.proto.PantheraOversertRequest request,
        io.grpc.stub.StreamObserver<db.fennec.proto.PantheraOperationStatusResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getInsertMethod(), responseObserver);
    }

    /**
     */
    public void overwrite(db.fennec.proto.PantheraOversertRequest request,
        io.grpc.stub.StreamObserver<db.fennec.proto.PantheraOperationStatusResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getOverwriteMethod(), responseObserver);
    }

    /**
     */
    public void deleteTable(db.fennec.proto.PantheraDeleteTableRequest request,
        io.grpc.stub.StreamObserver<db.fennec.proto.PantheraOperationStatusResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getDeleteTableMethod(), responseObserver);
    }

    /**
     */
    public void deleteRow(db.fennec.proto.PantheraDeleteRowRequest request,
        io.grpc.stub.StreamObserver<db.fennec.proto.PantheraOperationStatusResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getDeleteRowMethod(), responseObserver);
    }

    /**
     */
    public void size(db.fennec.proto.PantheraSizeRequest request,
        io.grpc.stub.StreamObserver<db.fennec.proto.PantheraSizeResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getSizeMethod(), responseObserver);
    }

    /**
     */
    public void list(db.fennec.proto.PantheraListTablesRequest request,
        io.grpc.stub.StreamObserver<db.fennec.proto.PantheraListTablesResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getListMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getGetMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                db.fennec.proto.PantheraGetRequest,
                db.fennec.proto.PantheraGetResponse>(
                  this, METHODID_GET)))
          .addMethod(
            getGetKeysMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                db.fennec.proto.PantheraGetKeysRequest,
                db.fennec.proto.PantheraGetKeysResponse>(
                  this, METHODID_GET_KEYS)))
          .addMethod(
            getGetKeySliceMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                db.fennec.proto.PantheraGetKeySliceRequest,
                db.fennec.proto.PantheraGetKeysResponse>(
                  this, METHODID_GET_KEY_SLICE)))
          .addMethod(
            getGetMultiMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                db.fennec.proto.PantheraGetMultiRequest,
                db.fennec.proto.PantheraGetMultiResponse>(
                  this, METHODID_GET_MULTI)))
          .addMethod(
            getInsertMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                db.fennec.proto.PantheraOversertRequest,
                db.fennec.proto.PantheraOperationStatusResponse>(
                  this, METHODID_INSERT)))
          .addMethod(
            getOverwriteMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                db.fennec.proto.PantheraOversertRequest,
                db.fennec.proto.PantheraOperationStatusResponse>(
                  this, METHODID_OVERWRITE)))
          .addMethod(
            getDeleteTableMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                db.fennec.proto.PantheraDeleteTableRequest,
                db.fennec.proto.PantheraOperationStatusResponse>(
                  this, METHODID_DELETE_TABLE)))
          .addMethod(
            getDeleteRowMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                db.fennec.proto.PantheraDeleteRowRequest,
                db.fennec.proto.PantheraOperationStatusResponse>(
                  this, METHODID_DELETE_ROW)))
          .addMethod(
            getSizeMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                db.fennec.proto.PantheraSizeRequest,
                db.fennec.proto.PantheraSizeResponse>(
                  this, METHODID_SIZE)))
          .addMethod(
            getListMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                db.fennec.proto.PantheraListTablesRequest,
                db.fennec.proto.PantheraListTablesResponse>(
                  this, METHODID_LIST)))
          .build();
    }
  }

  /**
   * <pre>
   * SERVICE
   * </pre>
   */
  public static final class PantheraServiceStub extends io.grpc.stub.AbstractStub<PantheraServiceStub> {
    private PantheraServiceStub(io.grpc.Channel channel) {
      super(channel);
    }

    private PantheraServiceStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected PantheraServiceStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new PantheraServiceStub(channel, callOptions);
    }

    /**
     */
    public void get(db.fennec.proto.PantheraGetRequest request,
        io.grpc.stub.StreamObserver<db.fennec.proto.PantheraGetResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getGetMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void getKeys(db.fennec.proto.PantheraGetKeysRequest request,
        io.grpc.stub.StreamObserver<db.fennec.proto.PantheraGetKeysResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getGetKeysMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void getKeySlice(db.fennec.proto.PantheraGetKeySliceRequest request,
        io.grpc.stub.StreamObserver<db.fennec.proto.PantheraGetKeysResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getGetKeySliceMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void getMulti(db.fennec.proto.PantheraGetMultiRequest request,
        io.grpc.stub.StreamObserver<db.fennec.proto.PantheraGetMultiResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getGetMultiMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void insert(db.fennec.proto.PantheraOversertRequest request,
        io.grpc.stub.StreamObserver<db.fennec.proto.PantheraOperationStatusResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getInsertMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void overwrite(db.fennec.proto.PantheraOversertRequest request,
        io.grpc.stub.StreamObserver<db.fennec.proto.PantheraOperationStatusResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getOverwriteMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void deleteTable(db.fennec.proto.PantheraDeleteTableRequest request,
        io.grpc.stub.StreamObserver<db.fennec.proto.PantheraOperationStatusResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getDeleteTableMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void deleteRow(db.fennec.proto.PantheraDeleteRowRequest request,
        io.grpc.stub.StreamObserver<db.fennec.proto.PantheraOperationStatusResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getDeleteRowMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void size(db.fennec.proto.PantheraSizeRequest request,
        io.grpc.stub.StreamObserver<db.fennec.proto.PantheraSizeResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getSizeMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void list(db.fennec.proto.PantheraListTablesRequest request,
        io.grpc.stub.StreamObserver<db.fennec.proto.PantheraListTablesResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getListMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   * <pre>
   * SERVICE
   * </pre>
   */
  public static final class PantheraServiceBlockingStub extends io.grpc.stub.AbstractStub<PantheraServiceBlockingStub> {
    private PantheraServiceBlockingStub(io.grpc.Channel channel) {
      super(channel);
    }

    private PantheraServiceBlockingStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected PantheraServiceBlockingStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new PantheraServiceBlockingStub(channel, callOptions);
    }

    /**
     */
    public db.fennec.proto.PantheraGetResponse get(db.fennec.proto.PantheraGetRequest request) {
      return blockingUnaryCall(
          getChannel(), getGetMethod(), getCallOptions(), request);
    }

    /**
     */
    public db.fennec.proto.PantheraGetKeysResponse getKeys(db.fennec.proto.PantheraGetKeysRequest request) {
      return blockingUnaryCall(
          getChannel(), getGetKeysMethod(), getCallOptions(), request);
    }

    /**
     */
    public db.fennec.proto.PantheraGetKeysResponse getKeySlice(db.fennec.proto.PantheraGetKeySliceRequest request) {
      return blockingUnaryCall(
          getChannel(), getGetKeySliceMethod(), getCallOptions(), request);
    }

    /**
     */
    public db.fennec.proto.PantheraGetMultiResponse getMulti(db.fennec.proto.PantheraGetMultiRequest request) {
      return blockingUnaryCall(
          getChannel(), getGetMultiMethod(), getCallOptions(), request);
    }

    /**
     */
    public db.fennec.proto.PantheraOperationStatusResponse insert(db.fennec.proto.PantheraOversertRequest request) {
      return blockingUnaryCall(
          getChannel(), getInsertMethod(), getCallOptions(), request);
    }

    /**
     */
    public db.fennec.proto.PantheraOperationStatusResponse overwrite(db.fennec.proto.PantheraOversertRequest request) {
      return blockingUnaryCall(
          getChannel(), getOverwriteMethod(), getCallOptions(), request);
    }

    /**
     */
    public db.fennec.proto.PantheraOperationStatusResponse deleteTable(db.fennec.proto.PantheraDeleteTableRequest request) {
      return blockingUnaryCall(
          getChannel(), getDeleteTableMethod(), getCallOptions(), request);
    }

    /**
     */
    public db.fennec.proto.PantheraOperationStatusResponse deleteRow(db.fennec.proto.PantheraDeleteRowRequest request) {
      return blockingUnaryCall(
          getChannel(), getDeleteRowMethod(), getCallOptions(), request);
    }

    /**
     */
    public db.fennec.proto.PantheraSizeResponse size(db.fennec.proto.PantheraSizeRequest request) {
      return blockingUnaryCall(
          getChannel(), getSizeMethod(), getCallOptions(), request);
    }

    /**
     */
    public db.fennec.proto.PantheraListTablesResponse list(db.fennec.proto.PantheraListTablesRequest request) {
      return blockingUnaryCall(
          getChannel(), getListMethod(), getCallOptions(), request);
    }
  }

  /**
   * <pre>
   * SERVICE
   * </pre>
   */
  public static final class PantheraServiceFutureStub extends io.grpc.stub.AbstractStub<PantheraServiceFutureStub> {
    private PantheraServiceFutureStub(io.grpc.Channel channel) {
      super(channel);
    }

    private PantheraServiceFutureStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected PantheraServiceFutureStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new PantheraServiceFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<db.fennec.proto.PantheraGetResponse> get(
        db.fennec.proto.PantheraGetRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getGetMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<db.fennec.proto.PantheraGetKeysResponse> getKeys(
        db.fennec.proto.PantheraGetKeysRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getGetKeysMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<db.fennec.proto.PantheraGetKeysResponse> getKeySlice(
        db.fennec.proto.PantheraGetKeySliceRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getGetKeySliceMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<db.fennec.proto.PantheraGetMultiResponse> getMulti(
        db.fennec.proto.PantheraGetMultiRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getGetMultiMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<db.fennec.proto.PantheraOperationStatusResponse> insert(
        db.fennec.proto.PantheraOversertRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getInsertMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<db.fennec.proto.PantheraOperationStatusResponse> overwrite(
        db.fennec.proto.PantheraOversertRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getOverwriteMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<db.fennec.proto.PantheraOperationStatusResponse> deleteTable(
        db.fennec.proto.PantheraDeleteTableRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getDeleteTableMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<db.fennec.proto.PantheraOperationStatusResponse> deleteRow(
        db.fennec.proto.PantheraDeleteRowRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getDeleteRowMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<db.fennec.proto.PantheraSizeResponse> size(
        db.fennec.proto.PantheraSizeRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getSizeMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<db.fennec.proto.PantheraListTablesResponse> list(
        db.fennec.proto.PantheraListTablesRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getListMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_GET = 0;
  private static final int METHODID_GET_KEYS = 1;
  private static final int METHODID_GET_KEY_SLICE = 2;
  private static final int METHODID_GET_MULTI = 3;
  private static final int METHODID_INSERT = 4;
  private static final int METHODID_OVERWRITE = 5;
  private static final int METHODID_DELETE_TABLE = 6;
  private static final int METHODID_DELETE_ROW = 7;
  private static final int METHODID_SIZE = 8;
  private static final int METHODID_LIST = 9;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final PantheraServiceImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(PantheraServiceImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_GET:
          serviceImpl.get((db.fennec.proto.PantheraGetRequest) request,
              (io.grpc.stub.StreamObserver<db.fennec.proto.PantheraGetResponse>) responseObserver);
          break;
        case METHODID_GET_KEYS:
          serviceImpl.getKeys((db.fennec.proto.PantheraGetKeysRequest) request,
              (io.grpc.stub.StreamObserver<db.fennec.proto.PantheraGetKeysResponse>) responseObserver);
          break;
        case METHODID_GET_KEY_SLICE:
          serviceImpl.getKeySlice((db.fennec.proto.PantheraGetKeySliceRequest) request,
              (io.grpc.stub.StreamObserver<db.fennec.proto.PantheraGetKeysResponse>) responseObserver);
          break;
        case METHODID_GET_MULTI:
          serviceImpl.getMulti((db.fennec.proto.PantheraGetMultiRequest) request,
              (io.grpc.stub.StreamObserver<db.fennec.proto.PantheraGetMultiResponse>) responseObserver);
          break;
        case METHODID_INSERT:
          serviceImpl.insert((db.fennec.proto.PantheraOversertRequest) request,
              (io.grpc.stub.StreamObserver<db.fennec.proto.PantheraOperationStatusResponse>) responseObserver);
          break;
        case METHODID_OVERWRITE:
          serviceImpl.overwrite((db.fennec.proto.PantheraOversertRequest) request,
              (io.grpc.stub.StreamObserver<db.fennec.proto.PantheraOperationStatusResponse>) responseObserver);
          break;
        case METHODID_DELETE_TABLE:
          serviceImpl.deleteTable((db.fennec.proto.PantheraDeleteTableRequest) request,
              (io.grpc.stub.StreamObserver<db.fennec.proto.PantheraOperationStatusResponse>) responseObserver);
          break;
        case METHODID_DELETE_ROW:
          serviceImpl.deleteRow((db.fennec.proto.PantheraDeleteRowRequest) request,
              (io.grpc.stub.StreamObserver<db.fennec.proto.PantheraOperationStatusResponse>) responseObserver);
          break;
        case METHODID_SIZE:
          serviceImpl.size((db.fennec.proto.PantheraSizeRequest) request,
              (io.grpc.stub.StreamObserver<db.fennec.proto.PantheraSizeResponse>) responseObserver);
          break;
        case METHODID_LIST:
          serviceImpl.list((db.fennec.proto.PantheraListTablesRequest) request,
              (io.grpc.stub.StreamObserver<db.fennec.proto.PantheraListTablesResponse>) responseObserver);
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

  private static abstract class PantheraServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    PantheraServiceBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return db.fennec.proto.PantheraServiceOuterClass.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("PantheraService");
    }
  }

  private static final class PantheraServiceFileDescriptorSupplier
      extends PantheraServiceBaseDescriptorSupplier {
    PantheraServiceFileDescriptorSupplier() {}
  }

  private static final class PantheraServiceMethodDescriptorSupplier
      extends PantheraServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    PantheraServiceMethodDescriptorSupplier(String methodName) {
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
      synchronized (PantheraServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new PantheraServiceFileDescriptorSupplier())
              .addMethod(getGetMethod())
              .addMethod(getGetKeysMethod())
              .addMethod(getGetKeySliceMethod())
              .addMethod(getGetMultiMethod())
              .addMethod(getInsertMethod())
              .addMethod(getOverwriteMethod())
              .addMethod(getDeleteTableMethod())
              .addMethod(getDeleteRowMethod())
              .addMethod(getSizeMethod())
              .addMethod(getListMethod())
              .build();
        }
      }
    }
    return result;
  }
}
