package project.save_load_handlers;

import java.util.List;

public class ZipDumpHandler {
    private boolean isZip;
    private String fileNamePrefix;
    private  String rootFolder;

    public ZipDumpHandler(boolean isZip, String fileNamePrefix, String rootFolder){
        this.isZip = isZip;
        this.fileNamePrefix = fileNamePrefix;
        this.rootFolder = rootFolder;
    }

    public void dump(Object object){
        // TODO: проверка fileNamePrefix


    }

    public Object load(){
        // TODO: проверка fileNamePrefix ???



        return null;
    }
}
