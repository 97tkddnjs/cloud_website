package com.marine.website.crawling;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class Crawling {
    private WebDriver driver;

    public Crawling() {
        System.setProperty("webdriver.chrome.driver","src/main/resources/static/chromedriver.exe");
        driver = new ChromeDriver();

    }
    public void test_site() {
        String baseurl ="https://www.naver.com/";
        baseurl ="https://new.portmis.go.kr/portmis/websquare/websquare.jsp?w2xPath=/portmis/w2/main/intro.xml";
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
}
