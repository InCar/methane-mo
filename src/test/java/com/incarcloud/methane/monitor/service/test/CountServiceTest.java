package com.incarcloud.methane.monitor.service.test;

import com.incarcloud.methane.monitor.serviceImpl.CountService;
import com.incarcloud.std.HelloM;
import io.grpc.Server;
import io.grpc.ServerBuilder;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.List;

public class CountServiceTest {
    private static Logger s_logger = LoggerFactory.getLogger(CountServiceTest.class);

    // 模拟的服务
    private static Server _mockServer = null;

    // 启动模拟的GRPC服务
    @BeforeClass
    public static void begin(){
        _mockServer = ServerBuilder.forPort(9999)
                .addService(new MockCountServiceV1WithFix3())
                .build();

        try {
            _mockServer.start();
        }catch (IOException ignore){
        }
    }

    // 关闭GRPC服务
    @AfterClass
    public static void end(){
        _mockServer.shutdown();
    }

    @Test
    public void TestWithFix3(){
        CountService target = new CountService("127.0.0.1", 9999);
        target.Initialize();

        List<HelloM.VinCount> listResult = target.FindAll();
        Assert.assertEquals(3, listResult.size());
        for(HelloM.VinCount vc : listResult){
            s_logger.info(" Fetched {} : {}", vc.getVin(), vc.getCount());
        }
    }
}

