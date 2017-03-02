package com.wit.paperadmin.Utils;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.StringTokenizer;

/**
 * Created by hps on 2017/2/28.
 */
public class AESUtil {
    public static byte[] aesEncrypt(byte[] bytes1, String key) throws Exception {
        if (bytes1 == null || key == null) return null;
        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
        cipher.init(Cipher.ENCRYPT_MODE, new SecretKeySpec(key.getBytes("utf-8"), "AES"));
        return cipher.doFinal(bytes1);
    }

    public static String aesEncrypt(String str, String key) throws Exception {
        if (str == null || key == null) return null;
        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
        cipher.init(Cipher.ENCRYPT_MODE, new SecretKeySpec(key.getBytes("utf-8"), "AES"));
        byte[] bytes = cipher.doFinal(str.getBytes("utf-8"));
        return Base64.encode(bytes);
    }

    public static String aesDecrypt(String str, String key) throws Exception {
        if (str == null || key == null) return null;
        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
        cipher.init(Cipher.DECRYPT_MODE, new SecretKeySpec(key.getBytes("utf-8"), "AES"));
        byte[] bytes = Base64.decode(str);
        bytes = cipher.doFinal(bytes);
        return new String(bytes, "utf-8");
    }

    public static byte[] aesDecrypt(byte[] bytes, String key) throws Exception {
        if (bytes == null || key == null) return null;
        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
        cipher.init(Cipher.DECRYPT_MODE, new SecretKeySpec(key.getBytes("utf-8"), "AES"));
        return cipher.doFinal(bytes);
    }

    /**
     * AES算法加密文件
     * @param src 原文件路径
     * @param des 目标文件路径
     * @param key 加密密钥
     */
    public static void aesEncryptFile(String src, String des, String key) {
        InputStream in = null;
        SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMddHHmmss");
        try {

            // 一次读4096个字节
            byte[] tempbytes = new byte[4096];
            int byteread = 0;
            in = new FileInputStream(src);

            // 获取原文件
            File file = new File(src);

            // 创建目标文件
            File file1=new File(des);
            if(!file1.exists())
                file1.createNewFile();

            FileOutputStream out=new FileOutputStream(file1, false);

            // 写入加密标识, 11位
            out.write("ENCRYPTBYHE".getBytes());

            // 写入加密时间，14位
            out.write(sdf.format(new Date()).getBytes());

            // 获取文件名
            String fileName = file.getName();

            // 获取文件名长度
            String nameLength = "";
            if(fileName.getBytes("UTF-8").length < 10) {
                nameLength = "0" + fileName.getBytes("UTF-8").length;
            } else{
                nameLength = "" + fileName.getBytes("UTF-8").length;
            }

            // 写入文件名长度，2位
            out.write(nameLength.getBytes());

            // 写入文件名
            out.write(fileName.getBytes("UTF-8"));

            // 获取文件创建时间
            String ct = getFileCreateDate(file);
            Date date1 =  new Date(ct);

            // 写入文件创建时间14位
            out.write(sdf.format(date1).getBytes());

            Long time = file.lastModified();
            Date date = new Date(time);

            // 写入文件修改时间14位
            out.write(sdf.format(date).getBytes());

            // 读入多个字节到字节数组中，byteread为一次读入的字节数
            while ((byteread = in.read(tempbytes)) != -1) {
                out.write(aesEncrypt(tempbytes, key));
                //System.out.write(tempbytes, 0, byteread);
            }

            out.close();
        } catch (Exception e1) {
            e1.printStackTrace();
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e1) {
                }
            }
        }
    }

    /**
     * 获取文件创建时间
     * @param _file
     * @return
     */
    public static String getFileCreateDate(File _file) {

        File file = _file;
        try {
            Process ls_proc = Runtime.getRuntime().exec(

                    "cmd.exe /c dir " + file.getAbsolutePath() + " /tc");
            BufferedReader br = new BufferedReader(new InputStreamReader(ls_proc.getInputStream()));
            for (int i = 0; i < 5; i++) {
                br.readLine();
            }

            String stuff = br.readLine();
            StringTokenizer st = new StringTokenizer(stuff);
            String dateC = st.nextToken();
            String time = st.nextToken();
            String datetime = dateC + " " + time;
            br.close();
            return datetime;

        } catch (Exception e) {
            return null;
        }
    }

    public static String getKey() {
        String seed = String.valueOf(System.currentTimeMillis());
        return Md5Wrapper.getMD5(seed);
    }
}
