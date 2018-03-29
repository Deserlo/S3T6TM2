
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

/**
 *
 * @author Marina
 */
public class TM_Model {

    private final String logFile = "timelog.txt";

    public TM_Model() {
    }

    public void process(String[] args) {

        // get comamand
        String command = args[0];
        if (command.equals("summary")) {
            summary(args);
        } else if (command.equals("start")) {
            start(args);
        } else if (command.equals("stop")) {
            stop(args);
        } else if (command.equals("size")) {
            size(args);
        } else if (command.equals("describe")) {
            describe(args);
        } else if (command.equals("max")) {
            max(args);
        } else if (command.equals("min")) {
            min(args);
        } else if (command.equals("avg")) {
            avg(args);
        } else if (command.equals("rename")) {
            rename(args);
        } else if (command.equals("delete")) {
            delete(args);
        }
    }

    private void delete(String[] args) {
        if (args.length == 2) {
            String task = args[1];
            // initialize the log file
            Log log = new Log(logFile);
            log.delete(task);
        }
    }

    private void rename(String[] args) {
        if (args.length == 3) {
            String oldTask = args[1];
            String newTask = args[2];
            // initialize the log file
            Log log = new Log(logFile);
            log.rename(oldTask, newTask);
        }
    }

    private void max(String[] args) {
        if (args.length == 2) {
            String size_ = args[1];
            // initialize the log file
            Log log = new Log(logFile);
            log.max(size_);
        }
    }

    private void min(String[] args) {
        if (args.length == 2) {
            String size_ = args[1];
            // initialize the log file
            Log log = new Log(logFile);
            log.min(size_);
        }
    }

    private void avg(String[] args) {
        if (args.length == 2) {
            String size_ = args[1];
            // initialize the log file
            Log log = new Log(logFile);
            log.avg(size_);
        }
    }

    private void describe(String[] args) {
        if (args.length > 2) {
            // initialize the log file
            Log log = new Log(logFile);
            // get task
            String task = args[1];
            // get description
            String description = "";
            for (int i = 2; i < args.length; i++) {
                description += args[i] + " ";
            }
            log.describe(task, description.trim());
        }
    }

    private void size(String[] args) {
        if (args.length == 3) {
            // initialize the log file
            Log log = new Log(logFile);
            // get task
            String task = args[1];
            log.size(task, args[2]);
        }
    }

    private void stop(String[] args) {
        if (args.length > 1) {
            // initialize the log file
            Log log = new Log(logFile);
            // get task
            String task = args[1];
            log.stop(task);
        }
    }

    private void start(String[] args) {
        if (args.length > 1) {
            // initialize the log file
            Log log = new Log(logFile);
            // get task
            String task = args[1];
            log.start(task);
        }
    }

    private void summary(String[] args) {
        Log log = new Log(logFile);
        if (args.length == 1) {
            // initialize the log file            
            log.summary();
        } else if (args.length == 2) {
            String task = args[1];
            log.summary(task);
        }
    }
}

class Log {

    ArrayList<Record> records;
    String logFile;

    /**
     * constructor
     *
     * @param logFile
     */
    public Log(String logFile) {
        records = new ArrayList<>();
        this.logFile = logFile;
        readFile();
    }

    /**
     * delete a task
     * @param task 
     */
    public void delete(String task) {
        for (int i = 0; i < records.size(); i++) {
            // find the task
            Record get = records.get(i);
            if (get.getName().equals(task)) {
                // remove it
                records.remove(i);
                /// update log file 
                writeFile();
                break;
            }
        }
    }

    /**
     * rename a task
     * @param oldTask
     * @param newTask 
     */
    public void rename(String oldTask, String newTask) {
        for (Record record : records) {
            // find it 
            if (record.getName().equals(oldTask)) {
                // rename it 
                record.setName(newTask);
                writeFile();
                break;
            }
        }
    }

