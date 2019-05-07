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





