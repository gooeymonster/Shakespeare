/*@author Daniel Ali
@version 1.0
@since 4/21/2019
@param main
Instructor: Dr. Macpherson
Class: Programming II, RSU
Program: In this program, quickosrt, linear search and binary search are used to loop through every written
work by Shakespeare, to find the instances of unique words in all plays..*/

import java.io.*;
import java.util.*;



public class Shakespeare {
    static final int MAX_WORDS=31000;

    /**
     *Linear search implemented
     * @param words for the words array
     * @param target for specific words to be searched
     * @return 0 if word already exists, -1 if it doesnt
     */
    private static int lSearch(String [] words, String target) {

        // Put stuff here
        int count = words.length;
        for (int i = 0; i < count; i++) {
            if(words[i] != null && words[i].equals(target)) {
                return 0;
            }
        }
        return -1;
    }


    /**
     *
     * @param args
     * @throws IOException
     */
    public static void main(String[] args)throws IOException {
        try {
            String[] words = new String[MAX_WORDS];
            Scanner sc = new Scanner(new File("./100-0.txt"));
            int count = 0;
            int found = 0;
            int unique = 0;
            while (sc.hasNext()) {
                String word = sc.next();
                word = word.replaceAll("[^a-zA-Z]", "");
                word = word.toLowerCase();
                //System.out.println(words);

                // Search the words array if this is a new word
                if (lSearch(words, word) == -1 && count < MAX_WORDS - 1) {
                    words[count] = word;
                    count = count + 1;
                    quickSort(words, 0, count - 1);
                    System.out.println(word);
                }
            }
            String file = "";
            Scanner sc2 = new Scanner(new File(file));
            //Input to enter file
            System.out.println("Enter file: ");
            file = sc2.nextLine();
            while (sc2.hasNext()) {
                String word2 = sc2.next();
                word2 = word2.replaceAll("[^a-zA-Z]", "").toLowerCase();
                if (bSearch(words, word2) == -1 && count < MAX_WORDS - 1) {
                    unique++;
                } else {
                    found++;
                }
            }
            System.out.println("Unique: " + unique);
            System.out.println("Found: " + found);
            int sum = found + unique;
            double percentage = (found * 100.0) / sum;
            System.out.println("Percentage: " + percentage);
            sc.close();
            sc2.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }


    /**
     *Quicksort is implemented
     * @param words for the string array
     * @param start startpoint of partition
     * @param end endpoint of partition
     */
    private static void quickSort(String[] words, int start, int end){
        //create partition
        int part = partition(words, start, end);

        if(part - 1 > start){
            quickSort(words, start, part - 1);
        }
        if(part + 1 < end){
            quickSort(words, part + 1, end);
        }
    }

    /**
     *Partition method for the quicksort
     * @param words
     * @param start
     * @param end
     * @return
     */
    private static int partition(String[] words, int start, int end){
        String pivot = words[end];

        for(int i = start; i<end; i++){
            if(words[i].compareTo(pivot)<0){
                String placeHolder = words[start];
                words[start] = words[i];
                words[i] = placeHolder;
                start++;

            }
        }
        String placeHolder = words[start];
        words[start] = pivot;
        words[end]= placeHolder;
        return start;
    }

    /**
     * Binary search is implemented
     * @param words
     * @param target
     * @return
     */
    public static int bSearch(String[] words, String target){
        int start = 0;
        int end = words.length - 1;
        while(start<=end){
            int middle = start+end/2;
            int ref = target.compareTo(words[middle]);
            if(ref == 0){
                return middle;
            }
            else if (ref<0){
                end = middle - 1;
            }
            else{
                start = middle + 1;
            }
        }
        return -1;
    }


}