    /**
     * get average value of all related tasks
     * @param size_ 
     */
    public void avg(String size_) {
        
         // test for valid size
        if (isValidSize(size_) == false) {
            System.out.println("Entered size is invalid");
            System.out.println("It should be one of these: "
                    + Arrays.toString(Record.sizes));
        } else {
            
            // try to get average value
            double total = 0;
            boolean finished = true;
            int num = 0;
            for (Record record : records) {
                if (record.getSize().equals(size_)) {
                    double runTime = record.getRunTime();
                    if (runTime < Double.MAX_VALUE) {
                        total += runTime;
                        num++;
                    } else {
                        finished = false;
                    }
                }
            }
            
            // show result 
            if (total == 0) {
                if (finished) {
                    System.out.println("there isn't nay related task");
                } else {
                    System.out.println("The related task hasn't stopped yet");
                }
            } else {
                System.out.println("" + total);
                System.out.printf("%f\n", total / (double) num);
            }
        }
    }

    /**
     * get max value of all related tasks
     * @param size_ 
     */
    public void max(String size_) {
        
        // test for valid size
        if (isValidSize(size_) == false) {
            System.out.println("Entered size is invalid");
            System.out.println("It should be one of these: "
                    + Arrays.toString(Record.sizes));
        } else {
            
            // try to get max value
            double maxValue = 0;
            double runTime = 0;
            for (Record record : records) {
                if (record.getSize().equals(size_)) {
                    runTime = record.getRunTime();
                    if (runTime < Double.MAX_VALUE
                            && runTime > maxValue) {
                        maxValue = runTime;
                    }
                }
            }
            
            // show result on screen
            if (maxValue > 0) {
                System.out.println("" + maxValue);
            } else if (runTime == Double.MAX_VALUE) {
                System.out.println("The related task hasn't stopped yet");
            } else {
                System.out.println(
                        "There isn't any task that meets the entered size");
            }
        }
    }

    /**
     * get min value of all related tasks
     * @param size_ 
     */
    public void min(String size_) {
                
        // test for valid size
        if (isValidSize(size_) == false) {
            System.out.println("Entered size is invalid");
            System.out.println("It should be one of these: "
                    + Arrays.toString(Record.sizes));
        } else {
            
            // try to get min value
            double minValue = Double.MAX_VALUE;
            double runTime;
            for (Record record : records) {
                if (record.getSize().equals(size_)) {
                    runTime = record.getRunTime();
                    if (runTime < minValue) {
                        minValue = runTime;
                    }
                }
            }
            
            // show result on screen 
            if (minValue < Double.MAX_VALUE) {
                System.out.println("" + minValue);
            } else {
                System.out.println(
                        "There isn't any task that meets the entered size");
            }
        }
    }

    /**
     * test the input for valid size 
     * @param size_
     * @return 
     */
    private boolean isValidSize(String size_) {
        for (String size : Record.sizes) {
            if (size_.equals(size)) {
                return true;
            }
        }
        return false;
    }

    /**
     * set size to a task
     *
     * @param task
     * @param size_
     */
    public void size(String task, String size_) {
        for (Record record : records) {

            // find that task
            if (record.getName().equals(task)) {

                // set the size
                if (record.setSize(size_) == false) {
                    System.out.println("The entered size " + size_ + " is invalid");
                    System.out.println("It's should be one of these: "
                            + Arrays.toString(Record.sizes));
                } else {
                    // write to file only when the value of size is valid
                    writeFile();
                }
                return;
            }
        }
    }

    /**
     * Provide a report of the activity and total time spent working on ALL
     * tasks
     */
    public void summary() {
        double runTime = 0;
        for (Record record : records) {
            record.summary();
            runTime += record.getRunTime();
        }
        System.out.println("Total running time: " + runTime + " seconds");
    }

    /**
     * Provides a report of the activity and total time spent working
     *
     * @param task
     */
    public void summary(String task) {
        for (Record record : records) {
            if (record.getName().equals(task)) {
                record.summary();
                return;
            }
        }
        System.out.println(task + " hasn't run");
    }

    /**
     * Logs the description of the task with name
     *
     * @param task
     * @param description
     */
    public void describe(String task, String description) {
        for (Record record : records) {
            if (record.getName().equals(task)) {
                record.setDescription(description);
                break;
            }
        }
        writeFile();
    }

    /**
     * Logs the stop time of a task with name
     *
     * @param task
     * @return
     */
    public boolean stop(String task) {
        for (Record record : records) {
            if (record.getName().equals(task)
                    && record.getStop().equals(Record.EMPTY)) {
                record.setStop(LocalTime.now().toString());
                writeFile();
                return true;
            }
        }
        System.out.println("Error: " + task + " isn't running");
        return false;
    }

