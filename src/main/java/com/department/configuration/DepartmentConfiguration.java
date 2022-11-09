package com.department.configuration;

import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Configuration;

/**
 * @author Vladimir F. 20.09.2022 22:32
 */

@Configuration
@EnableFeignClients(basePackages = "com.department.proxy")
public class DepartmentConfiguration {
}
