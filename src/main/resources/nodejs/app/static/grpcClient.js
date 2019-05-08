var messages = require('./Student_pb');
var services = require('./Student_grpc_pb');
var grpc = require('grpc');
var async = require('async');
var _ = require('lodash');


var client = new services.StudentServiceClient("localhost:8899", 
    grpc.credentials.createInsecure());

// 单请求，单响应
function GetRealNameByUsername(callback) {
    console.info('---------------------');
    console.info('GetRealNameByUsername');
    var next = _.after(1, callback);
    
    var request = new messages.MyRequest();
    request.setUsername('zhangsan');
    client.getRealNameByUsername(request, function (err, response) {
        console.info("response: " + response.getRealname());
        next();
    });
}

function printStudent(student) {
    console.info("===================")
    console.info("name: " + student.getName());
    console.info("age: " + student.getAge());
    console.info("city: " + student.getCity());
}

function GetStudentsByAge(callback) {
    console.info('---------------------');
    console.info('GetStudentsByAge');
    var request = new messages.StudentRequest();
    request.setAge(20);
    var call = client.getStudentsByAge(request);
    call.on('data', function (student) {
        printStudent(student);
    });
    call.on('end', callback);
}

function GetStudentWrapperByAges(callback) {
    console.info('---------------------'); 
    console.info('GetStudentWrapperByAges');
    var call = client.getStudentWrapperByAges(function (error, response) {
        if (error) {
            console.info("error!");
            callback(error);
            return;
        }
        var students = response.getStudentsList();
        var student;
        for (var i = 0; i < students.length; i++) {
            student = students[i];
            printStudent(student);
        }
        callback();
    });
    var request;
    for (var i = 0; i < 3; i++) {
        request = new messages.StudentRequest();
        request.setAge(20+i);
        call.write(request);
    }
    call.end();
}

function GetStudentsByAges(callback) {
    console.info('---------------------');
    console.info('GetStudentsByAges');
    var call = client.getStudentsByAges();
    call.on('data', function (student) {
        printStudent(student);
    });
    call.on('end', callback);
    var request;
    for (var i = 0; i < 3; i++) {
        request = new messages.StudentRequest();
        request.setAge(30+i);
        call.write(request);
    }
    call.end();
}

function main() {
    async.series([
        GetRealNameByUsername,
        GetStudentsByAge,
        GetStudentWrapperByAges,
        GetStudentsByAges
    ]);
}

main();