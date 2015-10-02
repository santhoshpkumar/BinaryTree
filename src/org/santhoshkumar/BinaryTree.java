package org.santhoshkumar;

import java.io.*;
import java.util.*;

public class BinaryTree {

    Node root;
    int maxDepth;
    int visitedLevel;

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
        System.out.println("Height of Tree: " + bt.getHeight(bt.root));
        System.out.println("Depth of Tree: " + bt.getMaxDepth());
        System.out.println("Width of Tree: " + bt.getMaxWidth());
        System.out.println("Size of Tree: " + bt.getSize(bt.root));
        System.out.println("Diameter of Tree: " + bt.getDiameter(bt.root));
        System.out.println("Level Order");
        bt.levelOrder();
        System.out.println();
        System.out.println("Spiral Order");
        bt.spiralOrder();
        System.out.println();
        System.out.println("Left view");
        bt.leftView();
        System.out.println();
        System.out.println("Right view");
        bt.rightView();

    }

    public void createTree(){
        root = new Node(50);
        root.left = new Node(25);
        root.right = new Node(75);

        root.left.left = new Node(20);
        root.left.right = new Node(30);
        root.left.right.left = new Node(29);
        root.left.right.right = new Node(31);
        //root.left.right.right.right = new Node(32);

        root.right.left = new Node(70);
        root.right.left.right = new Node(71);
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

    public void levelOrder(){
        if(root == null){
            return;
        }

        Queue<Node> queue = new LinkedList<Node>();
        queue.add(root);
        while(!queue.isEmpty()){
            Node node = queue.poll();
            System.out.print(node.data+" ");
            if(node.left!=null){
                queue.add(node.left);
            }
            if(node.right!=null){
                queue.add(node.right);
            }
        }
    }

    public void spiralOrder(){
        if (root == null){
            return;
        }

        Stack<Node> zig = new Stack<Node>();
        Stack<Node> zag = new Stack<Node>();
        zig.add(root);

        while(true){
            while(!zig.isEmpty()){
                Node node = zig.pop();
                System.out.print(node.data+" ");
                if(node.right!=null){
                    zag.push(node.right);
                }
                if(node.left!=null){
                    zag.push(node.left);
                }
            }

            while(!zag.isEmpty()){
                Node node = zag.pop();
                System.out.print(node.data+" ");
                if(node.left!=null){
                    zig.push(node.left);
                }
                if(node.right!=null){
                    zig.push(node.right);
                }
            }

            if(zig.isEmpty() && zag.isEmpty()){
                return;
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

    public int getMaxDepth(){
        //root node is at depth 0;
        this.maxDepth = 0;
        findMaxDepth(root, 0);
        return maxDepth;
    }

    private void findMaxDepth(Node node, int depth){
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

    public int getDiameter(Node node){
        if (node == null){
            return -1;
        }
        // Max(1+leftHeight+rightHeight, leftDiameter, rightDiameter
        int leftHeight = getHeight(node.left)+1; // leaf node has height 0 so add 1
        int rightHeight = getHeight(node.right)+1;
        int rootHeight = 1 /*root*/+leftHeight+rightHeight;

        int leftDiameter = getDiameter(node.left);
        int rightDiameter = getDiameter(node.right);
        return getMax(rootHeight, leftDiameter, rightDiameter);
    }

    private int getMax(int one, int two, int three){
        return (Math.max( one, Math.max(two,three)));
    }

    public void leftView(){
        visitedLevel = 0;
        printLeftView(root,visitedLevel+1);
    }

    public void printLeftView(Node node, int level){
        if(node == null){
            return;
        }

        if(level > visitedLevel){
            visitedLevel++;
            System.out.print(node.data+" ");
        }
        printLeftView(node.left,level+1);
        printLeftView(node.right,level+1);
    }

    public void rightView(){
        visitedLevel = 0;
        printRightView(root,visitedLevel+1);
    }

    public void printRightView(Node node, int level){
        if(node == null){
            return;
        }

        if(level > visitedLevel){
            visitedLevel++;
            System.out.print(node.data + " ");
        }
        printRightView(node.right, level+1);
        printRightView(node.left, level+1);
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
