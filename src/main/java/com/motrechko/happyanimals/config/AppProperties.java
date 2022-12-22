package com.motrechko.happyanimals.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@ConfigurationProperties(prefix = "app")
@Component
public class AppProperties {
    private final Cors cors = new Cors();

    public static class Cors {
        private Long maxConnectionTimeSec;

        public Long getMaxConnectionTimeSec() {
            return maxConnectionTimeSec;
        }

        public void setMaxConnectionTimeSec(Long maxConnectionTimeSec) {
            this.maxConnectionTimeSec = maxConnectionTimeSec;
        }
    }

    public Cors getCors() {
        return cors;
    }
}
