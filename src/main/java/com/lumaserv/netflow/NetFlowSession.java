package com.lumaserv.netflow;

import lombok.experimental.FieldDefaults;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

@FieldDefaults(makeFinal = true)
public class NetFlowSession implements Consumer<NetFlowPacket> {

    Map<Integer, NetFlowSource> sources = new HashMap<>();
    Consumer<NetFlowSource> consumer;

    public NetFlowSession(Consumer<NetFlowSource> consumer) {
        this.consumer = consumer;
    }

    public void accept(NetFlowPacket packet) {
        NetFlowSource source = sources.get(packet.getSourceId());
        if(source == null) {
            source = new NetFlowSource(packet.getSourceId());
            sources.put(source.getId(), source);
            consumer.accept(source);
        }
        source.accept(packet);
    }

}
