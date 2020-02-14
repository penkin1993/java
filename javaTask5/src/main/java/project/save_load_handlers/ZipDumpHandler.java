package project.save_load_handlers;

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


    }

    public Object load(){



        return null;
    }
}
