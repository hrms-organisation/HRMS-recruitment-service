package pfa.dev.recruitmentservice.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "clamav")
public class ClamavProperties {
    private String host;
    private int port; // aucune valeur par défaut ici

    public String getHost() { return host; }
    public void setHost(String host) { this.host = host; }

    public int getPort() { return port; }
    public void setPort(int port) { this.port = port; }
}