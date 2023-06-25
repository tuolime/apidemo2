package com.ss.apidemo.fragment.xmpp;

public interface NotifyConnectionListener {

    void notifyConnecting();

    void notifyConnected();

    void notifyAuthenticated();

    void notifyConnectionClosed();

    void notifyConnectionClosedOnError(String err);
}
