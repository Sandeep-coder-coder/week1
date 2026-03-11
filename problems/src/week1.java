import java.util.*;
public class week1 {
    static HashMap<String, Set<String>> ngramIndex = new HashMap<>();
    static int N = 3;
    public static List<String> getNgrams(String text) {
        String[] words = text.split(" ");
        List<String> ngrams = new ArrayList<>();
        for (int i = 0; i <= words.length - N; i++) {
            String gram = "";
            for (int j = 0; j < N; j++) {
                gram += words[i + j] + " ";
            }
            ngrams.add(gram.trim());
        }
        return ngrams;
    }
    public static void addDocument(String docId, String text) {
        List<String> ngrams = getNgrams(text);
        for (String gram : ngrams) {
            ngramIndex.putIfAbsent(gram, new HashSet<>());
            ngramIndex.get(gram).add(docId);
        }
    }
    public static void analyzeDocument(String docId, String text) {
        List<String> ngrams = getNgrams(text);
        HashMap<String, Integer> matchCount = new HashMap<>();
        for (String gram : ngrams) {
            if (ngramIndex.containsKey(gram)) {
                for (String doc : ngramIndex.get(gram)) {
                    if (!doc.equals(docId)) {
                        matchCount.put(doc, matchCount.getOrDefault(doc, 0) + 1);
                    }
                }
            }
        }
        System.out.println("Extracted " + ngrams.size() + " n-grams");
        for (String doc : matchCount.keySet()) {
            int matches = matchCount.get(doc);
            double similarity = (matches * 100.0) / ngrams.size();
            System.out.println("Found " + matches + " matching n-grams with " + doc);
            System.out.println("Similarity: " + similarity + "%");
            if (similarity > 50) {
                System.out.println("PLAGIARISM DETECTED");
            }
        }
    }
    public static void main(String[] args) {
        String essay1 = "data structures and algorithms are important for computer science";
        String essay2 = "data structures and algorithms are very important in programming";
        addDocument("essay_089.txt", essay1);
        analyzeDocument("essay_123.txt", essay2);
    }
}