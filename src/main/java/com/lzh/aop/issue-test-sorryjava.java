import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

public class AmazonScraper {
    public static void searchProduct(String accountID, String accountName, String searchTerm) {
        try {
            // Construct the search URL based on the search term
            String searchUrl = "https://www.amazon.com/s?k=" + searchTerm;

            // Send a GET request to the search URL and get the response
            Document document = Jsoup.connect(searchUrl).get();

            // Extract the product details from the search results
            Elements results = document.select("div[data-component-type='s-search-result']");
            for (Element result : results) {
                // Extract product title, price, and URL
                String title = result.select("span.a-text-normal").text();
                String price = result.select("span.a-offscreen").text();
                String url = "https://www.amazon.com" + result.select("a.a-link-normal").attr("href");

                // Print the product details
                System.out.println("Title: " + title);
                System.out.println("Price: " + price);
                System.out.println("URL: " + url);
                System.out.println("-----------------------------------");
            }
        } catch (IOException e) {
            System.out.println("An error occurred while searching for the product: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        String accountID = "123456";
        String accountName = "Example Account";
        String searchTerm = "laptop";

        searchProduct(accountID, accountName, searchTerm);
    }
}
