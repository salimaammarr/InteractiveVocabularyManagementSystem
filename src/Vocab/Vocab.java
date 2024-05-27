package Vocab;
/** object that stores a topic and a list of words*/
public class Vocab{
    private SNode words;
    private String topic;
    /** parameterized constructor*/
    public Vocab(String topic, SNode words) {
        this.topic = topic;
        this.words = words;
    }
    /** topic accessor*/
    public String getTopic(){
        return topic;
    }
    /** topic mutator*/
    public void setTopic(String topic){
        this.topic = topic;
    }
    /** words accessor*/
    public SNode getWords(){
        return words;
    }
    /** words mutator*/
    public void setWords(SNode words){
        this.words = words;
    }
    /** add word to the Vocab words list*/
    public void addWord(String word){
        words.addWord(word);
    }
    /** remove word from the Vocab words list*/
    public void removeWord(String word){
        words.removeWord(word);
    }
    /** display the Vocab topic and words*/
    public void display(){
        System.out.println("Topic: "+topic);
        words.displaySNode();
    }
}


