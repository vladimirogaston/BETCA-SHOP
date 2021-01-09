package ar.ungs.shop.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource({"classpath:application-qa.properties"})
@Profile("qa")
public class PropertiesSourceQa {
}
