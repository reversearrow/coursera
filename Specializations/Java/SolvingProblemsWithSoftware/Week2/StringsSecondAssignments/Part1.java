
/**
 * Write a description of Part1 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Part1 {
    private int findStopCodon(String dna, int startIndex, String stopCodon){
        int indexOfStopCodon = dna.indexOf(stopCodon, startIndex+3);
        if(indexOfStopCodon == -1 || (indexOfStopCodon-startIndex+3) % 3 != 0 ){
            return dna.length();
        }
        return indexOfStopCodon;
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
    
    public void testFindStopCodon(){
        System.out.println(findStopCodon("",0,""));
        System.out.println(findStopCodon("ATGHAHSFCKAJSCNKLASJNCKOLAJSNCKLJASNCTAA",0,"TAA"));
        System.out.println(findStopCodon("HAHSATGAFCKAJSCNKATAGCKOLAJSNCKLJASNCTAA",4,"TAG"));
        System.out.println(findStopCodon("HAHSATGAFCKAJSCNKLATAGCKOLAJSNCKLJASNCTAA",4,"TAG"));
    }
    
    public void testFindGene(){
        System.out.println(findGene("ATGHAHSFCKAJSCNKLASJNCKOLAJSNCKLJASNCTAA",0));
        System.out.println(findGene("HAHSATGAFCKAJSCNKATAGCKOLAJSNCKLJASNCTAA",0));
        System.out.println(findGene("HAHSATGAFCKAJSCNKLATAGCKOLAJSNCKLJASNCTAA",0));
        System.out.println(findGene("HAHSATGAFCKAJSCNKLATAGCKOLAJSNCTAGKLJATGASNCTAA",0));
        System.out.println(findGene("AJONSCOASJKNCAJKOCNASOCJKASNC",0));
    }
    
    
    
}
