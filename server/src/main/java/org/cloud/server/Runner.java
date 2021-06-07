package org.cloud.server;

import org.cloud.server.conf.Settings;

public final class Runner {
    public static void main(String[] args) {
        var app = new Application(Settings.getSettings());
        app.start();
    }
}
