package org.cloud.server.conf;

import lombok.extern.slf4j.Slf4j;

import java.util.Properties;

@Slf4j
public class Settings {
    private static volatile Properties _settings;

    public static Properties getSettings() {
        var result = _settings;
        if (result != null) {
            return result;
        }

        synchronized (Settings.class) {
            if (_settings == null) {

                try (var inputProperties = Settings.class.getClassLoader().getResourceAsStream("cloudf.properties")) {
                    _settings = new Properties();
                    _settings.load(inputProperties);
                } catch (Exception e) {
                    log.error("Error create Settings: ", e);
                }
            }
            return _settings;
        }
    }
}
