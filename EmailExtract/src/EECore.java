import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.regex.*;

public class EECore {
    public static void main(String[] args) {
        System.out.println(filePrinterAndCounterMatcher());
    }

    private static int filePrinterAndCounterMatcher() {
        int count = 0;
        try {
            FileReader reader = new FileReader("/Users/hparkera/ghstuff/email-extraction/sample.txt");
            BufferedReader bufferedReader = new BufferedReader(reader);
            String line;
            Pattern emailAddressPattern = Pattern.compile("(([a-z0-9_.]+)@softwire.com) ");
            while ((line = bufferedReader.readLine()) != null) {
                Matcher countEmailMatcher = emailAddressPattern.matcher(line);
                while (countEmailMatcher.find()) {
                    System.out.println(countEmailMatcher.group());
                    count++;
                }
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return count;
    }
}

