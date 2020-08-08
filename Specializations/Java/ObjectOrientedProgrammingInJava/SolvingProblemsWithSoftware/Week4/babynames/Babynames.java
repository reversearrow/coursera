
/**
 * Write a description of Babynames here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import java.io.File;
import org.apache.commons.csv.*;
public class Babynames {
    private void totalBirths(FileResource fr){
        int totalBirths = 0;
        int totalNames = 0;
        int numOfGirlNames = 0;
        int numOfBoysNames = 0;
        for(CSVRecord record: fr.getCSVParser(false)){
            int numBorn = Integer.parseInt(record.get(2));
            totalBirths += numBorn;
            if(record.get(1).equals("M")){
                numOfBoysNames += 1;
            }else {
                numOfGirlNames += 1;
            }
            totalNames += 1;
        }
        System.out.println("TotalBirths "+ totalBirths);
        System.out.println("TotalNames " + totalNames);
        System.out.println("numOfGirlNames " + numOfGirlNames);
        System.out.println("numOfBoysNames " + numOfBoysNames);
    }
    
    private int getRank(int year, String name, String gender){
        int rank = 0;
        String fileName = "yob" + year + ".csv" ;
        FileResource fr = new FileResource("us_babynames_by_year/"+ fileName);
        for (CSVRecord record: fr.getCSVParser(false)){
            if (record.get(1).equals(gender)){
                rank += 1;
                if(record.get(0).equals(name)){
                    return rank;
                }
            }
        }
        return -1;
    }

    private String getName(int year, int rank, String gender){
        String name = "No Name";
        String fileName = "yob" + year + ".csv" ;
        int count = 1;
        FileResource fr = new FileResource("us_babynames_by_year/"+ fileName);
        for (CSVRecord record: fr.getCSVParser(false)){
            if (record.get(1).equals(gender)){
                if (count == rank){
                   name = record.get(0);
                   return name;
                }else {
                   count++;
                }
            }

        }
        return name;
    }
    
    
    private void whatIsNameInYear(String name, int year, int newYear, String gender){
        int currentNameRank = getRank(year, name, gender);
        String getNameInNewYear = getName(newYear, currentNameRank, gender);
        System.out.println(name + " born in "+ year + " would be " + getNameInNewYear + " if she was born in " + newYear + ".");
    }
    
    private int yearOfHighestRank(String name, String gender){
        int year = -1;
        int highestRank = 0;
        int currentRank;
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
            int getYearFromFileName = Integer.parseInt((f.getName().substring(3, 7)));
            if (highestRank == 0){
                 currentRank = getRank(getYearFromFileName, name, gender);
                 if (currentRank != -1){
                     highestRank = currentRank; 
                     year = getYearFromFileName;
                 }
            } else {
                currentRank = getRank(getYearFromFileName, name, gender);
                if (currentRank != -1){
                    if (currentRank < highestRank){
                        highestRank = currentRank;
                        year = getYearFromFileName;
                    }
                }
            }
        }
        return year;
    }
    
    private double getAverageRank(String name, String gender){
        double totalRank = 0;
        double count = 0;
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
            int getYearFromFileName = Integer.parseInt((f.getName().substring(3, 7)));
            int currentRank = getRank(getYearFromFileName, name, gender);
            if (currentRank != -1){
                totalRank += currentRank;
                count += 1;
            }
            
        }
        if (totalRank != 0){
            return totalRank/count;
        } 
        return -1;
    }
    
    private int getTotalBirthsRankedHigher(int year, String name, String gender){
        String fileName = "yob" + year + ".csv" ;
        FileResource fr = new FileResource("us_babynames_by_year/"+ fileName);
        int rank = getRank(year, name, gender);
        int totalBirthsRankedHigher = 0;
        int count = 1;
        if(rank == -1){
            return -1;
        }
        
        if (rank == 1){
            return totalBirthsRankedHigher;
        }
        for (CSVRecord record: fr.getCSVParser(false)){
            if (count == rank){
                   return totalBirthsRankedHigher;
            }else {
                   if (record.get(1).equals(gender)){
                       totalBirthsRankedHigher += Integer.parseInt(record.get(2));
                       count++;
                   }
            }
        }
        return totalBirthsRankedHigher;
    }
    
    public void testGetTotalBirthsRankedHigher(){
       int totalBirthRankedHigher = getTotalBirthsRankedHigher(1990, "Drew", "M");
       System.out.println(totalBirthRankedHigher);
    }
    
    public void testGetAverageRank(){
        double averageRank = getAverageRank("Robert", "M");
        System.out.println(averageRank);
    }
    
    public void testYearOfHighestRank(){
        int year = yearOfHighestRank("Mich", "M");
        System.out.println(year);
    }
    
    public void testWhatIsNameInYear(){
        whatIsNameInYear("Owen", 1974 , 2014 , "M");
    }
    
    public void testGetName(){
        String name = getName(1982, 450, "M");
        System.out.println(name);
    }
    
    public void testGetRank(){
        int rank = getRank(1971, "Frank", "M");
        System.out.println(rank);
    }
    
    public void testTotalBirths(){
       FileResource fr = new FileResource();
       totalBirths(fr);
    }
    
}
