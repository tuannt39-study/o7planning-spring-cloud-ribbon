package vn.its.o7planningspringcloudribbon.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class RibbonController {
    @Autowired
    private DiscoveryClient discoveryClient;

    @Autowired
    private LoadBalancerClient loadBalancer;

    @GetMapping("/")
    public String showFirstService() {
        String serviceId = "LOCALHOSTVN".toLowerCase();
        List<ServiceInstance> instances = this.discoveryClient.getInstances(serviceId);
        if (instances == null || instances.isEmpty()) {
            return "No instances for service: " + serviceId;
        }
        String html = "<h2>Instances for Service Id: " + serviceId + "</h2>";
        for (ServiceInstance serviceInstance : instances) {
            html += "<h3>Instance :" + serviceInstance.getUri() + "</h3>";
        }
        html += "<h4>Call /hello of service: " + serviceId + "</h4>";
        try {
            ServiceInstance serviceInstance = this.loadBalancer.choose(serviceId);
            html += "===> Load Balancer choose: " + serviceInstance.getUri();
        } catch (IllegalStateException e) {
            html += "<br>loadBalancer.choose ERROR: " + e.getMessage();
            e.printStackTrace();
        } catch (Exception e) {
            html += "<br>Other ERROR: " + e.getMessage();
            e.printStackTrace();
        }
        return html;
    }
}
