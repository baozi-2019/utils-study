package com.baozi.zmq;

import org.zeromq.SocketType;
import org.zeromq.ZContext;
import org.zeromq.ZMQ;

/**
 * 作者：鲍庆洋
 * 时间：2023/8/11 10:14
 * 描述：
 */
public class Proxy {

    public static void main(String[] args) {
        try (
                ZContext ctx = new ZContext()
        ) {
//            while (!Thread.currentThread().isInterrupted()) {
                ZMQ.Socket subscriber = ctx.createSocket(SocketType.XSUB);
                subscriber.bind("tcp://*:7101");
                ZMQ.Socket publisher = ctx.createSocket(SocketType.XPUB);
                publisher.bind("tcp://*:7100");
//                ZMQ.Socket listener = ZThread.fork(ctx, new Listener());
                ZMQ.proxy(subscriber, publisher, null);

//            }
        }
    }
}
