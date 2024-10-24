package models;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.BufferedReader;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class CurrencyLogFile {

    public static void writeToFile(String str) throws IOException {

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();

        try{
            FileWriter fw = new FileWriter("log.txt", true);
            fw.write("[" + dtf.format(now) + "] " + str + "\n");
            fw.close();
        } catch (IOException e) {
            System.out.println("Error guardando el archivo");
            e.printStackTrace();
        }
    }

    public static void ReadLogFile() throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("log.txt"));
        System.out.println("Conversiones realizadas:");

        try {
            String line = br.readLine();
            while (line != null) {
                System.out.println(line);
                line = br.readLine();
            }
        } finally {
            System.out.println("=====================================");
            br.close();
        }
    }
}
