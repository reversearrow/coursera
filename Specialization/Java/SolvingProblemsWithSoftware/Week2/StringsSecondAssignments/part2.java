
/**
 * Write a description of part2 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class part2 {
    private int howMany(String A, String B){
        int occurances = 0;
        int indexOfAinB = B.indexOf(A);
        while (indexOfAinB != -1){
            occurances += 1;
            indexOfAinB = B.indexOf(A, indexOfAinB + A.length());
        }
        return occurances;
    }
    
    public void testHowmany(){
     System.out.println(howMany("GAA","ATGAACGAATTGAATC"));   
     System.out.println(howMany("AA","ATAAAA"));
     System.out.println(howMany("AB","ATAAAA"));  
     System.out.println(howMany("AA","ATAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA"));
    }
}
