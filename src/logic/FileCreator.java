package logic;

import java.io.File;
import java.io.IOException;

public class FileCreator {
    public static void createFile(File file) {

        boolean fileExist = file.exists();
        if (!fileExist) {
            try {
                fileExist = file.createNewFile();
            } catch (IOException e) {
                System.err.println("Nie udało się utworzyć pliku");
            }
        } else {
            System.out.println("Plik już istnieje");
        }
    }
}