package com.lumaserv.netflow.flowset;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@AllArgsConstructor
@Getter
@FieldDefaults(makeFinal = true)
public class TemplateField {
    FlowField type;
    int length;
}
