package io.github.carrothole.carrot.property;

import lombok.Data;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import static io.github.carrothole.carrot.config.CarrotConstant.DEFAULT_TOKEN_KEY;

/**
 * ClassName: WebProperty
 * Description:
 * date: 2024-9-1 17:30
 *
 * @author moon
 * @since 0.0.1
 */
@Setter
@Configuration
@ConfigurationProperties(prefix = "carrot.web")
public class WebProperty {

    public Integer listSize = 4000;

    public String tokenKey = DEFAULT_TOKEN_KEY;
}
