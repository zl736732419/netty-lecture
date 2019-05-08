// GENERATED CODE -- DO NOT EDIT!

'use strict';
var grpc = require('grpc');
var Student_pb = require('./Student_pb.js');

function serialize_com_zheng_protobuf_MyRequest(arg) {
  if (!(arg instanceof Student_pb.MyRequest)) {
    throw new Error('Expected argument of type com.zheng.protobuf.MyRequest');
  }
  return Buffer.from(arg.serializeBinary());
}

function deserialize_com_zheng_protobuf_MyRequest(buffer_arg) {
  return Student_pb.MyRequest.deserializeBinary(new Uint8Array(buffer_arg));
}

function serialize_com_zheng_protobuf_MyResponse(arg) {
  if (!(arg instanceof Student_pb.MyResponse)) {
    throw new Error('Expected argument of type com.zheng.protobuf.MyResponse');
  }
  return Buffer.from(arg.serializeBinary());
}

function deserialize_com_zheng_protobuf_MyResponse(buffer_arg) {
  return Student_pb.MyResponse.deserializeBinary(new Uint8Array(buffer_arg));
}

function serialize_com_zheng_protobuf_StudentRequest(arg) {
  if (!(arg instanceof Student_pb.StudentRequest)) {
    throw new Error('Expected argument of type com.zheng.protobuf.StudentRequest');
  }
  return Buffer.from(arg.serializeBinary());
}

function deserialize_com_zheng_protobuf_StudentRequest(buffer_arg) {
  return Student_pb.StudentRequest.deserializeBinary(new Uint8Array(buffer_arg));
}

function serialize_com_zheng_protobuf_StudentResponse(arg) {
  if (!(arg instanceof Student_pb.StudentResponse)) {
    throw new Error('Expected argument of type com.zheng.protobuf.StudentResponse');
  }
  return Buffer.from(arg.serializeBinary());
}

function deserialize_com_zheng_protobuf_StudentResponse(buffer_arg) {
  return Student_pb.StudentResponse.deserializeBinary(new Uint8Array(buffer_arg));
}

function serialize_com_zheng_protobuf_StudentResponseList(arg) {
  if (!(arg instanceof Student_pb.StudentResponseList)) {
    throw new Error('Expected argument of type com.zheng.protobuf.StudentResponseList');
  }
  return Buffer.from(arg.serializeBinary());
}

function deserialize_com_zheng_protobuf_StudentResponseList(buffer_arg) {
  return Student_pb.StudentResponseList.deserializeBinary(new Uint8Array(buffer_arg));
}


// service rpc中的请求和响应要求参数必须是message类型
var StudentServiceService = exports.StudentServiceService = {
  getRealNameByUsername: {
    path: '/com.zheng.protobuf.StudentService/GetRealNameByUsername',
    requestStream: false,
    responseStream: false,
    requestType: Student_pb.MyRequest,
    responseType: Student_pb.MyResponse,
    requestSerialize: serialize_com_zheng_protobuf_MyRequest,
    requestDeserialize: deserialize_com_zheng_protobuf_MyRequest,
    responseSerialize: serialize_com_zheng_protobuf_MyResponse,
    responseDeserialize: deserialize_com_zheng_protobuf_MyResponse,
  },
  getStudentsByAge: {
    path: '/com.zheng.protobuf.StudentService/GetStudentsByAge',
    requestStream: false,
    responseStream: true,
    requestType: Student_pb.StudentRequest,
    responseType: Student_pb.StudentResponse,
    requestSerialize: serialize_com_zheng_protobuf_StudentRequest,
    requestDeserialize: deserialize_com_zheng_protobuf_StudentRequest,
    responseSerialize: serialize_com_zheng_protobuf_StudentResponse,
    responseDeserialize: deserialize_com_zheng_protobuf_StudentResponse,
  },
  getStudentWrapperByAges: {
    path: '/com.zheng.protobuf.StudentService/GetStudentWrapperByAges',
    requestStream: true,
    responseStream: false,
    requestType: Student_pb.StudentRequest,
    responseType: Student_pb.StudentResponseList,
    requestSerialize: serialize_com_zheng_protobuf_StudentRequest,
    requestDeserialize: deserialize_com_zheng_protobuf_StudentRequest,
    responseSerialize: serialize_com_zheng_protobuf_StudentResponseList,
    responseDeserialize: deserialize_com_zheng_protobuf_StudentResponseList,
  },
  getStudentsByAges: {
    path: '/com.zheng.protobuf.StudentService/GetStudentsByAges',
    requestStream: true,
    responseStream: true,
    requestType: Student_pb.StudentRequest,
    responseType: Student_pb.StudentResponse,
    requestSerialize: serialize_com_zheng_protobuf_StudentRequest,
    requestDeserialize: deserialize_com_zheng_protobuf_StudentRequest,
    responseSerialize: serialize_com_zheng_protobuf_StudentResponse,
    responseDeserialize: deserialize_com_zheng_protobuf_StudentResponse,
  },
};

exports.StudentServiceClient = grpc.makeGenericClientConstructor(StudentServiceService);
