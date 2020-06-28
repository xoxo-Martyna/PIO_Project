import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class XaxaLoader {
    private BufferedReader reader;
    private List<String[]> items;

    public XaxaLoader( String filePath ) throws IOException {
        reader = new BufferedReader(
            new FileReader( filePath )
        );
        items = new ArrayList<String[]>();

        loadItems();
    }

    private void loadItems() {
        try {
            while( true ) {
                String[] itemInfo = fetch();
                if ( itemInfo == null ) break;

                items.add( itemInfo );
            }

            reader.close();
        } catch ( IOException e ) {
            System.out.println( "No items loaded!" );
        }
    }

    public void close() throws IOException {
        reader.close();
    }

    public String[] fetch() throws IOException {
        String line = reader.readLine();
        if ( line == null ) return null;
        if ( line.startsWith( "//" ) ) return fetch();

        return line.split( "\\|+" );
    }

    public String[] get( String id ) {
        for ( String[] item : items ) {
            if ( item[0].equals( id ) )
                return item;
        }

        return null;
    }
}