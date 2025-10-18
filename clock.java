import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;


public class clock {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        LocalDateTime localTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        LocalTime alarmtime = null;
        String filepath="C:\\Users\\adire\\IdeaProjects\\program\\src\\On The Flip - The Grey Room _ Density & Time.wav";
        while (alarmtime == null) {
            try {
                System.out.print("enter the alarm time to set(in military time): ");
                String timeset = sc.nextLine();
                alarmtime = LocalTime.parse(timeset, formatter);
                System.out.println("the time setted to " + alarmtime);
            } catch (DateTimeException e) {
                System.out.println("invalid .must entered in (HH:MM:SS)");
            }
        }
        alarmclock alarmclock=new alarmclock(alarmtime,filepath,sc);
        Thread th=new Thread(alarmclock);
        th.start();

    }

   
    }

