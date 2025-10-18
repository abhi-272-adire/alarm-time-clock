import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;
import java.time.LocalTime;
import java.util.Scanner;

public class alarmclock implements Runnable{
    private final LocalTime alarmtime;
    private final String filepath;
    private final Scanner sc;
    alarmclock(LocalTime alarmtime,String filepath,Scanner sc){
        this.alarmtime=alarmtime;
        this.filepath=filepath;
        this.sc=sc;
    }

    @Override
    public void run() {
        try {
            while (true) {
                LocalTime now = LocalTime.now();

                System.out.printf("\r%02d:%02d:%02d", now.getHour(), now.getMinute(), now.getSecond());
                Thread.sleep(1000);


                if (    now.getHour() == alarmtime.getHour() &&
                        now.getMinute() == alarmtime.getMinute() &&
                        now.getSecond() == alarmtime.getSecond()) {
                    playmusic(filepath);
                    break;
                }
            }
        } catch (InterruptedException e) {
            System.out.println("Thread interrupted");
        }
    }
    private void playmusic(String filepath){
        File audiofile=new File(filepath);
       try( AudioInputStream audiostream= AudioSystem.getAudioInputStream(audiofile)){
           Clip clip=AudioSystem.getClip();
           clip.open(audiostream);
           clip.start();
           System.out.println();
           System.out.println("press *enter* to stop music :");
            sc.nextLine();
           clip.stop();

          sc.close();

       } catch (RuntimeException e) {
           System.out.println("exception occurs");
       } catch (UnsupportedAudioFileException e) {
           System.out.println("doesnot supports the audio");
       } catch (IOException e) {
           System.out.println("input Exception");
       } catch (LineUnavailableException e) {
           System.out.println("file not found");
       }

    }

}
