package com.company;

public class TrieNode {
    boolean isEndOfWord;
    TrieNode children[];
    char value;
        
    public TrieNode(){
        isEndOfWord = false;
        children = new TrieNode[26];
    }
    public TrieNode(char value){
        isEndOfWord = false;
        this.value = value;
    }
}