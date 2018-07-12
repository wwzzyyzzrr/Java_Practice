package Get_pdf_from_yiiBai;
import org.jsoup.Connection.Response;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.*;
import java.util.Arrays;

public class Get_html {
    private static String html;
    private static void root(String url) throws IOException {
        Response rs = Jsoup.connect(url).execute();
        Document doc= rs.parse();
        Elements Eles= doc.body().getElementsByClass("pageside").first()
                .getElementsByClass("pagemenu").first()
                .getElementsByTag("li");
        String[] list= new String[Eles.size()-1];
        String[] name= new String[Eles.size()-1];
        for(int i=1;i<Eles.size();i++){
            list[i-1]=Eles.get(i).select("a").attr("href");
            name[i-1]=Eles.get(i).select("a").text();
        }
        for(int i=0;i<list.length;i++){
            html+="<p></p>" +
                    "<h1 class=\"Tech-title\" style=\"text-align:center;\">"+name[i]+"</h1>";
            get_folder_list(list[i]);
        }
    }

    private static void get_folder_list(String url) throws IOException {
        Response rs = Jsoup.connect(url).execute();
        Document doc= rs.parse();
        Elements Eles= doc.getElementsByClass("pageside").first()
                .getElementsByClass("pagemenu").first()
                .getElementsByTag("li");
        String[] list= new String[Eles.size()-1];
        String[] name= new String[Eles.size()-1];
        for(int i=1;i<Eles.size();i++){
            list[i-1]= Eles.get(i).select("a").attr("href");
            name[i-1]= Eles.get(i).select("a").text();
            char[] sf=name[i-1].toCharArray();
            for(int j=0;j<sf.length;j++){
                if(sf[j]=='/')
                    sf[j]='_';
            }
            name[i-1]="";
            for(char a:sf)
                name[i-1] += a;
        }
        for(int i=0;i<list.length;i++){
            html+="<p></p>" +
                    "<h2 class=\"SubTech-title\" style=\"text-align:center;\">"+name[i]+"</h2>" +
                    "<h3 class=\"BranchTech-title\" style=\"text-align:center;\">"+name[i]+"</h3>";
            get_Artical_list(list[i]);
        }
    }

    private static void get_Artical_list(String url) throws IOException {
        String[] list = get_web_list(url);
        boolean check=false;

        for(int i=0;i<list.length;i++){
            if(list[i].charAt(0)=='#') {
                html+="<h3 class=\"BranchTech-title\" style=\"text-align:center;\">"+list[i].substring(1)+"</h3>";
            }
            else{
                get_html_doc(list[i]);
            }
        }

    }

    private static String[] get_web_list(String url) throws IOException {
        Response rs = Jsoup.connect(url).execute();
        Document doc= rs.parse();
        Elements Eles= doc.getElementsByClass("pagemenu").first()
                .getElementsByTag("li");
        int n=Eles.size();
        String[] list= new String[n];
        //list[0] = doc.body().getElementsByClass("article-title").first().text();
        try {
            System.out.println(doc.body().getElementsByClass("article-title").first().text());
        }
        catch(Exception e){

        }
        for(int i=0;i<Eles.size();i++){
            if(Eles.get(i).select("a").attr("href").isEmpty())
                list[i]='#'+Eles.get(i).select("a").text();
            else
                list[i]= Eles.get(i).select("a").attr("href");
        }
        return list;
    }

    private static void get_html_doc( String url)  {
        Response rs = null;
        try {
            rs = Jsoup.connect(url).execute();
            Document doc = rs.parse();
            Element Ele = doc.body().getElementsByClass("article-content").first();
            while (Ele.getElementsByTag("input").size()>0)
                Ele.getElementsByTag("input").first().remove();
            while (Ele.getElementsByTag("output").size()>0)
                Ele.getElementsByTag("output").first().remove();
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
            Elements Eles=Ele.getElementsByTag("h3");
            for(Element E:Eles){
                E.tagName("h6");
            }
            Eles=Ele.getElementsByTag("h2");
            for(Element E:Eles){
                E.tagName("h5");
            }
            Eles=Ele.getElementsByTag("h1");
            for(Element E:Eles){
                E.tagName("h4");
            }
            String x="";
            x+=doc.body().getElementsByClass("article-title").first();
            x+=Ele;
            html+=x;
            /*String folder="";
            if(!subfolder.isEmpty()){
                folder+="/"+subfolder;
            }
            String name=doc.body().getElementsByClass("article-title").first().text();

            char[] sf=name.toCharArray();
            for(int i=0;i<sf.length;i++){
                if(sf[i]=='/')
                    sf[i]='_';
            }
            name="";
            for(char a:sf)
                name += a;

            String path="src"+folder+"/"+name+".html";
            System.out.println(path);
            FileOutputStream fileout=new FileOutputStream(new File(path));
            byte[] bt=x.getBytes();
            fileout.write(bt,0,bt.length);
            fileout.close();*/
        } catch (Exception e) {

        }
    }

    public static void main(String[] args) throws IOException {
        html="<html>" +
                "<head><meta charset=\"utf-8\"></meta></head>" +
                "<body>";
        root("https://www.yiibai.com");
        html+="</body></html>";;
        FileOutputStream fileout=new FileOutputStream(new File("src/yiiBai_教程.html"));
        byte[] bt=html.getBytes();
        fileout.write(bt,0,bt.length);
        fileout.close();
        //get_html_doc("https://www.yiibai.com/ai_with_python/with_python_getting_started.html","");
    }
}
