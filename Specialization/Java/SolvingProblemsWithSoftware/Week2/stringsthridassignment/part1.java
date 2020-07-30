
/**
 * Write a description of part1 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.StorageResource;
import edu.duke.FileResource;
public class part1 {
     private int findStopCodon(String dna, int startIndex, String stopCodon){
        int currIndex = dna.indexOf(stopCodon, startIndex + 3);
        while (currIndex != -1){
            int diff = currIndex - startIndex+3;
            if (diff % 3 == 0){
                return currIndex;
            }else{
                currIndex = dna.indexOf(stopCodon, currIndex+1);
            }
            
        }
        return dna.length();
    }
    
    private String findGene(String dna, int startIndex){
        int startCodonIndex = dna.indexOf("ATG", startIndex);
        if (startCodonIndex == -1) {
            return "";
        }
        int indexOfTAA = findStopCodon(dna, startCodonIndex, "TAA");
        int indexOfTGA = findStopCodon(dna, startCodonIndex, "TGA");
        int indexOfTAG = findStopCodon(dna, startCodonIndex, "TAG");
        int minIndex = Math.min(Math.min(indexOfTAA, indexOfTGA), indexOfTAG);
        if (minIndex == dna.length()){
            return "";
        }
        return dna.substring(startCodonIndex, minIndex+3);
    }
    
    private StorageResource getAllGenes(String dna){
        StorageResource sr = new StorageResource();
        int startIndex = 0; 
        while(true){
            String gene = findGene(dna, startIndex);
            if (gene == ""){
                break;
            }
            sr.add(gene);
            startIndex = dna.indexOf(gene, startIndex) + gene.length();

        }
        return sr;
    }
    
    private float cgRatio(String dna){
        float countCG = 0;
        int counter = 0;
        while(counter < dna.length()){
            if (dna.substring(counter, counter+1).equals("G") || dna.substring(counter, counter+1).equals("C")){
                countCG += 1;
            }
            counter++;
        }
        return countCG/dna.length();
    }
    

    
    private int countCTG(String dna){
        int occurances = 0;
        int indexOfCTG = dna.indexOf("CTG");
        while (indexOfCTG != -1){
            occurances += 1;
            indexOfCTG = dna.indexOf("CTG", indexOfCTG+3);
        }
        return occurances;
    }
   
    private int countAllGenes(String dna){
        int count = 0;
        int startIndex = 0; 
        while(true){
            String gene = findGene(dna, startIndex);
            if (gene == ""){
                break;
            }
            count += 1;
            startIndex = dna.indexOf(gene, startIndex) + gene.length();

        }
        return count;
    }
    
    
    private void printAllGenes(String dna){
        int startIndex = 0; 
        while(true){
            String gene = findGene(dna, startIndex);
            if (gene == ""){
                break;
            }
            System.out.println(gene);
            startIndex = dna.indexOf(gene, startIndex) + gene.length();

        }
    }
    
    private void processGenes(StorageResource sr){
        int stringsLongerThan9 = 0;
        int stringRationHigherThanPoint35 = 0;
        int lengthOfTheLongestGene = 0;
        for(String s: sr.data()){
            if (s.length() > lengthOfTheLongestGene){
                lengthOfTheLongestGene = s.length();
            }
            if (s.length() > 60){
                System.out.println(s);
                stringsLongerThan9++;
            }
            if(cgRatio(s) > 0.35){
                System.out.println(s);
                stringRationHigherThanPoint35++;
            }
        }
        System.out.println("StringsLongerThan60 " + stringsLongerThan9);
        System.out.println("StringCGRationHigherThan35 "+ stringRationHigherThanPoint35);
        System.out.println("Length of the Longest String " + lengthOfTheLongestGene);
    }
     
    public double testDouble(){
        return ((double) 1)/4;
    }
    
    public void testFindStopCodon(){
        System.out.println(findStopCodon("",0,""));
        System.out.println(findStopCodon("ATGAAABAACATAGATAA",0,"TAA"));
        System.out.println(findStopCodon("AAABATGAACATAGTAA",4,"TAA"));
        System.out.println(findStopCodon("AAABATGAACATAGTAATAGASTGA",4,"TGA"));
    }
    
    public void testFindGene(){
        System.out.println(findGene("ATGAAABAACATAGATAA",0));
        System.out.println(findGene("AAABATGAACAVVTAGTAA",4));
        System.out.println(findGene("AAABATGAACATAGVTAA",4));
        System.out.println(findGene("AAABATGAACATAGTAAAATGA",4));
        System.out.println(findGene("AAABATGAACATAGTAA",4));
    }
    
    public void testPrintAllDNA(){
        System.out.println("Test Printing All DNA");
        //printAllGenes("ATGTAAGATGCCCTAGT");
        printAllGenes("AAAAAAAAAAAAAAAAAAJAJNCAOSNCAONSCOASNCOAISKCNOASNCASICNOASCANOCIANSNCOANSCANOISCINOASOICONASCONIASNCO");
    }
    
    public void countCGRatio(){
        System.out.println(cgRatio("ATGCCATAG"));
    }
    
    
    public void testCountCTG(){
        System.out.println(countCTG("ABCCTGTGFCTG"));
    }
    
    public void testProcessGenes(){
       StorageResource sr = new StorageResource();
       sr.add("HAHSATGAFCKAJSCNKLATAGCKOLAJSNCTAGKLJATGASNCTAA");
       sr.add("ABCABCBA");
       sr.add("CCCCGFF");
       sr.add("CGFDFFF");
       processGenes(sr);
    }
    
    public void testRealDNA(){
       FileResource fr = new FileResource("GRch38dnapart.fa");
       String dna = fr.asString().toUpperCase();
       System.out.println("CTG " + countCTG(dna));
       System.out.println("All Counted Genes " + countAllGenes(dna));
       StorageResource allGenes = getAllGenes(dna);
       processGenes(allGenes);
    }
}
