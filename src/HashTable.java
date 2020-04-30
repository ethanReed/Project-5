//Reedx500 Johall 004

public class HashTable <T extends Comparable<T> >{

    private int firstHalfCount = 0;
    private int secondHalfCount = 0;
    private int firstHalfNodeSize = 0;
    private int secondHalfNodeSize = 0;

    NGen[] hashT;
    private int length;

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

    public void display(){
        
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

    public String[] textScan(String fileName){

    }

    public static void main(String[] args) {
        HashTable x = new HashTable();
    }
}
