package org.cloud.server.conf;

import lombok.extern.slf4j.Slf4j;
import org.cloud.server.core.SessionPool;

import java.io.FileInputStream;
import java.util.Properties;

@Slf4j
public class Settings {
    private static volatile Properties _settings;

    public static Properties getSettings() {
        var result = _settings;

        if (result != null) {
            return result;
        }

        synchronized (SessionPool.class) {
            if (_settings == null) {
                try (var inputProperties = new FileInputStream("cloudf.properties")) {
                    _settings = new Properties();
                    _settings.load(inputProperties);
                } catch (Exception e) {
                    log.error("Error create ConnectionPool: ", e);
                }
            }
            return _settings;
        }
    }
}
