package ua.mike.pub.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * Created by mike on 08.06.2022 17:38
 */
@Configuration
@EnableScheduling
@EnableAsync
public class ScheduledConfig {
}
