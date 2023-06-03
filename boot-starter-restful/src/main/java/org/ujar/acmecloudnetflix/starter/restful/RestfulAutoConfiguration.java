package org.ujar.acmecloudnetflix.starter.restful;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.ujar.acmecloudnetflix.starter.restful.web.WebConfig;

@Configuration
@Import(value = {
    WebConfig.class
})
@EnableConfigurationProperties(ApplicationBuildInfoProperties.class)
public class RestfulAutoConfiguration {
}
