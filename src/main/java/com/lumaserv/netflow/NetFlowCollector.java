package com.lumaserv.netflow;

import lombok.experimental.FieldDefaults;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.util.function.Consumer;

@FieldDefaults(makeFinal = true)
public class NetFlowCollector implements Runnable {

    DatagramSocket socket;
    Thread worker;
    Consumer<NetFlowPacket> processor;

    public NetFlowCollector(Consumer<NetFlowPacket> processor) throws SocketException {
        this(processor, 2055);
    }

    public NetFlowCollector(Consumer<NetFlowPacket> processor, int port) throws SocketException {
        this.processor = processor;
        this.socket = new DatagramSocket(port);
        this.worker = new Thread(this);
        this.worker.start();
    }

    public void run() {
        DatagramPacket packet = new DatagramPacket(new byte[4096], 4096);
        while (!worker.isInterrupted()) {
            try {
                socket.receive(packet);
                processor.accept(new NetFlowPacket(packet.getData()));
            } catch (IOException ignored) {}
        }
    }

    public void close() {
        this.worker.interrupt();
        this.socket.close();
    }

    public void join() {
        try {
            worker.join();
        } catch (InterruptedException ignored) {}
    }

}
