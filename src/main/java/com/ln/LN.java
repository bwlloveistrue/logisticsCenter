package com.ln;

import java.io.File;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.util.Utils;

public class LN {
    Date newdate = new Date();
    long datetime;
    Timestamp timestamp;
    String currentdate;
    String currenttime;
    private String companyname;
    private String license;
    private String licensecode;
    private String software;
    private String hrmnum;
    private String expiredate;
    private String message;
    private String licensepass;
    private String concurrentFlag;
    private byte[] licenseFile;
    private byte[] licenseKeyAndTypeId;
    private String cid;
    private String scType;
    private String scCount;
    private static boolean isInit = false;
    private static Map<String, Integer> ckHrmnumMap = new ConcurrentHashMap();
    private static int ckUnusedHrmnum = 0;
    private static int ckUsedHrmnum = 0;

    public LN() {
        this.datetime = this.newdate.getTime();
        this.timestamp = new Timestamp(this.datetime);
        this.currentdate = this.timestamp.toString().substring(0, 4) + "-" + this.timestamp.toString().substring(5, 7) + "-" + this.timestamp.toString().substring(8, 10);
        this.currenttime = this.timestamp.toString().substring(11, 13) + ":" + this.timestamp.toString().substring(14, 16) + ":" + this.timestamp.toString().substring(17, 19);
        this.companyname = "";
        this.license = "";
        this.licensecode = "";
        this.software = "";
        this.hrmnum = "";
        this.expiredate = "";
        this.message = "";
        this.licensepass = "";
        this.concurrentFlag = "";
//        this.staticobj = null;
        this.licenseFile = null;
        this.licenseKeyAndTypeId = null;
        this.cid = "";
        this.scType = "1";
        this.scCount = "0";
//        this.staticobj = StaticObj.getInstance();
        this.OutLicensecode();
    }

    public void setCompanyname(String newValue) {
        this.companyname = newValue.trim();
    }

    public String getCompanyname() {
        return this.companyname;
    }

    public void setLicense(String newValue) {
        this.license = newValue.trim();
    }

    public String getLicense() {
        return this.license;
    }

    public void setLicensecode(String newValue) {
        this.licensecode = newValue.trim();
    }

    public String getLicensecode() {
        return this.licensecode;
    }

    public void setSoftware(String newValue) {
        this.software = newValue.trim();
    }

    public String getSoftware() {
        return this.software;
    }

    public void setHrmnum(String newValue) {
        this.hrmnum = newValue.trim();
    }

    public String getHrmnum() {
        return this.hrmnum;
    }

    public void setExpiredate(String newValue) {
        this.expiredate = newValue.trim();
    }

    public String getExpiredate() {
        return this.expiredate;
    }

    public void setLicensepass(String newValue) {
        this.licensepass = newValue.trim();
    }

    public void setConcurrentFlag(String newValue) {
        this.concurrentFlag = newValue.trim();
    }

    public String getConcurrentFlag() {
        return this.concurrentFlag;
    }

    public String InLicense() {
        if(this.message.equals("0")) {
            return this.message;
        } else {
            this.message = this.CkLicense(this.currentdate);
            if("1".equals(this.message)) {
            }

            return this.message;
        }
    }


    public void ReadFromFile(String licensefilepath) {
        if(!isInit) {
            isInit = true;
            LnTimer stt = new LnTimer();
            ThreadWorkTimer tt = new ThreadWorkTimer(15L, stt);
            tt.start();
        }

        LNParse lnp = new LNParse();
        boolean isReload = false;

        try {
            LNBean lnb = lnp.getLNBean(licensefilepath, "ecology9");
            isReload = true;
            this.companyname = lnb.getCompanyname();
            this.license = lnb.getLicense();
            this.software = "ALL";
            this.hrmnum = lnb.getHrmnum();
            this.expiredate = lnb.getExpiredate();
            this.concurrentFlag = lnb.getConcurrentFlag();
            this.cid = lnb.getCid();
            this.scType = lnb.getScType();
            this.scCount = lnb.getScCount();
            if(Utils.null2String(lnb.getCid()).equals("")) {
                this.message = "0";
            }
        } catch (Exception var12) {

        } finally {

        }

    }

    public static void main(String[] args) {
        LN ln = new LN();
        String ncid = ln.ReadFromFile2("C:\\Users\\Administrator\\Downloads\\0FC9AA5719DE2F25E5E8A7FE5D21C95B_ecology8.license");
        System.out.println("ncid" + ncid);
    }

