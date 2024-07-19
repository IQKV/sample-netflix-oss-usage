package dev.knowhowto.acmecloudnetflix.starter.web.rest;

import dev.knowhowto.acmecloudnetflix.starter.web.rest.WebConfig;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import(value = {
    WebConfig.class
})
@EnableConfigurationProperties(ApplicationBuildInfoProperties.class)
public class RestfulAutoConfiguration {
}
