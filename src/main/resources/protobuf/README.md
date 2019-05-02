使用protoc.exe编译生成java代码
$ cd src/main/resources/protobuf
$ protoc.exe --proto_path=./ --java_out=../../java Student.proto
