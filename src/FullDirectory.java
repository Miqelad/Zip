import java.io.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class FullDirectory {
    //заведем ее финальную, что бы на всякий не архивировался созданный архив
    public static final String FILE = "F:\\Work\\Java\\test1.zip";
    public static void main(String[] args) throws IOException {
        ZipOutputStream zos =new ZipOutputStream(new FileOutputStream(FILE));
        //передаем поток, передаем список файлов, начальный каталог, с которого будут создаваться все сущности
        createZipDir(zos,new File("F:\\Work\\Java\\test").listFiles(),"");
    }

    private static void createZipDir(ZipOutputStream zos, File[] files, String path) throws IOException {
        for(File f: files){
            if(f.isDirectory()){
                //для погружения внутрь других директорий и рекурсивного вызова данного метода
                createZipDir(zos,f.listFiles(),path+f.getName()+ File.pathSeparator);
                //проверяем чторбы не попал файл
            }else if(!f.getName().equals(FILE)) {
                //создаем файл внутри архива с названием
                ZipEntry ze = new ZipEntry(path + f.getName());
                //добавляем в архив
                zos.putNextEntry(ze);
                //прочитать файл
                FileInputStream fis = new FileInputStream(f);
                //читаем байтами
                byte[] buffer = new byte[1024];
                int size= -1;
                //читаю из файла в буффер, и помещаю в переменную size
                while((size= fis.read(buffer))!=-1){
                    zos.write(buffer,0,size);
                }
            }

        }
        zos.closeEntry();
    }
}
