import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class OneFile {
    public static void main(String[] args) {
        //запись архивов
        //конструктор должен принимать поток вывода файл, путь по которому хотим создать архив
        try (ZipOutputStream zout= new ZipOutputStream(new FileOutputStream("F:\\Work\\Java\\test.zip"));){
            //создаем файл внутри архива с названием
            ZipEntry zipEntry = new ZipEntry("122.txt");
            //чтобы добавить файл в архив
            zout.putNextEntry(zipEntry);
            //Файл будет пустой, нам нужно разложить файл по байтам
            FileInputStream fis = new FileInputStream("F:\\Work\\Java\\test\\1.txt");
            //считываем в буффер данные
            byte[] buffer= new byte[fis.available()];
            //считываем с помощью read
            fis.read(buffer);
            //добавляем в архив
            zout.write(buffer);
            //закрываем поток, чтобы осуществлять новую запись
            zout.closeEntry();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
