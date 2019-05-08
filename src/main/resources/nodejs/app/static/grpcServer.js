var messages = require('./Student_pb');
var services = require('./Student_grpc_pb');
var grpc = require('grpc');

var server = new grpc.Server();
server.addService(services.StudentServiceService, {
    getRealNameByUsername: GetRealNameByUsername,
    getStudentsByAge: GetStudentsByAge,
    getStudentsByAges: GetStudentsByAges,
    getStudentWrapperByAges: GetStudentWrapperByAges
});

function GetRealNameByUsername(call, callback) {
    console.info('request: ' + call.request.getUsername());
    var response = new messages.MyResponse();
    response.setRealname("张三");
    callback(null, response);
}

function GetStudentsByAge(call) {
    var age = call.request.getAge();
    console.info("request: " + age);
    var response;
    for (var i = 0; i < 3; i++) {
        response = new messages.StudentResponse();
        response.setName('张三');
        response.setAge(age);
        response.setCity('深圳');
        call.write(response);
    }
    call.end();
}

function GetStudentWrapperByAges(call, callback) {
    var students = [];
    call.on('data', function (request) {
        var student = new messages.StudentResponse();
        student.setName('张三');
        student.setAge(request.getAge());
        student.setCity('深圳');
        students.push(student);
    });
    call.on('end', function () {
        var response = new messages.StudentResponseList();
        response.setStudentsList(students);
        callback(null, response);
    });
}

function GetStudentsByAges(call) {
    call.on('data', function (request) {
        var student = new messages.StudentResponse();
        student.setName('李四');
        student.setAge(request.getAge());
        student.setCity('深圳');
        call.write(student);
    });
    call.on('end', function () {
        call.end();
    })
}

server.bind('localhost:8899', grpc.ServerCredentials.createInsecure());
console.info('server listening on 8899...');
server.start();