package com.fabri.srvpatienthistoryregistry.infra;

public interface Consumer {

    /**
     * Consumes a message of type String.
     *
     * @param message the message to consume
     */
    void consume(String message);

}
