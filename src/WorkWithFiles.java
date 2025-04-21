import java.io.File;
import java.util.HashSet;
import java.util.Set;

public class WorkWithFiles {

    private final File path;
    private final Set<String> dirs = new HashSet<>();

    public WorkWithFiles(String p) {
        path = new File(p);
    }

    public boolean isDirectory() {
        if (this.path.exists()) {
            return this.path.isDirectory();
        }
        else {
            return false;
        }
    }

    public void listDirectoryContents() {
        File[] files = this.path.listFiles();
        if (files != null) {
            for (File file: files) {
                if (file.isDirectory()) {
                    this.dirs.add(file.toString());
                }
            }
        }
    }

    public void clearListDirs() {
        this.dirs.clear();
    }

    public Set<String> returnListDirs() {
        return this.dirs;
    }

}
