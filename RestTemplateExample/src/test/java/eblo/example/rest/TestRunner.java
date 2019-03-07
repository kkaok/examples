package eblo.example.rest;

public class TestRunner {

    public static void main(String[] args) 
    { 
        int n = 20; // Number of threads 
        for (int i=0; i<n; i++) 
        { 
            TestUrlConnectionThread thread = new TestUrlConnectionThread(); 
            thread.start(); 
        } 
    } 
}
