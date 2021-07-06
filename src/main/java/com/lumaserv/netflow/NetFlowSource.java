package com.lumaserv.netflow;

import com.lumaserv.netflow.flowset.DataTemplate;
import com.lumaserv.netflow.flowset.FlowField;
import com.lumaserv.netflow.flowset.FlowValue;
import lombok.Getter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

public class NetFlowSource implements Consumer<NetFlowPacket> {

    @Getter
    int id;
    Map<Integer, DataTemplate> templates = new HashMap<>();
    List<BiConsumer<Integer, Map<FlowField, FlowValue>>> listener = new ArrayList<>();

    public NetFlowSource(int id) {
        this.id = id;
    }

    public NetFlowSource listen(BiConsumer<Integer, Map<FlowField, FlowValue>> listener) {
        this.listener.add(listener);
        return this;
    }

    public void accept(NetFlowPacket packet) {
        packet.getDataTemplates().forEach(t -> templates.put(t.getTemplateId(), t));
        packet.getData().forEach(d -> {
            DataTemplate template = templates.get(d.getId());
            if(template != null) {
                Map<FlowField, FlowValue> values = d.parse(template);
                listener.forEach(l -> l.accept(d.getId(), values));
            }
        });
    }

}
