import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.RecursiveTask;
import java.util.stream.Collectors;

public class SiteTree extends RecursiveTask<List<String>> {
    private final List<String> urls;
    private final int timeoutMs;
    private final int treshhold;

    public SiteTree(List<String> urls, int treshhold, int timeoutMs) {
        this.urls = urls;
        this.timeoutMs = timeoutMs;
        this.treshhold = treshhold;
    }

    public boolean isSubURL(String URL, String subURL) {
        String regex = URL + "[-a-zA-Z0-9()@:%_\\+.~#?&//=]+/";
        return subURL.matches(regex) && subURL.startsWith(URL)
                && !subURL.equals(URL);
    }

    private List<String> computeDirectly(List<String> urlList) {
        List<String> result = new ArrayList<>();
        try {
            for (String url : urlList) {
                Thread.sleep(timeoutMs);
                result.add(url);
                System.out.println(url);
                Document document = Jsoup.connect(url).get();
                Elements elements = document.select("a");
                List<String> subURL = elements.stream()
                        .map(e -> e.absUrl("href"))
                        .distinct()
                        .filter(s -> isSubURL(url, s))
                        .collect(Collectors.toList());
                if (subURL.isEmpty()) {
                    continue;
                }

                if (subURL.size() <= treshhold) {
                    result.addAll(computeDirectly(subURL));
                    continue;
                }

                SiteTree newTask = new SiteTree(subURL, treshhold, timeoutMs);
                newTask.fork();
                result.addAll(newTask.join());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    protected List<String> compute() {
        int len = urls.size();
        if (len <= treshhold) {
            return computeDirectly(urls);
        }

        List<String> first = new ArrayList<>(urls.subList(0, len / 2));
        List<String> second = new ArrayList<>(urls.subList(len / 2 + 1, len));

        SiteTree firstHalfTask = new SiteTree(first, treshhold, timeoutMs);
        SiteTree secondHalfTask = new SiteTree(second, treshhold, timeoutMs);
        firstHalfTask.fork();
        secondHalfTask.fork();
        List<String> result = new ArrayList<>();
        result.addAll(firstHalfTask.join());
        result.addAll(secondHalfTask.join());
        return result;
    }
}
