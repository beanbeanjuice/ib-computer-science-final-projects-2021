package file;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * A {@link FileHandler} used for retrieving files from the resource folder.
 */
public class FileHandler {

    /**
     * Get the file from the Resource folder.
     * @param fileName The file name for the resource.
     * @return Returns a {@link File} object.
     */
    public File getResourceAsFile(String fileName) {
        File file = new File(fileName);
        System.out.println(file.getAbsolutePath());
        return file;
    }
}
