package pem.demo.util;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class PythonExecution {
    public static void convertRawToKML(String userName) throws IOException, InterruptedException {
//        python main.py -t < ./list/list_psy
        String command = "C:\\Users\\ChangJun.Choi\\Anaconda3\\python.exe";
        String arg1 = "C:\\Users\\ChangJun.Choi\\Desktop\\pemDB\\clusterPython\\main.py";
        String arg2 = "-t";

        ProcessBuilder processBuilder = new ProcessBuilder(command, arg1, arg2);
        Process process = processBuilder.start();


        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(process.getOutputStream()));

        String listFilePath = "C:\\Users\\ChangJun.Choi\\Desktop\\pemDB\\clusterPython\\list\\list_" + userName;
        bw.write(listFilePath);
        bw.flush();
        bw.close();

        int exitVal = process.waitFor();
        BufferedReader br = new BufferedReader(new InputStreamReader(process.getInputStream(), StandardCharsets.UTF_8));
        String line;
        while ((line = br.readLine()) != null) {
            System.out.println(line);
        }
        if (exitVal != 0) {
            System.out.println("비정상 종료!?");
        }
    }
}
