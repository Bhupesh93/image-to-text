package Helpers;

import java.io.*;

/**
 * Created by  Boobesh S  on 7/11/2016.
 */
public class FileHelper {

    public static boolean saveFileToLocation(InputStream fileInputStream, String pathToSave) throws IOException {

        OutputStream fileOutputStream = null;
        byte[] bytes = new byte[1024];
        try {
            fileOutputStream = new FileOutputStream(new File(pathToSave));
            while (fileInputStream.read(bytes) != -1) {
                fileOutputStream.write(bytes);
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return false;
        } finally {
            fileInputStream.close();
            fileOutputStream.close();
        }
        return true;
    }

}