    public String ReadFromFile2(String licensefilepath) {
        LNParse lnp = new LNParse();

        try {
            LNBean lnb = lnp.getLNBean(licensefilepath, "ecology9");
            return Utils.null2String(lnb.getCid());
        } catch (Exception var4) {
            this.message = "4";
            System.out.println(var4);
            return "";
        }
    }

    public boolean checkLicense(String licensefilepath) {
        LNParse lnp = new LNParse();

        try {
            LNBean lnb = lnp.getLNBean(licensefilepath, "ecology9");
            String src = lnb.getCompanyname() + this.licensecode + "ALL" + lnb.getHrmnum() + lnb.getExpiredate() + lnb.getConcurrentFlag();
            byte[] srcBytes = (byte[])null;

            try {
                srcBytes = src.getBytes("GBK");
            } catch (Exception var7) {
                srcBytes = src.getBytes();
            }

            MD5 md5 = new MD5();
            return !lnb.getLicense().equals("") && lnb.getLicense().equals(md5.getMD5ofStr(srcBytes, src.length()));
        } catch (Exception var8) {
            return false;
        }
    }

    public int CKLiCode() {
        int returnInt = 1;
        String src = this.companyname + this.licensecode + this.software + this.hrmnum + this.expiredate + this.concurrentFlag;
        byte[] srcBytes = (byte[])null;

        try {
            srcBytes = src.getBytes("GBK");
        } catch (Exception var5) {
            srcBytes = src.getBytes();
        }

        MD5 md5 = new MD5();
        if(!this.license.equals("") && this.license.equals(md5.getMD5ofStr(srcBytes, src.length()))) {
            this.message = "0";
        }

        return returnInt;
    }

    public void validateLicense() {
    }

    public String OutLicensecode() {
        this.licensecode = this.MakeLicensecode();
        return this.licensecode;
    }

    public String MakeLicensecode() {
        GetPhysicalAddress PhysicalAddress = new GetPhysicalAddress();
        List<String> addrList = PhysicalAddress.getPhysicalAddressByCzl();
        Map<String, Boolean> licenseCodes = null;
        MD5 md5 = new MD5();
        String codeStr = "";
        if(addrList.size() > 1) {
            if(licenseCodes == null) {
                licenseCodes = new ConcurrentHashMap();
            }

            for(Iterator var6 = addrList.iterator(); var6.hasNext(); ) {
                String addr = (String)var6.next();
                this.licensecode = md5.getMD5ofStr(addr);
                String filename = "I:\\workspace\\ecology9\\license" + "license" + File.separatorChar + this.licensecode + ".license";
                if(licenseCodes != null && licenseCodes.size() != 0 && licenseCodes.get(filename) != null && ((Boolean)licenseCodes.get(filename)).booleanValue()) {
                    try {
                        if(this.CkLicense(this.currentdate).equals("1")) {
                            return this.licensecode;
                        }

                        licenseCodes.put(filename, Boolean.valueOf(false));
                    } catch (Exception var9) {
                        System.out.println("license error");
                    }
                } else {
                    try {
                        File licenseFile = new File(filename);
                        if(licenseFile.exists()) {
                            if(this.CkLicense(this.currentdate).equals("1")) {
                                licenseCodes.put(filename, Boolean.valueOf(true));
                                if(codeStr == null || codeStr.equals("")) {
                                    codeStr = this.licensecode;
                                }
                            } else {
                            }
                        }
                    } catch (Exception var10) {
                        System.out.println("license error");
                    }
                }
            }
        }
        this.licensecode = codeStr.equals("")?md5.getMD5ofStr((String)addrList.get(0)):codeStr;
        return this.licensecode;
    }

    public String CkLicense(String currentdate) {
        String sql = "";
        String filename = "I:\\workspace\\ecology9\\license" + "license" + File.separatorChar + this.licensecode + ".license";
        this.ReadFromFile(filename);
        if(this.expiredate.compareTo(currentdate) < 0) {
            return "4";
        } else {
            String temphrmnum = this.hrmnum;
            String src = this.companyname + this.licensecode + this.software + temphrmnum + this.expiredate + this.concurrentFlag;
            byte[] srcBytes = (byte[])null;

            try {
                srcBytes = src.getBytes("GBK");
            } catch (Exception var8) {
                srcBytes = src.getBytes();
            }

            MD5 md5 = new MD5();
            if(!this.license.equals("") && this.license.equals(md5.getMD5ofStr(srcBytes, src.length()))) {
                this.message = "1";
            } else {
                this.message = "0";
            }

            return this.message;
        }
    }
}
