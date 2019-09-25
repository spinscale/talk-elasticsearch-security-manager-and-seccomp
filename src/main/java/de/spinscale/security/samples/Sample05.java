package de.spinscale.security.samples;

import com.google.common.io.CharStreams;

import java.io.InputStreamReader;

public class Sample05 {

    // execute arbitrary commands
    public static void main(String[] args) throws Exception {
        Process process = Runtime.getRuntime().exec("/bin/ls -l");
        process.waitFor();
        String output = CharStreams.toString(new InputStreamReader(process.getInputStream()));

        System.out.println(output);
    }
}
