package be.intecbrussel.logmachine;

import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Formatter;
import java.util.Random;

public class App {

    final static String[] urls = {"/api","/api/bart","/api/foo","/api/check","/api/new","/api/home","/api/accounts","/login"};

    public static void main(String[] args) {
        createLog("log10M",10_000_000);
    }
    public static void createLog(String name, long numberOfEntries){

    try(FileOutputStream fileOutputStream = new FileOutputStream(name);
            PrintWriter writer = new PrintWriter(fileOutputStream)
        ){
            for (long l = 0;l<numberOfEntries;l++){
                writer.println(generateRandomLogLine());


            }

        }
        catch (Exception ex){
            ex.printStackTrace();
        }



    }

    private static String generateRandomLogLine() {
        Random random = new Random();
        LocalDateTime time = LocalDateTime.now();
        String ip = "192.168.0." + random.nextInt(256);
        String url = urls[random.nextInt(urls.length)];
        String responseCode = "200";
        String responseSize = ""+random.nextInt(100000);

        return time.format(DateTimeFormatter.ofPattern("YYYY-MM-DD'T'HH:MM:SS")) + " "+ ip + " " + url + " " +responseCode + " " + responseSize;
    }
}
