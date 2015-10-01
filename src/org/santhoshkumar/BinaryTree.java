package org.santhoshkumar;

import java.io.*;
import java.util.*;

public class BinaryTree {

    Node root;
    int maxDepth;

    public static void main(String[] args){
        BinaryTree bt = new BinaryTree();
        bt.createTree();
        System.out.println("In Order Traversal");
        bt.inOrder();
        System.out.println();
        System.out.println("Pre Order Traversal");
        bt.preOrder();
        System.out.println();
        System.out.println("Post Order Traversal");
        bt.postOrder();
        System.out.println();
        System.out.println("Height of Tree: "+bt.getHeight(bt.root));
        System.out.println("Depth of Tree: "+bt.getDepth(bt.root));
        System.out.println("Width of Tree: "+bt.getMaxWidth());
        System.out.println("Size of Tree: "+bt.getSize(bt.root));
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
                System.out.print(current.data + " ");
                current = current.right;
            }
        }

    }

    public void preOrder(){
        if (root == null){
            return;
        }

        Stack<Node> stack = new Stack<Node>();
        stack.push(root);

        while(!stack.isEmpty()){
            Node current = stack.pop();
            System.out.print(current.data + " ");

            if(current.right != null){
                stack.push(current.right);
            }
            if(current.left != null){
                stack.push(current.left);
            }
        }
    }

    public void postOrder(){
        if(root == null){
            return;
        }

        Stack<Node> stack = new Stack<Node>();
        Node current = root;
        while(true){
            if(current!= null){
                if(current.right != null){
                    stack.push(current.right);
                }
                stack.push(current);
                current = current.left;
                continue;
            }

            if (stack.isEmpty()) {
                return;
            }

            current = stack.pop();

            if(current.right != null && !stack.isEmpty() && current.right == stack.peek()){
                stack.pop();
                stack.push(current);
                current = current.right;
            }else{
                System.out.print(current.data + " ");
                current = null;
            }
        }
    }

    public int getHeight(Node node){
        //Leaf node are at height 0;
        if(node == null){
            return -1;
        }
        else if(node.right == null && node.left == null){
            return 0;
        }
        return Math.max(getHeight(node.left), getHeight(node.right))+1;
    }

    public int getDepth(Node node){
        this.maxDepth = 0;
        findMaxDepth(node, 0);
        return maxDepth;
    }

    private void findMaxDepth(Node node, int depth){
        //root node is at depth 0;
        if(node != null) {
            if (depth > maxDepth) {
                maxDepth = depth;
            }
            findMaxDepth(node.left, depth + 1);
            findMaxDepth(node.right, depth+1);
        }
    }

    public int getMaxWidth(){
        // Largest BFS node chain length
        if(root == null){
            return 0;
        }
        Queue<Node> queue = new LinkedList<Node>();
        queue.add(root);
        int maxWidth = 0;
        int width = 0;
        while(!queue.isEmpty()){
            width = queue.size();
            if (width > maxWidth){
                maxWidth = width;
            }
            while(width > 0){
                Node node = queue.poll();
                if (node.right != null){
                    queue.add(node.right);
                }
                if (node.left != null){
                    queue.add(node.left);
                }
                width--;
            }
        }
        return maxWidth;
    }

    public int getSize(Node node){
        //no of nodes -> size of tree
        if (node == null){
            return 0;
        }
        return (1+getSize(node.right)+getSize(node.left));
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
