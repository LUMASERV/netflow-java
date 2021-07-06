package com.lumaserv.netflow.flowset;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

import java.util.HashMap;
import java.util.Map;

@AllArgsConstructor
@Getter
@FieldDefaults(makeFinal = true)
public class FlowData {

    int id;
    byte[] data;

    public Map<FlowField, FlowValue> parse(DataTemplate template) {
        Map<FlowField, FlowValue> values = new HashMap<>();
        int offset = 0;
        for(TemplateField field : template.getFields()) {
            byte[] value = new byte[field.getLength()];
            System.arraycopy(data, offset, value, 0, value.length);
            values.put(field.getType(), new FlowValue(value));
            offset += value.length;
        }
        return values;
    }

}
