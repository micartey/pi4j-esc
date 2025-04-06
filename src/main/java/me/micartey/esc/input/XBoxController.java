package me.micartey.esc.input;

import lombok.SneakyThrows;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class XBoxController implements AutoCloseable {

    private final ExecutorService executorService = Executors.newSingleThreadExecutor();

    // sudo apt-get install joystick
    // jstest /dev/input/js0

    private String line;

    @SneakyThrows
    public XBoxController(String input) {
        ProcessBuilder pb = new ProcessBuilder("jstest", "--normal", "/dev/input/" + input);
        Process process = pb.start();

        executorService.submit(() -> {
            while (true) {
                try(BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()))) {
                    while((line = reader.readLine()) != null) {
                    }
                }
            }
        });
    }

    /**
     * Default with input file js0 which is often the first connected controller
     */
    public XBoxController() {
        this("js0");
    }

    public double getValue(ControllerInput input) {
        String parsed = line
                .replace("Buttons:  ", "")
                .replace("Axes:  ", " ")
                .replace("off", "0")
                .replace("on", "1")
                .trim();

        String reg = "\\s*[0-9]+:";
        String[] parts = parsed.split(reg);

        // Strip first element away as it is empty
        System.arraycopy(parts, 1, parts, 0, parts.length - 1);

        return Double.parseDouble(parts[input.getValue()].trim());
    }


    @Override
    public void close() throws Exception {
        executorService.shutdown();
    }
}