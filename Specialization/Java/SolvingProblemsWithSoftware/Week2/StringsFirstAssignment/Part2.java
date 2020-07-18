
/**
 * Write a description of Part2 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Part2 {
public String findGene(String dna, String startCodon, String stopCodon){
        String gene = "";
        int indexOfStartCodon = dna.indexOf(startCodon);
        if (indexOfStartCodon == -1){
            return "";
        }
        int indexOfStopCodon = dna.indexOf(stopCodon, indexOfStartCodon);
        if (indexOfStopCodon == -1){
            return "";
        }
        gene = dna.substring(indexOfStartCodon, indexOfStopCodon+3);
        if ((gene.length())%3 != 0){
            return "";
        }
        return gene;
    }
    
    public void testSimpleGene(){
        String dna1 = "AAAATTAA";
        System.out.println("Result for" + dna1 + " " + "is" + findGene(dna1,"ATG","TAA"));
        String dna2 = "ATGAAAAATG";
        System.out.println("Result for" + dna2 + " " + "is" + findGene(dna2,"ATG","TAA"));
        String dna3 = "AAAAAAAABBBBCCCC";
        System.out.println("Result for" + dna3 + " " + "is" + findGene(dna3,"ATG","TAA"));
        String dna4 = "ATGTAA";
        System.out.println("Result for" + dna4 + " " + "is" + findGene(dna4,"ATG","TAA"));
        String dna5 = "ATGAATAA";
        System.out.println("Result for" + dna5 + " " + "is" + findGene(dna5,"ATG","TAA"));
     }
    
}
