//Reedx500 Johall 004
import java.util.Scanner;
import java.io.*;

public class HashTable <T extends Comparable<T> >{

    private int firstHalfCount = 0;
    private int secondHalfCount = 0;
    private int firstHalfNodeSize = 0;
    private int secondHalfNodeSize = 0;

    NGen[] hashT;
    private int length;
    private int count = 0;

    public HashTable(){
        hashT = new NGen[101];
        length = 101;
    }

    public HashTable(int size){
        hashT = new NGen[size];
        length = size;
    }
    //part 2
    public void add(T item) {
        int val = this.hash(item);

        NGen insert = new NGen(item, null);

        if (hashT[val] == null) {
            hashT[val] = insert;
        }else{
            NGen start = hashT[val];
            while((start.getNext() != null)){
                if (start.getData().equals(item)){
                    return;
                }else {
                    start = start.getNext();
                }
            }
            start.setNext(insert);
        }
    }
    //part 2 & 4
    private int hash(T key){
        String k = (String) key;
        int charToNum = 0;
        int totalValOfS = 0;

        char[] c = k.toCharArray();
        for(int i = 0; i < c.length; i++){
            c[i] = k.charAt(i);
        }
        for(int j = 0; j < c.length; j++){
            charToNum = c[j];
            totalValOfS += charToNum;
        }
        totalValOfS = totalValOfS % length-1;
        return totalValOfS;
    }
    //part 1
    public String[] textScan (String fileName){

        Scanner readFile = null;
        String s;
        String t;
        int i = 0;
        int count = 0;

        System.out.println();
        System.out.println("Attempting to read from file: " + fileName);
        try {
            readFile = new Scanner(new File(fileName));
        }
        catch (FileNotFoundException e) {
            System.out.println("File: " + fileName + " not found");
            System.exit(1);
        }

        System.out.println("Connection to file: " + fileName + " successful");
        System.out.println();
        while(readFile.hasNext()) {
            t = readFile.next();
            count++;
        }
        String[] myString = new String[count];

        Scanner newReadFile = null;
        try {
            newReadFile = new Scanner(new File(fileName));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        while (newReadFile.hasNext()) {//maybe create another while loop to go through and count to see how big we need to make our array?
            s = newReadFile.next();
            myString[i] = s;
            i++;
            System.out.println("Token found: " + s);
        }
        System.out.println();
        System.out.println(count + " Tokens found");
        System.out.println();

        readFile.close();

        return myString;
    }
    //part 3
    public void display() {
        int longChain = 0;
        int shortChain = 0;//do the first index you can find; if having a hard time consider making it a global variable
        int tokenCount = 0;
        for(int i = 0; i < hashT.length; i++) {
            if (hashT[i] != null) {
                int count = 0;
                //have a short change counter. If short chain counter < current short chain replace.
                NGen updatedHead = hashT[i];
                while (updatedHead != null) {
                    tokenCount++;
                    updatedHead = updatedHead.getNext();
                    count++;
                    if(count > longChain){
                        longChain = count;
                    }
                }
            }
            // print out the tokens, longest, and shortest chains as well
        }
        System.out.println(longChain);
        System.out.println("Tokens found: " + tokenCount);
    }
    //part 1
    public void hashedTokens(String fName){
        String[] words = this.textScan(fName); //Create string array
        //iterate thru array and add every part to the hash function
        for(int i = 0; i < words.length; i++){
            this.add((T) words[i]);
        }
    }
    //part 4 (inspired by DBJ2 function)

    public static void main(String[] args) {
        HashTable x = new HashTable();
        x.hashedTokens("keywords.txt");

        x.display();

        }
}
