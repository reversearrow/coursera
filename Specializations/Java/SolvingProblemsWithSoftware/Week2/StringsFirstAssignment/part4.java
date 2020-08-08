
/**
 * Write a description of part4 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.URLResource;
public class part4 {
    public void findWebLinks (){
        URLResource ur = new URLResource("https://www.dukelearntoprogram.com//course2/data/manylinks.html");
        for (String s : ur.words()){
            int findYoutubeinWords = s.indexOf("youtube.com");
            if (findYoutubeinWords != -1){
                int startIndex = s.indexOf("\"");
                int endIndex = s.indexOf("\"", startIndex+1);
                System.out.println(s.substring(startIndex, endIndex));
            }

        }
    }
}
