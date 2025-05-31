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
            WorkWithFiles fileClasses = new WorkWithFiles(path);
            var directories = fileClasses.listDirectoryContents();
            System.out.println(directories);
        }
        else {
            System.err.println("Не найден путь к каталогу");
        }

     }
}