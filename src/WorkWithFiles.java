import java.io.File;
import java.util.HashSet;
import java.util.Set;

public class WorkWithFiles {

    private File path;
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

    public Set<String> listDirectoryContents() {
        File[] files = this.path.listFiles();
        if (files != null) {
            for (File file: files) {
                if (file.isDirectory()) {
                    this.dirs.add(file.toString());
                }
            }
        }
        return this.dirs;
    }

    public void clearListDirs() {
        this.dirs.clear();
    }

    public Set<String> getListDirs() {
        return new HashSet<>(this.dirs);
    }

    public File getCurrentDir() {
        return new File(this.path.getPath());
    }

    public File modifyCurrentDir(String newPath) {
        this.path = new File(newPath);
        return this.path;
    }

}
