package com.didispace;

import com.didispace.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @author 翟永超
 * @create 2017/4/15.
 * @blog http://blog.didispace.com
 */
@RestController
public class DcController {

    @Autowired
    LoadBalancerClient loadBalancerClient;
    @Autowired
    RestTemplate restTemplate;

    @GetMapping("/consumer")
    public String dc() {
        ServiceInstance serviceInstance = loadBalancerClient.choose("eureka-client");
        String url = "http://" + serviceInstance.getHost() + ":" + serviceInstance.getPort() + "/dc";
        System.out.println(url);
        return restTemplate.getForObject(url, String.class);
    }

    @GetMapping("/consumer/user")
    public String user() {
        ServiceInstance choose = loadBalancerClient.choose("eureka-client");
        String url = "http://" + choose.getHost() + ":" + choose.getPort() + "/user";
        User forObject = restTemplate.getForObject(url, User.class);
        return forObject.toString();
    }

    @GetMapping("/getFotEntity")
    public String getForEntity() {
        ServiceInstance serviceInstance = loadBalancerClient.choose("eureka-client");
        String url = "http://" + serviceInstance.getHost() + ":" + serviceInstance.getPort() + "/user";
        Object responseEntity = restTemplate.getForEntity(url, Object.class);
        return responseEntity.toString();
    }

    @GetMapping("/getFotEntity/{id}")
    public String getForEntityWithId(@PathVariable("id") String id) {
        ServiceInstance serviceInstance = loadBalancerClient.choose("eureka-client");
        String url = "http://" + serviceInstance.getHost() + ":" + serviceInstance.getPort() + "/user/" + id;
        User responseEntity = restTemplate.getForObject(url, User.class);
        return responseEntity.toString();
    }

}
