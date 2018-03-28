/*Bryant Lu
  CSC 131-03*/

import java.util.Set;
import java.util.*;
import java.lang.Object;
import java.time.LocalDateTime;  //best choice to be able to grab system time and also add and subtract time, Oracle Docs
import java.time.Duration;  //Stack Overflow, to calculate difference in time
import java.io.*;  //create serializable class and saving/loading

public class TMModel implements ITMModel {
	
	static LocalDateTime now = LocalDateTime.now(); //grabbed from mkyong.com shorthand to grab system time
	static Node head = null;
	static Node cur = null;
	static Node prev = null;
	static Work temp = new Work();
    // set information in our model
    //
    public boolean startTask(String name){
		fileLoad();
		temp.Name = name;
		temp.Start = now;
		if(cur==null){
			head = new Node(temp);
		}
		else{
			cur.setNext(new Node(temp));
		}
		fileSave();
		return true;
	}
	
    public boolean stopTask(String name){
		fileLoad();
		cur = head;
		while(cur!=null){
			temp = (Work)cur.getData();
			if(temp.Name.equalsIgnoreCase(name)){
				temp.Stop = now;
				temp.Total = Duration.between(temp.Start,temp.Stop);
				cur.setData(temp);
			}
			cur = cur.getNext();
		}
		fileSave();
		return true;
	}
	
    public boolean describeTask(String name, String description){
		fileLoad();
		cur = head;
		while(cur!=null){
			temp = (Work)cur.getData();
			if(temp.Name.equalsIgnoreCase(name)){
				if(temp.Description==null){
					temp.Description = description;
				}
				else{
					temp.Description += "\n\t                  " + description;
				}
				cur.setData(temp);
			}
			cur = cur.getNext();
		}
		fileSave();
		return true;
	}
	
	public boolean sizeTask(String name, String size){
		fileLoad();
		cur = head;
		while(cur!=null){
			temp = (Work)cur.getData();
			if(temp.Name.equalsIgnoreCase(name)){
				temp.Size = size;
				cur.setData(temp);
			}
			cur = cur.getNext();
		}
		fileSave();
		return true;
	}
	
    public boolean deleteTask(String name){
		fileLoad();
		cur = head;
		boolean deleted = true;
		while(cur!=null && deleted){
			temp = (Work)cur.getData();
			if(temp.Name.equalsIgnoreCase(name)){
				if(cur==head){
					head = cur.getNext();
				}
				else{
					prev.setNext(cur.getNext());
				}
				deleted = false;
			}
			prev = cur;
			cur = cur.getNext();
		}
		fileSave();
		return true;
	}
			
    public boolean renameTask(String oldName, String newName){
		fileLoad();
		cur = head;
		boolean deleted = true;
		while(cur!=null){
			temp = (Work)cur.getData();
			if(temp.Name.equalsIgnoreCase(oldName)){
				temp.Name = newName;
			}
			cur = cur.getNext();
		}
		fileSave();
		return true;
	}	

    // return information about our tasks
    //
    public String taskElapsedTime(String name){
		fileLoad();
		String output = "\tTime Spent: ";
		cur = head;
		while(cur!=null){
			temp = (Work)cur.getData();
			if(temp.Name.equalsIgnoreCase(name)){
				if(temp.Total==null){
					return output+timeFormatted(Duration.between(temp.Start,now).getSeconds());
				}
				else{
					return output+timeFormatted(temp.Total.getSeconds());
				}
			}
			cur = cur.getNext();
		}
		return "error not found";
	}

				
    public String taskSize(String name){
		fileLoad();
		String output = "\tTask Size: ";
		cur = head;
		while(cur!=null){
			temp = (Work)cur.getData();
			if(temp.Name.equalsIgnoreCase(name)){
				if(temp.Size==null){
					return output+"none";
				}
				else{
					return output+temp.Size;
				}
			}
			cur = cur.getNext();
		}
		return "error not found";
	}
					
    public String taskDescription(String name){
		fileLoad();
		String output = "\tTask Description: ";
		cur = head;
		while(cur!=null){
			temp = (Work)cur.getData();
			if(temp.Name.equalsIgnoreCase(name)){
				if(temp.Description==null){
					return output+"none";
				}
				else{
					return output+temp.Description;
				}
			}
			cur = cur.getNext();
		}
		return "error not found";
	}
		

    // return information about some tasks
    //
    public String minTimeForSize(String size){
		Set<String> names = taskNamesForSize(size);
		long min = Long.MAX_VALUE;
		for( String name : names){
			cur = head;
			while(cur!=null){
				temp = (Work)cur.getData();
				if(name.equals(temp.Name)){
					if(temp.Total==null){
						if(min>Duration.between(temp.Start,now).getSeconds()){
							min=Duration.between(temp.Start,now).getSeconds();
						}
						else if(min>temp.Total.getSeconds()){
							min=temp.Total.getSeconds();
						}
					}
				}	
				cur = cur.getNext();
			}
		}
		return "\tMinimum Time: " + timeFormatted(min);
	}
	
