package com.ovo.unity.utils;

import com.ovo.common.utils.LogUtils;
import com.unity3d.player.UnityPlayer;

import java.io.DataInputStream;
import java.io.InputStream;
import java.io.PrintWriter;
import java.net.Socket;

public class TcpClient extends Thread {
    private static TcpClient client = null;
    private String TAG = "TcpClient";
    private String serverIP = "127.0.0.1";
    private int serverPort = 50001;
    private PrintWriter pw;
    private InputStream is;
    private DataInputStream dis;
    public boolean isRun = false;
    private Socket socket = null;
    byte buff[]  = new byte[1024];
//    byte buff[]  = new byte[4096*100*2];
    private String rcvMsg;
    private int rcvLen;

    public static TcpClient getIntance(){
        if (client == null){
            synchronized (TcpClient.class){
                if (client == null){
                    client = new TcpClient();
                }
            }
        }
        return client;
    }

    public void startTcp(){
        isRun = true;
        start();
    }

    public void exit(){
        isRun = false;
        try {
            if (client != null && !client.isInterrupted()){
                client.interrupt();
                client = null;
                LogUtils.log("TCPClient", "关闭TCP客户端");
            }
            if (pw != null){
                pw.close();
                pw = null;
            }
            if (is != null){
                is.close();
                is = null;
            }
            if (dis != null){
                dis.close();
                dis = null;
            }
            if (socket != null){
                socket.close();
                socket = null;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void send(String msg){
        try {
            if (socket != null && socket.isConnected() && !socket.isClosed()){
                if (pw != null){
                    pw.println(msg);
                    pw.flush();
//                    LogWSUtils.log("TCPClient-1", "发送成功");
                }else {
                    LogUtils.log("TCPClient-1", "发送失败");
                    connet();
                }
            }else {
                LogUtils.log("TCPClient-2", "发送失败");
                connet();
            }
        }catch (Exception e){
            LogUtils.log("TCPClient-3", "发送失败" + e.getMessage());
            connet();
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        try {
            LogUtils.log("TCPClient", "正在开启TCP客户端");
//            serverIP = APPConfig.getHostIP();
            try {
                if (!isRun)return;
                socket = new Socket(serverIP,serverPort);
            }catch (Exception e){
                if (!isRun)return;
                connet();
                e.printStackTrace();
            }
            connet();
            if (socket != null && socket.isConnected()){
                LogUtils.log("TCPClient", "已经开启TCP客户端");
                pw = new PrintWriter(socket.getOutputStream(),true);
                is = socket.getInputStream();
                dis = new DataInputStream(is);

                String start = "{\"commandData\":{\"control\":\"play\"}}";
                send(start);
                while (isRun){
                    try {
                        if (is != null && (rcvLen = is.read(buff)) != -1){
                            rcvMsg = new String(buff,0,rcvLen,"utf-8");
                            LogUtils.log(TAG, "TCPClient: 收到消息:"+ rcvMsg);
                        }
                        Thread.sleep(10);
                    } catch (Exception e) {
                        LogUtils.log("TCPClient", "接收失败" + e.getMessage());
//                        connet();
                        e.printStackTrace();
                    }
                }
                if (pw != null){
                    pw.close();
                    pw = null;
                }
                if (is != null){
                    is.close();
                    is = null;
                }
                if (dis != null){
                    dis.close();
                    dis = null;
                }
                if (socket != null){
                    socket.close();
                    socket = null;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**连接tcp服务器*/
    private void connet(){
        try {
            if (!isRun)return;
            if (!TcpClient.getIntance().isAlive()
                    || TcpClient.getIntance().isInterrupted()) {
                TcpClient.getIntance().exit();
                TcpClient.getIntance().startTcp();
                LogUtils.log("MyService", "onStartCommand--TcpClient");
            }
            if (socket == null || !socket.isConnected()){
                Thread.sleep(2000);
                if (!isRun)return;
//                LogWSUtils.log("TcpClient", "正在连接");
                socket = new Socket(serverIP,serverPort);
                connet();
            }else if (socket != null && socket.isConnected()){
                if (isFirst){
                    isFirst = false;
                    LogUtils.log("TcpClient", "连接成功");
                    UnityPlayer.UnitySendMessage("Canvas", "ShowFace","1");//0猫脸  1猪脸
//                    U3dUtils.getInstance().startData();
                    U3dUtils.getInstance().startPYData();
//                    String content = "白日依山尽，黄河入海流。欲穷千里目，更上一层楼。";
//                    U3dUtils.getInstance().playTTS(content);
//                    U3dUtils.getInstance().playU3d(content);
                }
            }
        }catch (Exception e){
            if (!isRun)return;
            connet();
            e.printStackTrace();
        }
    }

    private boolean isFirst = true;
}
