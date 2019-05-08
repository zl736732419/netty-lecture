var PROTO_PATH = __dirname + "/../../../proto/Student.proto";
var grpc = require("grpc");
var protoLoader = require('@grpc/proto-loader');
var packageDefinition = protoLoader.loadSync(
    PROTO_PATH,
    {keepCase: true,
        longs: String,
        enums: String,
        defaults: true,
        oneofs: true
    });

var studentServiceProto = grpc.loadPackageDefinition(packageDefinition).com.zheng.protobuf;
var server = new grpc.Server();
server.addService(studentServiceProto.StudentService.service, {
    GetRealNameByUsername: GetRealNameByUsername,
    GetStudentsByAge: GetStudentsByAge,
    GetStudentWrapperByAges: GetStudentWrapperByAges,
    GetStudentsByAges: GetStudentsByAges
});

/* 
    总结
    对于grpc提供的四种方法可以分为两类：单响应和多响应
    单响应方法参数(call, callback)，通过call.request对象来获取请求参数，callback返回单个响应值
    callback(err, responseObj);
    多响应方法参数(call), 通过call注册data事件获取流请求消息，通过call.request获取单个请求消息
    通过call.write响应消息
    call.on('data', function(request){});
    通过end通知客户端消息已经响应完毕
    call.on('end', function() {});
    call.write(responseObj)
 */

function GetStudentsByAge(call) {
    var age = call.request.age;
    console.log("request: " + age);
    for (var i = 0; i < 3; i++) {
        call.write({name: "李四", age: age,  city: "深圳"});
    }
    call.end();
}

function GetStudentWrapperByAges(call, callback) {
    var students = [];
    call.on('data', function (request) {
        console.info("request params: " + request.age);
        students.push({name: "李四", age: request.age,  city: "深圳"});
    });
    call.on('end', function () {
        callback(null, {students: students});
    });
}

function GetStudentsByAges(call) {
    call.on('data', function (request) {
        console.info("request params: " + request.age);
        call.write({name: "李四", age: request.age,  city: "深圳"});
    });
    call.on('end', function () {
        call.end();
    });
}


function GetRealNameByUsername(call, callback) {
    console.info("request: " + call.request.username);
    callback(null, {realname: '李四'});
}

server.bind("localhost:8899", grpc.ServerCredentials.createInsecure());
console.info("server listen on 8899...");
server.start();