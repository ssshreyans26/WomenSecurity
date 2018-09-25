package com.example.aftaab.codefury;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

public class Utility {

    public static String fetchFromUrl(URL url) {

        try {
            HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();
            connection.setConnectTimeout(5000);
            connection.connect();
            InputStream in = new BufferedInputStream(connection.getInputStream());
            BufferedReader read = new BufferedReader(new InputStreamReader(in));

            StringBuffer res = new StringBuffer();
            String line;

            while ((line = read.readLine()) != null) {
                res.append(line);
            }
            return res.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "";
    }

    public static String fetchFromUrlHttp(URL url) {

        try {
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setConnectTimeout(5000);
            connection.connect();
            InputStream in = new BufferedInputStream(connection.getInputStream());
            BufferedReader read = new BufferedReader(new InputStreamReader(in));

            StringBuffer res = new StringBuffer();
            String line;

            while ((line = read.readLine()) != null) {
                res.append(line);
            }
            return res.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "";
    }

    public static boolean isOnline() {
        try {
            int timeoutMs = 1500;
            Socket sock = new Socket();
            SocketAddress sockaddr = new InetSocketAddress("8.8.8.8", 53);

            sock.connect(sockaddr, timeoutMs);
            sock.close();

            return true;
        } catch (IOException e) {
            return false;
        }
    }
}