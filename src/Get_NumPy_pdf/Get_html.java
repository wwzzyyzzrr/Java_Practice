package Get_NumPy_pdf;
import org.jsoup.Connection;
import org.jsoup.Connection.Response;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.*;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;
public class Get_html {
    public static String[] get_web_list() throws IOException {
        Response rs = Jsoup.connect("https://www.yiibai.com/numpy/").execute();
        Document doc= rs.parse();
        Elements Eles= doc.getElementsByClass("pagemenu").first().getElementsByTag("li");
        String[] list= new String[Eles.size()-1];
        for(int i=1;i<Eles.size();i++){
            String temp= Eles.get(i).select("a").attr("href");
            list[i-1]= temp;
        }
        return list;
    }

    public static String get_html_doc( String url ) throws IOException {
        Response rs = Jsoup.connect(url).execute();
        Document doc = rs.parse();
        Element Ele = doc.body().getElementsByClass("article-content").first();
        Ele.getElementsByTag("input").first().remove();
        Ele.getElementsByTag("input").first().remove();
        Ele.getElementsByTag("ul").last().remove();
        Ele.getElementsByTag("blockquote").last().remove();
        Ele.getElementsByTag("h4").last().remove();
        Ele.getElementsByTag("div").last().remove();
        Ele.getElementsByTag("div").last().remove();
        Ele.getElementsByTag("p").last().remove();
        for(Element E:Ele.getElementsByTag("ins"))
            E.remove();
        for(Element E:Ele.getElementsByTag("script"))
            E.remove();
        return Ele.toString();
    }

    public static void main(String[] args) throws IOException {
        String[] list = get_web_list();
        String x="<html>" +
                "<head><meta charset=\"utf-8\"></meta></head>" +
                "<body>" +
                "<p><center><font style=\"微软雅黑\" size=\"64\">Numpy 教程</font></center></p>";
        for(String url : list){
            x+=get_html_doc(url);
        }
        x+="</body></html>";
        File file;
        FileOutputStream fileout=new FileOutputStream(new File("src/Numpy.html"));
        byte[] bt=x.getBytes();
        fileout.write(bt,0,bt.length);
        fileout.close();
    }
}
