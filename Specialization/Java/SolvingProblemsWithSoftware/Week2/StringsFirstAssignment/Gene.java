
/**
 * Write a description of part1 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Gene { 
    public String findGene(String dna){
        String gene = "";
        int indexOfATG = dna.indexOf("ATG");
        if (indexOfATG == -1){
            return "";
        }
        int indexOfTAA = dna.indexOf("TAA", indexOfATG);
        if (indexOfTAA == -1){
            return "";
        }
        gene = dna.substring(indexOfATG, indexOfTAA+3);
        if ((gene.length())%3 != 0){
            return "";
        }
        return gene;
    }
    
    public void testSimpleGene(){
        String dna1 = "AAAATTAA";
        System.out.println("Result for" + dna1 + " " + "is" + findGene(dna1));
        String dna2 = "ATGAAAAATG";
        System.out.println("Result for" + dna2 + " " + "is" + findGene(dna2));
        String dna3 = "AAAAAAAABBBBCCCC";
        System.out.println("Result for" + dna3 + " " + "is" + findGene(dna3));
        String dna4 = "ATGTAA";
        System.out.println("Result for" + dna4 + " " + "is" + findGene(dna4));
        String dna5 = "ATGAATAA";
        System.out.println("Result for" + dna5 + " " + "is" + findGene(dna5));
     }
    
    public static void main(String[] args){
        Gene gene = new Gene();
        gene.testSimpleGene();
    }
}
