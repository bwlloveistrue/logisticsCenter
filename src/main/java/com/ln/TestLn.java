package com.ln;

import org.apache.commons.codec.binary.Base64;
import org.json.JSONObject;

import javax.servlet.ServletOutputStream;
import java.io.*;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.util.Map;
import java.util.UUID;

/**
 * Created by Administrator on 2020/6/18.
 */
public class TestLn {
    public static void main(String[] args) {
        // 示例
        LN ckLicense = new LN();
        ckLicense.checkLicense("I:\\workspace\\ecology9\\license\\D21732DE448E68304984A5FF3D0E0C8E.license");
//        ckLicense.checkLicense("I:\\workspace\\createLicense\\createLicense.license");

        byte[] publicKey ;
        byte[] license;
        byte[] license2 ;
        byte[] licenseEncryptKey;
        byte[] licenseDencryKey;
        String content = "{\"loginid\":\"wld\",\"isAdmin\":false,\"userid\":3,\"status\":true}";
        try {
            Map rsaKey = RSACoder.initKey();
            RSAPublicKey rsaPublicKey = (RSAPublicKey)rsaKey.get("RSAPublicKey");
            RSAPrivateKey rsaPrivateKey = (RSAPrivateKey)rsaKey.get("RSAPrivateKey");
            String publicKeyString = new String(Base64.encodeBase64(rsaPublicKey.getEncoded()));
            // 得到私钥字符串
            String privateKeyString = new String(Base64.encodeBase64((rsaPrivateKey.getEncoded())));
            System.out.println(publicKeyString);
            System.out.println(privateKeyString);
            licenseEncryptKey = rsaPublicKey.getEncoded();
            licenseDencryKey = rsaPrivateKey.getEncoded();
            // 生成publicKey：Base64.decodeBase64(publicKey)
            publicKey = Base64.decodeBase64("MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAyC90YpaWPbLaQwqt3TYlRqYC+gDTivXiVU2ZnL+tVop7tm1Ss8gnXnkd1I0jr2ffQK6m4HIGdz4lyxOfJVuT9hwtDpnflxK5fBIpc6N5iB3bZkes3XMJTyXY+afvh7vKf9yW0p1ZgQkMp7Ty4nRNQ1H/JV7RIUohEM24udiZNZySLpIYeAxTl8gR/EKL/YCIxBQfFEyQtijB0+X6Sfd/CWgNGVPuPr8V5nUZm8vXIszWBSPamD/yfvwNI9PAOII7OBNMXOC9BFAjTdCKkxdRS4ovu2V9STxAu0P8hhTnH0/zpxi4VOn32povh4f5J7x5eV+vSaN5G1G1zVPs5lc62QIDAQAB");
            String publicKeyStr = new String(Base64.encodeBase64(publicKey));
            System.out.println(publicKeyStr);
            byte[] licenseKey = RSACoder.encryptByPrivateKey(publicKey, licenseDencryKey);
            license = DESCoder.encrypt(content.getBytes(), licenseKey);
            license2 = DESCoder.encrypt(content.getBytes(), licenseKey);
            createFile(publicKey,"I:\\workspace\\createLicense\\publicKey");
            createFile(license,"I:\\workspace\\createLicense\\license");
            createFile(license2,"I:\\workspace\\createLicense\\license2");
            createFile(licenseEncryptKey,"I:\\workspace\\createLicense\\licenseEncryptKey");
        } catch (Exception e) {
            e.printStackTrace();
        }


        JSONObject jsonLicense;
    }

    public static void createFile(byte[] data,String fileRealPath) throws Exception{
        File file = new File(fileRealPath);    //1、建立连接
        OutputStream os = null;
        try {
            File fileParent = file.getParentFile();
            if(!fileParent.exists()) {
                fileParent.mkdirs();
            }
            if(!file.exists()){
                file.createNewFile();
            }
            os = new FileOutputStream(file,true);
            os.write(data, 0, data.length);    //3、写入文件
            os.flush();    //将存储在管道中的数据强制刷新出去
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.out.println("文件没有找到！");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("写入文件失败！");
        }finally {
            if (os != null) {
                try {
                    os.close();
                } catch (IOException e) {
                    e.printStackTrace();
                    System.out.println("关闭输出流失败！");
                }
            }
        }
    }

}
