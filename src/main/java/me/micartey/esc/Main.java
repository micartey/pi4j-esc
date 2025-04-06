package me.micartey.esc;

import com.pi4j.Pi4J;
import com.pi4j.context.Context;
import com.pi4j.io.pwm.Pwm;
import com.pi4j.io.pwm.PwmType;
import me.micartey.esc.input.ControllerInput;
import me.micartey.esc.input.XBoxController;

public class Main {

    private static final int PWM_FREQUENCY = 50;

    public static void main(String[] args) throws Exception {
        try (XBoxController xBoxController = new XBoxController()) {
            Context pi4J = Pi4J.newAutoContext();
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

            /*
             * Initialize ESC by setting it to max speed and after
             * a few seconds to min speed
             */
            setEscSpeed(pwm, 100);
            Thread.sleep(2000);
            setEscSpeed(pwm, 0);
            Thread.sleep(2000);


            /*
             * Set a default speed of 8
             */
            double speed = 8;
            while (xBoxController.getValue(ControllerInput.HOME) == 0) {
                double inputY = xBoxController.getValue(ControllerInput.LEFT_JOYSTICK_UP_DOWN) / -32767;

                if (Math.abs(inputY) < 0.1)
                    continue;

                speed = Math.max(0, speed + inputY);

                setEscSpeed(pwm, speed);
                System.out.println(speed);

                Thread.sleep(50);
            }

            pi4J.shutdown();
        }
    }

    /**
     * Set the speed of for a brushless motor connected to an ESC.
     * As the motor operates between 1000-2000 µs pulse width, we need to
     * scale the percentage to a value between 5 and 10
     *
     * @param pwm pwm object
     * @param percentage esc speed
     */
    private static void setEscSpeed(Pwm pwm, double percentage) {
        double dutyCyle = percentage / 20;
        pwm.on(5 + dutyCyle, PWM_FREQUENCY);
    }
}