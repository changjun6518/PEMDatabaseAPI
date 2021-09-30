package pem.demo.util;

import org.junit.jupiter.api.Test;
import org.springframework.test.annotation.Rollback;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

import static org.junit.jupiter.api.Assertions.*;

class PythonExecutionTest {

    @Test
    @Rollback(value = false)
    public void executePy() throws IOException, InterruptedException {
        String command = "C:\\\\Users\\\\ChangJun.Choi\\\\Anaconda3\\\\python.exe";
        String arg1 = "C:\\\\Users\\\\ChangJun.Choi\\\\Desktop\\\\pemDB\\\\clusterPython\\\\main.py";
        String arg2 = "-a";
        String arg3 = "psy";
        String arg4 = "C:\\\\Users\\\\ChangJun.Choi\\\\Desktop\\\\pemDB\\\\clusterPython\\\\list\\\\list_psy";

//        ProcessBuilder processBuilder = new ProcessBuilder(command, arg1, arg2, arg3);
        ProcessBuilder processBuilder = new ProcessBuilder(command, arg1, arg2, arg3, arg4);
        Process process = processBuilder.start();

        BufferedReader br = new BufferedReader(new InputStreamReader(process.getInputStream(), StandardCharsets.UTF_8));
        String line;
        while ((line = br.readLine()) != null) {
            System.out.println(line);
        }


        if (process.waitFor() != 0) {
            System.out.println("비정상");
        }

    }

}