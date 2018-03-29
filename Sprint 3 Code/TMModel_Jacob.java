import java.io.*;
import java.lang.*;
import java.util.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Duration;
import java.time.Period;
import java.time.format.DateTimeFormatter;

public class TMModel implements ITMModel{
	LocalDateTime time1;
	LocalDateTime time2;
	Duration duration;
	File TempFile; 
	FileWriter writer;
	File TempFile2; 
	FileWriter writer2;
	PrintWriter eraser;
	
	private Scanner y;
	private Scanner y2;
	// set information in our model
	public boolean startTask(String name){
		OpenFile();
		time1 = LocalDateTime.now();
		try{
			writer.write("\n" + name + "\tStart\t" + time1);
			writer.flush();
		} catch(Exception e){
			System.out.println("error");
		}
		CloseFile();
		return true;
	}
	public boolean stopTask(String name){
		OpenFile();
		time2 = LocalDateTime.now();
		try{
			writer.write("\n" + name + "\tStop\t" + time2);
			writer.flush();
		} catch(Exception e){
			System.out.println("error");
		}
		CloseFile();
		return true;
	}
	public boolean describeTask(String name, String description){
		OpenFile();
		try{
			writer.write("\n" + name + "\tDescribe\t" + description);
			writer.flush();
		} catch(Exception e){
			System.out.println("error");
		}
		CloseFile();
		return true;
	}
	public boolean sizeTask(String name, String size){
		OpenFile();
		try{
			writer.write("\n" + name + "\tSize\t" + size);
			writer.flush();
		} catch(Exception e){
			System.out.println("error");
		}
		CloseFile();
		return true;
	}
	public boolean deleteTask(String name){
		RewriteFile(name, "null", 1);
		return true;
	}
	public boolean renameTask(String oldName, String newName){
		RewriteFile(oldName, newName, 2);
		return true;
	}
	
	// return information about our tasks
    public String taskElapsedTime(String name){
		return ReadFile2(name, 1);
	}
    public String taskSize(String name){
		return ReadFile2(name, 2);
	}
    public String taskDescription(String name){
		return ReadFile2(name, 3);
	}
	
	// return information about some tasks
    public String minTimeForSize(String size){
		return ReadFile(size, 1);
	}
    public String maxTimeForSize(String size){
		return ReadFile(size, 2);
	}
    public String avgTimeForSize(String size){
		return ReadFile(size, 3);
	}

    public Set<String> taskNamesForSize(String size){
		return ReadFile3(size, 1);
	}
	
