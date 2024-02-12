package com.company;

import java.util.*;

public class Trie {
    private TrieNode root;
    private int size;
 
    public Trie(){
        root = new TrieNode();
    }

    public Trie(char c){
        TrieNode t = new TrieNode(c);
        size++;
        root = t;
    }

    public boolean isEmpty(){
        TrieNode node = root;
        if(root==null) return true;
        for(TrieNode child: node.children){
            if(child != null){
                return false; 
            } 
        }
        return true;
    }

    public void clear(){
        root = null;
        size=0;
    }

    public int size(){ return size; }

    public void insert(String s) {
        if(root==null) root = new TrieNode();
        TrieNode node = root;
        s = s.toUpperCase();
        for (char c : s.toCharArray()) {
            if (node.children[c-'A'] == null) {
                size++;
                node.children[c-'A'] = new TrieNode();
            }
            node = node.children[c-'A'];
        }
        node.isEndOfWord = true;
    }

    public boolean contains(String s){
        if(root==null) return false;
        TrieNode node = root;
        s = s.toUpperCase();
        for (char c : s.toCharArray()) {
            if (node.children[c-'A'] == null) {
                return false;
            }
            node = node.children[c-'A'];
        }
        return node.isEndOfWord;
    }

    public boolean isPrefix(String p){
        if(root==null) return false;
        TrieNode node = root;
        p = p.toUpperCase();
        for (char c : p.toCharArray()){
            if(node.children[c-'A'] == null){
                return false;
            }
            node = node.children[c-'A'];
        }
        return true;
    }

    public void delete(String s){
        TrieNode node = root;
        s = s.toUpperCase();
        if(root==null) return;
        if(contains(s)){
            String word = "";
            for (char c : s.toCharArray()) {
                word += c;
                String[] checkingWordsInTrie = allWordsPrefix(word);
                if (checkingWordsInTrie.length==1) {
                    node.children[c-'A'].value = 0; 
                    size--;
                }
                node = node.children[c-'A'];
            }
            node.isEndOfWord = false;
        }
        else{
            System.out.println("There is no word start with this word " + s); 
            return;
        }
    }

    public String[] allWordsPrefix(String p){
        TrieNode node = root;
        p = p.toUpperCase();
        if (!isPrefix(p))
            return new String[0];
        ArrayList<String> foundWords = new ArrayList<>();
        Stack<TrieNode> s1 = new Stack<>();
        Stack<String> s2 = new Stack<>();
        
        for (char i : p.toCharArray()) node = node.children[i - 'A'];
        
        s1.push(node);
        s2.push(p);
        while (!s1.empty() && !s2.empty()) {
            node = s1.pop();
            p = s2.pop();
            if (node.isEndOfWord)
            foundWords.add(p);
            
            int count = 0;
            for (TrieNode child: node.children) {
                if (child != null) {
                    s1.push(child);
                    s2.push(p + ((char) ('A' + count)));
                }
                count++;
            }
        }
        String[] answer = new String[foundWords.size()];
        for(int i=0;i<answer.length; i++) answer[i] = foundWords.get(i);
        return answer;
    }
}
