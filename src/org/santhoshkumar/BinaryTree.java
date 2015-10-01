package org.santhoshkumar;

import java.io.*;
import java.util.*;

public class BinaryTree {

    Node root;

    public static void main(String[] args){
        BinaryTree bt = new BinaryTree();
        bt.createTree();
        bt.inOrder();
    }

    public void createTree(){
        root = new Node(50);
        root.left = new Node(25);
        root.right = new Node(75);

        root.left.left = new Node(20);
        root.left.right = new Node(30);
        root.left.right.left = new Node(29);
        root.left.right.right = new Node(31);

        root.right.left = new Node(70);
        root.right.right = new Node(80);
    }

    public void inOrder(){
        if (root == null){
            return;
        }

        Stack<Node> stack = new Stack<Node>();
        Node current = root;

        while (current != null || !stack.isEmpty()){
            if(current != null){
                stack.push(current);
                current = current.left;
            }else if (!stack.isEmpty()){
                current = stack.pop();
                System.out.println(current.data);
                current = current.right;
            }
        }

    }

    public void preOrder(){
        if (root == null){
            return;
        }

    }

    public void postOrder(){
        if(root == null){
            return;
        }
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
