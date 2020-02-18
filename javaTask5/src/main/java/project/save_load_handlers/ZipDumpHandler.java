package project.save_load_handlers;

import java.io.*;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

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
        ObjectOutputStream out;
        String pathFile = this.rootFolder + this.fileNamePrefix;
        if (this.isZip) {
            out = new ObjectOutputStream(new GZIPOutputStream(new FileOutputStream(new File(pathFile))));
            out.writeObject(object);
            out.flush();

        } else {
            out = new ObjectOutputStream(new FileOutputStream(pathFile));
            out.writeObject(object);
        }
        out.close();
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
