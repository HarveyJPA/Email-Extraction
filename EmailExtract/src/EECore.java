import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.regex.*;
import java.util.HashMap;

public class EECore {

    public static void main(String[] args) {
        System.out.println(filePrinterAndCounterMatcher());
    }

    private static HashMap<String, Integer> filePrinterAndCounterMatcher() {
        HashMap<String, Integer> emails = new HashMap<>();
        Pattern emailAddressPattern = Pattern.compile("(?<name>[a-z-._])+@(?<domain>[a-z0-9_.-]+[a-z])", Pattern.CASE_INSENSITIVE);
        try {
            FileReader reader = new FileReader("/Users/hparkera/ghstuff/email-extraction/sample.txt");
            BufferedReader bufferedReader = new BufferedReader(reader);
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                Matcher countEmailMatcher = emailAddressPattern.matcher(line);
                while (countEmailMatcher.find()) {
                    String temp = countEmailMatcher.group("domain");
                    emails.putIfAbsent(temp, 0);
                    emails.put(countEmailMatcher.group("domain"), (emails.get(temp)) + 1);
                }
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return emails;
    }
}

