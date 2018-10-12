package com.lxd.monitor;

import com.lxd.monitor.Util.MailUtil;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;

@SpringBootApplication
@MapperScan("com.lxd.monitor.dao")
public class MonitorApplication extends SpringBootServletInitializer {

    @Value("${spring.mail.username}")

    public static void main(String[] args) {
        SpringApplication.run(MonitorApplication.class, args);
        System.out.println("running......");
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder){
        return builder.sources(this.getClass());
    }
}
