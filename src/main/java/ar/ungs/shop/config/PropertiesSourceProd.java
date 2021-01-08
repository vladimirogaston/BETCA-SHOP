package ar.ungs.shop.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource({"classpath:application-prod.properties"})
@Profile(value = "prod")
public class PropertiesSourceProd {
}
