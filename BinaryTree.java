import java.io.*;
import java.util.*;

public class BinaryTree {
        public static void main(String[] args){
                System.out.println("Binary Tree Implementation");
        }
}

class Node{
        Node left;
        Node right;
        int data;
        
        Node(){
                data = -1;
        }
        
        Node(int data){
                this.data = data;
        }
}
