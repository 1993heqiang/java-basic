package org.rookie.test.nio_test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.util.concurrent.CountDownLatch;


public class EchoClient {

    private SocketChannel socketChannel;
    private int port = 8000;

    public EchoClient() throws Exception {
        socketChannel = SocketChannel.open();
        InetAddress inetAddress = InetAddress.getLocalHost();
        InetSocketAddress inetSocketAddress = new InetSocketAddress(inetAddress, port);
        socketChannel.connect(inetSocketAddress);
        System.out.println("准备连接服务器");
    }

    public static void main(String[] args) throws Exception {
        new EchoClient().talk();
    }

    public void talk() {
        try {
            CountDownLatch latch = new CountDownLatch(1);
            new Thread(()->{
                try(BufferedReader localReader = new BufferedReader(new InputStreamReader(System.in))) {
                    String msg;
                    while ((msg = localReader.readLine()) != null) {
                        System.out.println(msg);
                        socketChannel.write(ByteBuffer.wrap(msg.getBytes()));
                        if ("bye".equals(msg)) {
                            latch.countDown();
                            break;
                        }
                    }
                }catch (IOException e) {
                    e.printStackTrace();
                }
            }).start();
            new Thread(()->{
                try {
                    ByteBuffer readBuffer = ByteBuffer.allocate(1024);
                    while ((socketChannel.read(readBuffer))!=-1){
                        String msg = new String(readBuffer.array()).trim();
                        System.out.println(msg);
                        readBuffer.clear();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }).start();
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally{
            SocketUtils.close(socketChannel);
        }

    }
}
