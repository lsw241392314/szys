package xx;


import java.util.ArrayList;
import java.util.Random;  
  
  
class Four   
{  
      
    private TreeNodes root;  
    private int num;  
    private ArrayList<TreeNodes> opeList = new ArrayList<TreeNodes>(); 

 
      
    public Four(int num){  
        this.num = num;  
    }  
      
    public int getNum(){  
        return num;  
    }  
      
    public void setNum(int num){  
        this.num = num;  
    }  
      
    public void setTreeNode(TreeNodes root){
        this.root = root;  
    }  
    public String toString(){  
        String str = root.toString();  
        str = str.substring(1, str.length()-1);  
        return str;  
    } 
    public String CalAndVal(){  
        return root.getResult();  
    } 
    public int getDeep(){  
        int i = this.num;  
        int deep = 2;  
        while(i/2 > 0){  
            deep++;  
            i /= 2;  
        }  
        return deep;  
    }
    public void createBTree(){  
        TreeNodes lchild, rchild, lnode, rnode;  
          
        if(num == 1){  
            lchild = new TreeNodes(String.valueOf(Ran.getNumber(10)), 

null, null);  
            rchild = new TreeNodes(String.valueOf(Ran.getNumber(10)), 

null, null);  
            root = new TreeNodes(String.valueOf(Ran.getOperator()), 

lchild, rchild);  
        }  
        else{  
            int num1 = 0;  
            int n = getDeep() - 3;  
            boolean[] place = Ran.getChildPlace(num);  
            root = new TreeNodes(String.valueOf(Ran.getOperator()), 

null, null);  
            opeList.add(root);  
              
            for(int i = 0; i < n; i++){  
                for(int j = 0; j < (int)Math.pow(2, i); j++, num1++){  
                    lchild = new TreeNodes(String.valueOf

(Ran.getOperator()), null, null);  
                    rchild = new TreeNodes(String.valueOf

(Ran.getOperator()), null, null);  
                    opeList.get(j + num1).setChild(lchild, rchild);  
                    opeList.add(lchild);  
                    opeList.add(rchild);  
                }  
            }  
            for(int i = 0; i < place.length; i++){  
                if(place[i]){  
                    lnode  = new TreeNodes(String.valueOf

(Ran.getNumber(10)), null, null);  
                    rnode  = new TreeNodes(String.valueOf

(Ran.getNumber(10)), null, null);  
                    if(i%2 == 0){  
                        lchild = new TreeNodes(String.valueOf

(Ran.getOperator()), lnode, rnode);  
                        opeList.add(lchild);  
                        opeList.get(num1).setLchild(lchild);  
                    }  
                    else{  
                        rchild = new TreeNodes(String.valueOf

(Ran.getOperator()), lnode, rnode);  
                        opeList.add(rchild);  
                        opeList.get(num1).setRchild(rchild);  
                    }  
                }  
                else{  
                    if(i%2 == 0){  
                        lchild = new TreeNodes(String.valueOf

(Ran.getNumber(10)), null, null);  
                        opeList.get(num1).setLchild(lchild);  
                    }  
                    else{  
                          
                        rchild = new TreeNodes(String.valueOf

(Ran.getNumber(10)), null, null);  
                        opeList.get(num1).setRchild(rchild);  
                    }  
                }  
                num1 = num1 + i%2;  
            }  
        }  
    }  
}  
class Ran {  
     public static char getOperator(){  
        char operator = 0;  
        Random ran = new Random();  
        int i = ran.nextInt(4);  
        switch(i){  
            case 0:  
                operator = '+';  
                break;  
            case 1:  
                operator = '-';  
                break;  
            case 2:  
                operator = '*';  
                break;  
            case 3:  
                operator = '/';  
                break;  
        }  
        return operator;  
    }  
    public static int getNumber(int max){  
        int number = 0;  
        Random ran = new Random();  
        number = ran.nextInt(max+1);  
        return number;  
    }  
    public static boolean[] getChildPlace(int num){  
        int d = 0;  
        int size = 0, j=1;  
        while(num >= (int)Math.pow(2, j)){  
            j++;  
        }  
        d = (int)Math.pow(2, j) - 1 - num;  
        size = (int)Math.pow(2, j-1);  
        boolean[] k = new boolean[size];  
        for(int i = 0; i < size; i++){  
            k[i] = true;  
        }  
        for(int i = 0; i < d; i++){  
            Random ran = new Random();  
            int f = ran.nextInt(size);  
            while(k[f] == false)  
            {  
                f = ran.nextInt(size);  
            }  
            k[f] = false;  
        }  
        return k;  
    }  
}  
class TreeNodes {  
      
