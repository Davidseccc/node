/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.uhk.secda1.node01.service;

import java.net.*;
import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 *
 * @author David
 */
public class SocketServer extends Thread {

    public static final int TIMEOUT_NEWER = 0;
    private final ServerSocket serverSocket;

    public SocketServer(int port, int timeout) throws IOException {
        serverSocket = new ServerSocket(port);
        serverSocket.setSoTimeout(timeout);
    }

    @Override
    public void run() {
        while (true) {
            try {
                System.out.println("Waiting for client on port "
                        + serverSocket.getLocalPort() + "...");
                Socket server = serverSocket.accept();
                System.out.println("Just connected to "
                        + server.getRemoteSocketAddress());
                DataInputStream in
                        = new DataInputStream(server.getInputStream());
                String messString = in.readUTF();
                System.out.println(messString);
                DataOutputStream out
                        = new DataOutputStream(server.getOutputStream());

                String message = resolveMessage(messString);
                
                out.writeUTF(message);
                System.out.println("SEND: " + message);
                
            } catch (SocketTimeoutException s) {
                System.out.println("Socket timed out!");
                break;
            } catch (IOException e) {
                break;
            }
        }
    }

    private String resolveMessage(String messString) throws IOException {
        String message = null;
        if (messString.equalsIgnoreCase("Hi, What's the Date Today?")) {
            DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
            Calendar cal = Calendar.getInstance();
            message = "Hi, it is " + dateFormat.format(cal.getTime()) + ".";

        }
        else{ message = "Nothing to do...";}

        //out.writeUTF("Thank you for connecting to "
        // + server.getLocalSocketAddress() + "\nGoodbye!");
        //server.close();
        return message;

    }

    public static void main(String[] args) {
        int port = Integer.parseInt(args[0]);
        int timeout = Integer.parseInt(args[1]);
        try {
            Thread t = new SocketServer(port, timeout);
            t.start();
        } catch (IOException e) {
        }
    }
}
