package com.ln;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Enumeration;
import org.apache.commons.compress.archivers.zip.ZipArchiveEntry;
import org.apache.commons.compress.archivers.zip.ZipArchiveOutputStream;
import org.apache.commons.compress.archivers.zip.ZipFile;
import org.apache.commons.compress.utils.IOUtils;
import org.apache.commons.io.FileUtils;

public class Zip {
    private OutputStream out = null;
    private BufferedOutputStream bos = null;
    private ZipArchiveOutputStream zaos = null;
    private String zipFileName = null;

    public void packToolFiles(String dirpath, String pathName) throws FileNotFoundException, IOException {
        if(pathName != null && !pathName.equals("")) {
            pathName = pathName + File.separator;
        }

        this.packToolFiles(this.zaos, dirpath, pathName);
    }

    public void packToolFile(String dirpath, String pathName, String fileName) throws FileNotFoundException, IOException {
        if(pathName != null && !pathName.equals("")) {
            pathName = pathName + File.separator;
        }

        File f = new File(dirpath);
        if(f.isFile()) {
            if("".equals(fileName)) {
                fileName = f.getName();
            }

            this.zaos.putArchiveEntry(new ZipArchiveEntry(pathName + fileName));
            IOUtils.copy(new FileInputStream(f.getAbsolutePath()), this.zaos);
            this.zaos.closeArchiveEntry();
        }
    }

    public void packBytes2Zip(byte[] bytes, String zipInnerPathName, String fileName) throws FileNotFoundException, IOException {
        this.zaos.putArchiveEntry(new ZipArchiveEntry(zipInnerPathName + fileName));
        IOUtils.copy(new ByteArrayInputStream(bytes), this.zaos);
        this.zaos.closeArchiveEntry();
    }

    public void packToolFiles(ZipArchiveOutputStream zaos, String dirpath, String pathName) throws FileNotFoundException, IOException {
        File dir = new File(dirpath);
        if(dir.isFile()) {
            zaos.putArchiveEntry(new ZipArchiveEntry(pathName + dir.getName()));
            IOUtils.copy(new FileInputStream(dir.getAbsolutePath()), zaos);
            zaos.closeArchiveEntry();
        } else {
            File[] files = dir.listFiles();
            if(files != null && files.length >= 1) {
                for(int i = 0; i < files.length; ++i) {
                    if(files[i].isDirectory()) {
                        this.packToolFiles(zaos, files[i].getAbsolutePath(), pathName + files[i].getName() + File.separator);
                    } else {
                        zaos.putArchiveEntry(new ZipArchiveEntry(pathName + files[i].getName()));
                        IOUtils.copy(new FileInputStream(files[i].getAbsolutePath()), zaos);
                        zaos.closeArchiveEntry();
                    }
                }

            }
        }
    }

    public static byte[] getZipSomeByte(String zipFilename, String keyPath) throws IOException {
        File zipfile = new File(zipFilename);
        byte[] returnByte = (byte[])null;
        if(!zipfile.exists()) {
            throw new IOException("指定的解压文件不存在：\t" + zipFilename);
        } else {
            ZipFile zf = new ZipFile(zipfile, "UTF-8");
            Enumeration zipArchiveEntrys = zf.getEntries();

            while(zipArchiveEntrys.hasMoreElements()) {
                ZipArchiveEntry zipArchiveEntry = (ZipArchiveEntry)zipArchiveEntrys.nextElement();
                if(keyPath.equals(zipArchiveEntry.getName())) {
                    returnByte = org.apache.commons.io.IOUtils.toByteArray(zf.getInputStream(zipArchiveEntry));
                    break;
                }
            }

            zf.close();
            return returnByte;
        }
    }

    public static void unZipToFolder(String zipfilename, String outputdir) throws IOException {
        File zipfile = new File(zipfilename);
        if(zipfile.exists()) {
            outputdir = outputdir + File.separator;
            FileUtils.forceMkdir(new File(outputdir));
            ZipFile zf = new ZipFile(zipfile, "UTF-8");
            Enumeration zipArchiveEntrys = zf.getEntries();

            while(zipArchiveEntrys.hasMoreElements()) {
                ZipArchiveEntry zipArchiveEntry = (ZipArchiveEntry)zipArchiveEntrys.nextElement();
                if(zipArchiveEntry.isDirectory()) {
                    FileUtils.forceMkdir(new File(outputdir + zipArchiveEntry.getName() + File.separator));
                } else {
                    IOUtils.copy(zf.getInputStream(zipArchiveEntry), FileUtils.openOutputStream(new File(outputdir + zipArchiveEntry.getName())));
                }
            }

        } else {
            throw new IOException("指定的解压文件不存在：\t" + zipfilename);
        }
    }

    public Zip(String zipFileName) {
        this.zipFileName = zipFileName;
    }

    public void createZipOut() throws FileNotFoundException, IOException {
        File f = new File(this.zipFileName);
        this.out = new FileOutputStream(f);
        this.bos = new BufferedOutputStream(this.out);
        this.zaos = new ZipArchiveOutputStream(this.bos);
        this.zaos.setEncoding("UTF-8");
    }

    public void closeZipOut() throws Exception {
        this.zaos.flush();
        this.zaos.close();
        this.bos.flush();
        this.bos.close();
        this.out.flush();
        this.out.close();
    }
}
