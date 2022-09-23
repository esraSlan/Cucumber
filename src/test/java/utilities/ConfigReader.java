package utilities;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {
    public static Properties properties;

    static{//static blok olusturduk.çünkü static herseydenönce calısıyor yani herseyden önce bu çalısack demek//interwiev sorusu olabilir,static blok nerde kklndn?
            //static blok bir kere calıst mı yeter ama methodiçinde yazsa idik herseferinde load edecekti
        String dosyaYolu="configuration.properties";
        try {                  //throw kullanmadık çünküthrow olsa kullncgmz her dosyada throw kullanmamzı gerekir
            FileInputStream fis=new FileInputStream(dosyaYolu);
            // fis dosyayolunu tanimladigimiz configuration.properties dosyasini okudu
            properties=new Properties();
            properties.load(fis); // fis'in okudugu bilgileri properties'e yukledi



        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static String getProperty(String key){
        /*
          test method'undan yolladigimiz string key degerini alip
          Properties class'indan getProperty( ) method'unu kullanarak
          bu key'e ait value'u bize getirdi
         */
        return properties.getProperty(key);
    }
}


