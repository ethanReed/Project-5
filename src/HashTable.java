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
        } else {
            while((!(hashT[val].getNext() == null))){
                hashT[val].getNext();
            }
            hashT[val].setNext(insert);

        }
    }
    //part 2 & 4
    private int hash(T key){
        int charToNum = 0;
        int totalValOfS = 0;
        String s = key.toString();

        char[] c = new char[s.length()];
        for(int i = 0; i < s.length(); i++){
            c[i] = s.charAt(i);
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
        String[] myString = new String[length];//idk if we can assume it is 101 we need to see how much text we need to put in 1st
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
        while (readFile.hasNext()) {//maybe create another while loop to go through and count to see how big we need to make our array?
            s = readFile.next();
            myString[i] = s;
            i++;
            System.out.println("Token found: " + s);
            count++;
        }
        System.out.println();
        System.out.println(count + " Tokens found");
        System.out.println();

        readFile.close();

        return myString;
    }
    //part 3
    public void display() {
        int size;
        for(int i = 0; i < hashT.length; i++) {
            if (hashT[i] == null) {
                return;
            } else {
                NGen updatedHead = hashT[i];
                size = 0;
                while (updatedHead != null) {
                    updatedHead = updatedHead.getNext();
                    size++;
                }
            }
            System.out.println(size);
            System.out.println("Tokens found: " + count);
            // print out the tokens, longest, and shortest chains as well
        }
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
        x.hashedTokens("canterbury.txt");
        x.display();
    }
}
