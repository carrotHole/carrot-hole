package com.carrothole.carrot;

import com.mybatisflex.core.FlexGlobalConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CarrotApplication {

    public static void main(String[] args) {
        mybatisFlexConfig();
        SpringApplication.run(CarrotApplication.class, args);
    }

    public static void mybatisFlexConfig(){
        FlexGlobalConfig.getDefaultConfig().setTenantColumn("tenant_id");
    }
}
