package com.marine.website.visualization;

import org.json.simple.*;
import org.json.simple.parser.JSONParser;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

@Service
public class GeoService {

    public GeoService(String clientId, String clientSecret, String url, String addr) {
        this.clientId = clientId;
        this.clientSecret = clientSecret;
        this.url = url;
        this.address = addr;
    }

    private String clientId ="";
    private String clientSecret = "";
    private String url = "https://naveropenapi.apigw.ntruss.com/map-geocode/v2/geocode?query=";
    private String address;

    public GeoService()
    {

    }

    public String adder(String address){
        {
            this.address = address;
            try{
                // 기본 세팅

                String addr= URLEncoder.encode(address, "UTF-8");
                URL url=new URL(this.url+addr);
                HttpURLConnection con=(HttpURLConnection) url.openConnection();
                con.setRequestMethod("GET");
                con.setRequestProperty("X-NCP-APIGW-API-KEY-ID", clientId);
                con.setRequestProperty("X-NCP-APIGW-API-KEY", clientSecret);


                BufferedReader br;
                int responseCode=con.getResponseCode(); // 200
                if(responseCode==200) {
                    br=new BufferedReader(new InputStreamReader(con.getInputStream(), "UTF-8"));
                }else {
                    br=new BufferedReader(new InputStreamReader(con.getErrorStream()));
                }
                System.out.println(br);
                // 받아 온 json값 읽어들이는 것임
                String line;
                StringBuffer response=new StringBuffer(); // JSON
                while((line=br.readLine())!=null) {
                    response.append(line);
                }
                br.close();
                System.out.println("response = " + response);

                JSONParser jpr = new JSONParser();
                JSONObject jarr = (JSONObject) jpr.parse(response.toString());
                JSONArray arr = (JSONArray)jarr.get("addresses");
                System.out.println("okok"+arr.size());
                for(int i=0;i<arr.size();i++){
                    JSONObject temp = (JSONObject) arr.get(i);
                    System.out.println("address : "+temp.get("roadAddress"));
                    System.out.println("jibunAddress : "+temp.get("jibunAddress"));
                    System.out.println("경도 : "+temp.get("x"));
                    System.out.println("위도 : "+temp.get("y"));
                }



            }catch (Exception e)
            {

            }
            return " ";
        }
    }
}