    /**
     * Logs the start time of a task with name
     *
     * @param task
     * @return
     */
    public boolean start(String task) {
        for (Record record : records) {
            if (record.getName().equals(task)) {
                System.out.println("Error: " + task + " has been in the log");
                return false;
            }
        }
        Record record = new Record(task);
        records.add(record);
        writeFile();
        return true;
    }

    /**
     * write updated data to file
     */
    private void writeFile() {
        try {
            BufferedWriter writer
                    = new BufferedWriter(new FileWriter(logFile));

            for (Record record : records) {
                writer.write(record.toString());
            }

            writer.close();
        } catch (IOException ex) {

        }
    }

    /**
     * read data from file
     */
    private void readFile() {
        try {
            Scanner input = new Scanner(new File(logFile));

            while (input.hasNext()) {
                Record record = new Record();
                if (record.read(input)) {
                    records.add(record);
                } else {
                    break;
                }
            }
            input.close();
        } catch (FileNotFoundException ex) {
        }
    }

}

class Record {

    private String name;
    private String start;
    private String stop;
    private String description;
    // this value has to be checked so we have to set its mode private 
    private String size;

    static final String EMPTY = "-";
    // this static array let us check for the correctness of the input size 
    static final String[] sizes = {"XS", "S", "M", "L", "XL"};

    /**
     * default constructor
     */
    public Record() {
        this.name = EMPTY;
        this.start = EMPTY;
        this.stop = EMPTY;
        this.description = EMPTY;
        this.size = EMPTY;
    }

    /**
     * constructor
     *
     * @param name
     */
    public Record(String name) {
        this.name = name;
        this.start = LocalTime.now().toString();
        this.stop = EMPTY;
        this.description = EMPTY;
        size = EMPTY;
    }

    /**
     *
     * @return a string to be written to log file
     */
    @Override
    public String toString() {
        return name + System.lineSeparator()
                + start + System.lineSeparator()
                + stop + System.lineSeparator()
                + description + System.lineSeparator()
                + size + System.lineSeparator();
    }

    /**
     * read data from inputing stream
     *
     * @param input
     * @return
     */
    public boolean read(Scanner input) {
        try {
            name = input.nextLine();
            start = input.nextLine();
            stop = input.nextLine();
            description = input.nextLine();
            size = input.nextLine();
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    /**
     * print summary of this record
     */
    public void summary() {
        System.out.println("Task: " + name);
        System.out.println("\tStart time: " + start);
        if (stop.equals(EMPTY)) {
            System.out.println("\tStop time: " + "Still running");
        } else {
            System.out.println("\tStop time: " + stop);
            System.out.println("\tRunning time: " + getRunTime() + " seconds");
        }
        System.out.println("\tDescription: " + description);
        System.out.println("\tSize: " + size);
    }

    public boolean setSize(String size) {
        for (String sz : sizes) {

            // the size has to match a sample size
            if (sz.equals(size)) {
                this.size = size;
                return true;
            }
        }
        // it doesn't match
        return false;
    }

    public String getSize() {
        return size;
    }

    /**
     * get run time
     *
     * @return
     */
    public double getRunTime() {
        if (stop.equals(EMPTY)) {
            // the task is still running 
            return Double.MAX_VALUE;
        }

        // turn string to local time 
        LocalTime st = LocalTime.parse(start);
        LocalTime sp = LocalTime.parse(stop);
        int tmp = 0;
        double second, minute, hour;

        // get the right run time        
        if (sp.getSecond() < st.getSecond()) {
            second = sp.getSecond() + 60 - st.getSecond();
            tmp = 1;
        } else {
            second = sp.getSecond() - st.getSecond();
        }

        sp.minusMinutes(tmp);
        if (sp.getMinute() < st.getMinute()) {
            minute = sp.getMinute() + 60 - st.getMinute();
            tmp = 1;
        } else {
            minute = sp.getMinute() - st.getMinute();
            tmp = 0;
        }

        sp.minusHours(tmp);
        hour = sp.getHour() - st.getHour();

        return second + 60 * (minute + 60 * hour);
    }

    public String getName() {
        return name;
    }

    public String getStart() {
        return start;
    }

    public String getStop() {
        return stop;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setStop(String stop) {
        this.stop = stop;
    }

    public void setName(String name) {
        this.name = name;
    }

}
