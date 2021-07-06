package com.lumaserv.netflow;

import com.lumaserv.netflow.flowset.FlowData;
import com.lumaserv.netflow.flowset.DataTemplate;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

import java.util.ArrayList;
import java.util.List;

@Getter
@FieldDefaults(makeFinal = true)
public class NetFlowPacket {

    int version;
    long sysUptime;
    long timestamp;
    int flowSequence;
    int sourceId;
    List<DataTemplate> dataTemplates = new ArrayList<>();
    List<FlowData> data = new ArrayList<>();

    public NetFlowPacket(byte[] packet) {
        version = ((packet[0] & 0xFF) << 8) | (packet[1] & 0xFF);
        int count = ((packet[2] & 0xFF) << 8) | (packet[3] & 0xFF);
        sysUptime = ((packet[4] & 0xFFL) << 24) |
                ((packet[5] & 0xFFL) << 16) |
                ((packet[6] & 0xFFL) << 8) |
                (packet[7] & 0xFFL);
        timestamp = ((packet[8] & 0xFFL) << 24) |
                ((packet[9] & 0xFFL) << 16) |
                ((packet[10] & 0xFFL) << 8) |
                (packet[11] & 0xFFL);
        flowSequence = ((packet[12] & 0xFF) << 24) |
                ((packet[13] & 0xFF) << 16) |
                ((packet[14] & 0xFF) << 8) |
                (packet[15] & 0xFF);
        sourceId = ((packet[16] & 0xFF) << 24) |
                ((packet[17] & 0xFF) << 16) |
                ((packet[18] & 0xFF) << 8) |
                (packet[19] & 0xFF);
        int offset = 20;
        for(int i=0; i<count; i++) {
            int id = ((packet[offset] & 0xFF) << 8) | (packet[offset+1] & 0xFF);
            int length = ((packet[offset+2] & 0xFF) << 8) | (packet[offset+3] & 0xFF);
            byte[] data = new byte[length];
            System.arraycopy(packet, offset+4, data, 0, length);
            switch (id) {
                case 0:
                    dataTemplates.add(new DataTemplate(data));
                    break;
                default:
                    this.data.add(new FlowData(id, data));
                    break;
            }
        }
    }

}
