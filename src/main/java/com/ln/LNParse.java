//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.ln;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.security.InvalidKeyException;
import java.security.interfaces.RSAPublicKey;

import org.apache.commons.codec.binary.Base64;
import org.json.JSONObject;

public class LNParse {
    public LNParse() {
    }

    public LNBean getLNBean(String licensefilepath, String key) throws Exception {
        byte[] licenseFile = Zip.getZipSomeByte(licensefilepath, "license");
        byte[] publicKey = Zip.getZipSomeByte(licensefilepath, "publicKey");
        byte[] licenseEncryptKey = Zip.getZipSomeByte(licensefilepath, "licenseEncryptKey");
        byte[] licenseFile2 = Zip.getZipSomeByte(licensefilepath, "license2");
        String realPublicKey = "";
        if("emessage2".equals(key)) {
            realPublicKey = "MFwwDQYJKoZIhvcNAQEBBQADSwAwSAJBAIJWRm0eoQNEgZB9aUlM1PoT0N7cKCBCfkecycpeKeg57e73Fcj4ik9uYrGB01t38ut45iHJi8TLoeORYuUAhWUCAwEAAQ==";
        } else if(!"ecology7".equals(key) && !"ecology8".equals(key)) {
            if("ecology9".equals(key)) {
                realPublicKey = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAyC90YpaWPbLaQwqt3TYlRqYC+gDTivXiVU2ZnL+tVop7tm1Ss8gnXnkd1I0jr2ffQK6m4HIGdz4lyxOfJVuT9hwtDpnflxK5fBIpc6N5iB3bZkes3XMJTyXY+afvh7vKf9yW0p1ZgQkMp7Ty4nRNQ1H/JV7RIUohEM24udiZNZySLpIYeAxTl8gR/EKL/YCIxBQfFEyQtijB0+X6Sfd/CWgNGVPuPr8V5nUZm8vXIszWBSPamD/yfvwNI9PAOII7OBNMXOC9BFAjTdCKkxdRS4ovu2V9STxAu0P8hhTnH0/zpxi4VOn32povh4f5J7x5eV+vSaN5G1G1zVPs5lc62QIDAQAB";
            }
        } else {
            realPublicKey = "MFwwDQYJKoZIhvcNAQEBBQADSwAwSAJBALUgEZ7eGZmJJM/3Ajj5Zdd2MG1ZONVybJV+v+jQT+csNWBBqxosLVlWvwaod1ix8Gg9GsyRJgoTs1Mg25raZcsCAwEAAQ==";
        }

        String publicKeyStr = new String(Base64.encodeBase64(publicKey));
        if(!realPublicKey.equals(publicKeyStr)) {
            throw new Exception("license error!");
        } else {
            JSONObject jsonLicense;
            try {
                byte[] licenseKey = RSACoder.decryptByPublicKey(licenseEncryptKey, publicKey);
                jsonLicense = new JSONObject(new String(DESCoder.decrypt(licenseFile, licenseKey), "GBK"));
            } catch (InvalidKeyException var15) {
                var15.printStackTrace();
                byte[] licenseInfo2 = DESCoder.decrypt(licenseFile2, key.getBytes());
                jsonLicense = new JSONObject(new String(licenseInfo2));
            }

            LNBean lnb = new LNBean();
            lnb.setCompanyname(jsonLicense.getString("companyname"));
            lnb.setLicensecode(jsonLicense.getString("licensecode"));
            lnb.setHrmnum(jsonLicense.getString("hrmnum"));
            lnb.setExpiredate(jsonLicense.getString("expiredate"));
            lnb.setConcurrentFlag(jsonLicense.getString("concurrentFlag"));
            lnb.setLicense(jsonLicense.getString("license"));

            try {
                lnb.setCid(jsonLicense.getString("cid"));
            } catch (Exception var14) {
                System.out.println(var14);
            }

            try {
                lnb.setScType(jsonLicense.getString("scType"));
            } catch (Exception var13) {
                System.out.println(var13);
            }

            try {
                lnb.setScCount(jsonLicense.getString("scCount"));
            } catch (Exception var12) {
                System.out.println(var12);
            }

            return lnb;
        }
    }

    public boolean checkLicesne(LNBean lnb) {
        boolean returnValue = false;
        String src = lnb.getCompanyname() + lnb.getLicensecode() + "ALL" + lnb.getHrmnum() + lnb.getExpiredate() + lnb.getConcurrentFlag();
        MD5 md5 = new MD5();
        if(lnb.getLicense().equals(md5.getMD5ofStr(src))) {
            returnValue = true;
        }

        return returnValue;
    }

    public static Object unserializeObj(byte[] bytes)
    {
        ByteArrayInputStream byteInputStream = null;

        ObjectInputStream inputStream = null;

        try
        {
            byteInputStream = new ByteArrayInputStream(bytes);

            inputStream = new ObjectInputStream(byteInputStream);

            return inputStream.readObject();
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return null;
        }
        finally
        {
            if (null != byteInputStream)
            {
                try
                {
                    byteInputStream.close();
                }
                catch (IOException e)
                {
                    e.printStackTrace();
                    return null;
                }
            }

            if (null != inputStream)
            {
                try
                {
                    inputStream.close();
                }
                catch (IOException e)
                {
                    e.printStackTrace();
                    return null;
                }
            }
        }
    }

}
