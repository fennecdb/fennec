// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: resc/proto/Fennec.proto

package db.fennec.proto;

public interface FMetaProtoOrBuilder extends
    // @@protoc_insertion_point(interface_extends:db.fennec.proto.FMetaProto)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <code>repeated int64 used_label_suffix = 1;</code>
   */
  java.util.List<java.lang.Long> getUsedLabelSuffixList();
  /**
   * <code>repeated int64 used_label_suffix = 1;</code>
   */
  int getUsedLabelSuffixCount();
  /**
   * <code>repeated int64 used_label_suffix = 1;</code>
   */
  long getUsedLabelSuffix(int index);

  /**
   * <code>string field = 2;</code>
   */
  java.lang.String getField();
  /**
   * <code>string field = 2;</code>
   */
  com.google.protobuf.ByteString
      getFieldBytes();

  /**
   * <code>int64 time_per_bucket = 3;</code>
   */
  long getTimePerBucket();
}
