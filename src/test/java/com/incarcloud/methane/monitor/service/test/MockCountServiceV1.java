package com.incarcloud.methane.monitor.service.test;

import com.incarcloud.std.CountServiceV1Grpc;
import com.incarcloud.std.HelloM;
import io.grpc.stub.StreamObserver;

class MockCountServiceV1WithFix3 extends CountServiceV1Grpc.CountServiceV1ImplBase {
    @Override
    public void count(HelloM.HelloCountArgV1 request, StreamObserver<HelloM.HelloCountV1> responseObserver) {

        HelloM.VinCount vc1 = HelloM.VinCount.newBuilder()
                .setVin("TEST98765ABCDE001")
                .setCount(32)
                .build();

        HelloM.VinCount vc2 = HelloM.VinCount.newBuilder()
                .setVin("TEST98765ABCDE002")
                .setCount(0)
                .build();

        HelloM.VinCount vc3 = HelloM.VinCount.newBuilder()
                .setVin("TEST98765ABCDE003")
                .setCount(23765)
                .build();

        HelloM.HelloCountV1 result = HelloM.HelloCountV1.newBuilder()
                .addList(vc1)
                .addList(vc2)
                .addList(vc3)
                .build();

        responseObserver.onNext(result);
        responseObserver.onCompleted();
    }
}
