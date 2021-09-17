import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.regex.*;

public class EECore {
    public static void main(String[] args) {
        filePrinter();
    }

    private static void filePrinter() {
        try {
            FileReader reader = new FileReader("/Users/hparkera/ghstuff/email-extraction/sample.txt");
            BufferedReader bufferedReader = new BufferedReader(reader);
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                int count = 0;
                System.out.println(line);
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

