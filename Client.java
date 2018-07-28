package client;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Client {

    public static void main(String[] args) {
        ServerSocket server = null;
        Socket socket = null;
        try {
            socket = new Socket("localhost", 8189);
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

            DataInputStream in = new DataInputStream(socket.getInputStream());
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());
            {
                System.out.println("Установлено соединение");
            }
            while (!socket.isOutputShutdown()) {
                if (br.ready()) {
                    Thread.sleep(1000);
                    String str = br.readLine();
                    out.writeUTF(str);
                    out.flush();

                    if (str.equals("/end")) {
                        System.out.println(("Client kill connections"));
                        Thread.sleep(1000);

                        break;
                    }
                    System.out.println("Client:" + str);
                    System.out.println("Server печатает....");
                    Thread.sleep(1000);

                    String ser = in.readUTF();
                    System.out.println("Server:" + ser);
                    Thread.sleep(1000);
                }
            }
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                server.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
