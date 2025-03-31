package me.micartey.esc;

import com.pi4j.Pi4J;
import com.pi4j.context.Context;
import com.pi4j.io.pwm.Pwm;
import com.pi4j.io.pwm.PwmType;

public class Main {


    public static void main(String[] args) throws Exception {
        Context pi4J = Pi4J.newAutoContext();

        System.out.println(pi4J.boardInfo().getBoardModel().getLabel());

        int channel = 0;

        Pwm pwm = pi4J.create(
                Pwm.newConfigBuilder(pi4J)
                        .id("Channel" + channel)
                        .name("ESC1")
                        .address(channel)
                        .pwmType(PwmType.HARDWARE)
                        .provider("linuxfs-pwm")
                        .initial(0)
                        .shutdown(0)
                        .build()
        );

        pwm.on(50, 2000);
        Thread.sleep(2000);
        pwm.on(50, 1000);
        Thread.sleep(2000);
        pwm.on(50, 1150);

        Thread.sleep(20000);

        pi4J.shutdown();
    }

}
