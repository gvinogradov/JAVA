import java.io.File;

public class Main {

    private static final int CORES = Runtime.getRuntime().availableProcessors();
    private static final int NEW_WIDTH = 600;
    private static final String SRC_FOLDER = "d:/_src";
    private static final String DST_FOLDER = "d:/_dst";

    public static void main(String[] args) {

        File srcDir = new File(SRC_FOLDER);
        File[] files = srcDir.listFiles();

        long start = System.currentTimeMillis();

        for (int i = 0; i < CORES; i++) {
            int length = i  < CORES - 1 ? files.length / CORES :
                    files.length / CORES + files.length % CORES;
            File[] oneThreadFiles = new File[length];
            System.arraycopy(files, i * files.length / CORES, oneThreadFiles, 0, length);
            ImageResizer oneThreadResizer = new ImageResizer(oneThreadFiles, NEW_WIDTH, DST_FOLDER, start);
            new Thread(oneThreadResizer).start();
        }
    }
}
