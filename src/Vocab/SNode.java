package Vocab;
import java.util.ArrayList;
import java.util.Collections;


/** single linked list that stores words*/
public class SNode {
    /** inner class Node*/
    private class Node implements Comparable<Node> {
        private Node next;
        private String word;
        public Node(String word, Node next){
            this.word = word;
            this.next = next;
        }
        /** compare two nodes by their words*/
        public int compareTo(Node other) {
            return this.word.compareTo(other.word);
        }
    }
    /** instance variables: head, counter*/
    private Node head;
    private int counter;
    /** constructor*/
    public SNode(){
        this.head = null;
        this.counter = 0;
    }
    /** add word to the head of the list*/
    public void addAtHead(String word){
        head = new Node(word, head);
        counter++;
    }
    /** add word to the list*/
    public boolean addWord(String word){
        if(head==null){
            addAtHead(word);
            return false;
        }
        else{
            Node position=head;
            while(position.next!=null){
                if (position.word.equalsIgnoreCase(word)){
                    return true;
                }
                position=position.next;
            }
            position.next= new Node(word,null);
            counter++;
            return false;
        }
    }
    /** remove word from the head of the list*/
    public void removeHead(){
        if(head==null){
            System.out.println("Sorry the list is empty.");
        }
        else {
            Node temp = head;
            head = head.next;
            temp.next = null;
            counter--;
        }
    }
    /** remove word from the list*/
    public void removeWord(String word){
        if(head==null){
            System.out.println("Sorry the list is empty.");
        }
        else if(head.word.equals(word)){
            removeHead();
        }
        else {
            Node position = head;
            while (position.next != null&&!position.next.word.equals(word)) {
                position = position.next;
            }
            if(position.next!=null){
                position.next=position.next.next;
                counter--;
            }
            else{
                System.out.println("Sorry, the word "+word+" is not listed.");
            }
        }
    }
    /** change word in the list*/
    public void changeWord(String word, String newWord){
        if(head==null) {
            System.out.println("Sorry the list is empty.");
        }
        else if(head.word.equals(word)){
            head=new Node (newWord, head.next);
        }
        else{
            Node position=head;
            while(position!=null&&!position.word.equals(word)){
                position=position.next;
            }
            if(position!=null){
                position.word=newWord;
            }
            else{
                System.out.println("Sorry, the word "+word+" is not listed.");
            }
        }
    }
    /** get word from the list*/
    public String getWord(int index){
        if(index<0||index>counter){
            System.out.println("Sorry, the index is out of bounds.");
            return null;
        }
        else if (head == null) {
            System.out.println("Sorry the list is empty.");
            return null;
        }
        else if(index==0){
            return head.word;
        }
        else {
            Node position = head;
            int i = 0;
            while (i<index ) {
                position = position.next;
                i++;
            }
            return position.word;
        }
    }
    /** display the list*/
    public void displaySNode(){
        if (head == null) {
            System.out.println("Sorry the list is empty.");
        }
        else {
            ArrayList<Node> nodeList = new ArrayList<>();
            Node position = head;
            while (position != null) {
                nodeList.add(position);
                position = position.next;
            }
            Collections.sort(nodeList);
            for (Node node : nodeList) {
                System.out.println(node.word);
            }
        }
    }
    /** size of the list*/
    public int size() {
        return counter;
    }
    /** search for a word in the list*/
    public boolean foundWord(String word) {
        if (head == null) {
            return false;
        } else {
            Node position = head;
            while (position != null) {
                if (position.word.equals(word)) {
                    return true;
                }
                position = position.next;
            }
        }
        return false;
    }
}
