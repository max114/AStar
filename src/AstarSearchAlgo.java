
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.PriorityQueue;
import java.util.HashSet;
import java.util.Set;
import java.util.List;
import java.util.Comparator;
import java.util.ArrayList;
import java.util.Collections;

import javax.swing.JOptionPane;

 

public class AstarSearchAlgo{

 

 

        //h scores is the stright-line distance from the current city to Bucharest

        public static void main(String[] args){
        	String input_File="";
    		
    		//this helps to input the name of the file from the user
    		JOptionPane pane=new JOptionPane();
    		String name1;
    		int huristic=0;
    		
    		//String node=null;
    		
    		input_File=JOptionPane.showInputDialog("Please Enter a Text File!");
    		
    		try{
    			BufferedReader br = new BufferedReader(new FileReader(input_File));
    		 String line=null;
    			
    			
    			
    			while((line=br.readLine())!= null){{
    				
    			
    				
    				String tempArray[]=line.split("\t");
    				int i=0;
    				name1=tempArray[0];
    				huristic=Integer.parseInt(tempArray[1]);
    				 System.out.println(name1 + "\t"  + huristic);
    				
    				Node n1 = new Node(tempArray[0],huristic);

                    Node n2 = new Node(tempArray[2],huristic);

                    Node n3 = new Node(tempArray[4],huristic);

                    Node n4 = new Node(tempArray[6],huristic);
                    
                    System.out.println("checking" +n1 );
                    
                    n1.adjacencies = new Edge[]{

                            new Edge(n4,2),

                            new Edge(n3, 5),
                    };
                    n2.adjacencies = new Edge[]{

                            new Edge(n3,2),

                    };

                    
                    n3.adjacencies = new Edge[]{

                            new Edge(n2, 2),

                            new Edge(n1, 4)

                    };

                
                    n4.adjacencies = new Edge[]{

                            new Edge(n1, 2),

                            

                    };
                    
                    for (Edge edge : edges) {
                        //System.out.println(edge);
                        vv.insertEdge(edge);
                    }
                   
                    File fout = new File("AStar.txt");
                	FileOutputStream fos = new FileOutputStream(fout);
                   // }
                    try {
                    	BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos));
            			for (Edge edge : vv.getEdges()) {
            		          try {
            					bw.write(edge.toString() );
            					bw.newLine();
            				} catch (IOException e) {
            					// TODO Auto-generated catch block
            					e.printStackTrace();
            				}
            			}
            			bw.close();
            		} catch (FileNotFoundException e) {
            			// TODO Auto-generated catch block
            			e.printStackTrace();
            		}
                    
                    AstarSearch(n1,n3);

                    

                    List<Node> path = printPath(n4);

     

                            System.out.println("Path: " + path);

     

     


    				
    				//for (int i=0;i<=tempArray.length;i++) {
    					//System.out.println(name1 + "\t"+ huristic);
    					//System.out.println(tempArray[1]);
    					//System.out.println(tempArray[2]);
    					//System.out.println(tempArray[3]);
    					//System.out.println(tempArray[4]);
    					//System.out.println(tempArray[5]);
    					//System.out.println(tempArray[6]);
    					//System.out.println(tempArray[7]);
    					//System.out.println(tempArray[8]);
    					//System.out.println(tempArray[9]);
    				   
    					
    					
    				}  
    			
                        
    				
    				
    			}
    			
    			br.close();
    			
    			
    	}catch (IOException e){
    		e.printStackTrace();
    	}
    	
    	
    
    	
    	
        


 

                

                
        }
        


        public static List<Node> printPath(Node target){

                List<Node> path = new ArrayList<Node>();

        

        for(Node node = target; node!=null; node = node.parent){

            path.add(node);

        }

 

        Collections.reverse(path);

 

        return path;

        }



        public static void AstarSearch(Node source, Node goal){

 

                Set<Node> explored = new HashSet<Node>();

 

                PriorityQueue<Node> queue = new PriorityQueue<Node>(15, 

                        new Comparator<Node>(){

                                 //override compare method

                 public int compare(Node i, Node j){

                    if(i.f_scores > j.f_scores){

                        return 1;

                    }

 

                    else if (i.f_scores < j.f_scores){

                        return -1;

                    }

 

                    else{

                        return 0;

                    }

                 }

 

                        }

                        );

 

                //cost from start

                source.g_scores = 0;

 

                queue.add(source);

 

                boolean found = false;

 

                while((!queue.isEmpty())&&(!found)){

 

                        

                        Node current = queue.poll();

 

                        explored.add(current);

 

                     

                        if(current.value.equals(goal.value)){

                                found = true;

                        }

 

                        

                        for(Edge e : current.adjacencies){

                                Node child = e.target;

                                double cost = e.cost;

                                double temp_g_scores = current.g_scores + cost;

                                double temp_f_scores = temp_g_scores + child.h_scores;

 

 

                                /*if child node has been evaluated and 

                                the newer f_score is higher, skip*/

                                

                                if((explored.contains(child)) && 

                                        (temp_f_scores >= child.f_scores)){

                                        continue;

                                }

 

                                /*else if child node is not in queue or 

                                newer f_score is lower*/

                                

                                else if((!queue.contains(child)) || 

                                        (temp_f_scores < child.f_scores)){

 

                                        child.parent = current;

                                        child.g_scores = temp_g_scores;

                                        child.f_scores = temp_f_scores;

 

                                        if(queue.contains(child)){

                                                queue.remove(child);

                                        }

 

                                        queue.add(child);

 

                                }

 

                        }

 

                }

 

        }

        

}

 

class Node{

 

        public String name;

		public int hurisitc;

		public final String value;

        public double g_scores;

        public final int h_scores;

        public double f_scores = 0;

        public Edge[] adjacencies;

        public Node parent;

 

        public Node(String val, double hVal){

                value = val;

                h_scores = (int) hVal;

        }
       

 

        public String toString(){

                return value;

        }

 

}

 

class Edge{

        public final double cost;

        public final Node target;

 

        public Edge(Node targetNode, double cos){

                target = targetNode;

                cost = cos;

        }

}


