//Reedx500 Johal 004
import java.util.Scanner;
import java.io.*;
public class HashTable <T extends Comparable<T> >{

    private int firstHalfCount = 0;
    private int secondHalfCount = 0;
    private int firstHalfNodeSize = 0;
    private int secondHalfNodeSize = 0;

    NGen[] hashT;
    private int length;
    private int count;

    public HashTable(){
        hashT = new NGen[101];
        length = 101;
    }

    public HashTable(int size){
        hashT = new NGen[size];
        length = size;
    }

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
    public String[] textScan (String[] fileName){

        Scanner readFile = null;
        String s;
        String[] myString = new String[length];
        int i = 0;
        count = 0;

        System.out.println();
        System.out.println("Attempting to read from file: " + fileName[0]);
        try {
            readFile = new Scanner(new File(fileName[0]));
        }
        catch (FileNotFoundException e) {
            System.out.println("File: " + fileName[0] + " not found");
            System.exit(1);
        }

        System.out.println("Connection to file: " + fileName[0] + " successful");
        System.out.println();
        while (readFile.hasNext()) {
            s = readFile.next();
            myString[i] = s;
            i++;
            System.out.println("Token found: " + s);
            count++;
        }
        System.out.println();
        System.out.println(count + " Tokens found");
        System.out.println();
        return myString;
    }

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
        totalValOfS = totalValOfS % length;
        return totalValOfS;
    }

    public static void main(String[] args) {
        HashTable x = new HashTable();
    }
}
