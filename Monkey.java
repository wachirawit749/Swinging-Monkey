//Chattarin Muksakul 6213124
//Wachirawit peerapisarnpon 6213145
//Winn Ladawuthiphan 6213146
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Stack;

class Tree
{
    private int height;
    private int index;
    
    public Tree(int h,int i)
    {
        height = h;
        index = i;
    }
       
    public int getHeight()
    {
        return height;
    }
    
    public int getIndex()
    {
        return index;
    }
}

public class Monkey 
{
    Stack<Tree> stack;
    int numPairs = 0;
  
    public Monkey()
    {
        boolean repeat = true;
        while(repeat){
            stack = new Stack<Tree>();
            numPairs = 0;

            boolean inputPhase = true;
            int noTree = 0;
            ArrayList<Integer> all = new ArrayList();
            ArrayList<Integer> path[] = null;

            while(inputPhase)
            {
                try
                {
                    Scanner in = new Scanner(System.in);
                    System.out.println("Enter number of tree [Enter 0 to exit]: ");
                    noTree = in.nextInt();
                    if(noTree<3)
                        if(noTree==0)   {repeat=false;  break;}
                        else            throw new Exception("Number of trees must higher than 2");

                    path = new ArrayList[noTree];
                    for(int i=0;i<noTree-1;i++)
                    {
                        path[i] = new ArrayList();
                    }

                    int i =0;  
                    int n=0;
                    while(i<noTree)
                    {                    
                        try
                        {                                                
                            Scanner scan;
                            System.out.println("Height of tree ("+(i+1)+") = ");  
                            scan = new Scanner(System.in);
                            n = scan.nextInt();          
                            if(n<1)
                                throw new Exception("Height of tree must higher than 0");

                            all.add(n);
                            i++;
                        }                   
                        catch(Exception ex)
                        {                                         
                            System.out.println(ex);
                        }
                    }            
                    inputPhase = false;
                }
                catch(Exception ex)
                {
                    System.out.println(ex);
                }        
            } 

            int input;
            for(int i=0; i<all.size(); i++)
            {
                input = all.get(i);
                if(stack.isEmpty()) 
                {
                    stack.push(new Tree(input,i));
                }                
                else if(!stack.isEmpty())
                {                
                    while(!stack.isEmpty() && input > stack.peek().getHeight()) 
                    {
                        path[stack.peek().getIndex()].add(i);

                        stack.pop();                    
                        numPairs++;
                    }               
                    if(stack.isEmpty())
                    {
                        stack.push(new Tree(input,i)); 
                    }                                   
                    else if(!stack.isEmpty() && input < stack.peek().getHeight())
                    {
                        path[stack.peek().getIndex()].add(i);

                        stack.push(new Tree(input,i));
                        numPairs++;
                    }                
                    else if(input == stack.peek().getHeight())
                    {
                        path[stack.peek().getIndex()].add(i);

                        stack.pop();
                        stack.push(new Tree(input,i));                    
                        numPairs++;   
                    }                               
                }
            }    

            if(noTree != 0){
                System.out.printf("--Solution--\n\n");
                for(int i=0;i<noTree-1;i++)
                {
                    int j;
                    for(j=0;j<path[i].size();j++)
                    {
                        System.out.printf("Tree(%2d),(%3d) ft --> Tree(%2d),(%3d) ft\n",i+1,all.get(i),path[i].get(j)+1,all.get(path[i].get(j)));
                    }           
                    System.out.printf("Tree(%2d) pair = %d\n-------------------------------------------\n",i+1,j);
                }

                System.out.println("Total Pair = "+numPairs+"\n"); 
            }
        }
    }

    public static void main(String[] args)
    {
         new Monkey();                  
    } 
}
