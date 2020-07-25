
/**
 * Write a description of Part3 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Part3 {
    private boolean twoOccurrences(String A, String B){
        boolean result = false;
        int firstIndexOfAinB = B.indexOf(A);
        if (firstIndexOfAinB == -1){
            return result;
        }
        int secondIndexOfAinB = B.indexOf(A, firstIndexOfAinB+1);
        if (secondIndexOfAinB == -1){
            return result;
        }
        return true;
    }
    
    private String lastPart(String A, String B){
        int indexOfStringAinB = B.indexOf(A);
        System.out.println(indexOfStringAinB);
        if (indexOfStringAinB != -1){
            return B.substring((A.length()+indexOfStringAinB));
        }
        return B;
    }
    
    public void testing(){
        System.out.println(twoOccurrences("by","A story by Abby Long"));
        System.out.println("atg in ctgtatgta " + twoOccurrences("atg","ctgtatgta"));
        System.out.println(twoOccurrences("a","banana"));
        System.out.println("an in banana "+lastPart("an","banana"));
    }
}
