package com.test.ribbon.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Random;

@Service
public class GetURLService {


    @Autowired
    RestTemplate restTemplate;

    @Autowired
    private DiscoveryClient discoveryClient;


    /**
     * 引入DiscoveryClient 解决restTemplate 无法找到SERVICE映射地址服务
     * 手动简单实现负载均衡
     * @param str
     * @return
     */
    public String getShortenURL(String str){
        String url = "http://SERVICE/getShortenURL";
//        String url = "http://localhost:9100/getShortenURL";
        HttpHeaders headers = new HttpHeaders();
        HttpMethod method = HttpMethod.POST;
        // 以表单的方式提交
        headers.setContentType(MediaType.APPLICATION_JSON);
        //将请求头部和参数合成一个请求
        HttpEntity requestEntity = new HttpEntity(str, headers);
        Object ret = null;
//        List<ServiceInstance> instances = discoveryClient.getInstances("SERVICE");
//        System.out.println("+++++++++++++++++++++++++++SHOW SERVICE URL++++++++++++++++++++++++++++++++++++++++++");
//        if(instances.size()<1){
//            System.out.println("+++++++++++++++++++++++++++NO SERVICE URL++++++++++++++++++++++++++++++++++++++++++");
//        }
//        for (ServiceInstance serviceInstance : instances) {
//            System.out.println("SERVICE'url inlcuded : "+serviceInstance.getUri());
//        }
//
//        List<ServiceInstance> instances1 = discoveryClient.getInstances("GATEWAY-MASTER");
//        for (ServiceInstance serviceInstance : instances1) {
//            System.out.println("GATEWAY-MASTER'url inlcuded : "+serviceInstance.getUri());
//        }
//
//
//        if(instances.size()>1){
//            if((new Random().nextInt(10) + 1)%2==0){
//                url = instances.get(0).getUri()+"/getShortenURL";
//            }else{
//                url = instances.get(1).getUri()+"/getShortenURL";
//            }
//        }else{
//            url = instances.get(0).getUri()+"/getShortenURL";
//        }

        try{
            ret = restTemplate.postForObject(url, requestEntity,String.class);
        }catch(Exception e){
            System.out.println(e);
        }finally{
            return ret.toString();
        }

    }

}
