package com.statefarm.codingcomp.reader;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URI;
import java.net.URL;

public class Reader {

    /**
     * This method will read in a given file on a given node and return the
     * contents of the file as a String array. Each entry in the array
     * represents a single line in the file which is a comma separated
     * representation of either an Email object or a User object.
     * 
     * @param nodeNumber
     * @param filename
     * @return
     * @throws Exception
     */

    public String[] read( int nodeNumber, String filename ) throws Exception {
        URL url = this.getClass().getResource( "/node" + nodeNumber + "/" + filename );
        URI uri = new URI( url.toString() );
        File file = new File( uri.getPath() );
        FileInputStream fis = new FileInputStream( file );
        byte[] data = new byte[(int) file.length()];
        try {
            fis.read( data );
        } catch ( IOException e ) {
            e.printStackTrace();
        } finally {
            if ( fis != null ) {
                fis.close();
            }
        }
        return new String( data, "UTF-8" ).split( "\n" );
    }
}
