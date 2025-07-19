import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;


public class Main {
    public static void main(String[] args) throws IOException {

        String path = null;
        int sec = 0;

        for (int i = 0; i < args.length; i++) {
            String arg = args[i];
            switch (arg) {
                case "-path":
                    path = args[++i];
                    break;

                case "-sec":
                    sec = Integer.parseInt(args[++i]);
                    break;

                default:
                    System.err.printf("Неизвестный параметр: %s%n", arg);
                    return;
            }
        }

        if (path != null & sec != 0) {
            System.out.println(path);
            System.out.println(sec);
            Files.walk(Paths.get(path)).forEach(System.out::println);
        }
        else {
            System.out.println("Ошибка");
        }
     }
}