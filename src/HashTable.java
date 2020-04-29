//Reedx500 Johall 004

public class HashTable <T extends Comparable<T> >{

    private int firstHalfCount = 0;
    private int secondHalfCount = 0;
    private int firstHalfNodeSize = 0;
    private int secondHalfNodeSize = 0;

    NGen[] hashT;
    private int size;

    public HashTable(){
        hashT = new NGen[101];
        firstHalfNodeSize = 50;
        secondHalfNodeSize = 51;
    }

    public HashTable(int size){
        hashT = new NGen[size];
    }

    public void add(T item) {

    }
    public void display() {
        for(int i = 0; i < hashT.length; i++) {
            if (hashT[i] == null) {
                return 0;
            } else {
                NGen updatedHead = hashT[i];
                int size = 0;
                while (updatedHead != null) {
                    updatedHead = updatedHead.getNext();
                    size++;
                }
            }
            System.out.print(size);
            // print out the tokens as well
        }
    }
    private int hash(T key){
        /*private int hash(int key)
T key
String s  = key.toString()
loop thru s
add and/or multiplyeach character together
..
% array.length
return that index

         */
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
        return totalValOfS;
    }

    public static void main(String[] args) {
        HashTable x = new HashTable();
        int r = x.hash("Hello");
        System.out.println(r + "");
    }
}
