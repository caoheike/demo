
package com.bigyoung.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.ResponseBody;

//入口  公共类
@SpringBootApplication
@ResponseBody
@ComponentScan(basePackages={"com.bigyoung"})
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }
}
