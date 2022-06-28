package org.ujar.micro.oss.acmedepartments.userprofile.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.ujar.boot.starter.logbook.LogbookJsonBodyFilter;
import org.ujar.boot.starter.logbook.LogbookResponseOnStatus;

@Configuration
@LogbookResponseOnStatus
@LogbookJsonBodyFilter
@EnableTransactionManagement
public class ApplicationConfig {

}
