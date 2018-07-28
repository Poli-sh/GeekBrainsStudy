package server;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Server {
    public static void main(String[] args) {
        ServerSocket server = null;
        Socket socket = null;
        try {
            server = new ServerSocket(8189);
            System.out.println("Сервер запущен!");

            socket = server.accept();
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

            System.out.println("Клиент подключился");

            DataOutputStream out = new DataOutputStream(socket.getOutputStream());
            DataInputStream in = new DataInputStream(socket.getInputStream());

            while (!socket.isClosed()) {
                String msg = in.readUTF();
                System.out.println("Client: " + msg);

                if (msg.equals("/end")) {
                    System.out.println("Client kill connections");
                    out.writeUTF("Good Bye");
                    out.flush();
                    Thread.sleep(3000);
                    break;
                }
                String str = br.readLine();
                out.writeUTF(str);
                out.flush();

            }


} catch(IOException e){
        e.printStackTrace();
        }catch(InterruptedException e){
        e.printStackTrace();
        }finally{
        try{
        socket.close();
        }catch(IOException e){
        e.printStackTrace();
        }
        try{
        server.close();
        }catch(IOException e){
        e.printStackTrace();
        }
        }
        }
        }
