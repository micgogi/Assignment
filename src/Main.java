import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/** @author Micgogi Rahul Gogyani */
public class Main {
  public static void main(String args[]) {
    FastReader sc = new FastReader();
    System.out.println("Enter The No of tweets:");
    HashMap<String, Integer> countMap = new HashMap<>();
    // store the total no of tweets
    int noOfTweets = sc.nextInt();
    while (noOfTweets-- != 0) {
      String tweet = sc.nextLine();
      // getting the list of hashtags
      List<String> hashTagList = getHashtags(tweet);
      // calculating the hashtags
      countHashtags(hashTagList, countMap);
    }
    System.out.println("Top Trending Tweets:");
    // printing the hashtags
    printTrendingHashTags(countMap);
  }

  public static List<String> getHashtags(String tweet) {
    Pattern pattern = Pattern.compile("#(\\S+)");
    Matcher matcher = pattern.matcher(tweet);
    List<String> hashTags = new ArrayList<>();
    while (matcher.find()) {
      hashTags.add(matcher.group(1));
    }
    return hashTags;
  }

  public static void countHashtags(List<String> hashTags, HashMap<String, Integer> countMap) {
    for (String hashTag : hashTags) {
      countMap.putIfAbsent(hashTag, 0);
      countMap.put(hashTag, countMap.get(hashTag) + 1);
    }
  }

  public static void printTrendingHashTags(HashMap<String, Integer> countMap) {
    countMap.entrySet().stream()
        .sorted(
            (firstHashTag, secondHashtag) ->
                secondHashtag.getValue().compareTo(firstHashTag.getValue()))
        .limit(10)
        .forEach(Main::print);
  }

  private static void print(Map.Entry<String, Integer> hashTags) {
    System.out.println(hashTags.getKey());
  }

  static class FastReader {
    BufferedReader br;
    StringTokenizer st;

    public FastReader() {
      br = new BufferedReader(new InputStreamReader(System.in), 32768);
    }

    String next() {
      while (st == null || !st.hasMoreElements()) {
        try {
          st = new StringTokenizer(br.readLine());
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
      return st.nextToken();
    }

    int nextInt() {
      return Integer.parseInt(next());
    }

    long nextLong() {
      return Long.parseLong(next());
    }

    double nextDouble() {
      return Double.parseDouble(next());
    }

    String nextLine() {
      String str = "";
      try {
        str = br.readLine();
      } catch (IOException e) {
        e.printStackTrace();
      }
      return str;
    }
  }
}
