package com.newsboi.jonathanwesterfield.newsboi;

public class NewsObj
{
    private String status;
    private String numResults;
    private Article[] articles;


    public NewsObj()
    {
        // No args constructor for Gson
    }

    public static class Article
    {
         String author;
         String title;
         String description;
         String url;
         String urlToImage;
         String publishedAt;
         String content;

         public Article()
         {
             // no args constructor for GSON
         }
    }

    public String toString()
    {
        String result = String.format("{\n\tStatus: %s\n\ttotalResults: %s\n\t- articles: [\n",
                status, numResults);

        for (int i = 0; i < articles.length; i++)
        {
            result += String.format("-{\n\t\tauthor: %s,\n\t\ttitle: \"%s\",\n\t\tdescription: \"%s\",\n\t\t",
                articles[i].author, articles[i].title, articles[i].description);
            result += String.format("url: %s,\n\t\turlToImage: %s,\n\t\tpublishedAt: %s,\n\t\t",
                    articles[i].url, articles[i].urlToImage, articles[i].publishedAt);
            result += String.format("content: \"%s\",\n\t\t},\n\t\t", articles[i].content);
        }

        result += "}\n\t]\n}";
        return result;
    }

}
