package app.backend.library.util;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Setter
@Getter
@Configuration
@ConfigurationProperties(prefix = "demo")
public class DemoDataProperties {
    private String adminPassword;
    private String userPassword;

}
