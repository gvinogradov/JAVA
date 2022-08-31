import org.imgscalr.Scalr;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;

public class ImageResizer implements Runnable{
    private File[] files;
    private int newWidth;
    private String dstFolder;
    private long start;

    public ImageResizer(File[] files, int newWidth, String dstFolder, long start) {
        this.files = files;
        this.newWidth = newWidth;
        this.dstFolder = dstFolder;
        this.start = start;
    }

    @Override
    public void run() {
        try {
            for (File file : files) {
                if (file.isDirectory()) {
                    continue;
                }
                BufferedImage srcImage = ImageIO.read(file);
                if (srcImage == null) {
                    continue;
                }
                File newFile = new File(dstFolder + "/" + file.getName());
                BufferedImage scaledImage = Scalr.resize(srcImage, newWidth);
                ImageIO.write(scaledImage, "jpg", newFile);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        System.out.println("Duration: " + (System.currentTimeMillis() - start));
    }

}
