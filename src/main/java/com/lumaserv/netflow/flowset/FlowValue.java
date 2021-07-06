package com.lumaserv.netflow.flowset;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class FlowValue {

    final byte[] data;

    public int length() {
        return data.length;
    }

    public byte[] asBytes() {
        return data;
    }

    public byte asByte() {
        return data[0];
    }

    public int asUShort() {
        return ((data[0] & 0xFF) << 8) |
                (data[1] & 0xFF);
    }

    public int asInt() {
        return ((data[0] & 0xFF) << 24) |
                ((data[1] & 0xFF) << 16) |
                ((data[2] & 0xFF) << 8) |
                (data[3] & 0xFF);
    }

    public long asUInt() {
        return ((data[0] & 0xFFL) << 24) |
                ((data[1] & 0xFFL) << 16) |
                ((data[2] & 0xFFL) << 8) |
                (data[3] & 0xFFL);
    }

    public long asLong() {
        return ((data[0] & 0xFFL) << 56) |
                ((data[1] & 0xFFL) << 48) |
                ((data[2] & 0xFFL) << 40) |
                ((data[3] & 0xFFL) << 32) |
                ((data[4] & 0xFFL) << 24) |
                ((data[5] & 0xFFL) << 16) |
                ((data[6] & 0xFFL) << 8) |
                (data[7] & 0xFFL);
    }

}
