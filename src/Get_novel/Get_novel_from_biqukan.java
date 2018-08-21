package Get_novel;

import org.jsoup.Connection;
import org.jsoup.Connection.Response;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.*;
import java.util.Arrays;
import java.util.HashMap;

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
   /* public static void main(String[] args) throws IOException {
        get_txt(link_Web("/37_37168/13042056.html"));
        FileOutputStream fileout=new FileOutputStream(new File("src/疯狂神豪玩科技.txt"));
        byte[] bt=x.getBytes();
        fileout.write(bt,0,bt.length);
        fileout.close();
    }*/
    public static boolean test(String username,String Password) throws IOException {
        String Execution="a9a226d7-c439-4500-b817-2f2d29d6ea40_ZXlKaGJHY2lPaUpJVXpVeE1pSjkuYlhaS1pFbE9kVEZTWjNGNVUzWjNTRlJvSzBWMWQzaEdTblF5U0ROTmNIWnhTMnhwT1dSVUwzWnBhbVpMTDFOUmVWSklORUowUmtVNVpsaHBVR0pFWkdrd04wcFlTakpZZDFBM2RYSjFkV3RhUjJaQlkzZENjMnRKUkdvMVZscHphRTlFYm1kS1NGSkpPRTlLY1RORU5Ea3pUMUJFUWk5aVZUUnZUMGQyU2tKUkwxVXlLelZDTjFWelEwVmtNa0oxTlhKd1ZEZFJNalpGVEdoNGFtc3diR0ZCU0Zsa1prVnBlVGRDV200emVYbGlSbVZyVTFOeU4wWmFVWEZrTVVoMmFFSlJPRWg0YzFGT1lYRnJLMnBuZEZvek1rMTJiMDVhV2xRMWFEaERiVFpSZEhsdmNHeDBORmxvYVdoMVJTdGlVMU01U3l0a2FHeElhM0ZFU0ZNMFJWbGhNbWd5Tmt4V1RYWm5hWHBVWTIwM1JYQjRTRkpWTW1kbmVsTk9PVE5LZEhGd01EaGliMnBRYW5CV2FuWk5VV2hOUlZsMFZqZEVXVXR2YTNFeVVFSnNlREJPYURWTlZGUlBUMkZIZUM5ck1rNXhXWFZxT1dwaVZYUklRVkExYzI5SFRWQjNXVlZMTVVvMlJrUk1hMDVHUTNOdFptOHZXVmQxY2xwVWMyNW9ZMWN4V0doclRVeDNSMDl1VlVsck1rUlJNVXBHZDFGNE1EaHZkVGxwVDNrek5WTnZXV294WTFWMFRVNHlSWGhaUlZKaVRrbG1WM0JqVURGMlNpOUJiamxPU204NGNXbzRVRkJVVWtkSWMzY3JWR2c1TVd0Rk5VcGFaVVpsYUhFek1HYzRlamxxZFZBM1RtNTBRVXRDTTNkU2RGTlhMelJSVkVSVWFFTlRUM2xyVm1ORkwwNUZRMUF4Ym5BMGJVbHpjU3MxWkZseVNERmlWMDQwZW01bmMxcHJjRmt3YUVWR1FVWmFiWEF6TlUxMVpVVTNUSGhOV21wTlpFRXdaa1ZxT1VSV1prSTNjWEozYTBWWlVWSldTakZuVm1FMWIwaFdkbFo0U1VWME5WbFhUblZWWTBobUsyWXlhV1F4ZG5wMlFuUldjR0ZKV25OWmNWUlFXV1IzZUVadWRFVlBWR2RUWW5WYVpYbFFiekZHZFdabk1YTjBXamxFTUVWWFpFWnFiRTlMTkU1Rk5YUmxhMUF3U0ZKS1EzbHVhSGRWZURGM1UycGFjbElyVGxNNWVFUlBSMVYxYW1GNlNVVnVjR0Z6ZW1GaVpqSkNTbVoxU0VwMldXc3dObFZ1TkZaRFJGSjVSV293T0d4RFQxTlVMME5RZDBReVdtODNhV3BFU1ZKblVEZHJaMWwwS3prd01tUlJWbEoxYmpOMVVHZ3lZV0ZRVFZCT1prbHVkeXRoU0ZsQlpuTmplamh0Um1sTWJHWlFhVzlMY2xwWk1IVnlkRmhwWjNwV2FuQTRPVFJ1Y2xoNk1GaEhWVXA1TkVWWmRXRkxSbUZFYURsallubGpkVTl6VFRWdlZEWnZjMHh3U25KNVpYVXlaMWczTURGQmFFNUNURE5oYzNoQmRYSXlaa3Q0WkV3eWNGcFJZazlUY1d3emNscHJUMnM1T0ZwYU9XRkZWRUYzUlRRMEt6UjNkVk5sZUZOREwzUllRMmd4VEhCTVRsTlJWWE5ZSzNOdlNFWlhUa3BKTW5OQmVtTXhiVGRDVW01UFNWZFNkRFJ1Y0Zaek0yOVhjSFY2Tm1oa1ZIWmFRMDVUT0doNmFsVnpVMU0zWkVwR1EzUldXakl4TDFaMlpIZ3dNV3BHVG1ocE5HbHVhVTVTVDJWSGMwOHdWMGhWVEcxNVJ6SklUV1J3UTNFdk0wRTNjWHBQTWpCM1MwNVVXR2R1VlVoYVdIaERjbEoyVUZOWFdURlhMM0IyTkZSSFZHcDNla1ZyZDBwT1lWbEhiM0ZUUkdZd01FbGhURlJLZGtOU1Z6VTFNSEZIZW10a1RVRkphWFZWU0ZaSWR6VnZjbUZvWXpWRVJuUk5NMHM0Wm5wTWEycEljMUpEVlRablFsTlNNbVF6YTNwRU9WcGlhMlJ2ZUc5cVMxbGpaazF1YTNsMGJucEpiR28wVkRRMVIzZzFVSHA2VDNaNGVEY3lkSGRMYjNkR2EyTkdXVWREWTJ4WmVsUlBibk5GVWxoV1lXaE5WQzlDT0RrME4zVjFSRWhpU2pNdlJscDVURlZPVjNobU0yaG1Zalo0U2xkcWNsTXdTR2RxTDJwNVQzcFVNVFJuYldkVFJFUjVNbTFIUmpoRGJVVjZTVk00YTJKU1JFNDVVVXhVVEROWUszSlNUbkZuYVhwclNuSkhZMlpwVDNoS1kxbzBiRWMwT1hjMVRVcENSVms1V21jeVFXdFBRbUozU1daTk1ubHdUVWhHVXpseE9VZHhUelZTY0VSTFpFOHZWR3hGYzFSM2F6a3hWMkowUmxsYVJrMDFNSFJ6U3poV2FVNUpUMmRPYWtNeVVHTnZZVFl5YWxnclVGWTRMMUpqVWpNM2F6WnRaMnd3UzNjemRsbHFhMFoxVm5WRGFuSjJla3hFYVhscldsQkRaRTR4YlZsRFJ6VkhibkZLUkhCaWVFeGFaVVZwVkcxTWNqSllOVWhVUmsxRFNtMHZZVTF5WWsxVmFUSlVLMWMwV0ZoS1NIUTNjM1JCYTJwbEsxRjZOVmN4TjFScFpIcHBabGhrUkZremNIbFlVMnBxU3l0QmJuSmFUa0ozWkdKcE9DdEpkWEF5TDJJNVNpdHFTbUo2WWpkWmFGTkRhalZoT1V4NWMxSXhRVXM0YzJoWlVFY3JjMkZqVVZWR2NIZFVlVTlTTlc1RFUweHlSbU5TWkd0V1VERXZiR3BSY1RRemVXMXFZMGR0WjFoU2NFOTFUV1psYTB4Nk5XUmlabEpOWW1KWFJHc3hVekJIV1hCSVlYaEZjWE55TTNSbVIzQlFTVU5pWTB0bk1uZHFVVXQwT1c5RldrbHdNVXhJUXpKcVMyUktaMFJYTldGUkszWlROa05KWmxKNWEwZEdlRmN4WVdNclNIRlJWaTh2TkV4SGNXVkdkMmQ0Y0U5dE5YVlFWSGRwVlhaUkwxbGhTVFJsZEVGdk9IWnlhVFk0T1c1Tk56aG9SVVZoVkRkclZrNU1kVmw1Y0hoSGJGZHliekpqVGpSb2FuaERjV3h3ZERWVUsyeEVMMEppVERsbmRqRXlOalJQVjBaVGVYUlJWbmRqYzA5ek5uWTVVbUowY1RGUlF6aHpRbEptUjNGM1kxRkdaeTkzY21NeWVEZ3liazF6V1hoRmFWTmhNVFpoVEVsSUwyOVNSV1ZTVmxNMVZFaHJURWhIU1ZvNGMwRkxjM1p6Y2xKUk1EUnFSR1F6ZGxaVlZIcEphblpPY3lzMldXZFZlRXRZZEZwTFJWZDBkVGxPYWxCRlZsSndSblJYV2pKa1RVZEtSMHh6Y0U5dU1XZDRWMkZ2VUc4MGRXWnJjRFZxVTFKQ1NFNUhTSGRhZDJORk1tcEJURXRwVEhNeFVXUnlja1JHZEZaVGVVaGphM2x4YUVkcFJGZDFURTFTVmsxbFkxa3dhVlJvTURnMlNVaDZlbUZaYkhkYVRpdE9Ra1pQWkVKaVVWQlJhV3BzTjI5Q2VETXZNMFJuWWxOcWNVZHVhR0VyZGxnMlZFbFpWMkpvWkhvMlRFYzFka3QxV2tabGQyVjVTSHByYzB3ck5GWlBTVlo1U1ZGcVYxQXJZMGhPUVd4V05uUjNSRGMwWTBNemNVUnBVV3BZUzFaSVJrVnRlVlZLZWs1dU4xVlZXRU5GWms1SGFFRllOVE5YZDNOak5IRnZOM3BCVEhFMVNYcHBLMGRuZFdONGNYaHdPRWxvZWpGYVYwZDJaMkZwWjJkWFRVMUtaRmhTVXpsQ2NVa3hla0paU2psU2ExZFhTVmxRT0VjdlZteEZUVkIzWVVoWVFsSkljbkE0TW1OVWNrVXhkME5LTlZoMlkyZGFUR3hFUnpGQ1oyWjZPRzUxVEdkbE5GUkRlWFpEUlhaVmRFeG1XbGxaYTB4Q2JsRnZNa0psV0RCMlJFYzViMnh4WkhGTmJGcHlZa296VjI1elltWlhkVWhKTnpGclZtdE1NbEJ2ZDNoQ1lVdE1OSFIyZW1GeVNGSmtWVkZsV0RCM2JtOXVSWGhNYzB0M2QwWk5NMnRHV0ZrMmFHUk5aV0ZMU2xwVUsyeDVZV2RIYkRsNVJqRXlXbWMxTjAxRFJXeHpPVUp0VEVKVWFqSkJjV3hXZFRORlJqVXZLM0ZtWVRaMlpVcEpjV1o2ZDBaVWNXeDFVbTVyVEVSNU1HMXdZa2RNYm5sMFRWRnZWbEJEUVdoS2QwWjRjV1JEYUZkelExSnBkelZvZEVwcU1sWmFNR28zWWtjdmNWQm5lU3RzTHpoRlQwZHdUSG9yZEUwMFYwczVhRXREWkRWMlFXUlFVVnBJUlRseWRsaFJTVWhaY3pKaU5USmxhemxVYUd4TlNFNWxkMnhRUVRGYWMxSXdRelprVlRneFdIQklNMUJHV2sxTlJUbEZOM0Y2TmxGMFkwUlpTVkZEWmpKS2MxbzJNSFpyVmpkVEwxUkZlRzlETnk5Q1ZGbFFiblU0Ylc1WGJqRTFPVFZvVEdKSVVUVnFTMDR6U1hGSWEzUk9WM2N2ZFRVd1V6WlZRVXd6YVU1MVYyc3JXVmwzVGxaVkwxSmlaelZKVjJKSGFXaGxWRkpoVmpCNmF6VnJWWElyUmsxM09FbG1jM1pNUnprMFRVZzViWFJHWjJkc01ITklabmxDWjFrMU0yRmFlRFZ0VmxKM2JFWlhhMHRoVTNKYU4zVk9OREZPYlM5VFpqUmlaVU1yYWtFcmVXUkdTR3ROUnpsYWJ5OWFhM0pCTmxWMFJXNXhhR3RuVlZCMFRFRnlTelpaU21ObUswMVJWMmt2YUcxaU5qWkZiRUZzYkU0NU9HeDBTRU14VDFGSVdVUmpXbkYyUm5Od1MwcHRLMFZ1VmxGdVNrVmFTVGRsU0VkNGFrcEJhSEJoWTNoSGFWWktSVkZGUlZoS1lsRnRRMEp2YWtacU4yMHpSbmhyWm14blRsQmlkVEl2UkhGV2VsUklaME5RWVU1UE1tdG1iMmR5YVhsRVpGSnFhV0ZtV1RGdVlqQm1RMVZ1SzBvMVNuTmFXRU1yYnpOMU9WcHJhR1E1YkZaclRqUkVZbGQxZWxCTWNGTnZhamRqU1RVM1VsRmxiRTV1YTJkaWFuSnNXRE5IVmpOYWVsQkdabXBIVlc5blZtbFhZbEJaVldaaGRGUk5TWFJKZDJGc1dUVmpVVmgzTVhKaGJYcElZalk0UzBablYwMUpkalJFVlZNNWQzSklPWEpIWjBGTGFpdHZkMmhEZUc5dGRIQnBaV1JRSzJoT1RXNUpSa2hCTDJkRmQwMXlOVEpUWkZWS1kyUkZNRGR0YlROVUwySnVTbmt5WTFkQlVXbFVhMkV5VFdwSVYxSnJPVU5EY2xrMU5YQm1TREpOTjBKNFQyeGtORTFGVTBZM1NEbDFTVWRYSzFRelVHOXVVazQ0WmxWR1EwUlJNSFYyYlZSR1JuUk1hRWhsTkRCc1NFSTVTakpCVkdoRU5TOUNjMUJ1UlN0b1pVaHNXVmM1YlRoR2EzVlVka0pQTkdsaWRXNDFiM2hFVVRScVRqUnlaMndyUVVzMWRXZHBXak5xZUd4WWR6ZHFSREJ0VldndlZqSkJSMk0xYlVWUlVVRkxXRU5CYUZFdk16UmlTalEyZFdoc05tdFJLMlprUjBsMWFrVm9hbkJZTXpSbFpFaHJORlI0TmtaR1dXWkVSSFIzUzA5eVpUa3lSemhRVkhkbGJrNUxhRnB3TURZdllWRXpRWEZPTDFwTGNqUmFTVzFwYkZsa01WWklXamc1U1VKSE4weFNOMUZ4WW1nMFdrZzBSVVoyT1dFdmJFVjBVVVkxVFdZNWJVOURaRGRaZW1oT1RHRnJOR2R1UW5OaldrNU5WM3AzZEZwRkszWmxiMnhoUzFwNmRUQXpOa2x1ZWxGNFoyNVpSekJQVEVsUGJGcFNabGxGWTJKa0wxSm5SMVIzUm5WTVNUZE9RVXhZU0ZSRlRraElVakpaVDJkd1dTOUtPV3Q0TjNGMVpUQmFOMGN6ZG1NMVRrVlVUSFFyV0d0SVUwWnZRVmh4Um5FM1FXSjBha3B3ZEd3eFdHUmFTbVo1Y1ZkdU5VMW9XVTlpTWtwTVdsQmhWRGRPZEVaaVRFTjBRbEJPY3pkb0t6TndWbUp2ZFhaNVNYcGpTRWhvZGpGNU4weGtRbVpxUm1sclMwNU9hbE5ZZFRRMFlTdHpPRlJrZWtwRmVXc3hLM1pTUW01QmFTOXpjRU5hVm01T2FXc3phRlZoU2psbWEwODNkVTVFYUZjM00yVXZiM0ozU1hST1JEWlFRWEZwZEU5SVNHZDVkVXRUVG5sVWJrbDVNSFo0VnpKNk5EWlZNSEJEVjJGcWNrdGpPR1ZVUkM5M09EUjFlRk15YWtzd1RDOUhOMDVCWkVSeGRqSkdZMlIzU0hFNWN6SjZWRk5TUzNsUk5HUlpZa0pxUzNCeWFuZGxTVnBaZHpCa1JEaE1lRFF6YURWWmMzcFhkVE5uZEd3MmNWQTVZMGx5ZG5Wak5YWlVWMnMwU0ZSaFN6Wmtla05wWmxwS1pFRlNSbWhJSzBjMU5rbEdRek40TjNVelNHaGtPSFpVU21KWmNVcDBOVGwwVEVWd1ZrUTRkVXhYUzIxbmVITjZSWGwwVjJ0QlJqaFhiM281WjNVcmFFRjNZM0kyTmxWeVQyYzNiM1pOUVdObU9FVndNVWxRU1VWMGRWRkpVV1JOU0ZsaWF6MC5NREpyWl9SWXotblU1bndCMENZYS02ZFpDektQbjBVS1N3aUx6SzdvYVVOVWRTUXItZC1DVG1BY2JxaUo2LTg1RW9idXZJWDd2Wk1BMl9pV2phd0VjQQ==";
        HashMap<String,String> datas= new HashMap<String, String>();
        datas.put("username",username);
        datas.put("password",Password);
        datas.put("execution",Execution);
        datas.put("_eventId","submit");
        datas.put("geolocation","");
        Response rs= null;
        try {
            rs = Jsoup.connect("https://cas.sustc.edu.cn/cas/login")
                    .data(datas)
                    .method(Connection.Method.POST).execute();
        } catch (IOException e) {
            return false;
        }
        return rs.parse().body().getElementById("content")
                .getElementsByClass("alert alert-success").first()
                .getElementsByTag("h2").size()>0;
    }
    public static void main(String[] args) throws IOException, InterruptedException {
        System.out.println(test("11610634", "wzt123"));
        loginThread t = new loginThread("11610634", "wzt123");
        t.start();
        Thread.yield();
        System.out.println(t.i>0);

    }
}