    private String str;  
    private TreeNodes rchild = null;  
    private TreeNodes lchild = null;  
      
    public TreeNodes(String str){  
        this.str = str;  
    }  
      
    public TreeNodes(String str, TreeNodes lchild, TreeNodes rchild){  
        this.str = str;  
        this.rchild = rchild;  
        this.lchild = lchild;  
    }  
      
    public void setChild(TreeNodes lchild, TreeNodes rchild){  
        this.lchild = lchild;  
        this.rchild = rchild;  
    }  
      
    public TreeNodes getRchild() {    
        return rchild;    
    }    
    public void setRchild(TreeNodes rchild) {    
        this.rchild = rchild;    
    }    
    public TreeNodes getLchild() {    
        return lchild;    
    }    
    public void setLchild(TreeNodes lchild) {    
        this.lchild = lchild;    
    }  
      
    public String getStr(){  
        return str;  
    }  
    public String getResult(){  
        if(hasChild()){  
            switch(str){  
                case "+":  
                    return String.valueOf(Integer.parseInt(getLchild

().getResult()) + Integer.parseInt(getRchild().getResult()));  
                case "-":  
                    return String.valueOf(Integer.parseInt(getLchild

().getResult()) - Integer.parseInt(getRchild().getResult()));  
                case "*":  
                    return String.valueOf(Integer.parseInt(getLchild

().getResult()) * Integer.parseInt(getRchild().getResult()));  
                case "/":  
                    if(getRchild().getResult().equals("0")){  
                        while(str.equals("/")){  
                            str = String.valueOf(Ran.getOperator());  
                        }  
                        return this.getResult();  
                    }  
                    else if(Integer.parseInt(getLchild().getResult()) 

% Integer.parseInt(getRchild().getResult()) != 0){  
                        while(str.equals("/")){  
                            str = String.valueOf(Ran.getOperator());  
                        }  
                        return this.getResult();  
                    }  
                    else  
                        return String.valueOf(Integer.parseInt

(getLchild().getResult()) / Integer.parseInt(getRchild().getResult

()));  
            }  
        }  
        return str;  
    } 
    public String toString(){  
        String Lstr = "", Rstr = "", Str = "";  
        if(hasChild()){  
            if(getRchild().hasChild()){                           
                if(str.equals("/")){
                    Rstr = getRchild().toString();                
                }  
                else if(str.equals("*") || str.equals("-")){
                    if(getRchild().str.equals("+") || getRchild

().str.equals("-")){   
                        Rstr = getRchild().toString();            
                    }  
                    else{
                        Rstr = getRchild().toString().substring(1, 

getRchild().toString().length()-1);    
                    }  
                }  
                else{
                    Rstr = getRchild().toString().substring(1, 

getRchild().toString().length()-1);        
                }  
            }  
            else{  
                Rstr = getRchild().str;  
            }
            if(getLchild().hasChild()){                                

               
                if(str.equals("*") || str.equals("/")){  
                    if(getLchild().str.equals("+") || getLchild

().str.equals("-")){  
                        Lstr = getLchild().toString();  
                    }  
                    else{  
                        Lstr = getLchild().toString().substring(1, 

getLchild().toString().length()-1);  
                    }  
                }  
                else{  
                    Lstr = getLchild().toString().substring(1, 

getLchild().toString().length()-1);  
                }  
            }  
            else{  
                Lstr = getLchild().str;  
            } 
            Str = "(" + Lstr + str + Rstr + ")";                       

               
        }  
        else{ 
            Str = str;  
        }  
        return Str;  
    }  
      
    public boolean hasChild(){  
        if(lchild == null && rchild == null)  
            return false;  
        else  
            return true;  
    }  
}  
public class FourOperation {  
      
    public static void main(String args[]){  
        Four bTree;  
        for(int i = 0; i < 6; i++){  
            bTree = new Four(3);  
            bTree.createBTree();  
            String result = bTree.CalAndVal();  
            System.out.println(bTree.toString() + "=" + result);  
        }  
    }  
}  
