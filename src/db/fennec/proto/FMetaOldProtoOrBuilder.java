// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: resc/proto/Fennec.proto

package db.fennec.proto;

public interface FMetaOldProtoOrBuilder extends
    // @@protoc_insertion_point(interface_extends:db.fennec.proto.FMetaOldProto)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <code>repeated .db.fennec.proto.FMetaLabelProto used_label = 1;</code>
   */
  java.util.List<db.fennec.proto.FMetaLabelProto> 
      getUsedLabelList();
  /**
   * <code>repeated .db.fennec.proto.FMetaLabelProto used_label = 1;</code>
   */
  db.fennec.proto.FMetaLabelProto getUsedLabel(int index);
  /**
   * <code>repeated .db.fennec.proto.FMetaLabelProto used_label = 1;</code>
   */
  int getUsedLabelCount();
  /**
   * <code>repeated .db.fennec.proto.FMetaLabelProto used_label = 1;</code>
   */
  java.util.List<? extends db.fennec.proto.FMetaLabelProtoOrBuilder> 
      getUsedLabelOrBuilderList();
  /**
   * <code>repeated .db.fennec.proto.FMetaLabelProto used_label = 1;</code>
   */
  db.fennec.proto.FMetaLabelProtoOrBuilder getUsedLabelOrBuilder(
      int index);

  /**
   * <code>int64 time_per_bucket = 2;</code>
   */
  long getTimePerBucket();
}
