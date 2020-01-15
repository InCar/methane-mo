package com.incarcloud.methane.monitor.controller;

import com.incarcloud.methane.monitor.model.VinCount;
import com.incarcloud.methane.monitor.service.ICountService;
import com.incarcloud.std.HelloM;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("hello")
public class HelloController{
    private final ICountService _countSrv;

    @Autowired
    public HelloController(ICountService countSrv){
        _countSrv = countSrv;
    }

    @GetMapping("count")
    public List<VinCount> getMessageCont(@RequestParam(required = false) String vin) {
        // 调用远程服务
        List<HelloM.VinCount> listResult = _countSrv.FindAll();

        // 构造返回值
        List<VinCount> listCount = new ArrayList<>();
        for(HelloM.VinCount entry : listResult){
            VinCount vc = new VinCount();
            vc.setVin(entry.getVin());
            vc.setCount(entry.getCount());
            listCount.add(vc);
        }

        return listCount;
    }
}
