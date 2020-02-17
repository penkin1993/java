package project.save_load_handlers;

import java.io.*;
import java.util.zip.GZIPInputStream;

public class ZipDumpHandler {
    private boolean isZip;
    private String fileNamePrefix;
    private String rootFolder;

    public ZipDumpHandler(boolean isZip, String fileNamePrefix, String rootFolder) {
        this.isZip = isZip;
        this.fileNamePrefix = fileNamePrefix;
        this.rootFolder = rootFolder;
    }

    public void dump(Object object) throws IOException {
        // TODO: Добавить полный путь
        if (this.isZip) {

        } else {
            String filename = this.fileNamePrefix;
            FileOutputStream file = new FileOutputStream(filename);
            ObjectOutputStream out = new ObjectOutputStream(file);
            out.writeObject(object);
            out.close();
            file.close();
        }

    }

    public Object load() throws IOException, ClassNotFoundException {
        Object object;
        ObjectInputStream in;
        String pathFile = this.rootFolder + this.fileNamePrefix;

        if (this.isZip) {
            in = new ObjectInputStream(new GZIPInputStream(new FileInputStream(new File(pathFile))));
        } else {
            in = new ObjectInputStream(new FileInputStream(pathFile));
        }
        object = in.readObject();
        in.close();

        return object;
    }
}
