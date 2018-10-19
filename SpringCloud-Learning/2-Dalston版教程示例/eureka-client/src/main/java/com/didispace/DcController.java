package com.didispace;

import com.didispace.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 翟永超
 * @create 2017/4/15.
 * @blog http://blog.didispace.com
 */
@RestController
public class DcController {

    @Autowired
    DiscoveryClient discoveryClient;

    @GetMapping("/dc")
    public String dc() {
        String services = "Services: " + discoveryClient.getServices();
        String description = discoveryClient.description();
        System.out.println(services);
        return services + "<br>" + "Description:" + description;
    }

    @GetMapping("/user")
    public User selectOne() {
        User user = new User();
        user.setId("001");
        user.setName("tom");
        return user;
    }

    @GetMapping("/user/{id}")
    public User selectOne(@PathVariable("id") String id) {
        User user = new User();
        user.setId(id);
        user.setName("tom");
        return user;
    }

}
