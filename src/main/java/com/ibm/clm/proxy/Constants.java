package com.ibm.clm.proxy;

/**
 * @author bishoybasily
 * @since 2/15/20
 */
public interface Constants {

    interface Profiles {

        String
                TEST = "test",
                K8S = "k8s";

    }

    String
            EXCHANGE = "_proxy",
            QUEUE = "_proxy",
            CONSUME_ROUTING_KEY = "proxy.consume",
            PRODUCE_ROUTING_KEY = "proxy.produce";

}
