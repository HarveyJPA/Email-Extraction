import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.regex.*;
import java.util.HashMap;
import java.util.Map;

public class EECore {

    public static void main(String[] args) {
        sortEmails(filePrinterAndCounterMatcher());
    }

    private static Map<String, Integer> filePrinterAndCounterMatcher() {
        Map<String, Integer> emails = new HashMap<>();
        Pattern emailAddressPattern = Pattern.compile("(?<name>[a-z-._])+@(?<domain>[a-z0-9_.-]+[a-z])", Pattern.CASE_INSENSITIVE);
        try {
            FileReader reader = new FileReader("/Users/hparkera/ghstuff/email-extraction/sample.txt");
            BufferedReader bufferedReader = new BufferedReader(reader);
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                Matcher countEmailMatcher = emailAddressPattern.matcher(line);
                while (countEmailMatcher.find()) {
                    String domainName = countEmailMatcher.group("domain");
                    emails.putIfAbsent(domainName, 0);
                    emails.put(countEmailMatcher.group("domain"), (emails.get(domainName)) + 1);
                }
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return emails;
    }

    private static void sortEmails(Map<String, Integer> emails) {
        Map<String, Integer> sortedEmails = new LinkedHashMap<>();
        emails.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .forEachOrdered(x -> sortedEmails.put(x.getKey(), x.getValue()));
        int count = 0;
        for (String key : sortedEmails.keySet()) {
            count++;
            if (count <= 10) {
                System.out.println("# " + count + " " + key + " appears " + sortedEmails.get(key) + " times ");
            }
        }
    }
}

