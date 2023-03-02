import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);
        System.out.print("Введите произвольную строку: ");
        String inputString = in.nextLine() + " ";

        List<String> words = Stream.of(inputString.split("\\s"))
                .map(String::new)
                .toList();

        Map<String, Integer> countedWords = words.stream()
                .filter(word -> word.length() > 1)
                .collect(Collectors.toMap(key -> key, value -> 1, Integer::sum));

        Map<String, Integer> sortedWords = countedWords.entrySet().stream()
                .sorted((word1, word2) -> {
                    int compareByFrequencies = word2.getValue().compareTo(word1.getValue());
                    if (compareByFrequencies != 0) return compareByFrequencies;
                    return word1.getKey().compareTo(word2.getKey());
                })
                .limit(10)
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));

        System.out.println("\nThere are " + countedWords.size() + " words in the input text\n" + "TOP10:");

        sortedWords.forEach((key, value) -> System.out.println(value + "-" + key));


    }
}