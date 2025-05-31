import java.util.Collections;

public class Main {
    public static void main(String[] args) {

        String path = null;

        for (int i = 0; i < args.length; i++) {
            String arg = args[i];
            switch (arg) {
                case "-path":
                    path = args[++i];
                    break;

                default:
                    System.err.printf("Неизвестный параметр: %s%n", arg);
                    return;
            }
        }

        if (path != null) {
            var files_worker = new WorkWithFiles(path);

            files_worker.processFiles();
            var bat = files_worker.getBatFiles();
            var prop = files_worker.getPropertiesFiles();
            var jar = files_worker.getJarFiles();

        }

     }
}