    public String maxTimeForSize(String size){
		Set<String> names = taskNamesForSize(size);
		long max = Long.MIN_VALUE;
		for( String name : names){
			cur = head;
			while(cur!=null){
				temp = (Work)cur.getData();
				if(name.equals(temp.Name)){
						if(max<Duration.between(temp.Start,now).getSeconds()){
							max=Duration.between(temp.Start,now).getSeconds();
						}
						else if(max<temp.Total.getSeconds()){
							max=temp.Total.getSeconds();
						}
				}
				cur = cur.getNext();
			}
		}
		return "\tMaximum Time: " + timeFormatted(max);
	}
	
    public String avgTimeForSize(String size){
		Set<String> names = taskNamesForSize(size);
		long avg = 0;
		int counter = 0;
		for( String name : names){
			cur = head;
			while(cur!=null){
				temp = (Work)cur.getData();
				if(name.equals(temp.Name)){
					if(temp.Total==null){
						avg = avg + Duration.between(temp.Start,now).getSeconds();
					}
					else{
						avg = avg + temp.Total.getSeconds();
					}
					counter++;
				}
				cur = cur.getNext();
			}
		}
		return "\tAverage Time: " + timeFormatted(avg/counter);
	}


    public Set<String> taskNamesForSize(String size){
		fileLoad();
		Set<String> sizeNames = new TreeSet<String>();
		cur = head;
		while(cur!=null){
			temp = (Work)cur.getData();
			if(size.equalsIgnoreCase(temp.Size)){
				sizeNames.add(temp.Name);
			}
			cur = cur.getNext();
		}
		return sizeNames;
	}	

    // return information about all tasks
    //
    public String elapsedTimeForAllTasks(){
		fileLoad();
		String output = "Total Time: ";
		Duration all = Duration.ZERO;
		cur = head;
		while(cur!=null){
			temp = (Work)cur.getData();
			if(temp.Total==null){
				all = all.plus(Duration.between(temp.Start,now));
			}
			else{
				all = all.plus(temp.Total);
			}
			cur = cur.getNext();
		}
		return output+timeFormatted(all.getSeconds());		
	}
	
    public Set<String> taskNames(){
		fileLoad();
		Set<String> names = new TreeSet<String>();
		cur = head;
		while(cur!=null){
			temp = (Work)cur.getData();
			names.add(temp.Name);
			cur = cur.getNext();
		}
		return names;
	}
	
    public Set<String> taskSizes(){
		fileLoad();
		Set<String> sizes = new TreeSet<String>();
		cur = head;
		while(cur!=null){
			temp = (Work)cur.getData();
			if(temp.Size==null){
			}
			else{
				sizes.add(temp.Size.toUpperCase());
			}
			cur = cur.getNext();
		}
		return sizes;
	}
	
	//util methods
	String timeFormatted(long s){  //method to print time in a standardized manner, grabbed from Stack Overflow
		long hr=s/3600;
		long min=(s%3600)/60;
		long sec=s%60;
		String time = String.format("%d:%02d:%02d",hr,min,sec);
		return time;
	}
	
	static void fileLoad(){
		try{  //loading previous log on boot
			FileInputStream fi = new FileInputStream("Log.ser");
			ObjectInputStream oi = new ObjectInputStream(fi);
				cur = new Node((Work)oi.readObject());
				head = cur;
			while(true){
				cur.setNext(new Node((Work)oi.readObject()));
				cur = cur.getNext();
			}			
		} catch(EOFException EOF){
		} catch(Exception in){}
	}
	static void fileSave(){
		try{  //saving log at end of program
			FileOutputStream f = new FileOutputStream(new File("Log.ser"));
			ObjectOutputStream o = new ObjectOutputStream(f);
			cur = head;
			while(cur!=null){
				o.writeObject((Work)cur.getData());
				cur = cur.getNext();
			}
			o.close();
			f.close();
		} catch (Exception out){}
	}

}

class Work implements Serializable{
	String Name;
	String Description;
	String Size;
	LocalDateTime Start;
	LocalDateTime Stop;
	Duration Total;
}

class Node{
	private Object Data;
	private Node Next;
	
	public Node(Object Element){
		Data=Element;
	}
	public void setNext(Node N){
		Next=N;
	}
	public Node getNext(){
		return Next;
	}
	public Object getData(){
		return Data;
	}
	public void setData(Object Element){
		Data=Element;
	}
}