var grpc = require("grpc");
var _ = require('lodash');
var async = require('async');

var PROTO_PATH = __dirname + "/../../../proto/Student.proto";
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
var client = new studentServiceProto.StudentService("localhost:8899", grpc.credentials.createInsecure());

/*
    单请求，单响应
    直接调用 <method>(requestObj, callbackFn(err, responseObj){});
 */
function GetRealNameByUsername(callback) {
    console.info("==========================");
    console.info("GetRealNameByUsername");
    var next = _.after(1, callback);
    function realNameCallback(err, response) {
        if (err) {
            callback(err);
            return;
        }
        console.info("response: " + response.realname);
        next();
    }
    client.GetRealNameByUsername({username: 'zhangsan'}, realNameCallback);
}

// 总结：返回单个响应对象，通过responseCallback()接收，返回多个响应对象，通过call监听data事件接收

/* 
    单请求，多响应
    通过call监听事件接收流响应，调用方法 var call = <method>(requestObj);
    // 接收响应对象
    call.on('data', function(responseObj){});
    // 标识接收完毕
    call.on('end', function() {});
    // 发生错误
    call.on('error', function(err) {});
    // 请求状态
    call.on('status', function(status) {});
 */
function GetStudentsByAge(callback) {
    console.info("==========================");
    console.info("GetStudentsByAge");
    var call = client.GetStudentsByAge({age: 20});
    call.on('data', function (student) {
        printStudent(student);
    });
    call.on('end', callback);
    call.on('error', function (err) {
        console.error("error: " + err);
    });
    call.on('status', function (status) {
        console.info('status code: ' + status.code + ", details: " + status.details);
    });
}

function printStudent(student) {
    console.info("===================")
    console.info("name: " + student.name);
    console.info("age: " + student.age);
    console.info("city: " + student.city);
}

/*
    多请求，单响应
    通过方法返回的call发送请求流对象，通过方法传入的响应回调函数接收服务端消息
    var call = <method>(responseCallback(err, responseObj){});
    发送请求消息
    foreach to call.write(requestObj);
    标识请求消息发送完毕
    call.end();
    消息发送完成后服务端会受到请求流并返回响应的响应对象，最终会回调responseCallback接收响应对象
 */
function GetStudentWrapperByAges(callback) {
    console.info("==================");
    console.info("GetStudentWrapperByAges");
    var call = client.GetStudentWrapperByAges(function(error, response) {
        if (error) {
            callback(error);
            return;
        }
        var students = response.students;
        var student;
        for (var i = 0; i < students.length; i++) {
            student = students[i];
            printStudent(student);
        } 
        callback();
    });
    function buildSender(age) {
        return function (callback) {
            call.write(age);
            _.delay(callback, _.random(500, 800));
        }
    }
    
    var senders = [];
    for (var i = 0; i < 3; i++) {
        senders.push(buildSender({age: 20+i}));
    }
    async.series(senders, function() {
        call.end();
    });
}

/*
    多请求，多响应
    通过回调call来发送和接收消息
    var call = <method>();
    // 接收请求
    call.on('data', function(responseObj){});
    // 标识接收完毕
    call.on('end', function() {});
    // 发送请求流对象
    foreach to call.write(requestObj);
    // 标识请求对象发送完毕
    call.end();
 */
function GetStudentsByAges(callback) {
    console.info("==================");
    console.info("GetStudentsByAges");
    var call = client.GetStudentsByAges();
    call.on('data', function (student) {
        printStudent(student);
    });
    call.on('end', callback);
    
    for (var i = 0; i < 3; i++) {
        call.write({age: 20+i});
    } 
    call.end();
}


function main() {
    // 任务串行执行，每个任务都会有一个callback,在任务执行完成时调用callback，将继续执行下一个任务
    async.series([
        GetRealNameByUsername,
        GetStudentsByAge,
        GetStudentWrapperByAges,
        GetStudentsByAges
    ])
}

// 程序主入口
main();





