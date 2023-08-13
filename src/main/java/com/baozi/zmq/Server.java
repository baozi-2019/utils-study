package com.baozi.zmq;

import org.zeromq.SocketType;
import org.zeromq.ZContext;
import org.zeromq.ZMQ;

import java.util.Random;

public class Server {
    public static void main(String[] args)
    {
        try (ZContext context = new ZContext()) {
            ZMQ.Socket publisher = context.createSocket(SocketType.PUB); //publish类型
            publisher.connect("tcp://*:5555");

            Random random = new Random();
            while (true) {
                String update;
                //随机将update赋值为Time: 或Order: 开头的值
                if (random.nextInt(10)<=5)
                    update = "Time: "+System.currentTimeMillis();
                else
                    update = "Order: "+System.currentTimeMillis();
                publisher.send(update); //发送
                Thread.sleep(1000);
                System.out.println("SEND:["+update+"]");
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
