package com.carrothole.carrot;

import com.carrothole.carrot.config.mf.MfConstant;
import com.carrothole.carrot.config.mf.MfUUIDGenerator;
import com.mybatisflex.core.FlexGlobalConfig;
import com.mybatisflex.core.audit.AuditManager;
import com.mybatisflex.core.audit.ConsoleMessageCollector;
import com.mybatisflex.core.audit.MessageCollector;
import com.mybatisflex.core.keygen.KeyGeneratorFactory;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = "com.carrothole.carrot.mapper")
public class CarrotApplication {

    public static void main(String[] args) {
        mybatisFlexConfig();
        SpringApplication.run(CarrotApplication.class, args);
    }

    public static void mybatisFlexConfig(){
        FlexGlobalConfig.getDefaultConfig().setTenantColumn("tenant_id");
        KeyGeneratorFactory.register(MfConstant.ID_GENERATOR, new MfUUIDGenerator());

        //开启审计功能
        AuditManager.setAuditEnable(true);

        //设置 SQL 审计收集器
        MessageCollector collector = new ConsoleMessageCollector();
        AuditManager.setMessageCollector(collector);
    }
}
