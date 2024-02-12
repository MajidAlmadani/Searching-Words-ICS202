package com.company;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class App {
    public static void main(String[] args) throws Exception {
        Trie trieOfDictionary = new Trie();
        try{
            File file = new File("dictionary.txt");
            Scanner input  = new Scanner(file);
            while (input.hasNextLine()) {
              String line  = input.nextLine();
              line = line.replace("-","");
              trieOfDictionary.insert(line);
            }
            input.close();
          }catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        Trie trie = null;
        Scanner input = new Scanner(System.in);
        while(true){
            System.out.println("TRIE PROJECT: Enter your choice?");
            System.out.println("\t1) Create an empty trie");
            System.out.println("\t2) Create a trie with initial letters");
            System.out.println("\t3) Insert a word");
            System.out.println("\t4) Delete a word");
            System.out.println("\t5) List all words that begin with a prefix");
            System.out.println("\t6) Size of the trie");
            System.out.println("\t7) End");

            int choice = input.nextInt();

            if(choice==1){
                trie = new Trie();
            }
            else if(choice==2){
                trie = new Trie();
                Scanner input2 = new Scanner(System.in);
                System.out.println("Enter your list of letter> ");
                String[] inputList = input2.nextLine().split(" ");
                String word ="";
                for(String i:inputList) word += i;
                for (int i = word.length();i>0; i--) {
                    insertingEveryPossibility(word.substring(0,i), "",trieOfDictionary,trie);
                    insertingEveryPossibility(word.substring(i,word.length()),"",trieOfDictionary,trie);
                }
            }
            else if(choice==3){
                if(trie==null){
                    System.out.println("\nThere is no values in the trie\n");
                }
                else{
                    Scanner input3 = new Scanner(System.in);
                    System.out.println("Enter a word to insert> ");
                    String[] inputList = input3.nextLine().split(" ");
                    String word ="";
                    for(String i:inputList) word += i;
                    trie.insert(word);
                }
            }
            else if(choice==4){
                if(trie==null){
                    System.out.println("\nThere is no values in the trie\n");
                }
                else{
                    Scanner input4 = new Scanner(System.in);
                    System.out.println("Enter a words to delete> ");
                    String[] inputList = input4.nextLine().split(" ");
                    String word ="";
                    for(String i:inputList) word += i;
                    trie.delete(word);
                }
            }
            else if(choice==5){
                if(trie==null){
                    System.out.println("\nThere is no values in the trie\n");
                }
                else{
                    Scanner input5 = new Scanner(System.in);
                    System.out.println("Enter a prefix> ");
                    String[] inputList = input5.nextLine().split(" ");
                    String word ="";
                    for(String i:inputList) word += i;
                    String[] words = trie.allWordsPrefix(word);
                    if(words.length==0) System.out.println("No words found");
                    else{
                        System.out.print("Found the following words: ");
                        for(String i:words) System.out.print(i + " ");
                        System.out.println();
                    } 
                }
            }
            else if(choice==6){
                if(trie==null){
                    System.out.println("\nThe size of trie is 0\n");
                }
                else{
                    System.out.println("\nThe size of trie is " + trie.size()+"\n");
                }
            }
            else if(choice==7){
                break;
            }
        }
    }

    static void insertingEveryPossibility(String string, String answer,Trie trieOfDictionary,Trie trie){
  
        if (string.length() == 0) {
            if(trieOfDictionary.contains(answer)) trie.insert(answer);
            return ;
        }
  
        for (int i = 0; i < string.length(); i++) {
  
            char character = string.charAt(i);
  
            String temp = string.substring(0, i) + string.substring(i + 1);

            insertingEveryPossibility(temp, answer + character,trieOfDictionary,trie);
        }
    }
}
