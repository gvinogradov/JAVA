import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.concurrent.ForkJoinPool;

public class Main {
    private static final String URL = "https://skillbox.ru/";
    private static final String PATH = "src/main/resources/map.txt";
    private static final int TIMEOUT = 150;     //ms
    private static final int TRESHHOLD = 10;

    public static void main(String[] args) {
        SiteTree siteTree = new SiteTree(List.of(URL), TRESHHOLD, TIMEOUT);
        ForkJoinPool pool = new ForkJoinPool(10);
        List<String> urls = pool.invoke(siteTree);

        for (int i = 0; i < urls.size(); i++) {
            String str = urls.get(i);
            int slashCount = str.length() - str.replace("/", "").length() - 3;
            str = "\t".repeat(slashCount) + str;
            urls.set(i, str);
        }

        saveSiteMap(PATH, urls);
    }

    public static void saveSiteMap(String path, List<String> urls) {
        try {
            Files.write(Path.of(path), urls);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
