package eblo.example.rest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class TestUrlConnectionThread extends Thread {

    public void run(){
        for(int i=0;i<10;i++) {
            try {
                test();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        
    }
    
    public void test() throws IOException{
        URL test = new URL("http://localhost:8083/delegate/demo");
        URLConnection yc = test.openConnection();

        try(BufferedReader in = new BufferedReader(new InputStreamReader(yc.getInputStream()));) {
            String inputLine;
            while ((inputLine = in.readLine()) != null) 
                System.out.println(inputLine);
        }catch(Exception e) {
            e.printStackTrace();
        }
        
    }
}
