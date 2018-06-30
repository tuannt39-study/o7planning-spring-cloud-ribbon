package vn.its.o7planningspringcloudribbon;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import vn.its.o7planningspringcloudribbon.config.RibbonConfiguration;

@SpringBootApplication
@EnableEurekaClient
@RibbonClient(name = "ping-a-server", configuration = RibbonConfiguration.class)
public class O7planningSpringCloudRibbonApplication {

    public static void main(String[] args) {
        SpringApplication.run(O7planningSpringCloudRibbonApplication.class, args);
    }
}
