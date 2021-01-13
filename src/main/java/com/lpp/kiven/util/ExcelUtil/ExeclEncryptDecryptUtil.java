package com.lpp.kiven.util.ExcelUtil;

import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.openxml4j.opc.PackageAccess;
import org.apache.poi.poifs.crypt.EncryptionInfo;
import org.apache.poi.poifs.crypt.EncryptionMode;
import org.apache.poi.poifs.crypt.Encryptor;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;

public class ExeclEncryptDecryptUtil {
    /**
     * Excel加密
     * @param sourceUrl
     * @param targetUrl
     * @param pwd
     */
    public static void encryptExcel(String sourceUrl,String targetUrl,String pwd) throws Exception{
        //POI对Excel文件加密
        POIFSFileSystem fs = new POIFSFileSystem();
        EncryptionInfo info = new EncryptionInfo(EncryptionMode.standard);
        Encryptor enc = info.getEncryptor();
        enc.confirmPassword(pwd);
        OPCPackage opc = OPCPackage.open(new File(sourceUrl), PackageAccess.READ_WRITE);
        OutputStream os = enc.getDataStream(fs);
        opc.save(os);
        opc.close();

        FileOutputStream fos = new FileOutputStream(targetUrl);
        fs.writeFilesystem(fos);
        fos.close();
    }
}
