import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Main {

    public static void main(String[] args) {

        GameProgress game1 = new GameProgress(23, 75, 9, 152.2);
        GameProgress game2 = new GameProgress(28, 123, 15, 253.8);
        GameProgress game3 = new GameProgress(39, 214, 25, 432.4);
        saveGame("C://Games1//savegames//game1.dat", game1);
        saveGame("C://Games1//savegames//game2.dat", game2);
        saveGame("C://Games1//savegames//game3.dat", game3);

        ArrayList<String> saveList = new ArrayList();
        saveList.add("D://Games1//savegames//game1.dat");
        saveList.add("D://Games1//savegames//game2.dat");
        saveList.add("D://Games1//savegames//game3.dat");

        zipFiles("D://Games1//savegames//zip.zip", saveList);

        File game1Dat = new File("D://Games1//savegames//game1.dat");
        File game2Dat = new File("D://Games1//savegames//game2.dat");
        File game3Dat = new File("D://Games1//savegames//game3.dat");
        if (game1Dat.delete()) System.out.println("Файл \"game1.dat\" удален");
        if (game2Dat.delete()) System.out.println("Файл \"game2.dat\" удален");
        if (game3Dat.delete()) System.out.println("Файл \"game3.dat\" удален");

    }

    private static void saveGame(String path, GameProgress game) {
        try (FileOutputStream fos = new FileOutputStream(path);
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(game);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    private static void zipFiles(String path, List<String> saveList) {
        try (ZipOutputStream zout = new ZipOutputStream(new FileOutputStream(path))) {
            for (String save : saveList) {
                try (FileInputStream fis = new FileInputStream(save)) {
                    ZipEntry entry = new ZipEntry(save);
                    zout.putNextEntry(entry);
                    byte[] buffer = new byte[fis.available()]; fis.read(buffer);
                    while (fis.available() > 0) {
                        zout.write(fis.read());
                    }
                    zout.closeEntry();
                } catch (Exception ex) {
                    System.out.println(ex.getMessage());
                }
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

    }
}