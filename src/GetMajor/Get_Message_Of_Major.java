package GetMajor;

import org.jsoup.Connection;
import org.jsoup.Connection.Response;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class Get_Message_Of_Major  {
    static String csv_all="";
    static int listid=1;
    public static void get_major(String username,String Password) throws Exception {
        String Execution="a9a226d7-c439-4500-b817-2f2d29d6ea40_ZXlKaGJHY2lPaUpJVXpVeE1pSjkuYlhaS1pFbE9kVEZTWjNGNVUzWjNTRlJvSzBWMWQzaEdTblF5U0ROTmNIWnhTMnhwT1dSVUwzWnBhbVpMTDFOUmVWSklORUowUmtVNVpsaHBVR0pFWkdrd04wcFlTakpZZDFBM2RYSjFkV3RhUjJaQlkzZENjMnRKUkdvMVZscHphRTlFYm1kS1NGSkpPRTlLY1RORU5Ea3pUMUJFUWk5aVZUUnZUMGQyU2tKUkwxVXlLelZDTjFWelEwVmtNa0oxTlhKd1ZEZFJNalpGVEdoNGFtc3diR0ZCU0Zsa1prVnBlVGRDV200emVYbGlSbVZyVTFOeU4wWmFVWEZrTVVoMmFFSlJPRWg0YzFGT1lYRnJLMnBuZEZvek1rMTJiMDVhV2xRMWFEaERiVFpSZEhsdmNHeDBORmxvYVdoMVJTdGlVMU01U3l0a2FHeElhM0ZFU0ZNMFJWbGhNbWd5Tmt4V1RYWm5hWHBVWTIwM1JYQjRTRkpWTW1kbmVsTk9PVE5LZEhGd01EaGliMnBRYW5CV2FuWk5VV2hOUlZsMFZqZEVXVXR2YTNFeVVFSnNlREJPYURWTlZGUlBUMkZIZUM5ck1rNXhXWFZxT1dwaVZYUklRVkExYzI5SFRWQjNXVlZMTVVvMlJrUk1hMDVHUTNOdFptOHZXVmQxY2xwVWMyNW9ZMWN4V0doclRVeDNSMDl1VlVsck1rUlJNVXBHZDFGNE1EaHZkVGxwVDNrek5WTnZXV294WTFWMFRVNHlSWGhaUlZKaVRrbG1WM0JqVURGMlNpOUJiamxPU204NGNXbzRVRkJVVWtkSWMzY3JWR2c1TVd0Rk5VcGFaVVpsYUhFek1HYzRlamxxZFZBM1RtNTBRVXRDTTNkU2RGTlhMelJSVkVSVWFFTlRUM2xyVm1ORkwwNUZRMUF4Ym5BMGJVbHpjU3MxWkZseVNERmlWMDQwZW01bmMxcHJjRmt3YUVWR1FVWmFiWEF6TlUxMVpVVTNUSGhOV21wTlpFRXdaa1ZxT1VSV1prSTNjWEozYTBWWlVWSldTakZuVm1FMWIwaFdkbFo0U1VWME5WbFhUblZWWTBobUsyWXlhV1F4ZG5wMlFuUldjR0ZKV25OWmNWUlFXV1IzZUVadWRFVlBWR2RUWW5WYVpYbFFiekZHZFdabk1YTjBXamxFTUVWWFpFWnFiRTlMTkU1Rk5YUmxhMUF3U0ZKS1EzbHVhSGRWZURGM1UycGFjbElyVGxNNWVFUlBSMVYxYW1GNlNVVnVjR0Z6ZW1GaVpqSkNTbVoxU0VwMldXc3dObFZ1TkZaRFJGSjVSV293T0d4RFQxTlVMME5RZDBReVdtODNhV3BFU1ZKblVEZHJaMWwwS3prd01tUlJWbEoxYmpOMVVHZ3lZV0ZRVFZCT1prbHVkeXRoU0ZsQlpuTmplamh0Um1sTWJHWlFhVzlMY2xwWk1IVnlkRmhwWjNwV2FuQTRPVFJ1Y2xoNk1GaEhWVXA1TkVWWmRXRkxSbUZFYURsallubGpkVTl6VFRWdlZEWnZjMHh3U25KNVpYVXlaMWczTURGQmFFNUNURE5oYzNoQmRYSXlaa3Q0WkV3eWNGcFJZazlUY1d3emNscHJUMnM1T0ZwYU9XRkZWRUYzUlRRMEt6UjNkVk5sZUZOREwzUllRMmd4VEhCTVRsTlJWWE5ZSzNOdlNFWlhUa3BKTW5OQmVtTXhiVGRDVW01UFNWZFNkRFJ1Y0Zaek0yOVhjSFY2Tm1oa1ZIWmFRMDVUT0doNmFsVnpVMU0zWkVwR1EzUldXakl4TDFaMlpIZ3dNV3BHVG1ocE5HbHVhVTVTVDJWSGMwOHdWMGhWVEcxNVJ6SklUV1J3UTNFdk0wRTNjWHBQTWpCM1MwNVVXR2R1VlVoYVdIaERjbEoyVUZOWFdURlhMM0IyTkZSSFZHcDNla1ZyZDBwT1lWbEhiM0ZUUkdZd01FbGhURlJLZGtOU1Z6VTFNSEZIZW10a1RVRkphWFZWU0ZaSWR6VnZjbUZvWXpWRVJuUk5NMHM0Wm5wTWEycEljMUpEVlRablFsTlNNbVF6YTNwRU9WcGlhMlJ2ZUc5cVMxbGpaazF1YTNsMGJucEpiR28wVkRRMVIzZzFVSHA2VDNaNGVEY3lkSGRMYjNkR2EyTkdXVWREWTJ4WmVsUlBibk5GVWxoV1lXaE5WQzlDT0RrME4zVjFSRWhpU2pNdlJscDVURlZPVjNobU0yaG1Zalo0U2xkcWNsTXdTR2RxTDJwNVQzcFVNVFJuYldkVFJFUjVNbTFIUmpoRGJVVjZTVk00YTJKU1JFNDVVVXhVVEROWUszSlNUbkZuYVhwclNuSkhZMlpwVDNoS1kxbzBiRWMwT1hjMVRVcENSVms1V21jeVFXdFBRbUozU1daTk1ubHdUVWhHVXpseE9VZHhUelZTY0VSTFpFOHZWR3hGYzFSM2F6a3hWMkowUmxsYVJrMDFNSFJ6U3poV2FVNUpUMmRPYWtNeVVHTnZZVFl5YWxnclVGWTRMMUpqVWpNM2F6WnRaMnd3UzNjemRsbHFhMFoxVm5WRGFuSjJla3hFYVhscldsQkRaRTR4YlZsRFJ6VkhibkZLUkhCaWVFeGFaVVZwVkcxTWNqSllOVWhVUmsxRFNtMHZZVTF5WWsxVmFUSlVLMWMwV0ZoS1NIUTNjM1JCYTJwbEsxRjZOVmN4TjFScFpIcHBabGhrUkZremNIbFlVMnBxU3l0QmJuSmFUa0ozWkdKcE9DdEpkWEF5TDJJNVNpdHFTbUo2WWpkWmFGTkRhalZoT1V4NWMxSXhRVXM0YzJoWlVFY3JjMkZqVVZWR2NIZFVlVTlTTlc1RFUweHlSbU5TWkd0V1VERXZiR3BSY1RRemVXMXFZMGR0WjFoU2NFOTFUV1psYTB4Nk5XUmlabEpOWW1KWFJHc3hVekJIV1hCSVlYaEZjWE55TTNSbVIzQlFTVU5pWTB0bk1uZHFVVXQwT1c5RldrbHdNVXhJUXpKcVMyUktaMFJYTldGUkszWlROa05KWmxKNWEwZEdlRmN4WVdNclNIRlJWaTh2TkV4SGNXVkdkMmQ0Y0U5dE5YVlFWSGRwVlhaUkwxbGhTVFJsZEVGdk9IWnlhVFk0T1c1Tk56aG9SVVZoVkRkclZrNU1kVmw1Y0hoSGJGZHliekpqVGpSb2FuaERjV3h3ZERWVUsyeEVMMEppVERsbmRqRXlOalJQVjBaVGVYUlJWbmRqYzA5ek5uWTVVbUowY1RGUlF6aHpRbEptUjNGM1kxRkdaeTkzY21NeWVEZ3liazF6V1hoRmFWTmhNVFpoVEVsSUwyOVNSV1ZTVmxNMVZFaHJURWhIU1ZvNGMwRkxjM1p6Y2xKUk1EUnFSR1F6ZGxaVlZIcEphblpPY3lzMldXZFZlRXRZZEZwTFJWZDBkVGxPYWxCRlZsSndSblJYV2pKa1RVZEtSMHh6Y0U5dU1XZDRWMkZ2VUc4MGRXWnJjRFZxVTFKQ1NFNUhTSGRhZDJORk1tcEJURXRwVEhNeFVXUnlja1JHZEZaVGVVaGphM2x4YUVkcFJGZDFURTFTVmsxbFkxa3dhVlJvTURnMlNVaDZlbUZaYkhkYVRpdE9Ra1pQWkVKaVVWQlJhV3BzTjI5Q2VETXZNMFJuWWxOcWNVZHVhR0VyZGxnMlZFbFpWMkpvWkhvMlRFYzFka3QxV2tabGQyVjVTSHByYzB3ck5GWlBTVlo1U1ZGcVYxQXJZMGhPUVd4V05uUjNSRGMwWTBNemNVUnBVV3BZUzFaSVJrVnRlVlZLZWs1dU4xVlZXRU5GWms1SGFFRllOVE5YZDNOak5IRnZOM3BCVEhFMVNYcHBLMGRuZFdONGNYaHdPRWxvZWpGYVYwZDJaMkZwWjJkWFRVMUtaRmhTVXpsQ2NVa3hla0paU2psU2ExZFhTVmxRT0VjdlZteEZUVkIzWVVoWVFsSkljbkE0TW1OVWNrVXhkME5LTlZoMlkyZGFUR3hFUnpGQ1oyWjZPRzUxVEdkbE5GUkRlWFpEUlhaVmRFeG1XbGxaYTB4Q2JsRnZNa0psV0RCMlJFYzViMnh4WkhGTmJGcHlZa296VjI1elltWlhkVWhKTnpGclZtdE1NbEJ2ZDNoQ1lVdE1OSFIyZW1GeVNGSmtWVkZsV0RCM2JtOXVSWGhNYzB0M2QwWk5NMnRHV0ZrMmFHUk5aV0ZMU2xwVUsyeDVZV2RIYkRsNVJqRXlXbWMxTjAxRFJXeHpPVUp0VEVKVWFqSkJjV3hXZFRORlJqVXZLM0ZtWVRaMlpVcEpjV1o2ZDBaVWNXeDFVbTVyVEVSNU1HMXdZa2RNYm5sMFRWRnZWbEJEUVdoS2QwWjRjV1JEYUZkelExSnBkelZvZEVwcU1sWmFNR28zWWtjdmNWQm5lU3RzTHpoRlQwZHdUSG9yZEUwMFYwczVhRXREWkRWMlFXUlFVVnBJUlRseWRsaFJTVWhaY3pKaU5USmxhemxVYUd4TlNFNWxkMnhRUVRGYWMxSXdRelprVlRneFdIQklNMUJHV2sxTlJUbEZOM0Y2TmxGMFkwUlpTVkZEWmpKS2MxbzJNSFpyVmpkVEwxUkZlRzlETnk5Q1ZGbFFiblU0Ylc1WGJqRTFPVFZvVEdKSVVUVnFTMDR6U1hGSWEzUk9WM2N2ZFRVd1V6WlZRVXd6YVU1MVYyc3JXVmwzVGxaVkwxSmlaelZKVjJKSGFXaGxWRkpoVmpCNmF6VnJWWElyUmsxM09FbG1jM1pNUnprMFRVZzViWFJHWjJkc01ITklabmxDWjFrMU0yRmFlRFZ0VmxKM2JFWlhhMHRoVTNKYU4zVk9OREZPYlM5VFpqUmlaVU1yYWtFcmVXUkdTR3ROUnpsYWJ5OWFhM0pCTmxWMFJXNXhhR3RuVlZCMFRFRnlTelpaU21ObUswMVJWMmt2YUcxaU5qWkZiRUZzYkU0NU9HeDBTRU14VDFGSVdVUmpXbkYyUm5Od1MwcHRLMFZ1VmxGdVNrVmFTVGRsU0VkNGFrcEJhSEJoWTNoSGFWWktSVkZGUlZoS1lsRnRRMEp2YWtacU4yMHpSbmhyWm14blRsQmlkVEl2UkhGV2VsUklaME5RWVU1UE1tdG1iMmR5YVhsRVpGSnFhV0ZtV1RGdVlqQm1RMVZ1SzBvMVNuTmFXRU1yYnpOMU9WcHJhR1E1YkZaclRqUkVZbGQxZWxCTWNGTnZhamRqU1RVM1VsRmxiRTV1YTJkaWFuSnNXRE5IVmpOYWVsQkdabXBIVlc5blZtbFhZbEJaVldaaGRGUk5TWFJKZDJGc1dUVmpVVmgzTVhKaGJYcElZalk0UzBablYwMUpkalJFVlZNNWQzSklPWEpIWjBGTGFpdHZkMmhEZUc5dGRIQnBaV1JRSzJoT1RXNUpSa2hCTDJkRmQwMXlOVEpUWkZWS1kyUkZNRGR0YlROVUwySnVTbmt5WTFkQlVXbFVhMkV5VFdwSVYxSnJPVU5EY2xrMU5YQm1TREpOTjBKNFQyeGtORTFGVTBZM1NEbDFTVWRYSzFRelVHOXVVazQ0WmxWR1EwUlJNSFYyYlZSR1JuUk1hRWhsTkRCc1NFSTVTakpCVkdoRU5TOUNjMUJ1UlN0b1pVaHNXVmM1YlRoR2EzVlVka0pQTkdsaWRXNDFiM2hFVVRScVRqUnlaMndyUVVzMWRXZHBXak5xZUd4WWR6ZHFSREJ0VldndlZqSkJSMk0xYlVWUlVVRkxXRU5CYUZFdk16UmlTalEyZFdoc05tdFJLMlprUjBsMWFrVm9hbkJZTXpSbFpFaHJORlI0TmtaR1dXWkVSSFIzUzA5eVpUa3lSemhRVkhkbGJrNUxhRnB3TURZdllWRXpRWEZPTDFwTGNqUmFTVzFwYkZsa01WWklXamc1U1VKSE4weFNOMUZ4WW1nMFdrZzBSVVoyT1dFdmJFVjBVVVkxVFdZNWJVOURaRGRaZW1oT1RHRnJOR2R1UW5OaldrNU5WM3AzZEZwRkszWmxiMnhoUzFwNmRUQXpOa2x1ZWxGNFoyNVpSekJQVEVsUGJGcFNabGxGWTJKa0wxSm5SMVIzUm5WTVNUZE9RVXhZU0ZSRlRraElVakpaVDJkd1dTOUtPV3Q0TjNGMVpUQmFOMGN6ZG1NMVRrVlVUSFFyV0d0SVUwWnZRVmh4Um5FM1FXSjBha3B3ZEd3eFdHUmFTbVo1Y1ZkdU5VMW9XVTlpTWtwTVdsQmhWRGRPZEVaaVRFTjBRbEJPY3pkb0t6TndWbUp2ZFhaNVNYcGpTRWhvZGpGNU4weGtRbVpxUm1sclMwNU9hbE5ZZFRRMFlTdHpPRlJrZWtwRmVXc3hLM1pTUW01QmFTOXpjRU5hVm01T2FXc3phRlZoU2psbWEwODNkVTVFYUZjM00yVXZiM0ozU1hST1JEWlFRWEZwZEU5SVNHZDVkVXRUVG5sVWJrbDVNSFo0VnpKNk5EWlZNSEJEVjJGcWNrdGpPR1ZVUkM5M09EUjFlRk15YWtzd1RDOUhOMDVCWkVSeGRqSkdZMlIzU0hFNWN6SjZWRk5TUzNsUk5HUlpZa0pxUzNCeWFuZGxTVnBaZHpCa1JEaE1lRFF6YURWWmMzcFhkVE5uZEd3MmNWQTVZMGx5ZG5Wak5YWlVWMnMwU0ZSaFN6Wmtla05wWmxwS1pFRlNSbWhJSzBjMU5rbEdRek40TjNVelNHaGtPSFpVU21KWmNVcDBOVGwwVEVWd1ZrUTRkVXhYUzIxbmVITjZSWGwwVjJ0QlJqaFhiM281WjNVcmFFRjNZM0kyTmxWeVQyYzNiM1pOUVdObU9FVndNVWxRU1VWMGRWRkpVV1JOU0ZsaWF6MC5NREpyWl9SWXotblU1bndCMENZYS02ZFpDektQbjBVS1N3aUx6SzdvYVVOVWRTUXItZC1DVG1BY2JxaUo2LTg1RW9idXZJWDd2Wk1BMl9pV2phd0VjQQ==";
        HashMap<String,String> datas= new HashMap<String, String>();
        datas.put("username",username);
        datas.put("password",Password);
        datas.put("execution",Execution);
        datas.put("_eventId","submit");
        datas.put("geolocation","");
        Response rs=Jsoup.connect("https://cas.sustc.edu.cn/cas/login")
                .data(datas)
                .method(Connection.Method.POST).execute();
        Response re=Jsoup.connect("http://jwxt.sustc.edu.cn/jsxsd/xsxj/toQueryZyfl.do").cookies(rs.cookies()).execute();
        Response res;
        Document doc;
        Element Form1,page_change;
        Elements table_zhuanye;
        String csv = "";
        int index=1;
        do {
            res = Jsoup.connect("http://jwxt.sustc.edu.cn/jsxsd/xsxj/toQueryZyfl.do").data("pageIndex", ""+index).cookies(re.cookies()).execute();
            doc = res.parse();
            Form1 = doc.body().getElementsByClass("Nsb_pw").get(2)
                    .getElementsByClass("Nsb_layout_r").get(0)
                    .getElementById("Form1");
            table_zhuanye = Form1.getElementsByTag("table").first()
                    .getElementsByTag("tbody").first().getElementsByTag("tr");
            for (int i = 1; i < table_zhuanye.size(); i++) {
                Elements row = table_zhuanye.get(i).getElementsByTag("td");
                //csv += row.get(0).text() + ",";
                String name=row.get(1).select("font").text();
                String url="http://jwxt.sustc.edu.cn"+row.get(1).select("a").attr("href");
                csv += "\'" + name + "\',";
                csv += "\'" + row.get(2).text() + "\',";
                csv += row.get(3).text();
                csv += "\n";
                //System.out.println(url);
                All_student(url,name,re);

            }
            page_change = Form1.getElementById("PagingControl1_divOuterClass")
                    .getElementsByClass("Nsb_r_list_fy").first()
                    .getElementsByClass("Nsb_r_list_fy4").first()
                    .getElementById("PagingControl1_btnNextPage");
            index++;
        }while(page_change.attr("disabled").isEmpty());

        FileOutputStream fileout=new FileOutputStream(new File("C:\\Users\\Metaron\\OneDrive\\Documents\\总览.csv"));
        byte bt[] = new byte[1024];
        bt = csv.getBytes();
        fileout.write(bt,0,bt.length);
        fileout.close();
    }

    public static void All_student(String url, String name,Response re) throws Exception {
        Response res;
        Document doc;
        Element Form1,page_change;
        Elements table_detail;
        HashMap<String,String> datas=new HashMap<String, String>();
        int index=1;
        datas.put("zy","");
        datas.put("zyid",url.substring(url.length()-32,url.length()));
        datas.put("pageIndex",""+index);
        do {
            res = Jsoup.connect(url).data(datas).cookies(re.cookies()).execute();
            doc = res.parse();
            Form1 = doc.body().getElementsByClass("Nsb_pw").get(2)
                   .getElementsByClass("Nsb_layout_r").get(0)
                    .getElementById("Form1");
            table_detail = Form1.getElementsByTag("table").first()
                    .getElementsByTag("tbody").first().getElementsByTag("tr");

            page_change = Form1.getElementById("PagingControl1_divOuterClass")
                    .getElementsByClass("Nsb_r_list_fy").first()
                    .getElementsByClass("Nsb_r_list_fy4").first()
                    .getElementById("PagingControl1_btnNextPage");
            for (int i = 1; i < table_detail.size(); i++) {
                Elements row = table_detail.get(i).getElementsByTag("td");
                csv_all+=listid+",";
                csv_all+=row.first().text();
                for(int j=1;j<row.size();j++){
                    Element r=row.get(j);
                    csv_all+=",\'"+r.text()+"\'";
                }
                listid++;
                csv_all+="\n";
            }
            index++;
            datas.put("pageIndex",""+index);
        }while(page_change.attr("disabled").isEmpty());

        FileOutputStream fileout=new FileOutputStream(new File("C:\\Users\\Metaron\\OneDrive\\Documents\\学生细节.csv"));
        byte bt[] = new byte[1024];
        bt = csv_all.getBytes();
        fileout.write(bt,0,bt.length);
        fileout.close();

    }
    public static void main(String[] args) throws Exception {
        Scanner sc=new Scanner(System.in);
        System.out.println("Input your studentId and the password");
        get_major(sc.next(),sc.next());
        System.out.println("Input your PassWord Of the DataBase");
        String pass =sc.next();
        insertTel_test(readCSV("C:\\Users\\Metaron\\OneDrive\\Documents\\学生细节.csv"),"major.student_detail",pass);
        insertTel_test(readCSV("C:\\Users\\Metaron\\OneDrive\\Documents\\总览.csv"),"major.major_detail",pass);
        System.out.println("Finish");
    }
    public static List readCSV(String location) {
        try {
            ArrayList<String> list = new ArrayList<String>();
            BufferedReader reader = new BufferedReader(new FileReader(location));// 文件名
            String line = null;
            while ((line = reader.readLine()) != null) {
                String item[] = line.split("/n");// CSV格式文件为逗号分隔符文件，这里根据逗号切分
                String last = item[item.length - 1];// 获取到的数据

                list.add(last);
            }
            return list;
        } catch (Exception e) {
        }
        return null;
    }
    public static void insertTel_test(List<String> list,String name,String password) throws Exception {


        Class.forName("org.postgresql.Driver");
        java.sql.Connection con;
        Statement stmt;
        String sql="";
        con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/yuan_data","metaron", password);
        stmt=con.createStatement();
        stmt.executeUpdate("delete from "+name);
        sql="insert into "+name+" values";
        for (String value : list) {
            sql+="\n ("+value+"),";
        }
        sql=sql.substring(0,sql.length()-1);
        System.out.println(sql);
        stmt.executeUpdate(sql);
    }
}

