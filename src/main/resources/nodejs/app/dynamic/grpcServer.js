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