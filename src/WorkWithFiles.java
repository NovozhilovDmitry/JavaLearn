import java.nio.file.*;
import java.io.IOException;


public class WorkWithFiles {

    private Path path;

    public WorkWithFiles(String p) {
        path = Paths.get(p);
    }

    public void printDirectories() {
        try (DirectoryStream<Path> stream = Files.newDirectoryStream(path)) {
            for (Path entry : stream) {
                System.out.println(entry.getFileName());
            }
        } catch (IOException e) {
            System.out.println("Ошибка при чтении каталога: " + e.getMessage());
        }
    }

}
