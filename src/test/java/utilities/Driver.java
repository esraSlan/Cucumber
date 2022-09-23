package utilities;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;

import java.time.Duration;

public class Driver {
    /*
  POM'de Driver icin TestBase class'ina extends etmek yerine
  Driver class'indan static method'lar kullanarak
  driver olusturup, ilgili ayarlarin yapilmasi
  ve en sonda driver'in kapatilmasi tercih edilmistir.
   */
    //testbase bizi sınırlıyor Driver clasına nisbetle:1) browser secemiyrz 2) before ve after sabit ,ayarlamıyurz

    /*POM'de Driver class'indaki getDriver()'nin obje olusturularak kullanilmasini
    engellemek icin
    Singleton pattern kullanimi benimsenmistir.
    Singleton Pattern : tekli kullanim, bir class'in farkli class'lardan
    obje olusturularak kullanimini engellemek icin kullanilir.
    Bunu saglamak icin yapmamiz gereken sey oldukca basit
    obje olusturmak icin kullanilan constructor'i private yaptiginizda
    bsaka class'larda Driver class'indan obje olusturulmasi mumkun OLAMAZ*/
    private Driver(){
        //burada da OOP kullandık//interwiev.burası görünmyen default constrctr idi. obje olusmasını engellemk için PRİVATE YAPTIK
    }
    static WebDriver driver;
    public static WebDriver getDriver(){
        if (driver==null) {  // driver'a deger atanMAmıssa..yani  eğer değer atanmamaıs ise atanacak ve o drive return olacak.ama değer atanmıs ise ise değer atama.o drive return et.yoksa her seferinde yeni sayfa acar
            switch (ConfigReader.getProperty("browser")) {
                case "chrome":
                    WebDriverManager.chromedriver().setup();
                    driver = new ChromeDriver();
                    break;
                case "safari":
                    WebDriverManager.safaridriver().setup();
                    driver = new SafariDriver();
                    break;
                case "firefox":
                    WebDriverManager.firefoxdriver().setup();
                    driver = new FirefoxDriver();
                    break;
                case "headless-chrome": // görünmez olarak chrome calısır,arka planda calsıır ve sonucu döndürür
                    WebDriverManager.chromedriver().setup();
                    driver = new ChromeDriver(new ChromeOptions().setHeadless(true));
                    break;
                default:
                    WebDriverManager.chromedriver().setup();
                    driver = new ChromeDriver();

            }
            driver.manage().window().maximize();
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
        }
        return driver;
    }
    public static void closeDriver(){
        if (driver!=null){ // driver'a deger atanmissa
            driver.close();
            driver=null;
        }
    }
}
