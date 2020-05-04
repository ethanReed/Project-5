//Reedx500 Johall 004
import java.util.Scanner;
import java.io.*;
import java.util.Random;

public class HashTable <T extends Comparable<T> >{



    NGen[] hashT;
    private int length;
    private int count = 0;
    private int offset = 1;
    int collisionCounter = 0;
    Random r = new Random();
    boolean keyword = false;


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
        if (fileName.equals("keyword.txt")){
            keyword = true;
        }
        Scanner readFile = null;
        String s;
        String t;
        int i = 0;
        int count = 0;

        try {
            readFile = new Scanner(new File(fileName));
        }
        catch (FileNotFoundException e) {
            System.exit(1);
        }


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

        }
        System.out.println();
        System.out.println(count + " Tokens found");
        System.out.println();

        readFile.close();

        return myString;
    }
    //part 2
    public void add(T item) {
        if (keyword == true){
            int val = this.hashKeywords(item);
        }
        int val = this.hash(item);
        if(val < 0){
            val = val * -1;
        }

        NGen insert = new NGen(item, null);

        if (hashT[val] == null) {
            hashT[val] = insert;
        }else{
            NGen start = hashT[val].getNext();
            NGen tracer = hashT[val];
            if (tracer.getData().equals(item)) {
                return;
            }
            while((start != null)){
                if (start.getData().equals(item)|| tracer.getData().equals(item)){
                    return;
                }else {
                    start = start.getNext();
                    tracer = tracer.getNext();
                }
            }
            collisionCounter++;
            tracer.setNext(insert);
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
        shortChain++;
        System.out.println(start);
        System.out.println("spots with at least 1 element: " + arrayOrginalSpots);
        System.out.println("Number of collisions: " + collisionCounter);
        System.out.println("Long Chain: " + longChain);
        System.out.println("Short Chain: " + shortChain);
        System.out.println(tokenCount + " Tokens found");
    }

    //old hash function
/*private int hash(T key){
        /*
        This hash function works by converting a string into a character array. This is ideal because each
        character has a corresponding number assigned to it so essential that means we can translate words into numbers.
        This is very helpful because we can get into the index of the array.
        To get a number for a word so, we start with a value of 0 and for every character, we add it to that total; finally
        modding by the size of the array to give us an index.
         */
        /*
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
    */
    //part 4
    private int hash(T key){
        /*
        This hash function works by converting a string into a character array. This is ideal because each
        character has a corresponding number assigned to it so essential that means we can translate words into numbers.
        This is very helpful because we can get into the index of the array.
        To get a number for a word so, we start with a value of 0 and for every character, we add it to that total; finally
        modding by the size of the array to give us an index.

        **
        This is an improvement from the function above because it uses more prime numbers. Prime numbers have a way of spreading out that data into
        new arrays
        **


         */

        String k = key.toString();
        int totalValOfS = 0;
        int hash = 7;

        for(int i = 0; i < k.length(); i++){
            hash = (hash * 31) + k.charAt(i);
            totalValOfS += hash;
        }
        totalValOfS = totalValOfS % length;
        return totalValOfS;
    }
    private int hashKeywords(T key){
        /*
        This hash function works by converting a string into a character array. This is ideal because each
        character has a corresponding number assigned to it so essential that means we can translate words into numbers.
        This is very helpful because we can get into the index of the array.
        To get a number for a word so, we start with a value of 0 and for every character, we add it to that total; finally
        modding by the size of the array to give us an index.
         */

        //This one is different than the previous one because since there are no duplicated values we can be more random
        //with our placing. There is an incrementer that increases each time this function is called and it is used to create
        //prime number. This is the best I could do. I tried for a couple hours and could not fully get the hash table to be collision free.
        

            //Create an array of random integers that are prime.

            String k = key.toString();
            int charToNum = 0;
            int totalValOfS = 0;
            int offsetSQRD = offset * offset;
            int hash = offsetSQRD + offset + 41; //this formula will give a prime number for any integer put into it.
        //the formula is x^2 + x + 41
            offset++;

            for(int i = 0; i < k.length(); i++){
                hash = (k.charAt(i) * 31) + hash;
                totalValOfS += hash;
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
        HashTable keywords = new HashTable(501);
        keywords.hashedTokens("keywords.txt");
        keywords.display();

        HashTable gettsyburg  = new HashTable();
        gettsyburg.hashedTokens("gettysburg.txt");
        gettsyburg.display();

        HashTable proverbs = new HashTable();
        proverbs.hashedTokens("proverbs.txt");
        proverbs.display();

        HashTable that_bad = new HashTable();
        that_bad.hashedTokens("that_bad.txt");
        that_bad.display();

        HashTable canterbury = new HashTable();
        canterbury.hashedTokens("canterbury.txt");
        canterbury.display();

        }
}
