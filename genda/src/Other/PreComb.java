package Other;

public class PreComb {
    public static void main(String[] args) {
        PreComb p = new PreComb();
    	p.run();
    }
    public void run(){
    	preCombAndCount(preCombination());
    }
    private String[] preCombination() {

        String[] firstLetter = new String[]{"b", "c", "d", "f", "g", "h", "j", "k", "l", "m", "n", "p", "q", "r", "s", "t", "v", "w", "x", "y", "z",};
        String[] secondLetter = new String[]{"a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z"};
        int sum = firstLetter.length * secondLetter.length;
        String[] comb = new String[sum];

        int combIndex = 0;
        for (int i = 0; i < firstLetter.length; i++) {
            for (int j = 0; j < secondLetter.length; j++) {
                comb[combIndex] = firstLetter[i] + secondLetter[j];
                combIndex += 1;
            }
        }
        return comb;
    }
    private String[][] preCombAndCount(String[] preComb) {
        System.out.println("==========开始==========");
        int combLength = preComb.length;
        String[][] preCombAndCount = new String[combLength][2];
        for (int i = 0; i < preCombAndCount.length; i++) {
            preCombAndCount[i][0] = preComb[i];
            preCombAndCount[i][1] = "0";
            System.out.println(preCombAndCount[i][0]+"出现了"+ preCombAndCount[i][1]);
        }
        System.out.println("==========结束==========");
        return preCombAndCount;
    }
}
