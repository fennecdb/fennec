// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: resc/proto/Fennec.proto

package db.fennec.proto;

public final class FennecProto {
  private FennecProto() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistryLite registry) {
  }

  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
    registerAllExtensions(
        (com.google.protobuf.ExtensionRegistryLite) registry);
  }
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_db_fennec_proto_FResultProto_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_db_fennec_proto_FResultProto_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_db_fennec_proto_FDataBucketProto_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_db_fennec_proto_FDataBucketProto_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_db_fennec_proto_FDataEntryProto_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_db_fennec_proto_FDataEntryProto_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_db_fennec_proto_FDataProto_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_db_fennec_proto_FDataProto_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_db_fennec_proto_FMetaOldProto_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_db_fennec_proto_FMetaOldProto_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_db_fennec_proto_FMetaLabelProto_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_db_fennec_proto_FMetaLabelProto_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_db_fennec_proto_FNamespacesProto_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_db_fennec_proto_FNamespacesProto_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_db_fennec_proto_FMetaProto_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_db_fennec_proto_FMetaProto_fieldAccessorTable;

  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static  com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    java.lang.String[] descriptorData = {
      "\n\027resc/proto/Fennec.proto\022\017db.fennec.pro" +
      "to\">\n\014FResultProto\022.\n\004data\030\001 \003(\0132 .db.fe" +
      "nnec.proto.FDataEntryProto\"=\n\020FDataBucke" +
      "tProto\022)\n\004data\030\001 \003(\0132\033.db.fennec.proto.F" +
      "DataProto\"^\n\017FDataEntryProto\022\r\n\005field\030\001 " +
      "\001(\t\022\021\n\tnamespace\030\002 \001(\t\022)\n\004data\030\003 \001(\0132\033.d" +
      "b.fennec.proto.FDataProto\".\n\nFDataProto\022" +
      "\r\n\005value\030\001 \001(\001\022\021\n\ttimestamp\030\002 \001(\003\"^\n\rFMe" +
      "taOldProto\0224\n\nused_label\030\001 \003(\0132 .db.fenn" +
      "ec.proto.FMetaLabelProto\022\027\n\017time_per_buc" +
      "ket\030\002 \001(\003\"3\n\017FMetaLabelProto\022\r\n\005label\030\001 " +
      "\001(\t\022\021\n\ttimestamp\030\002 \001(\003\"\036\n\020FNamespacesPro" +
      "to\022\n\n\002ns\030\001 \003(\t\"O\n\nFMetaProto\022\031\n\021used_lab" +
      "el_suffix\030\001 \003(\003\022\r\n\005field\030\002 \001(\t\022\027\n\017time_p" +
      "er_bucket\030\003 \001(\003B\017B\013FennecProtoP\001b\006proto3"
    };
    com.google.protobuf.Descriptors.FileDescriptor.InternalDescriptorAssigner assigner =
        new com.google.protobuf.Descriptors.FileDescriptor.    InternalDescriptorAssigner() {
          public com.google.protobuf.ExtensionRegistry assignDescriptors(
              com.google.protobuf.Descriptors.FileDescriptor root) {
            descriptor = root;
            return null;
          }
        };
    com.google.protobuf.Descriptors.FileDescriptor
      .internalBuildGeneratedFileFrom(descriptorData,
        new com.google.protobuf.Descriptors.FileDescriptor[] {
        }, assigner);
    internal_static_db_fennec_proto_FResultProto_descriptor =
      getDescriptor().getMessageTypes().get(0);
    internal_static_db_fennec_proto_FResultProto_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_db_fennec_proto_FResultProto_descriptor,
        new java.lang.String[] { "Data", });
    internal_static_db_fennec_proto_FDataBucketProto_descriptor =
      getDescriptor().getMessageTypes().get(1);
    internal_static_db_fennec_proto_FDataBucketProto_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_db_fennec_proto_FDataBucketProto_descriptor,
        new java.lang.String[] { "Data", });
    internal_static_db_fennec_proto_FDataEntryProto_descriptor =
      getDescriptor().getMessageTypes().get(2);
    internal_static_db_fennec_proto_FDataEntryProto_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_db_fennec_proto_FDataEntryProto_descriptor,
        new java.lang.String[] { "Field", "Namespace", "Data", });
    internal_static_db_fennec_proto_FDataProto_descriptor =
      getDescriptor().getMessageTypes().get(3);
    internal_static_db_fennec_proto_FDataProto_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_db_fennec_proto_FDataProto_descriptor,
        new java.lang.String[] { "Value", "Timestamp", });
    internal_static_db_fennec_proto_FMetaOldProto_descriptor =
      getDescriptor().getMessageTypes().get(4);
    internal_static_db_fennec_proto_FMetaOldProto_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_db_fennec_proto_FMetaOldProto_descriptor,
        new java.lang.String[] { "UsedLabel", "TimePerBucket", });
    internal_static_db_fennec_proto_FMetaLabelProto_descriptor =
      getDescriptor().getMessageTypes().get(5);
    internal_static_db_fennec_proto_FMetaLabelProto_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_db_fennec_proto_FMetaLabelProto_descriptor,
        new java.lang.String[] { "Label", "Timestamp", });
    internal_static_db_fennec_proto_FNamespacesProto_descriptor =
      getDescriptor().getMessageTypes().get(6);
    internal_static_db_fennec_proto_FNamespacesProto_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_db_fennec_proto_FNamespacesProto_descriptor,
        new java.lang.String[] { "Ns", });
    internal_static_db_fennec_proto_FMetaProto_descriptor =
      getDescriptor().getMessageTypes().get(7);
    internal_static_db_fennec_proto_FMetaProto_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_db_fennec_proto_FMetaProto_descriptor,
        new java.lang.String[] { "UsedLabelSuffix", "Field", "TimePerBucket", });
  }

  // @@protoc_insertion_point(outer_class_scope)
}