	// return information about all tasks
    public String elapsedTimeForAllTasks(){
		return ReadFile("null", 4);
	}
    public Set<String> taskNames(){
		return ReadFile3("null", 2);
	}
    public Set<String> taskSizes(){
		return ReadFile3("null", 3);
	}
	private void OpenFile(){
		try{
			TempFile = new File("TM_Tasks.txt");
			writer  = new FileWriter(TempFile, true);
		} catch(Exception e){
			System.out.println("error");
		}
		try{
			y = new Scanner(new File("TM_Tasks.txt"));
		} catch(Exception e){
			System.out.println("can't find file");
		}
	}
	private void CloseFile(){
		y.close();
	}
	private String ReadFile(String size, int value){
		//value == 1(min), 2(max), 3(avg), 4(elapsed time)
		String StringToReturn = "null";
		boolean TimeOn1 = false;
		boolean TimeOn2 = false;
		boolean KeepGoing = true;
		boolean FoundSize = false;
		boolean RestartLoop = false;
		String[] ListOfStrings = new String[150]; 
		String[] ListOfSizes = new String[150];
		int[] AmountOfSizes = new int[150];
		Duration[] duration2 = new Duration[150];
		Duration[] MinDuration = new Duration[150];
		Duration[] MaxDuration = new Duration[150];
		Duration[] AvgDuration = new Duration[150];
		Duration ElapsedTime = Duration.between(LocalDateTime.now(), LocalDateTime.now());
		int CurrentSize = 0;
		int ThisSize = 0;
		int CurrentString = 0;
		String TaskNameIn = "";
		String TaskName = "";
		String TempSize = "null";
		OpenFile();
		while(KeepGoing == true){
			if(CurrentString == 0){
				TaskNameIn = y.next();
				TaskName = TaskNameIn;
			}
			else{
				TimeOn1 = false; //need to reset these each loop
				TimeOn2 = false; //these determine if we should calculate time between start and stop
				try{ 
					y = new Scanner(new File("TM_Tasks.txt"));
				} catch(Exception e){
					System.out.println("can't find file");
				}
				TaskNameIn = y.next();
				
				for(int i = 0; i < CurrentString;){
				restart_loop:
					if(TaskNameIn.equals(ListOfStrings[i]) || RestartLoop == true){ //i.e. Get "test1" is equal to "test1"
						RestartLoop = false;
						if(y.hasNext()) 
							TaskNameIn = y.next(); //start
						else{
							KeepGoing = false; //end of the line, end for loop
							break;
						}
						if(TaskNameIn.equals("Start") || TaskNameIn.equals("Stop") || TaskNameIn.equals("Size")){ 
							if(y.hasNext()) 
								TaskNameIn = y.next(); //time
							else{
								KeepGoing = false; 
								break;
							}
						}
						else if(TaskNameIn.equals("Describe")){ 
							if(y.hasNext()){
								TaskNameIn = y.next(); //Skip over stars.
								TaskNameIn = y.next();
								while(TaskNameIn.equals("**") == false){
									TaskNameIn = y.next();
								}
							}
							else{
								KeepGoing = false; 
								break;
							}
						}
						else if(TaskNameIn.equals("Describe2")){ //extra one to include size parameter
							if(y.hasNext()){
								TaskNameIn = y.next(); //Skip over stars.
								TaskNameIn = y.next();
								while(TaskNameIn.equals("**") == false){
									TaskNameIn = y.next();
								}
								TaskNameIn = y.next(); //Size Parameter
							}
							else{
								KeepGoing = false; 
								break;
							}
						}
						
						if(y.hasNext())
							TaskNameIn = y.next(); //get "test2" or whatever next task is
						else{
							KeepGoing = false; //end of the line, end for loop
							break;
						}
					}
					else{
						for(int i2 = 0; i2 < i; i2++){
							if(TaskNameIn.equals(ListOfStrings[i2])){
								RestartLoop = true;
								break restart_loop; 
							}
						}
						i++;
					}
				}
				TaskName = TaskNameIn;
				try{
					TaskNameIn = y.next();
				} catch(Exception e){
					break; //it grabs the last word in the file as a task, this stops that.
				}
			}
			try{
				y = new Scanner(new File("TM_Tasks.txt"));
			} catch(Exception e){
				System.out.println("can't find file");
			}
			while(y.hasNext()){
				TaskNameIn = y.next();
				if(TaskNameIn.equals(TaskName)){
					try{
						TaskNameIn = y.next();
					} catch(Exception e){
						break; 
					}
					if(TaskNameIn.equals("Start")){
						TaskNameIn = y.next();
						time1 = LocalDateTime.parse(TaskNameIn);
						TimeOn1 = true;
					}
					else if(TaskNameIn.equals("Stop")){
						TaskNameIn = y.next();
						time2 = LocalDateTime.parse(TaskNameIn);
						TimeOn2 = true;
						if(TimeOn1 == true && TimeOn2 == true){
							duration = Duration.between(time1, time2);
							if(duration2[CurrentString] == null)
								duration2[CurrentString] = duration;
							else
								duration2[CurrentString] = duration2[CurrentString].plus(duration);
							if(value == 4){
								ElapsedTime = ElapsedTime.plus(duration2[CurrentString]);
							}
						}
					}
					else if(TaskNameIn.equals("Describe")){
						TaskNameIn = y.next();
						if(TaskNameIn.equals("**")){
							TaskNameIn = y.next();
							while(TaskNameIn.equals("**") == false){
								TaskNameIn = y.next();
							}
						}
					}
					else if(TaskNameIn.equals("Describe2")){
						TaskNameIn = y.next();
						if(TaskNameIn.equals("**")){
							TaskNameIn = y.next();
							while(TaskNameIn.equals("**") == false){
								TaskNameIn = y.next();
							}
							TaskNameIn = y.next();
							TempSize = TaskNameIn;
						}
					}
					else if(TaskNameIn.equals("Size")){
						TaskNameIn = y.next();
						TempSize = TaskNameIn;
					}
				}
			}
			//Size stuff
			if(TempSize != "null"){
				if(CurrentSize == 0){
					ListOfSizes[CurrentSize] = TempSize;
					if(TimeOn1 == true && TimeOn2 == true){
						MinDuration[CurrentSize] = duration2[CurrentString];
						MaxDuration[CurrentSize] = duration2[CurrentString];
						AvgDuration[CurrentSize] = duration2[CurrentString];
					}
					AmountOfSizes[CurrentSize]++;
					CurrentSize++;
				}
				else{
					for(int i = 0; i < CurrentSize; i++){
						if(TempSize.equals(ListOfSizes[i])){
							FoundSize = true;
							ThisSize = i;
							AmountOfSizes[ThisSize]++;
							if(MinDuration[ThisSize].compareTo(duration2[CurrentString]) > 0){
								MinDuration[ThisSize] = duration2[CurrentString];
							}
							if(MaxDuration[ThisSize].compareTo(duration2[CurrentString]) < 0){
								MaxDuration[ThisSize] = duration2[CurrentString];
							}
							AvgDuration[ThisSize] = AvgDuration[ThisSize].plus(duration2[CurrentString]);
							break;
						}
					}
					if(FoundSize == false){
						ListOfSizes[CurrentSize] = TempSize;
						if(TimeOn1 == true && TimeOn2 == true){
							MinDuration[CurrentSize] = duration2[CurrentString];
							MaxDuration[CurrentSize] = duration2[CurrentString];
							AvgDuration[CurrentSize] = duration2[CurrentString];
						}
						AmountOfSizes[CurrentSize]++;
						CurrentSize++;
					}
					FoundSize = false;
				}
			}
			TempSize = "null";
			ListOfStrings[CurrentString] = TaskName;
			CurrentString++;
		}
		//print max,min,avg durations
		for(int i = 0; i < CurrentSize; i++){
			if(AmountOfSizes[i] > 1){
				if(value == 1){
					if(ListOfSizes[i].equals(size)){
						StringToReturn = MinDuration[i].toString();
						y.close();
						return StringToReturn;
					}
				}
				else if(value == 2){
					if(ListOfSizes[i].equals(size)){
						StringToReturn = MaxDuration[i].toString();
						y.close();
						return StringToReturn;
					}
				}
				else if(value == 3){
					if(ListOfSizes[i].equals(size)){
						AvgDuration[i] = AvgDuration[i].dividedBy(AmountOfSizes[i]);
						StringToReturn  = AvgDuration[i].toString();
						y.close();
						return StringToReturn;
					}
				}
			}
		}
		CloseFile();
		if(value == 4)
			StringToReturn = ElapsedTime.toString();
		return StringToReturn;
	}
	private String ReadFile2(String name, int value){
		//value == 1(elapsed time), 2(task size), 3(task description)
		String StringToReturn = "";
		try{
			y = new Scanner(new File("TM_Tasks.txt"));
		} catch(Exception e){
			System.out.println("can't find file");
		}
		boolean TimeOn1 = false;
		boolean TimeOn2 = false;
		while(y.hasNext()){ 
			String TaskNameIn = y.next();
			if(TaskNameIn.equals(name)){
				TaskNameIn = y.next();
				if(TaskNameIn.equals("Start")){
					TaskNameIn = y.next();
					time1 = LocalDateTime.parse(TaskNameIn);
					TimeOn1 = true;
				}
				else if(TaskNameIn.equals("Stop")){
					TaskNameIn = y.next();
					time2 = LocalDateTime.parse(TaskNameIn);
					TimeOn2 = true;
					if(TimeOn1 == true && TimeOn2 == true){
						duration = Duration.between(time1, time2);
						if(value == 1)
							StringToReturn = duration.toString();
					}
				}
				else if(TaskNameIn.equals("Describe")){
					TaskNameIn = y.next();
					if(TaskNameIn.equals("**")){
						TaskNameIn = y.next();
						while(TaskNameIn.equals("**") == false){
							if(value == 3){
								if(TaskNameIn.equals("**") == false)
									StringToReturn = StringToReturn + " " + TaskNameIn;
							}
							TaskNameIn = y.next();
						}
					}
				}
				else if(TaskNameIn.equals("Describe2")){
					TaskNameIn = y.next();
					if(TaskNameIn.equals("**")){
						TaskNameIn = y.next();
						while(TaskNameIn.equals("**") == false){
							if(value == 3){
								if(TaskNameIn.equals("**") == false)
									StringToReturn = StringToReturn + " " + TaskNameIn;
							}
							TaskNameIn = y.next();
						}
						TaskNameIn = y.next();
					}
				}
				else if(TaskNameIn.equals("Size")){
					TaskNameIn = y.next();
					if(value == 2)
						 StringToReturn = TaskNameIn;
				}
			}
		}
		return StringToReturn;
	}
	private Set<String> ReadFile3(String size, int value){
		//value == 1(taskNamesForSize), 2(taskNames()), 3(taskSizes())
		Set<String> set = new HashSet<String>();
		boolean TimeOn1 = false;
		boolean TimeOn2 = false;
		boolean KeepGoing = true;
		boolean FoundSize = false;
		boolean RestartLoop = false;
		String[] ListOfStrings = new String[150]; 
		String[] ListOfSizes = new String[150];
		int[] AmountOfSizes = new int[150];
		Duration[] duration2 = new Duration[150];
		Duration[] MinDuration = new Duration[150];
		Duration[] MaxDuration = new Duration[150];
		Duration[] AvgDuration = new Duration[150];
		int CurrentSize = 0;
		int ThisSize = 0;
		int CurrentString = 0;
		String TaskNameIn = "";
		String TaskName = "";
		String TempSize = "null";
		
		//OpenFile()
		try{
			TempFile = new File("TM_Tasks.txt");
			writer  = new FileWriter(TempFile, true);
		} catch(Exception e){
			System.out.println("error");
		}
		try{
			y = new Scanner(new File("TM_Tasks.txt"));
		} catch(Exception e){
			System.out.println("can't find file");
		}
		while(KeepGoing == true){
			if(CurrentString == 0){
				TaskNameIn = y.next();
				TaskName = TaskNameIn;
				if(value == 2)
					set.add(TaskName);
			}
			else{
				TimeOn1 = false; //need to reset these each loop
				TimeOn2 = false; //these determine if we should calculate time between start and stop
				try{ //This try/catch reopens file to reset y.next loop
					y = new Scanner(new File("TM_Tasks.txt"));
				} catch(Exception e){
					System.out.println("can't find file");
				}
				TaskNameIn = y.next();
				for(int i = 0; i < CurrentString;){
				restart_loop:
					if(TaskNameIn.equals(ListOfStrings[i]) || RestartLoop == true){ //i.e. Get "test1" is equal to "test1"
						RestartLoop = false;
						if(y.hasNext()) 
							TaskNameIn = y.next(); //start
						else{
							KeepGoing = false; //end of the line, end for loop
							break;
						}
						if(TaskNameIn.equals("Start") || TaskNameIn.equals("Stop") || TaskNameIn.equals("Size")){ 
							if(y.hasNext()) 
								TaskNameIn = y.next(); //time
							else{
								KeepGoing = false; 
								break;
							}
						}
						else if(TaskNameIn.equals("Describe")){ 
							if(y.hasNext()){
								TaskNameIn = y.next(); //Skip over stars.
								TaskNameIn = y.next();
								while(TaskNameIn.equals("**") == false){
									TaskNameIn = y.next();
								}
								//TaskNameIn = y.next();
							}
							else{
								KeepGoing = false; 
								break;
							}
						}
						else if(TaskNameIn.equals("Describe2")){ //extra one to include size parameter
							if(y.hasNext()){
								TaskNameIn = y.next(); //Skip over stars.
								TaskNameIn = y.next();
								while(TaskNameIn.equals("**") == false){
									TaskNameIn = y.next();
								}
								TaskNameIn = y.next(); //Size Parameter
							}
							else{
								KeepGoing = false; 
								break;
							}
						}
						
						if(y.hasNext())
							TaskNameIn = y.next(); //get "test2" or whatever next task is
						else{
							KeepGoing = false; //end of the line, end for loop
							break;
						}
					}
					else{
						for(int i2 = 0; i2 < i; i2++){
							if(TaskNameIn.equals(ListOfStrings[i2])){
								//i = (CurrentString - 1);
								RestartLoop = true;
								//KeepGoing = false;
								break restart_loop; 
							}
						}
						i++;
					}
				}
				TaskName = TaskNameIn;
				try{
					TaskNameIn = y.next();
				} catch(Exception e){
					break; //it grabs the last word in the file as a task for some reason, this stops that.
				}
				if(value == 2)
					set.add(TaskName);
			}
			try{
				y = new Scanner(new File("TM_Tasks.txt"));
			} catch(Exception e){
				System.out.println("can't find file");
			}
			while(y.hasNext()){ //stays in loop until end of file
				TaskNameIn = y.next();
				if(TaskNameIn.equals(TaskName)){
					try{
						TaskNameIn = y.next();
					} catch(Exception e){
						break; //One task, need to break here.
					}
					if(TaskNameIn.equals("Start")){
						TaskNameIn = y.next();
						time1 = LocalDateTime.parse(TaskNameIn);
						TimeOn1 = true;
					}
					else if(TaskNameIn.equals("Stop")){
						TaskNameIn = y.next();
						time2 = LocalDateTime.parse(TaskNameIn);
						TimeOn2 = true;
						if(TimeOn1 == true && TimeOn2 == true){
							duration = Duration.between(time1, time2);
							if(duration2[CurrentString] == null)
								duration2[CurrentString] = duration;
							else
								duration2[CurrentString] = duration2[CurrentString].plus(duration);
						}
					}
					else if(TaskNameIn.equals("Describe")){
						TaskNameIn = y.next();
						if(TaskNameIn.equals("**")){
							TaskNameIn = y.next();
							while(TaskNameIn.equals("**") == false){
								TaskNameIn = y.next();
							}
						}
					}
					else if(TaskNameIn.equals("Describe2")){
						TaskNameIn = y.next();
						if(TaskNameIn.equals("**")){
							TaskNameIn = y.next();
							while(TaskNameIn.equals("**") == false){
								TaskNameIn = y.next();
							}
							TaskNameIn = y.next();
							TempSize = TaskNameIn;
							if(value == 1){
								if(TempSize.equals(size)){
									set.add(TaskName);
								}
							}
						}
					}
					else if(TaskNameIn.equals("Size")){
						TaskNameIn = y.next();
						TempSize = TaskNameIn;
						if(value == 1){
							if(TempSize.equals(size)){
								set.add(TaskName);
							}
						}
					}
				}
			}
			//Size stuff
			if(TempSize != "null"){
				if(CurrentSize == 0){
					ListOfSizes[CurrentSize] = TempSize;
					if(TimeOn1 == true && TimeOn2 == true){
						MinDuration[CurrentSize] = duration2[CurrentString];
						MaxDuration[CurrentSize] = duration2[CurrentString];
						AvgDuration[CurrentSize] = duration2[CurrentString];
					}
					AmountOfSizes[CurrentSize]++;
					CurrentSize++;
				}
				else{
					for(int i = 0; i < CurrentSize; i++){
						if(TempSize.equals(ListOfSizes[i])){
							FoundSize = true;
							ThisSize = i;
							AmountOfSizes[ThisSize]++;
							if(MinDuration[ThisSize].compareTo(duration2[CurrentString]) > 0){
								MinDuration[ThisSize] = duration2[CurrentString];
							}
							if(MaxDuration[ThisSize].compareTo(duration2[CurrentString]) < 0){
								MaxDuration[ThisSize] = duration2[CurrentString];
							}
							AvgDuration[ThisSize] = AvgDuration[ThisSize].plus(duration2[CurrentString]);
							break;
						}
					}
					if(FoundSize == false){
						ListOfSizes[CurrentSize] = TempSize;
						if(TimeOn1 == true && TimeOn2 == true){
							MinDuration[CurrentSize] = duration2[CurrentString];
							MaxDuration[CurrentSize] = duration2[CurrentString];
							AvgDuration[CurrentSize] = duration2[CurrentString];
						}
						AmountOfSizes[CurrentSize]++;
						CurrentSize++;
					}
					FoundSize = false;
				}
			}
			TempSize = "null";
			ListOfStrings[CurrentString] = TaskName;
			CurrentString++;
		}
		//print max,min,avg durations
		for(int i = 0; i < CurrentSize; i++){
			if(value == 3){
				if(AmountOfSizes[i] > 0)
					set.add(ListOfSizes[i]);
			}
		}
		y.close();
		return set;
	}
	private void RewriteFile(String oldName, String newName, int value){
		OpenFile();
		//Create temp file
		try{
			TempFile2 = new File("TM_Temp.temp"); //TM_Temp.txt
			writer2  = new FileWriter(TempFile2, true);
		}catch(Exception e){
			System.out.println("error");
		}
		try{
			y2 = new Scanner(new File("TM_Temp.temp"));
		}catch(Exception e){
			System.out.println("can't find file");
		}
		//Restart TM_Tasks
		try{
			y = new Scanner(new File("TM_Tasks.txt"));
		}
		catch(Exception e){
			System.out.println("can't find file");
		}
		//Write TM_Tasks.txt->TM_Temp.temp(but skip over "TaskName")
		while(y.hasNext()){
			String TaskNameIn = y.next();
			if(TaskNameIn.equals(oldName)){ //Skip over this task to delete it, or rename
				if(value == 2){
					try{
						writer2.write("\n" + newName); //Rename here
						writer2.flush();
					} catch(Exception e){
						System.out.println("error");
					}
				}
				TaskNameIn = y.next();
				if(value == 2){
					try{
						writer2.write("\n" + TaskNameIn);
						writer2.flush();
					} catch(Exception e){
						System.out.println("error");
					}
				}
				if(TaskNameIn.equals("Start") || TaskNameIn.equals("Stop") || TaskNameIn.equals("Size")){
					TaskNameIn = y.next(); //Time, or Size
					if(value == 2){
						try{
							writer2.write("\n" + TaskNameIn);
							writer2.flush();
						} catch(Exception e){
							System.out.println("error");
						}
					}
				}

				else if(TaskNameIn.equals("Describe")){
					TaskNameIn = y.next();
					if(value == 2){
						try{
							writer2.write("\n" + TaskNameIn);
							writer2.flush();
						} catch(Exception e){
							System.out.println("error");
						}
					}
					if(TaskNameIn.equals("**")){
						TaskNameIn = y.next();
						if(value == 2){
							try{
								writer2.write("\n" + TaskNameIn);
								writer2.flush();
							} catch(Exception e){
								System.out.println("error");
							}
						}
						while(TaskNameIn.equals("**") == false){
							TaskNameIn = y.next();
							if(value == 2){
								try{
									writer2.write("\n" + TaskNameIn);
									writer2.flush();
								} catch(Exception e){
									System.out.println("error");
								}
							}
						}
					}
				}
				else if(TaskNameIn.equals("Describe2")){
					TaskNameIn = y.next();
					if(value == 2){
						try{
							writer2.write("\n" + TaskNameIn);
							writer2.flush();
						} catch(Exception e){
							System.out.println("error");
						}
					}
					if(TaskNameIn.equals("**")){
						TaskNameIn = y.next();
						if(value == 2){
							try{
								writer2.write("\n" + TaskNameIn);
								writer2.flush();
							} catch(Exception e){
								System.out.println("error");
							}
						}
						while(TaskNameIn.equals("**") == false){
							TaskNameIn = y.next();
							if(value == 2){
								try{
									writer2.write("\n" + TaskNameIn);
									writer2.flush();
								} catch(Exception e){
									System.out.println("error");
								}
							}
						}
						TaskNameIn = y.next();
						if(value == 2){
							try{
								writer2.write("\n" + TaskNameIn);
								writer2.flush();
							} catch(Exception e){
								System.out.println("error");
							}
						}
					}
				}
			}
			else{ //Just write normally, don't delete or rename
				try{
					writer2.write("\n" + TaskNameIn);
					writer2.flush();
				} catch(Exception e){
					System.out.println("error");
				}
				TaskNameIn = y.next();
				try{
					writer2.write("\t" + TaskNameIn);
					writer2.flush();
				} catch(Exception e){
					System.out.println("error");
				}
				if(TaskNameIn.equals("Start") || TaskNameIn.equals("Stop") || TaskNameIn.equals("Size")){
					TaskNameIn = y.next(); //Time
					try{
						writer2.write("\t" + TaskNameIn);
						writer2.flush();
					} catch(Exception e){
						System.out.println("error");
					}
				}
				else if(TaskNameIn.equals("Describe")){
					TaskNameIn = y.next();
					if(TaskNameIn.equals("**")){
						TaskNameIn = y.next();
						try{
							writer2.write("\t" + TaskNameIn);
							writer2.flush();
						} catch(Exception e){
							System.out.println("error");
						}
						while(TaskNameIn.equals("**") == false){
							TaskNameIn = y.next();
							try{
								writer2.write(" " + TaskNameIn);
								writer2.flush();
							} catch(Exception e){
								System.out.println("error");
							}
						}
					}
				}
				else if(TaskNameIn.equals("Describe2")){
					TaskNameIn = y.next();
					if(TaskNameIn.equals("**")){
						TaskNameIn = y.next();
						try{
							writer2.write("\t" + TaskNameIn);
							writer2.flush();
						} catch(Exception e){
							System.out.println("error");
						}
						while(TaskNameIn.equals("**") == false){
							TaskNameIn = y.next();
							try{
								writer2.write(" " + TaskNameIn);
								writer2.flush();
							} catch(Exception e){
								System.out.println("error");
							}
						}
						TaskNameIn = y.next(); //Get size
					}
				}
			} 
		}
		//Delete TM_Tasks.txt content
		try{
			eraser = new PrintWriter("TM_Tasks.txt");
		}catch(Exception e){
			System.out.println("error");
		}
		eraser.print("");
		eraser.close();
		//Write TM_Temp.temp->TM_Tasks.txt
		try{
			y2 = new Scanner(new File("TM_Temp.temp"));
		}catch(Exception e){
			System.out.println("can't find file");
		}
		while(y2.hasNext()){
			String TaskNameIn = y2.next();
			try{
				writer.write("\n" + TaskNameIn);
				writer.flush();
			} catch(Exception e){
				System.out.println("error");
			}
			TaskNameIn = y2.next();
			try{
				writer.write("\t" + TaskNameIn);
				writer.flush();
			} catch(Exception e){
				System.out.println("error");
			}
			if(TaskNameIn.equals("Start") || TaskNameIn.equals("Stop") || TaskNameIn.equals("Size")){
				TaskNameIn = y2.next(); //Time
				try{
					writer.write("\t" + TaskNameIn);
					writer.flush();
				} catch(Exception e){
					System.out.println("error");
				}
			}
			else if(TaskNameIn.equals("Describe")){
				TaskNameIn = y2.next();
				if(TaskNameIn.equals("**")){
					TaskNameIn = y2.next();
					try{
						writer.write("\t" + TaskNameIn);
						writer.flush();
					} catch(Exception e){
						System.out.println("error");
					}
					while(TaskNameIn.equals("**") == false){
						TaskNameIn = y2.next();
						try{
							writer.write(" " + TaskNameIn);
							writer.flush();
						} catch(Exception e){
							System.out.println("error");
						}
					}
				}
			}
			else if(TaskNameIn.equals("Describe2")){
				TaskNameIn = y2.next();
				if(TaskNameIn.equals("**")){
					TaskNameIn = y2.next();
					try{
						writer.write("\t" + TaskNameIn);
						writer.flush();
					} catch(Exception e){
						System.out.println("error");
					}
					while(TaskNameIn.equals("**") == false){
						TaskNameIn = y2.next();
						try{
							writer.write(" " + TaskNameIn);
							writer.flush();
						} catch(Exception e){
							System.out.println("error");
						}
					}
					TaskNameIn = y2.next(); //Get size
				}
			}
		}
		//Erase temp file
		try{
			eraser = new PrintWriter("TM_Temp.temp");
		}catch(Exception e){
			System.out.println("error");
		}
		CloseFile();
	}
}