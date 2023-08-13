package com.baozi.zmq;

import org.zeromq.SocketType;
import org.zeromq.ZContext;
import org.zeromq.ZMQ;

public class Client {
    public static void main(String[] args){
        try(ZContext context= new ZContext()) {
            ZMQ.Socket subscriber = context.createSocket(SocketType.SUB); //subscribe类型00
            subscriber.connect("tcp://*:5556");

            subscriber.subscribe("Order:".getBytes()); //只订阅Time: 开头的信息

            for (int i = 0; i < 1000; i++) {
                System.out.println("111111111111---- " + subscriber.getReceiveTimeOut());
                System.out.println(subscriber.recvStr()); //recvStr直接返回String，内部调用了recv，将byte数组转化为String
            }
        }

    }
}
