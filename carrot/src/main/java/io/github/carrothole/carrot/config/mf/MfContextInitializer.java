package io.github.carrothole.carrot.config.mf;

import com.mybatisflex.core.FlexGlobalConfig;
import com.mybatisflex.core.audit.AuditManager;
import com.mybatisflex.core.audit.ConsoleMessageCollector;
import com.mybatisflex.core.audit.MessageCollector;
import com.mybatisflex.core.keygen.KeyGeneratorFactory;
import com.mybatisflex.spring.boot.MyBatisFlexCustomizer;
import org.springframework.context.annotation.Configuration;

/**
 * Description: 项目启动前加载需要加载的mf配置
 * date: 2024-9-1 16:31
 *
 * @author moon
 * @since 0.0.1
 */
@Configuration
public class MfContextInitializer implements MyBatisFlexCustomizer {

    @Override
    public void customize(FlexGlobalConfig globalConfig) {
        System.out.println("mybatis flex config init");
        globalConfig.setTenantColumn("tenant_id");

        KeyGeneratorFactory.register(MfConstant.ID_GENERATOR, new MfUUIDGenerator());

        //开启审计功能
        AuditManager.setAuditEnable(true);

        //设置 SQL 审计收集器
        MessageCollector collector = new ConsoleMessageCollector();
        AuditManager.setMessageCollector(collector);
    }



}
