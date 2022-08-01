package com.marine.website.crawling;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

@SpringBootTest
public class TestCrawling {

    private WebDriver driver;

    @BeforeEach
    public void setUp() {
        System.setProperty("webdriver.chrome.driver","src/main/resources/static/chromedriver.exe");
        driver = new ChromeDriver();
    }

//    @AfterEach
//    public void testEnd() {
//        driver.quit();
//    }

    @Test
    public void test_site() {
        String baseurl ="https://www.naver.com/";
        baseurl ="https://korean.visitseoul.net/restaurants?curPage=3";
        driver.get(baseurl);

        WebElement u_skip =driver.findElement(By.cssSelector("#mf_btnMove11")); //driver.findElement(By.id("mf_btnMove11"));
        u_skip.click();
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);

        // 선박 입출항 정보로 이동
        WebElement element = driver.findElement(By.cssSelector("#mf_tacMain_contents_M0182_body_chkSrchListPrtAgCd_main_tbody > tr > td.w2checkcombobox_col_label"));
        element.click();

        // 820 울산 항만 코드 입력 후 클릭~
        WebElement input_port = driver.findElement(By.cssSelector("#mf_tacMain_contents_M0182_body_chkSrchListPrtAgCd_itemTable_58"));
        input_port.click();

        // 검색 버튼을 누른다 !
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        WebElement button = driver.findElement(By.xpath("//*[@id=\"mf_tacMain_contents_M0182_body_btnSrch_btnSearch\"]/a"));
        button.click();


        try {


            //System.out.println("u_skip = " +  u_skip.getText());
        }catch (Exception e)
        {
            System.out.println("fail");
        }

    }

    @Test
    public void test_site2() {

        String clientId = "8v1d7xdlkc";
        String clientSecret = "p75ej6MbvLyiEzZbPduseqzo9fapDYXArlTkNJKo";
        String baseurl = "https://naveropenapi.apigw.ntruss.com/map-geocode/v2/geocode?query=";
        String address = "서울시 중구 을지로 15길 6-5";

        try {
            // 기본 세팅
            String addr=URLEncoder.encode(address, "UTF-8");
            URL url = new URL(baseurl + addr);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            con.setRequestProperty("X-NCP-APIGW-API-KEY-ID", clientId);
            con.setRequestProperty("X-NCP-APIGW-API-KEY", clientSecret);


            BufferedReader br;
            int responseCode = con.getResponseCode(); // 200
            if (responseCode == 200) {
                br = new BufferedReader(new InputStreamReader(con.getInputStream(), "UTF-8"));
            } else {
                br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
            }
            System.out.println(br);
            // 받아 온 json값 읽어들이는 것임
            String line;
            StringBuffer response = new StringBuffer(); // JSON
            while ((line = br.readLine()) != null) {
                response.append(line);
            }
            br.close();
            System.out.println("response = " + response);

            JSONParser jpr = new JSONParser();
            JSONObject jarr = (JSONObject) jpr.parse(response.toString());
            JSONArray arr = (JSONArray) jarr.get("addresses");
            System.out.println("okok" + arr.size());
            for (int i = 0; i < arr.size(); i++) {
                JSONObject temp = (JSONObject) arr.get(i);
                System.out.println("address : " + temp.get("roadAddress"));
                System.out.println("jibunAddress : " + temp.get("jibunAddress"));
                System.out.println("경도 : " + temp.get("x"));
                System.out.println("위도 : " + temp.get("y"));
            }


        } catch (Exception e) {

        }

    }
}
