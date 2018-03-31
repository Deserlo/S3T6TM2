//Eva Najera
//CSC 131 - SPRINT 3

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.time.Duration;
import java.time.Instant;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

@SuppressWarnings("unused")
public class TMModel implements ITMModel { // contains business logic/rules	
	ArrayList<TaskLogEntry> entries = new ArrayList<TaskLogEntry>();
	Set<String> taskNames = new HashSet<String>();
	Set<String> taskSizes = new HashSet<String>();
	Set<String> xsmalls = new HashSet<String>();
	Set<String> smalls = new HashSet<String>();
	Set<String> mediums = new HashSet<String>();
	Set<String> larges = new HashSet<String>();
	Set<String> xlarges = new HashSet<String>();
	
	public TMModel() {
		readLog();
	}
	public void readLog() {	
		String filename = "f.txt";
		Scanner s=null;
		try {
			s = new Scanner(new File(filename));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		while(s.hasNextLine()) {
			String arr[] = null;
			String nextLine = s.nextLine();
			arr = nextLine.split("\\t", 5);
			entries.add(new TaskLogEntry(arr[0],arr[1],arr[2],arr[3], arr[4]));
			taskNames.add(arr[2]);
			taskSizes.add(arr[3]);
			addTaskToSizeSet(arr[3], arr[2]);
		}
		s.close();	
	}
		
	public class TaskLogEntry {
		String start;
		String duration;
		String taskname;
		String description;
		String size;
		public TaskLogEntry() {
			start="00:00:00";
			duration="00:00:00";
			taskname="";
			description="none";
			size="none";
		}
		public TaskLogEntry(String start, String duration, String taskname, String size, String description) {
			this.start = start;
			this.duration = duration;
			this.taskname = taskname;
			this.description = description;
			this.size = size;	
		}
	}
	
	void addTaskToSizeSet(String size, String name){
		switch(size) 
		{
		case "xs":
			xsmalls.add(name);
			break;
		case "s":
			smalls.add(name);
			break;
		case "m":
			mediums.add(name);
			break;
		case "l":
			larges.add(name);
			break;
		case "xl":
			xlarges.add(name);
			break;
		}

		
	}

	
	Duration calcDuration(String stop, String start) {
		OffsetDateTime odt = OffsetDateTime.parse(start);
		OffsetDateTime odp = OffsetDateTime.parse(stop);
		Instant Istart = odt.toInstant();//convert start timestamp string back into Instant
		Instant Istop = odp.toInstant();//convert stop timestamp string back into Instant
	    return Duration.between(Istop, Istart);
	}
			
	public boolean writeLog() {
		try {
			PrintWriter fw = new PrintWriter("f.txt");
			BufferedWriter bw = new BufferedWriter(fw);
			for (int i=0; i<entries.size(); i++) {
				fw.print(entries.get(i).start + "\t");
				fw.print(entries.get(i).duration + "\t");
				fw.print(entries.get(i).taskname + "\t");
				fw.print(entries.get(i).size+ "\t");
				fw.print(entries.get(i).description);
				fw.println("");
			}
			bw.close();
		} catch (IOException e) {
		}
		return false;
	}

	@Override
	public boolean startTask(String name) {
		Instant Timestamp = Instant.now();
		if (taskNameExists(name)==true) {
			for (int i=0; i< entries.size(); i++) {
				if (entries.get(i).taskname.equals(name)) {
					entries.get(i).start = Timestamp.toString();
					writeLog();
				}					
			}
		}
		else {
		TaskLogEntry newentry = new TaskLogEntry();
		newentry.taskname = name;
		newentry.start = Timestamp.toString();
		entries.add(newentry);
		taskNames.add(newentry.taskname);
		writeLog();
		}
		return true;
	}

	@Override
	public boolean stopTask(String name) {
		Instant Timestamp = Instant.now();
		String stop= Timestamp.toString();
		if (taskNameExists(name)==true) {
			for (int i=0; i< entries.size(); i++) {
				if (entries.get(i).taskname.equals(name)) {
					Long taskDuration =  calcDuration(entries.get(i).start,stop).toMinutes();
					entries.get(i).duration = taskDuration.toString(); 
					writeLog();
				}					
			}
		}
		else {
			return false;
		}
		return true;

	}
	public boolean taskNameExists(String name) {
		for (int i=0; i< entries.size(); i++) {
			if (entries.get(i).taskname.equals(name)) {
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean describeTask(String name, String description){
				if (taskNameExists(name)==true) {
					for (int i=0; i< entries.size(); i++) {
						if (entries.get(i).taskname.equals(name))   
							if (entries.get(i).description.contains("none")){
								entries.get(i).description = description; 
								writeLog();
							}
							else {
								entries.get(i).description += ("// "+description); 
								writeLog();
						}					
					}
				}
				else {
				TaskLogEntry newentry = new TaskLogEntry();
				newentry.taskname = name;
				newentry.description = description;
				entries.add(newentry);
				taskNames.add(newentry.taskname);
				writeLog();
				}
				return true;
	}

	@Override
	public boolean sizeTask(String name, String size) {
		if (taskNameExists(name)==true) {
			for (int i=0; i< entries.size(); i++) {
				if (entries.get(i).taskname.equals(name)) {
					entries.get(i).size = size; 
					writeLog();
				}					
			}
		}
		else {
		TaskLogEntry newentry = new TaskLogEntry();
		newentry.taskname = name;
		newentry.size = size;
		entries.add(newentry);
		taskNames.add(newentry.taskname);
		taskSizes.add(newentry.size);
		writeLog();
		}
		return true;

	}

	@Override
	public boolean deleteTask(String name) {
		if (taskNameExists(name)==true) {
			for (int i=0; i< entries.size(); i++) {
				if (entries.get(i).taskname.equals(name)) {
					entries.remove(i); 
					taskNames.remove(name);
					writeLog();
				}					
			}
		}	
		return false;
	}
	
	public boolean replaceTaskNameInSet(String oldName, String newName) {
		taskNames.remove(oldName);
		taskNames.add(newName);
		return true;
	}

	@Override
	public boolean renameTask(String oldName, String newName) {
		if (taskNameExists(oldName)==true) {
			for (int i=0; i< entries.size(); i++) {
				if (entries.get(i).taskname.equals(oldName)) {
					entries.get(i).taskname = newName; 
					replaceTaskNameInSet(oldName, newName);
					writeLog();
				}					
			}
		}	
		return false;
	}

	@Override
	public String taskElapsedTime(String name) {
			for (int i=0; i< entries.size(); i++) {
				if (entries.get(i).taskname.equals(name) && !(entries.get(i).duration.contains("00:00:00"))) {
					return entries.get(i).duration;
				}				
			}	
		return null;
	}

	@Override
	public String taskSize(String name) {
		for (int i=0; i< entries.size(); i++) {
			if (entries.get(i).taskname.equals(name)) {
				return entries.get(i).size; 
			}
		}
		return null;
	}

	@Override
	public String taskDescription(String name) {
		for (int i=0; i< entries.size(); i++) {
			if (entries.get(i).taskname.equals(name)) {
				return entries.get(i).description; 
			}
		}
		return null;
	}

	@Override
	public String minTimeForSize(String size) {
		TreeSet<Integer> elapsedTimes = new TreeSet<Integer>();
		int min;
		if (taskNamesForSize(size).size()>1) {
			for (int i=0; i< entries.size(); i++) {
				if (entries.get(i).size.equals(size) && !(entries.get(i).duration.contains("00:00:00"))) {
					min = Integer.parseInt(entries.get(i).duration); 
					elapsedTimes.add(min);
				}
			}		
			return elapsedTimes.first().toString();		
		}
		else 
			return null;
	}

	@Override
	public String maxTimeForSize(String size) {
		TreeSet<Integer> elapsedTimes = new TreeSet<Integer>();
		int max;
		if (taskNamesForSize(size).size()>1) {
			for (int i=0; i< entries.size(); i++) {
				if (entries.get(i).size.equals(size) && !(entries.get(i).duration.contains("00:00:00"))) {
					max = Integer.parseInt(taskElapsedTime(entries.get(i).taskname));
					elapsedTimes.add(max);
				}
			}
			
			return elapsedTimes.last().toString();		
			}
		else 
			return null;
	}

	@Override
	public String avgTimeForSize(String size) {
		double sum = 0;
		Integer n = 0;
			if (taskNamesForSize(size).size()>1) {
				for (int i=0; i< entries.size(); i++) {
					if (entries.get(i).size.equals(size) && !(entries.get(i).duration.contains("00:00:00"))) {
						sum += Double.parseDouble(taskElapsedTime(entries.get(i).taskname));
						n++;
					}
				}
				Double mean = sum/n;
				return String.format("%.2f", mean);
				}
			else 
				return null;
	}

	@Override
	public Set<String> taskNamesForSize(String size) {
		switch(size) 
		{
		case "xs":
			return xsmalls;
		case "s":
			return smalls;
		case "m":
			return mediums;
		case "l":
			return larges;
		case "xl":
			return xlarges;
		}
		return null;
		
	}

	@Override
	public String elapsedTimeForAllTasks() {
		double sum = 0;
		for (int i=0; i< entries.size(); i++) {
			if (!entries.get(i).duration.contains("00:00:00"))
				sum += Double.parseDouble(taskElapsedTime(entries.get(i).taskname));
		}
		Double elapsedTime = sum;
		return String.format("%.2f", elapsedTime);
	}

	@Override
	public Set<String> taskNames() {
		return taskNames;
	}

	@Override
	public Set<String> taskSizes() {
		return taskSizes;
	}
	
}
