package bamboo.core;

import java.nio.file.Path;
import java.nio.file.Paths;

public class Config {
    private String getEnv(String name, String defaultValue) {
        String value = System.getenv(name);
        if (value != null) {
            return value;
        } else {
            return defaultValue;
        }
    }

    public String getDbUser() {
        return getEnv("BAMBOO_DB_USER", "bamboo");
    }

    public String getDbPassword() {
        return getEnv("BAMBOO_DB_PASSWORD", "bamboo");
    }

    public String getDbUrl() {
        return getEnv("BAMBOO_DB_URL", "jdbc:h2:mem:bamboo");
    }

    public Path getHeritrixJobs() {
        return Paths.get(getEnv("HERITRIX_JOBS", "/heritrix/jobs"));
    }
}