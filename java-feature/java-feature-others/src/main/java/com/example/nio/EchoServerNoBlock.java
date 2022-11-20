package com.example.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class EchoServerNoBlock {

    private int port = 8000;
    private ServerSocketChannel serverSocketChannel;
    Selector selector;
    BlockingQueue<String> msgQueue = new ArrayBlockingQueue<>(100);

    public EchoServerNoBlock() throws Exception {
        selector = Selector.open();
        serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.socket().setReuseAddress(true);
        serverSocketChannel.configureBlocking(false);// 设置通信为非阻塞模式，没有这一配置，则无法实现非阻塞
        serverSocketChannel.socket().bind(new InetSocketAddress(port));
        System.out.println("等待连接...");
    }

    public void service() throws Exception {
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
        while (selector.select() > 0) {
            String broadcastMsg = "";
            if(!msgQueue.isEmpty()){
                broadcastMsg = msgQueue.take();
            }
            Set<SelectionKey> readyKeys = selector.selectedKeys();
            Iterator<SelectionKey> iterator = readyKeys.iterator();
            SelectionKey key = null;
            while (iterator.hasNext()) {
                try {
                    key = iterator.next();
                    iterator.remove();
                    if (key.isAcceptable()) {
                        System.out.println("accept");
                        ServerSocketChannel ssc = (ServerSocketChannel) key
                                .channel();
                        SocketChannel sc = ssc.accept();
                        System.out.println("接收到链接"
                                + sc.socket().getInetAddress() + ":"
                                + sc.socket().getPort());
                        sc.configureBlocking(false);
                        ByteBuffer buffer = ByteBuffer.allocate(1024);
                        sc.register(selector, SelectionKey.OP_READ
                                | SelectionKey.OP_WRITE, buffer);
                    }
                    if (key.isReadable()) {
                        receive(key);
                    }
                    if(key.isWritable()){
                        if(!"".equals(broadcastMsg)){
                            send(key,broadcastMsg);
                        }

                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    if (key != null) {
                        key.cancel();
                        key.channel().close();
                    }
                }
            }
        }
    }

    /**
     * 发送消息处理方法
     *
     * @param key
     * @throws Exception
     * @author henushang
     */
    public void send(SelectionKey key,String broadcastMsg) throws Exception {
        SocketChannel sc = (SocketChannel) key.channel();
        sc.write(ByteBuffer.wrap((sc.getRemoteAddress()+":"+broadcastMsg).getBytes()));
//        Socket socket = sc.socket();
//        BufferedWriter br = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
//        br.write(sc.getRemoteAddress()+":"+broadcastMsg);
    }

    /**
     * 接受链接处理方法
     *
     * @param key
     * @throws IOException
     * @author henushang
     */
    public void receive(SelectionKey key) throws IOException {
        SocketChannel sc = (SocketChannel) key.channel();
        ByteBuffer readBuffer = ByteBuffer.allocate(1024);
        int i = sc.read(readBuffer);
        if (i != -1) {
            String msg = new String(readBuffer.array()).trim();
            msgQueue.add(msg);
            System.out.println(((SocketChannel) key.channel()).getRemoteAddress()+":" + msg);
            readBuffer.clear();
        }
    }

    static Charset charset = Charset.forName("UTF-8");

    /**
     * 解码
     *
     * @param byteBuffer
     * @return
     * @author henushang
     */
    public static String decode(ByteBuffer byteBuffer) {
        CharBuffer charBuffer = charset.decode(byteBuffer);
        return charBuffer.toString();
    }

    /**
     * 编码
     *
     * @param msg
     * @return
     * @author henushang
     */
    public static ByteBuffer encode(String msg) {
        return charset.encode(msg);
    }

    public static void main(String[] args) {
        try {
            EchoServerNoBlock server = new EchoServerNoBlock();
            server.service();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
