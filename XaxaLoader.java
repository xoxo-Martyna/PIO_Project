import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class XaxaLoader {
    private BufferedReader reader;

    public XaxaLoader(String filePath) throws IOException {
        reader = new BufferedReader(
            new FileReader(filePath)
        );
    }

    public void close() throws IOException {
        reader.close();
    }

    public String[] fetch() throws IOException {
        String line = reader.readLine();
        if (line == null) return null;
        if (line.startsWith("//")) return fetch();

        return line.split("\\|+");
    }
}