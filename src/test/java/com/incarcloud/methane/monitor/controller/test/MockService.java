package com.incarcloud.methane.monitor.controller.test;

import com.incarcloud.methane.monitor.service.ICountService;
import com.incarcloud.std.HelloM;

import java.util.ArrayList;
import java.util.List;

class MockServiceWithFix3 implements ICountService {
    @Override
    public List<HelloM.VinCount> FindAll() {
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

        List<HelloM.VinCount> listVC = new ArrayList<>(3);
        listVC.add(vc1);
        listVC.add(vc2);
        listVC.add(vc3);

        return listVC;
    }
}
