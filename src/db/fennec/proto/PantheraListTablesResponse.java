// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: resc/proto/PantheraService.proto

package db.fennec.proto;

/**
 * Protobuf type {@code db.fennec.proto.PantheraListTablesResponse}
 */
public  final class PantheraListTablesResponse extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:db.fennec.proto.PantheraListTablesResponse)
    PantheraListTablesResponseOrBuilder {
private static final long serialVersionUID = 0L;
  // Use PantheraListTablesResponse.newBuilder() to construct.
  private PantheraListTablesResponse(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private PantheraListTablesResponse() {
    statusCode_ = 0;
    statusMsg_ = "";
    table_ = com.google.protobuf.LazyStringArrayList.EMPTY;
  }

  @java.lang.Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return this.unknownFields;
  }
  private PantheraListTablesResponse(
      com.google.protobuf.CodedInputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    this();
    if (extensionRegistry == null) {
      throw new java.lang.NullPointerException();
    }
    int mutable_bitField0_ = 0;
    com.google.protobuf.UnknownFieldSet.Builder unknownFields =
        com.google.protobuf.UnknownFieldSet.newBuilder();
    try {
      boolean done = false;
      while (!done) {
        int tag = input.readTag();
        switch (tag) {
          case 0:
            done = true;
            break;
          default: {
            if (!parseUnknownFieldProto3(
                input, unknownFields, extensionRegistry, tag)) {
              done = true;
            }
            break;
          }
          case 8: {

            statusCode_ = input.readInt32();
            break;
          }
          case 18: {
            java.lang.String s = input.readStringRequireUtf8();

            statusMsg_ = s;
            break;
          }
          case 26: {
            java.lang.String s = input.readStringRequireUtf8();
            if (!((mutable_bitField0_ & 0x00000004) == 0x00000004)) {
              table_ = new com.google.protobuf.LazyStringArrayList();
              mutable_bitField0_ |= 0x00000004;
            }
            table_.add(s);
            break;
          }
        }
      }
    } catch (com.google.protobuf.InvalidProtocolBufferException e) {
      throw e.setUnfinishedMessage(this);
    } catch (java.io.IOException e) {
      throw new com.google.protobuf.InvalidProtocolBufferException(
          e).setUnfinishedMessage(this);
    } finally {
      if (((mutable_bitField0_ & 0x00000004) == 0x00000004)) {
        table_ = table_.getUnmodifiableView();
      }
      this.unknownFields = unknownFields.build();
      makeExtensionsImmutable();
    }
  }
  public static final com.google.protobuf.Descriptors.Descriptor
      getDescriptor() {
    return db.fennec.proto.PantheraServiceOuterClass.internal_static_db_fennec_proto_PantheraListTablesResponse_descriptor;
  }

  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return db.fennec.proto.PantheraServiceOuterClass.internal_static_db_fennec_proto_PantheraListTablesResponse_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            db.fennec.proto.PantheraListTablesResponse.class, db.fennec.proto.PantheraListTablesResponse.Builder.class);
  }

  private int bitField0_;
  public static final int STATUSCODE_FIELD_NUMBER = 1;
  private int statusCode_;
  /**
   * <code>int32 statusCode = 1;</code>
   */
  public int getStatusCode() {
    return statusCode_;
  }

  public static final int STATUSMSG_FIELD_NUMBER = 2;
  private volatile java.lang.Object statusMsg_;
  /**
   * <code>string statusMsg = 2;</code>
   */
  public java.lang.String getStatusMsg() {
    java.lang.Object ref = statusMsg_;
    if (ref instanceof java.lang.String) {
      return (java.lang.String) ref;
    } else {
      com.google.protobuf.ByteString bs = 
          (com.google.protobuf.ByteString) ref;
      java.lang.String s = bs.toStringUtf8();
      statusMsg_ = s;
      return s;
    }
  }
  /**
   * <code>string statusMsg = 2;</code>
   */
  public com.google.protobuf.ByteString
      getStatusMsgBytes() {
    java.lang.Object ref = statusMsg_;
    if (ref instanceof java.lang.String) {
      com.google.protobuf.ByteString b = 
          com.google.protobuf.ByteString.copyFromUtf8(
              (java.lang.String) ref);
      statusMsg_ = b;
      return b;
    } else {
      return (com.google.protobuf.ByteString) ref;
    }
  }

  public static final int TABLE_FIELD_NUMBER = 3;
  private com.google.protobuf.LazyStringList table_;
  /**
   * <code>repeated string table = 3;</code>
   */
  public com.google.protobuf.ProtocolStringList
      getTableList() {
    return table_;
  }
  /**
   * <code>repeated string table = 3;</code>
   */
  public int getTableCount() {
    return table_.size();
  }
  /**
   * <code>repeated string table = 3;</code>
   */
  public java.lang.String getTable(int index) {
    return table_.get(index);
  }
  /**
   * <code>repeated string table = 3;</code>
   */
  public com.google.protobuf.ByteString
      getTableBytes(int index) {
    return table_.getByteString(index);
  }

  private byte memoizedIsInitialized = -1;
  public final boolean isInitialized() {
    byte isInitialized = memoizedIsInitialized;
    if (isInitialized == 1) return true;
    if (isInitialized == 0) return false;

    memoizedIsInitialized = 1;
    return true;
  }

  public void writeTo(com.google.protobuf.CodedOutputStream output)
                      throws java.io.IOException {
    if (statusCode_ != 0) {
      output.writeInt32(1, statusCode_);
    }
    if (!getStatusMsgBytes().isEmpty()) {
      com.google.protobuf.GeneratedMessageV3.writeString(output, 2, statusMsg_);
    }
    for (int i = 0; i < table_.size(); i++) {
      com.google.protobuf.GeneratedMessageV3.writeString(output, 3, table_.getRaw(i));
    }
    unknownFields.writeTo(output);
  }

  public int getSerializedSize() {
    int size = memoizedSize;
    if (size != -1) return size;

    size = 0;
    if (statusCode_ != 0) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt32Size(1, statusCode_);
    }
    if (!getStatusMsgBytes().isEmpty()) {
      size += com.google.protobuf.GeneratedMessageV3.computeStringSize(2, statusMsg_);
    }
    {
      int dataSize = 0;
      for (int i = 0; i < table_.size(); i++) {
        dataSize += computeStringSizeNoTag(table_.getRaw(i));
      }
      size += dataSize;
      size += 1 * getTableList().size();
    }
    size += unknownFields.getSerializedSize();
    memoizedSize = size;
    return size;
  }

  @java.lang.Override
  public boolean equals(final java.lang.Object obj) {
    if (obj == this) {
     return true;
    }
    if (!(obj instanceof db.fennec.proto.PantheraListTablesResponse)) {
      return super.equals(obj);
    }
    db.fennec.proto.PantheraListTablesResponse other = (db.fennec.proto.PantheraListTablesResponse) obj;

    boolean result = true;
    result = result && (getStatusCode()
        == other.getStatusCode());
    result = result && getStatusMsg()
        .equals(other.getStatusMsg());
    result = result && getTableList()
        .equals(other.getTableList());
    result = result && unknownFields.equals(other.unknownFields);
    return result;
  }

  @java.lang.Override
  public int hashCode() {
    if (memoizedHashCode != 0) {
      return memoizedHashCode;
    }
    int hash = 41;
    hash = (19 * hash) + getDescriptor().hashCode();
    hash = (37 * hash) + STATUSCODE_FIELD_NUMBER;
    hash = (53 * hash) + getStatusCode();
    hash = (37 * hash) + STATUSMSG_FIELD_NUMBER;
    hash = (53 * hash) + getStatusMsg().hashCode();
    if (getTableCount() > 0) {
      hash = (37 * hash) + TABLE_FIELD_NUMBER;
      hash = (53 * hash) + getTableList().hashCode();
    }
    hash = (29 * hash) + unknownFields.hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static db.fennec.proto.PantheraListTablesResponse parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static db.fennec.proto.PantheraListTablesResponse parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static db.fennec.proto.PantheraListTablesResponse parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static db.fennec.proto.PantheraListTablesResponse parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static db.fennec.proto.PantheraListTablesResponse parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static db.fennec.proto.PantheraListTablesResponse parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static db.fennec.proto.PantheraListTablesResponse parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static db.fennec.proto.PantheraListTablesResponse parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static db.fennec.proto.PantheraListTablesResponse parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static db.fennec.proto.PantheraListTablesResponse parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static db.fennec.proto.PantheraListTablesResponse parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static db.fennec.proto.PantheraListTablesResponse parseFrom(
      com.google.protobuf.CodedInputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }

  public Builder newBuilderForType() { return newBuilder(); }
  public static Builder newBuilder() {
    return DEFAULT_INSTANCE.toBuilder();
  }
  public static Builder newBuilder(db.fennec.proto.PantheraListTablesResponse prototype) {
    return DEFAULT_INSTANCE.toBuilder().mergeFrom(prototype);
  }
  public Builder toBuilder() {
    return this == DEFAULT_INSTANCE
        ? new Builder() : new Builder().mergeFrom(this);
  }

  @java.lang.Override
  protected Builder newBuilderForType(
      com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
    Builder builder = new Builder(parent);
    return builder;
  }
  /**
   * Protobuf type {@code db.fennec.proto.PantheraListTablesResponse}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:db.fennec.proto.PantheraListTablesResponse)
      db.fennec.proto.PantheraListTablesResponseOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return db.fennec.proto.PantheraServiceOuterClass.internal_static_db_fennec_proto_PantheraListTablesResponse_descriptor;
    }

    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return db.fennec.proto.PantheraServiceOuterClass.internal_static_db_fennec_proto_PantheraListTablesResponse_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              db.fennec.proto.PantheraListTablesResponse.class, db.fennec.proto.PantheraListTablesResponse.Builder.class);
    }

    // Construct using db.fennec.proto.PantheraListTablesResponse.newBuilder()
    private Builder() {
      maybeForceBuilderInitialization();
    }

    private Builder(
        com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
      super(parent);
      maybeForceBuilderInitialization();
    }
    private void maybeForceBuilderInitialization() {
      if (com.google.protobuf.GeneratedMessageV3
              .alwaysUseFieldBuilders) {
      }
    }
    public Builder clear() {
      super.clear();
      statusCode_ = 0;

      statusMsg_ = "";

      table_ = com.google.protobuf.LazyStringArrayList.EMPTY;
      bitField0_ = (bitField0_ & ~0x00000004);
      return this;
    }

    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return db.fennec.proto.PantheraServiceOuterClass.internal_static_db_fennec_proto_PantheraListTablesResponse_descriptor;
    }

    public db.fennec.proto.PantheraListTablesResponse getDefaultInstanceForType() {
      return db.fennec.proto.PantheraListTablesResponse.getDefaultInstance();
    }

    public db.fennec.proto.PantheraListTablesResponse build() {
      db.fennec.proto.PantheraListTablesResponse result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    public db.fennec.proto.PantheraListTablesResponse buildPartial() {
      db.fennec.proto.PantheraListTablesResponse result = new db.fennec.proto.PantheraListTablesResponse(this);
      int from_bitField0_ = bitField0_;
      int to_bitField0_ = 0;
      result.statusCode_ = statusCode_;
      result.statusMsg_ = statusMsg_;
      if (((bitField0_ & 0x00000004) == 0x00000004)) {
        table_ = table_.getUnmodifiableView();
        bitField0_ = (bitField0_ & ~0x00000004);
      }
      result.table_ = table_;
      result.bitField0_ = to_bitField0_;
      onBuilt();
      return result;
    }

    public Builder clone() {
      return (Builder) super.clone();
    }
    public Builder setField(
        com.google.protobuf.Descriptors.FieldDescriptor field,
        java.lang.Object value) {
      return (Builder) super.setField(field, value);
    }
    public Builder clearField(
        com.google.protobuf.Descriptors.FieldDescriptor field) {
      return (Builder) super.clearField(field);
    }
    public Builder clearOneof(
        com.google.protobuf.Descriptors.OneofDescriptor oneof) {
      return (Builder) super.clearOneof(oneof);
    }
    public Builder setRepeatedField(
        com.google.protobuf.Descriptors.FieldDescriptor field,
        int index, java.lang.Object value) {
      return (Builder) super.setRepeatedField(field, index, value);
    }
    public Builder addRepeatedField(
        com.google.protobuf.Descriptors.FieldDescriptor field,
        java.lang.Object value) {
      return (Builder) super.addRepeatedField(field, value);
    }
    public Builder mergeFrom(com.google.protobuf.Message other) {
      if (other instanceof db.fennec.proto.PantheraListTablesResponse) {
        return mergeFrom((db.fennec.proto.PantheraListTablesResponse)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(db.fennec.proto.PantheraListTablesResponse other) {
      if (other == db.fennec.proto.PantheraListTablesResponse.getDefaultInstance()) return this;
      if (other.getStatusCode() != 0) {
        setStatusCode(other.getStatusCode());
      }
      if (!other.getStatusMsg().isEmpty()) {
        statusMsg_ = other.statusMsg_;
        onChanged();
      }
      if (!other.table_.isEmpty()) {
        if (table_.isEmpty()) {
          table_ = other.table_;
          bitField0_ = (bitField0_ & ~0x00000004);
        } else {
          ensureTableIsMutable();
          table_.addAll(other.table_);
        }
        onChanged();
      }
      this.mergeUnknownFields(other.unknownFields);
      onChanged();
      return this;
    }

    public final boolean isInitialized() {
      return true;
    }

    public Builder mergeFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      db.fennec.proto.PantheraListTablesResponse parsedMessage = null;
      try {
        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        parsedMessage = (db.fennec.proto.PantheraListTablesResponse) e.getUnfinishedMessage();
        throw e.unwrapIOException();
      } finally {
        if (parsedMessage != null) {
          mergeFrom(parsedMessage);
        }
      }
      return this;
    }
    private int bitField0_;

    private int statusCode_ ;
    /**
     * <code>int32 statusCode = 1;</code>
     */
    public int getStatusCode() {
      return statusCode_;
    }
    /**
     * <code>int32 statusCode = 1;</code>
     */
    public Builder setStatusCode(int value) {
      
      statusCode_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>int32 statusCode = 1;</code>
     */
    public Builder clearStatusCode() {
      
      statusCode_ = 0;
      onChanged();
      return this;
    }

    private java.lang.Object statusMsg_ = "";
    /**
     * <code>string statusMsg = 2;</code>
     */
    public java.lang.String getStatusMsg() {
      java.lang.Object ref = statusMsg_;
      if (!(ref instanceof java.lang.String)) {
        com.google.protobuf.ByteString bs =
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        statusMsg_ = s;
        return s;
      } else {
        return (java.lang.String) ref;
      }
    }
    /**
     * <code>string statusMsg = 2;</code>
     */
    public com.google.protobuf.ByteString
        getStatusMsgBytes() {
      java.lang.Object ref = statusMsg_;
      if (ref instanceof String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (java.lang.String) ref);
        statusMsg_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }
    /**
     * <code>string statusMsg = 2;</code>
     */
    public Builder setStatusMsg(
        java.lang.String value) {
      if (value == null) {
    throw new NullPointerException();
  }
  
      statusMsg_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>string statusMsg = 2;</code>
     */
    public Builder clearStatusMsg() {
      
      statusMsg_ = getDefaultInstance().getStatusMsg();
      onChanged();
      return this;
    }
    /**
     * <code>string statusMsg = 2;</code>
     */
    public Builder setStatusMsgBytes(
        com.google.protobuf.ByteString value) {
      if (value == null) {
    throw new NullPointerException();
  }
  checkByteStringIsUtf8(value);
      
      statusMsg_ = value;
      onChanged();
      return this;
    }

    private com.google.protobuf.LazyStringList table_ = com.google.protobuf.LazyStringArrayList.EMPTY;
    private void ensureTableIsMutable() {
      if (!((bitField0_ & 0x00000004) == 0x00000004)) {
        table_ = new com.google.protobuf.LazyStringArrayList(table_);
        bitField0_ |= 0x00000004;
       }
    }
    /**
     * <code>repeated string table = 3;</code>
     */
    public com.google.protobuf.ProtocolStringList
        getTableList() {
      return table_.getUnmodifiableView();
    }
    /**
     * <code>repeated string table = 3;</code>
     */
    public int getTableCount() {
      return table_.size();
    }
    /**
     * <code>repeated string table = 3;</code>
     */
    public java.lang.String getTable(int index) {
      return table_.get(index);
    }
    /**
     * <code>repeated string table = 3;</code>
     */
    public com.google.protobuf.ByteString
        getTableBytes(int index) {
      return table_.getByteString(index);
    }
    /**
     * <code>repeated string table = 3;</code>
     */
    public Builder setTable(
        int index, java.lang.String value) {
      if (value == null) {
    throw new NullPointerException();
  }
  ensureTableIsMutable();
      table_.set(index, value);
      onChanged();
      return this;
    }
    /**
     * <code>repeated string table = 3;</code>
     */
    public Builder addTable(
        java.lang.String value) {
      if (value == null) {
    throw new NullPointerException();
  }
  ensureTableIsMutable();
      table_.add(value);
      onChanged();
      return this;
    }
    /**
     * <code>repeated string table = 3;</code>
     */
    public Builder addAllTable(
        java.lang.Iterable<java.lang.String> values) {
      ensureTableIsMutable();
      com.google.protobuf.AbstractMessageLite.Builder.addAll(
          values, table_);
      onChanged();
      return this;
    }
    /**
     * <code>repeated string table = 3;</code>
     */
    public Builder clearTable() {
      table_ = com.google.protobuf.LazyStringArrayList.EMPTY;
      bitField0_ = (bitField0_ & ~0x00000004);
      onChanged();
      return this;
    }
    /**
     * <code>repeated string table = 3;</code>
     */
    public Builder addTableBytes(
        com.google.protobuf.ByteString value) {
      if (value == null) {
    throw new NullPointerException();
  }
  checkByteStringIsUtf8(value);
      ensureTableIsMutable();
      table_.add(value);
      onChanged();
      return this;
    }
    public final Builder setUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return super.setUnknownFieldsProto3(unknownFields);
    }

    public final Builder mergeUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return super.mergeUnknownFields(unknownFields);
    }


    // @@protoc_insertion_point(builder_scope:db.fennec.proto.PantheraListTablesResponse)
  }

  // @@protoc_insertion_point(class_scope:db.fennec.proto.PantheraListTablesResponse)
  private static final db.fennec.proto.PantheraListTablesResponse DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new db.fennec.proto.PantheraListTablesResponse();
  }

  public static db.fennec.proto.PantheraListTablesResponse getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  private static final com.google.protobuf.Parser<PantheraListTablesResponse>
      PARSER = new com.google.protobuf.AbstractParser<PantheraListTablesResponse>() {
    public PantheraListTablesResponse parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return new PantheraListTablesResponse(input, extensionRegistry);
    }
  };

  public static com.google.protobuf.Parser<PantheraListTablesResponse> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<PantheraListTablesResponse> getParserForType() {
    return PARSER;
  }

  public db.fennec.proto.PantheraListTablesResponse getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}
