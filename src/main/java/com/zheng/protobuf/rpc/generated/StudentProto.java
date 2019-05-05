// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: Student.proto

package com.zheng.protobuf.rpc.generated;

public final class StudentProto {
  private StudentProto() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistryLite registry) {
  }

  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
    registerAllExtensions(
        (com.google.protobuf.ExtensionRegistryLite) registry);
  }
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_com_zheng_protobuf_MyRequest_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_com_zheng_protobuf_MyRequest_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_com_zheng_protobuf_MyResponse_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_com_zheng_protobuf_MyResponse_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_com_zheng_protobuf_StudentRequest_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_com_zheng_protobuf_StudentRequest_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_com_zheng_protobuf_StudentResponse_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_com_zheng_protobuf_StudentResponse_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_com_zheng_protobuf_StudentResponseList_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_com_zheng_protobuf_StudentResponseList_fieldAccessorTable;

  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static  com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    String[] descriptorData = {
      "\n\rStudent.proto\022\022com.zheng.protobuf\"\035\n\tM" +
      "yRequest\022\020\n\010username\030\001 \001(\t\"\036\n\nMyResponse" +
      "\022\020\n\010realname\030\001 \001(\t\"\035\n\016StudentRequest\022\013\n\003" +
      "age\030\001 \001(\005\":\n\017StudentResponse\022\014\n\004name\030\001 \001" +
      "(\t\022\013\n\003age\030\002 \001(\005\022\014\n\004city\030\003 \001(\t\"L\n\023Student" +
      "ResponseList\0225\n\010students\030\001 \003(\0132#.com.zhe" +
      "ng.protobuf.StudentResponse2\267\002\n\016StudentS" +
      "ervice\022X\n\025GetRealNameByUsername\022\035.com.zh" +
      "eng.protobuf.MyRequest\032\036.com.zheng.proto" +
      "buf.MyResponse\"\000\022_\n\020GetStudentsByAge\022\".c" +
      "om.zheng.protobuf.StudentRequest\032#.com.z" +
      "heng.protobuf.StudentResponse\"\0000\001\022j\n\027Get" +
      "StudentWrapperByAges\022\".com.zheng.protobu" +
      "f.StudentRequest\032\'.com.zheng.protobuf.St" +
      "udentResponseList\"\000(\001B2\n com.zheng.proto" +
      "buf.rpc.generatedB\014StudentProtoP\001b\006proto" +
      "3"
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
    internal_static_com_zheng_protobuf_MyRequest_descriptor =
      getDescriptor().getMessageTypes().get(0);
    internal_static_com_zheng_protobuf_MyRequest_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_com_zheng_protobuf_MyRequest_descriptor,
        new String[] { "Username", });
    internal_static_com_zheng_protobuf_MyResponse_descriptor =
      getDescriptor().getMessageTypes().get(1);
    internal_static_com_zheng_protobuf_MyResponse_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_com_zheng_protobuf_MyResponse_descriptor,
        new String[] { "Realname", });
    internal_static_com_zheng_protobuf_StudentRequest_descriptor =
      getDescriptor().getMessageTypes().get(2);
    internal_static_com_zheng_protobuf_StudentRequest_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_com_zheng_protobuf_StudentRequest_descriptor,
        new String[] { "Age", });
    internal_static_com_zheng_protobuf_StudentResponse_descriptor =
      getDescriptor().getMessageTypes().get(3);
    internal_static_com_zheng_protobuf_StudentResponse_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_com_zheng_protobuf_StudentResponse_descriptor,
        new String[] { "Name", "Age", "City", });
    internal_static_com_zheng_protobuf_StudentResponseList_descriptor =
      getDescriptor().getMessageTypes().get(4);
    internal_static_com_zheng_protobuf_StudentResponseList_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_com_zheng_protobuf_StudentResponseList_descriptor,
        new String[] { "Students", });
  }

  // @@protoc_insertion_point(outer_class_scope)
}
