package project.save_load_handlers;

import java.io.*;

public class ZipDumpHandler {
    private boolean isZip;
    private String fileNamePrefix;
    private  String rootFolder;

    public ZipDumpHandler(boolean isZip, String fileNamePrefix, String rootFolder){
        this.isZip = isZip;
        this.fileNamePrefix = fileNamePrefix;
        this.rootFolder = rootFolder;
    }

    public void dump(Object object) throws IOException {
        // TODO: Добавить полный путь
        String filename = this.fileNamePrefix;





        FileOutputStream file = new FileOutputStream(filename);
        ObjectOutputStream out = new ObjectOutputStream(file);



        // TODO: Добавиь сериализацию объекта
        out.writeObject(object);
        out.close();
        file.close();



    }

    public Object load() throws IOException, ClassNotFoundException {
        String filename = this.fileNamePrefix;

        FileInputStream file = new FileInputStream(filename);
        ObjectInputStream in = new ObjectInputStream(file);

        // Method for deserialization of object
        Object object = in.readObject();

        in.close();
        file.close();

        return object;
    }
}
