// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: resc/proto/Fennec.proto

package db.fennec.proto;

public interface FDataEntryProtoOrBuilder extends
    // @@protoc_insertion_point(interface_extends:db.fennec.proto.FDataEntryProto)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <code>string field = 1;</code>
   */
  java.lang.String getField();
  /**
   * <code>string field = 1;</code>
   */
  com.google.protobuf.ByteString
      getFieldBytes();

  /**
   * <code>string namespace = 2;</code>
   */
  java.lang.String getNamespace();
  /**
   * <code>string namespace = 2;</code>
   */
  com.google.protobuf.ByteString
      getNamespaceBytes();

  /**
   * <code>.db.fennec.proto.FDataProto data = 3;</code>
   */
  boolean hasData();
  /**
   * <code>.db.fennec.proto.FDataProto data = 3;</code>
   */
  db.fennec.proto.FDataProto getData();
  /**
   * <code>.db.fennec.proto.FDataProto data = 3;</code>
   */
  db.fennec.proto.FDataProtoOrBuilder getDataOrBuilder();
}
