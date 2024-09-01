package com.carrothole.carrot.property;

import lombok.Data;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * ClassName: CarrotProperty
 * Description:
 * date: 2024-9-1 17:28
 *
 * @author moon
 * @since JDK 1.8
 */
@Setter
@Configuration
@ConfigurationProperties(prefix = "carrot")
public class CarrotProperty {

    @Autowired
    public WebProperty web;

}
