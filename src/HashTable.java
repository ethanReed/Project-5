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
    int collisionCounter = 0;

    public HashTable(){
        hashT = new NGen[101];
        length = 101;
    }

    public HashTable(int size){
        hashT = new NGen[size];
        length = size;
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
                    collisionCounter++;
                    start = start.getNext();
                }
            }
            start.setNext(insert);
        }
    }
    //part 3
    public void display() {
        int longChain = 0;
        int shortChain = -1;
        int tokenCount = 0;
        int arrayOrginalSpots = 0;


        for(int i = 0; i < hashT.length; i++) {
            if (hashT[i] != null) {
                arrayOrginalSpots ++;
                int count = 0;
                int countShort = 0;
                NGen updatedHead = hashT[i];
                while (updatedHead != null) {
                    tokenCount++;
                    updatedHead = updatedHead.getNext();
                    count++;
                    countShort++;

                    if(count > longChain){
                        longChain = count;
                    }
                    if(arrayOrginalSpots == 1){
                        shortChain = countShort;
                    }
                    if(countShort < shortChain && countShort != 0) {
                        shortChain = countShort;
                    }
                }
            }
        }
        String start = "";
        for(int j = 0; j <hashT.length; j++) {
            if (hashT[j] != null) {
                NGen updatedHead = hashT[j];
                String row = j + "";
                while (updatedHead != null) {
                    String val = (String) updatedHead.getData();
                    row += " " +val;
                    updatedHead = updatedHead.getNext();
                }
                start += row + "\n";
            }
        }
        System.out.println(start);
        System.out.println("spots with at least 1 element: " + arrayOrginalSpots);
        System.out.println("Number of collisions: " + collisionCounter);
        System.out.println("Long Chain: " + longChain);
        System.out.println("Short Chain: " + shortChain);
        System.out.println(tokenCount + " Tokens found");
    }
    //part 4
    private int hash(T key){
        /*
        This hash function works by converting a string into a character array. This is ideal because each
        character has a corresponding number assigned to it so essential that means we can translate words into numbers.
        This is very helpful because we can get into the index of the array.
        To get a number for a word so, we start with a value of 0 and for every character, we add it to that total; finally
        modding by the size of the array to give us an index.
         */
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
        totalValOfS = totalValOfS % length;
        return totalValOfS;
    }
    //Combines the methods, makes it simpilier.
    public void hashedTokens(String fName){
        String[] words = this.textScan(fName); //Create string array
        //iterate thru array and add every part to the hash function
        for(int i = 0; i < words.length; i++){
            this.add((T) words[i]);
        }
    }

    public static void main(String[] args) {
        HashTable x = new HashTable();
        x.hashedTokens("canterbury.txt");

        x.display();

        }
}
