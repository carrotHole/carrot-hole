package com.carrothole.carrot.property;

import lombok.Data;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * ClassName: WebProperty
 * Description:
 * date: 2024-9-1 17:30
 *
 * @author moon
 * @since JDK 1.8
 */
@Setter
@Configuration
@ConfigurationProperties(prefix = "carrot.web")
public class WebProperty {

    public Integer listSize = 4000;

}
