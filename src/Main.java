import java.io.FileOutputStream;
import java.io.ObjectOutputStream;

public class Main {

    public static void main(String[] args) {

        GameProgress game1 = new GameProgress(23, 75, 9, 152.2);
        GameProgress game2 = new GameProgress(28, 123, 15, 253.8);
        GameProgress game3 = new GameProgress(39, 214, 25, 432.4);
        saveGame("C://Games//savegames//game1.dat", game1);
        saveGame("C://Games//savegames//game2.dat", game2);
        saveGame("C://Games//savegames//game3.dat", game3);


    }

    private static void saveGame(String path, GameProgress game) {
        try (FileOutputStream fos = new FileOutputStream(path);
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(game);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
}