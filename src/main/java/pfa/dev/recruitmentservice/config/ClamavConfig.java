package pfa.dev.recruitmentservice.config;


import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import xyz.capybara.clamav.ClamavClient;

@Configuration
@EnableConfigurationProperties(ClamavProperties.class)
public class ClamavConfig {

    @Bean
    public ClamavClient clamavClient(ClamavProperties props) {
        return new ClamavClient(props.getHost(), props.getPort());
    }
}