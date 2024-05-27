package Vocab;
/** double linked list that stores Vocab objects*/
public class DNode {
    /** inner class Node*/
    private class Node{
        private Node next;
        private Node prev;
        private Vocab vocab;
        public Node(Vocab vocab, Node prev,Node next){
            this.vocab = vocab;
            this.next = next;
            this.prev=prev;
        }
    }
    /** instance variables: head, tail, counter*/
    private Node head;
    private Node tail;
    private int counter;
    /** constructor*/
    public DNode(){
        this.head = null;
        this.tail=null;
        this.counter = 0;
    }
    /** add Vocab object to the head of the list*/
    public void addAtHead(Vocab vocab){
        Node temp=head;
        head=new Node(vocab,null,temp);
        if(tail==null) {
            tail = head;
        }
        else{
            temp.prev=head;
        }
        counter++;
    }
    /** remove Vocab object from the head of the list*/
    public void removeAtHead(){
        if(head==null){
            System.out.println("Sorry the list is empty.");
        }
        else if (head==tail){
            head=tail=null;
            counter--;
        }
        else{
            head=head.next;
            head.prev=null;
            counter--;
        }
    }
    /** add Vocab object to the tail of the list*/
    public void addAtTail(Vocab vocab){
        Node temp=tail;
        tail=new Node (vocab,temp,null);
        if(head==null){
            head=tail;
        }
        else{
            temp.next=tail;
        }
        counter++;
    }
    /** remove Vocab object from the tail of the list*/
    public void removeAtTail(){
        if(tail==null){
            System.out.println("Sorry the list is empty.");
        }
        else if(head==tail) {
            head = tail = null;
            counter--;
        }
        else{
            tail=tail.prev;
            tail.next=null;
            counter--;
        }
    }
    /** add Vocab object to the list*/
    public void addVocab(Vocab vocab){
        if(head==null){
            addAtHead(vocab);
        }
        else{
            addAtTail(vocab);
        }
    }
    /** add Vocab object after a specified Vocab object*/
    public void addVocabAfter(Vocab vocab, Vocab newVocab){
        Node position=head;
        if(head==null){
            System.out.println("Sorry, the list is empty.");
        }
        else {
            while (position != null && position.vocab != vocab) {
                position = position.next;
            }
            if (position != null) {
                if (position.next == null) {
                    addAtTail(newVocab);
                } else {
                    Node newNode = new Node(vocab, position, position.next);
                    position.next.prev = newNode;
                    position.next = newNode;
                    counter++;
                }
            }
            else {
                System.out.println("Sorry, the word " + vocab + " is not listed.");

            }
        }
    }
    /** add Vocab object before a specified Vocab object*/
    public void addVocabBefore(Vocab vocab, Vocab newVocab){
        Node position=tail;
        if(tail==null){
            System.out.println("Sorry, the list is empty.");
        }
        else{
            while(position!=null && position.vocab!=vocab){
                position=position.next;
            }
            if(position!=null){
                if(position.prev==null){
                    addAtHead(newVocab);
                }
                else{
                    Node newNode=new Node(vocab,position.prev,position);
                    position.prev.next=newNode;
                    position.prev=newNode;
                    counter++;
                }
            }
            else{
                System.out.println("Sorry, the word "+vocab+" is not listed.");
            }
        }
    }
    /** remove Vocab object from the list*/
    public void removeVocab(Vocab vocabToRemove){
        if (head == null) {
            System.out.println("Sorry the list is empty.");
        }
        else if(head.vocab==vocabToRemove){
                removeAtHead();
        }
        else if(tail.vocab==vocabToRemove) {
                removeAtTail();
        }
        else{
            Node position=head;
            while(position!=null&&position.vocab!=vocabToRemove){
                position=position.next;
            }
            if(position!=null){
                position.prev.next=position.next;
                position.next.prev=position.prev;
                counter--;
            }
            else{
                System.out.println("Sorry, the word "+vocabToRemove+" is not listed.");
            }
        }
    }
    /** display the Vocab at an index*/
    public Vocab getVocab(int index){
        if(index<0||index>counter){
            System.out.println("Sorry, the index is out of bounds.");
            return null;
        }
        else if (head==null){
            System.out.println("Sorry the list is empty.");
            return null;
        }
        else if(index==0){
            return head.vocab;
        }
        else if(index==counter-1){
            return tail.vocab;
        }
        else{
            int i=0;
            Node position =head;
            while(i<index) {
                position = position.next;
                i++;
            }
            return position.vocab;
        }
    }
    /** display the size of the Vocab list*/
    public int size(){
        return counter;
    }
    /** display the Vocab of a specified word*/
    public String searchTopicsForWord(String word){
        if(head==null){
            System.out.println("Sorry the list is empty.");
        } else if (head.vocab.getWords().foundWord(word)) {
            return "The word " + word + " was found in topic: " + head.vocab.getTopic();
        } else {
            Node position = head;
            while (position != null) {
                if (position.vocab.getWords().foundWord(word)) {
                    return "The word " +word+" was found in topic: " +position.vocab.getTopic();
                }
                position = position.next;
            }
        }
        return "Sorry, the word "+word+" is not listed.";
    }
}
