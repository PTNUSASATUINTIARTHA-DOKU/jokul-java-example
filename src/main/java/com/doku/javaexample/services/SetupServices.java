package com.doku.javaexample.services;

import com.doku.sdk.pojo.ServerLocation;
import org.springframework.stereotype.Service;

@Service
public class SetupServices {
    public String getServerUrl(String serverEnv) {
        String serverLocation = null;
        if ("sandbox".equals(serverEnv)) {
            serverLocation = ServerLocation.SANDBOX.getUrl();
        } else if ("production".equals(serverEnv)) {
            serverLocation = ServerLocation.PROD.getUrl();
        }
        return serverLocation;
    }
}
