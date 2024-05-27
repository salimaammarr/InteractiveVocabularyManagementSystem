import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.NoSuchElementException;
import java.util.Scanner;
import Vocab.*;

public class Main{
    static DNode vocabList = new DNode();

    public static void main(String[] args) {
        try {
            File folder = new File("./src/Output");
            folder.mkdirs();
            menu();
        }catch(NullPointerException e){
            System.out.println("Error: NullPointerException");
        }
    }
    /** Save the current state of the vocabList to a file*/
    public static void saveToFile(String filename) {
        try {
            PrintWriter writer = new PrintWriter(new FileOutputStream("./src/Output/" + filename));
            for(int i=0;i<vocabList.size();i++){
                writer.println("#"+vocabList.getVocab(i).getTopic());
                for(int j=0;j<vocabList.getVocab(i).getWords().size();j++){
                    writer.println(vocabList.getVocab(i).getWords().getWord(j));
                }
                writer.println();
            }
            writer.close();
        }catch(FileNotFoundException e) {
            System.out.println("Error: File not found");
        }catch(NullPointerException e){
            System.out.println("Error: NullPointerException");
        }catch(NoSuchElementException e){
            System.out.println("Error: NoSuchElementException");
        }
    }
    /** Load the vocabList from a file*/
    public static void loadFromFile(String filename) {
        try {
            Scanner topicsScanner = new Scanner(new FileInputStream("./src/Input/" + filename));
            while (topicsScanner.hasNext()) {
                String line = topicsScanner.nextLine();
                if (!line.isEmpty() && line.charAt(0) == '#') {
                    SNode words = new SNode();
                    while (topicsScanner.hasNext()) {
                        String word = topicsScanner.nextLine();
                        if (word.isEmpty()) {
                            break;
                        } else {
                            words.addWord(word);
                        }
                    }
                    vocabList.addVocab(new Vocab(line.substring(1),words));
                }
            }
            System.out.println("File loaded successfully");
            topicsScanner.close();
        }catch(NoSuchElementException e) {
            System.out.println("Error: NoSuchElementException");
        }catch(FileNotFoundException e) {
            System.out.println("Error: File not found");
        }
    }
    /** Display the topics in the vocabList*/
    public static void displayTopics(){
        try {
            System.out.println("------------------------------\n\tPick a topic\n------------------------------");
            int lineNumber = 1;
            if (vocabList.size() == 0) {
                System.out.println("No topics available");
            }
            for (int j = 0; j < vocabList.size(); j++) {
                System.out.println(lineNumber + ": " + vocabList.getVocab(j).getTopic());
                lineNumber++;
            }
            System.out.println("0 Exit\n------------------------------\nEnter Your Choice:");
        }catch(NullPointerException e){
            System.out.println("Error: NullPointerException");
        }
    }
    /** Display the menu and handle user input*/
    public static void menu() {
        int chosenTopic;
        String newTopic;
        SNode newWords;
        String word;
        String oldWord;
        String modifyChoice;
        Scanner sc = new Scanner(System.in);
        int choice;
        boolean exit = false;
        while (!exit) {
            System.out.println("------------------------------\n Vocabulary Control Center\n------------------------------\n1 browse a topic\n2 insert a new topic before another one\n3 insert a new topic after another one\n4 remove a topic\n5 modify a topic\n6 search topics for a word\n7 load from a file\n8 show all words starting with a given letter\n9 save to file\n0 exit\n------------------------------\nEnter Your Choice:");
            choice = sc.nextInt();
            switch(choice){
                case 1:
                    /** Display the topics and sorted words in the vocabList*/
                    displayTopics();
                    chosenTopic = sc.nextInt();
                    if(chosenTopic==0){
                        break;
                    }
                    else{
                        for(int i=0;i<vocabList.getVocab(chosenTopic-1).getWords().size();i++){
                            System.out.print(i+1+": "+vocabList.getVocab(chosenTopic-1).getWords().getWord(i)+"\t");
                            System.out.println();
                        }
                    }
                    break;
                case 2:
                    /** Insert a new topic before another one*/
                    displayTopics();
                    chosenTopic = sc.nextInt();
                    System.out.println("Enter a topic name: ");
                    newTopic = sc.next();
                    newWords = new SNode();
                    sc.nextLine();
                    System.out.println("Enter a word - to quit press enter: ");
                    while(true){
                        word = sc.nextLine();
                        if(word.isEmpty()){
                            break;
                        }
                        else {
                            newWords.addWord(word);
                        }
                    }
                    vocabList.addVocabBefore(vocabList.getVocab(chosenTopic-1),new Vocab(newTopic,newWords));
                    break;
                case 3:
                    /** Insert a new topic after another one*/
                    displayTopics();
                    chosenTopic = sc.nextInt();
                    System.out.println("Enter a topic name: ");
                    newTopic = sc.next();
                    newWords = new SNode();
                    sc.nextLine();
                    System.out.println("Enter a word - to quit press enter: ");
                    while(true){
                        word = sc.nextLine();
                        if(word.isEmpty()){
                            break;
                        }
                        else {
                            newWords.addWord(word);
                        }
                    }
                    vocabList.addVocabAfter(vocabList.getVocab(chosenTopic-1),new Vocab(newTopic,newWords));
                    break;
                case 4:
                    /** Remove a topic*/
                    displayTopics();
                    chosenTopic = sc.nextInt();
                    vocabList.removeVocab(vocabList.getVocab(chosenTopic-1));
                    break;
                case 5:
                    /** Modify a topic*/
                    displayTopics();
                    chosenTopic = sc.nextInt();
                    System.out.println("-----------------------------\n\tModify a topic\n-----------------------------\n\ta add a word\n\tr remove a word\n\tc change a word\n\t0 exit\n-----------------------------\nEnter Your Choice:");
                    modifyChoice = sc.next();
                    while(!modifyChoice.equals("a")&&!modifyChoice.equals("r")&&!modifyChoice.equals("c")&&!modifyChoice.equals("0")){
                        System.out.println("Please enter a valid choice:");
                        modifyChoice = sc.next();
                    }
                    if(!modifyChoice.equals("0")) {
                        switch (modifyChoice) {
                            /** Add a word*/
                            case "a":
                                System.out.println("Type a word and press Enter, or press Enter to end input");
                                word = sc.next();
                                if(vocabList.getVocab(chosenTopic - 1).getWords().addWord(word)==true){
                                    System.out.println("The word "+word+" already exists in the list.");
                                }
                                break;
                            /** Remove a word*/
                            case "r":
                                System.out.println("Type a word and press Enter, or press Enter to end input");
                                word = sc.next();
                                vocabList.getVocab(chosenTopic-1).getWords().removeWord(word);
                                break;
                            /** Change a word*/
                            case "c":
                                System.out.println("Type the word to change and press Enter");
                                oldWord = sc.next();
                                System.out.println("Type the new word and press Enter");
                                word = sc.next();
                                vocabList.getVocab(chosenTopic-1).getWords().changeWord(oldWord,word);
                                break;
                        }
                    }
                    break;
                case 6:
                    /** Search topics for a word*/
                    System.out.println("Enter a word to search for:");
                    word = sc.next();
                    System.out.println(vocabList.searchTopicsForWord(word));
                    break;
                case 7:
                    /** Load from a file*/
                    System.out.println("Enter the name of the input file:");
                    String filename = sc.next();
                    loadFromFile(filename);
                    break;
                case 8:
                    /** Show all words starting with a given letter*/
                    System.out.println("Show all words starting with a given letter");
                    String letter = sc.next();
                    while(letter.length()!=1){
                        System.out.println("Please enter a single letter:");
                        letter = sc.next();
                    }
                    ArrayList<String> wordsBeginnigByletter = new ArrayList<String>();
                    for(int i=0;i<vocabList.size();i++){
                        for(int j=0;j<vocabList.getVocab(i).getWords().size();j++){
                            if(vocabList.getVocab(i).getWords().getWord(j).toString().charAt(0)==letter.charAt(0)){
                                wordsBeginnigByletter.add(vocabList.getVocab(i).getWords().getWord(j));
                            }
                        }
                    }
                    Collections.sort(wordsBeginnigByletter);
                    for(String wordBeginnigByletter:wordsBeginnigByletter){
                        System.out.println(wordBeginnigByletter);
                    }
                    break;
                case 9:
                    /** Save the current vocablist to file*/
                    saveToFile("Output.txt");
                    break;
                case 0:
                    /** Exit*/
                    System.out.println("Exit");
                    exit = true;
                    break;
            }
        }
    }
}



