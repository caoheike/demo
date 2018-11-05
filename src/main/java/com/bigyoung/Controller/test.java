package com.bigyoung.Controller;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

public class test {
    public static void main(String[] args) {
        //加载驱动
        System.setProperty("webdriver.chrome.driver", "C://ie//test/chromedriver.exe");
        //创建浏览器驱动
         WebDriver driver=new ChromeDriver(); //chrome
        //请求地址
        driver.get("https://www.facebook.com/");
        //设置浏览器大小,可在后台运行，不影响服务器使用
        driver.manage().window().maximize();
        //文本框输入数据库存储的账号,当然也可以实现爬虫主动注册,原理一致
        driver.findElement(By.name("email")).sendKeys("17316650877");
        driver.findElement(By.name("pass")).sendKeys("Weizai@123");
        //定位按钮，进行点击操作
        driver.findElement(By.xpath("//*[@id=\"loginbutton\"]/input")).click();
        //由于时间关系 这里登录之后直接将爬取的内容输出
        System.out.println(driver.getPageSource());

        //你们需要做的是
        //1根据标题 或者登录成功的字样 判断是否登录成功
        //2登录成功后在检索框 模拟输入 接口传入的 关键字 然后模拟点击 搜索按钮
        //3 拿到PageSource 网页内容进行筛选,解析


    }
}