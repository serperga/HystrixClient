package com.sanuk.histrix.histryxPOCClient.controller;


import com.sanuk.histrix.histryxPOCClient.service.HystrixService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HystrixClient {

    @Autowired
    private HystrixService hystrixService;

    @RequestMapping("/read")
    public String readingList() {
        return hystrixService.getRead();
    }

    @RequestMapping("/readTimeOut")
    public String readingListTiemOut() {
        return hystrixService.getReadTimeOut();
    }
}

