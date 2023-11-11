package pl.patrykkawula.autocare;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class Test implements CommandLineRunner {
    private final ScheduledTasks scheduledTasks;

    public Test(ScheduledTasks scheduledTasks) {
        this.scheduledTasks = scheduledTasks;
    }

    @Override
    public void run(String... args) throws Exception {
        log.info("Starting run method");
        scheduledTasks.saveEmailToSend();
        log.info("Save emails to send");
        scheduledTasks.sendEmail();
        log.info("Send emails");
    }
}