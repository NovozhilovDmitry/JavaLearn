import java.util.stream.Stream;
import java.nio.file.*;
import java.io.IOException;
import java.util.*;


public class WorkWithFiles {

    private Path path;
    private final Set<String> batFiles = new HashSet<>();
    private final Set<String> propertiesFiles = new HashSet<>();
    private final Set<String> jarFiles = new HashSet<>();

    public WorkWithFiles(String p) {
        path = Paths.get(p);
    }

    public void processFiles() {
        try (Stream<Path> stream = Files.walk(path)) {
            stream.filter(Files::isRegularFile)
                    .forEach(this::classifyFileByExtension);
        } catch (IOException e) {
            System.err.println("Ошибка обхода: " + e.getMessage());
        }
    }

    private void classifyFileByExtension(Path filePath) {
        String filename = filePath.getFileName().toString().toLowerCase();
        String absolutePath = filePath.toAbsolutePath().toString();

        if (filename.endsWith(".bat")) {
            batFiles.add(absolutePath);
        } else if (filename.endsWith(".properties")) {
            propertiesFiles.add(absolutePath);
        } else if (filename.endsWith(".jar")) {
            jarFiles.add(absolutePath);
        }
    }

    public Set<String> getBatFiles() {
        return Collections.unmodifiableSet(batFiles);
    }

    public Set<String> getPropertiesFiles() {
        return Collections.unmodifiableSet(propertiesFiles);
    }

    public Set<String> getJarFiles() {
        return Collections.unmodifiableSet(jarFiles);
    }

}
