/**This program can extract all artist's name and store them in a 2D array with no 
 * duplicates and the amount of times they showed, and print them out.
 * 
 * This program can create a singly linked list, add items into linked list and sort string
 * elements in ascending order
 * 
 * This program can only extra data in CVS format as "pos, "track name", name, stream, url"
 */

/**
 * @author Jiewen mei
 *
 */
import java.util.*;
import java.io.*;

//pos,"track name",name,#,url
public class Assignment1{
	public static void main(String[] args)throws IOException{
	    File fileName=new File("src/inputs/songList");
	    String[][] rawData=readData(fileName);
	    PrintWriter op=new PrintWriter("src/outputs/dataRecord.txt");
	    for(int i=0;i<rawData.length;i++){
	      op.println(rawData[i][0]+" appeared "+rawData[i][1]+" times.");
	    }
	    op.close();
	    TopStreamingArtists linkedlist = new TopStreamingArtists();
	    for(int i=0;i<rawData.length;i++){
	      linkedlist.add(rawData[i][0]);
	    }
	    linkedlist.sortList();
	    linkedlist.printData();
	      
	    
	  }//close main method
  
	//readData
	//input- a CSV file
	//function- extract artists' names
	//output- a 2d string array contain artists' names in the first column and the # of times 
	//they appear in the file in the second column
	public static String[][] readData(File fileName)throws IOException{
	Scanner sc=new Scanner(fileName,"UTF-8");
    ArrayList<String> artist = new ArrayList<String>(210);
    
    int lastC=0, secLast=0,thirdLast=0;
    sc.nextLine();
    sc.nextLine();
    while(sc.hasNextLine()){
      String line=sc.nextLine();
      lastC=line.lastIndexOf(",");
      secLast=line.lastIndexOf(",",lastC - 1);
      thirdLast=line.lastIndexOf(",", secLast - 1 );
      String str=line.substring(thirdLast+1, secLast);
      String strr=str.replace("\"","");
      String strrr=strr.trim();
      artist.add(strrr);
    }
    sc.close();
    Set<String> nameSet = new HashSet<String>();
    for(String name: artist){
      nameSet.add(name);
    }//remove duplicate
    int size=nameSet.size();
    String[][] data= new String[size][2];
    int i=0;
    for(String name: nameSet){
      int count = 0;
      for(String n: artist){
        if(name.contains(n)){
          count++;
        }
      }
      data[i][0]=name;
      data[i][1]=Integer.toString(count);
      i++;
    }
    return data;
  }//close readData() method
  
	
	//Artist class contains a string and an artist object
  static class Artist{
    private String name;
    Artist next;
    public Artist (String name) {
      this.name=name;
      next=null;
    }
  }//close Artist class
  
  
  //TopStreamArtists contains a add(String) method, sort() and a printData() methods
  static class TopStreamingArtists{
    private Artist first;
    private Artist last;
    public TopStreamingArtists() {
    	first=null;
    	last=null;
    }
    
    //add()
    //input- a string literal representing the name of one artist
    //function- add such an artist to the linked list
    //output- none
    public void add(String n){
      Artist nextname = new Artist(n);
      if(first==null){
        first=nextname;
        last=nextname;
      }
      else {
    	  last.next=nextname;
    	  last=nextname;
      }
    }//close add() method
    
    //sortList()
    //input- none
    //function- sort names in ascending order(ignore letter case)
    //output- none
    public void sortList() {
    	Artist current=first;
    	Artist nextOne=null;
    	String temp="";
    	if(first==null) {
    		return;
    	}
    	else {
    		while(current!=null) {
    			nextOne=current.next;
    			while(nextOne!=null) {
    				
    				if((current.name.compareToIgnoreCase(nextOne.name))>0) {
    					temp=current.name;
    					current.name=nextOne.name;
    					nextOne.name=temp;
    				}
    				nextOne=nextOne.next;
    			}
    			current=current.next;
    			
    		}
    	}
    	
    }//close sortList method
    
    //printData()
    //input- none
    //function- print all elements in the linked list
    //output- none
    public void printData() throws IOException{
      PrintWriter output=new PrintWriter("src/outputs/VIP nameList.txt");
      Artist curr=first;
      if(curr==null) {
    	  output.println("The list is empty.");
    	  output.close();
    	  return;
      }
      while(curr!=null){
        output.println(curr.name);
        curr=curr.next;
      }
      output.close();
    }//close printData();
    
    }//close topStreamList class   
    
}//close the assignment class    
    