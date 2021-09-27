package Ankawi;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

// Author: Martin Dawood

public class Main {

    public static void main(String[] args) throws InterruptedException {
        String[] resources = {
                "https://docs.oracle.com/javase/7/docs/api/java/net/URL.html",
                "https://www.apple.com/",
                "https://microsoft.com",
                "https://www.blogger.com",
                "https://mozilla.org",
                "https://adobe.com",
                "https://whatsapp.com",
                "http://verisimilitudes.net/",
                "https://w3.org",
                "https://www.slideshare.net/"
        };

        Thread[] threads = new Thread[resources.length];

        long start = System.currentTimeMillis();
        for(int i = 0; i < resources.length; i++){
            int finalI = i;
            Thread t = new Thread(() -> {
                System.out.println("\n\n\n\n\n-----------" + Thread.currentThread().getId());
                try{
                    URL url = new URL(resources[finalI]);
                    InputStreamReader isr = new InputStreamReader(url.openStream());
                    BufferedReader br = new BufferedReader(isr);
                    StringBuilder sb = new StringBuilder();
                    String line = "";
                    while((line = br.readLine()) != null){
                        sb.append(line);
                        sb.append(System.lineSeparator());
                    }
                    System.out.println(sb);
                } catch (IOException ex) {
                    System.out.println(ex.getMessage());
                }
            });

            t.start();
            threads[i] = t;
        }

        for (Thread thread : threads) {
            thread.join();
        }

        long end = System.currentTimeMillis();
        System.out.println("Time taken: " + (end-start));
    }
}
