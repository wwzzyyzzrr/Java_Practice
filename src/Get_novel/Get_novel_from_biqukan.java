package Get_novel;

import org.jsoup.Connection.Response;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.*;
import java.util.Arrays;

public class Get_novel_from_biqukan {
    static String x = "";
    public static Element link_Web(String chapter_Num) throws IOException {
        Document doc = Jsoup.connect("http://www.biqukan.com"+chapter_Num).execute().parse();
        return doc.body().getElementsByClass("content").first();
    }
    public static void get_txt(Element content) throws IOException {
        x += "\n";
        String t= content.getElementsByTag("h1").first().text();
        x += t;
        System.out.println(t);
        x += "\n";
        Elements Eles = content.getElementsByClass("showtxt");
        String temp = Eles.first().html();
        temp=temp.replace("&nbsp;"," ");
        temp=temp.replace("<br>","");
        x += temp.substring(0,temp.length()-151);
        x += "\n\n\n\n\n";
        String next_Chapter = content.getElementsByClass("page_chapter").first()
                .getElementsByTag("li").get(2)
                .select("a").attr("href");
        if((!next_Chapter.equals("/37_37168/")) && next_Chapter.length()>12)
            get_txt(link_Web(next_Chapter));
    }
    public static void main(String[] args) throws IOException {
        get_txt(link_Web("/37_37168/13042056.html"));
        FileOutputStream fileout=new FileOutputStream(new File("src/疯狂神豪玩科技.txt"));
        byte[] bt=x.getBytes();
        fileout.write(bt,0,bt.length);
        fileout.close();
    }
}
