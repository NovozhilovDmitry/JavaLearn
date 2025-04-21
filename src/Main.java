public class Main {
    public static void main(String[] args) {

        String path = null;
        int sec = 0;

        for (int i = 0; i < args.length; i++) {
            String arg = args[i];
            switch (arg) {
                case "-path":
                    path = args[0];
            }
        }

        if (path != null) {
            WorkWithFiles fileClasses = new WorkWithFiles("D:\\Учеба\\Java");
            fileClasses.listDirectoryContents();
            System.out.println(fileClasses.returnListDirs());
        }
        else {
            System.err.println("Не найден путь к каталогу");
        }

        // запускаем цикл while
        // запускаем скан каталога и записываем в лист все каталоги внутри
        // засыпаем на ? секунд
        // после ? секунд повторяем
        // если появилась новая сборка, то вызывать уведомление windows
        // подумать как нормально останавливать процесс
     }
}