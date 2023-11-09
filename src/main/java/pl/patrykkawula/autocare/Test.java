//package pl.patrykkawula.autocare;
//
//import org.springframework.boot.CommandLineRunner;
//
//public class Test implements CommandLineRunner {
//    private final ScheduledTasks scheduledTasks;
//
//    public Test(ScheduledTasks scheduledTasks) {
//        this.scheduledTasks = scheduledTasks;
//    }
//
//    @Override
//    public void run(String... args) throws Exception {
//        scheduledTasks.saveEmailToSend();
//        scheduledTasks.sendEmail();
//    }
//}