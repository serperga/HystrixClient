package com.sanuk.histrix.histryxPOCClient.service.impl;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.sanuk.histrix.histryxPOCClient.service.HystrixService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import com.netflix.hystrix.*;

import java.net.URI;

@Service
public class HystrixServiceImpl implements HystrixService{

    @Autowired
    private RestTemplate restTemplate;

    @HystrixCommand(fallbackMethod = "reliable")
    public String getRead(){
        URI uri = URI.create("http://localhost:8090/testcall");

        return this.restTemplate.getForObject(uri, String.class);
    }

    @HystrixCommand(fallbackMethod = "reliableTimeOut", commandProperties = {@HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "1")})
    public String getReadTimeOut(){
        URI uri = URI.create("http://localhost:8090/testcalltimeout");

        return this.restTemplate.getForObject(uri, String.class);
    }

    public String reliable() {
        return "Fallback method executed";
    }

    public String reliableTimeOut() {
        return "Fallback method with TimeOut";
    }
}
