package com.fabri.srvmessagebroker.infra.consts;

public final class ExchangeConstants {

    public static final String DIRECT = "amq.direct";
    public  static final String FAN_OUT = "amq.fanout";

    ExchangeConstants() {
        throw new IllegalStateException("Utility class");
    }
}
