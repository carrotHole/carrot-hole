package io.github.carrothole.carrot.config.mf;

import com.mybatisflex.core.FlexGlobalConfig;
import com.mybatisflex.core.audit.AuditManager;
import com.mybatisflex.core.audit.ConsoleMessageCollector;
import com.mybatisflex.core.audit.MessageCollector;
import com.mybatisflex.core.keygen.KeyGeneratorFactory;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * Description: 项目启动前加载需要加载的mf配置
 * date: 2024-9-1 16:31
 *
 * @author moon
 * @since 0.0.1
 */

public class MfContextInitializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {


    @Override
    public void initialize(ConfigurableApplicationContext applicationContext) {
        mybatisFlexConfig();
    }

    public static void mybatisFlexConfig(){
        System.out.println("mybatis config init");
        FlexGlobalConfig.getDefaultConfig().setTenantColumn("tenant_id");
        KeyGeneratorFactory.register(MfConstant.ID_GENERATOR, new MfUUIDGenerator());

        //开启审计功能
        AuditManager.setAuditEnable(true);

        //设置 SQL 审计收集器
        MessageCollector collector = new ConsoleMessageCollector();
        AuditManager.setMessageCollector(collector);
    }

}
