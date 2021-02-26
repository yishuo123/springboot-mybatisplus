package com.yishuo.utils;


import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.tools.zip.ZipEntry;
import org.apache.tools.zip.ZipFile;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.*;

@Component
public class ZipUtil {


    @PostConstruct
    public void init() {
        zipUtil = this;
    }

    public static ZipUtil zipUtil;



    static void getType(Map<String, Integer> classMap, JSONObject parseOutputs) {
        Object annotation = parseOutputs.get("object");
        JSONArray jsonArray = JSONObject.parseArray(annotation.toString());
        for (int i = 0; i < jsonArray.size(); i++) {
            JSONObject cla = (JSONObject) JSONObject.parse(jsonArray.getJSONObject(i).toString());
            Object name2 = cla.get("name");
            // 放在 redis里面
//            RarUtil.setCount(classMap, name2.toString(), classMap.containsKey(name2), classMap.get(name2));
        }
    }

    private static String getName(ZipEntry entry, int count, String name) {
        if (count == 2) {
            String str = entry.getName();
            name = str.substring(str.indexOf("/") + 1);
        }
        if (count == 3) {
            String str = entry.getName();
            name = str.substring(str.lastIndexOf("/") + 1);
            name = "outputs/" + name;
        }
        if(count == 1){
            String str = entry.getName();
            if(!str.contains("outputs")){
                name = str.substring(str.indexOf("/") + 1);
            }
        }
        return name;
    }

    static void readTypeAndCount(Map<String, Integer> classMap, JSONObject parseOutputs) {
        Object annotation = parseOutputs.get("annotation");
        JSONArray jsonArray = JSONObject.parseArray(annotation.toString());
        for (int i = 0; i < jsonArray.size(); i++) {
            JSONObject cla = (JSONObject) JSONObject.parse(jsonArray.getJSONObject(i).toString());
            Object clss = cla.get("class");
            JSONObject claValue = (JSONObject) JSONObject.parse(clss.toString());
            String value = (String) claValue.get("value");
            if (classMap.containsKey(value)) {
                Integer countNum = classMap.get(value);
                ++countNum;
                classMap.put(value, countNum);

            } else {
                classMap.put(value, 1);
            }
        }
    }

    public static JSONObject getJsonObject(InputStream stream1) throws IOException {
        ByteArrayOutputStream results = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int length;
        while ((length = stream1.read(buffer)) != -1) {
            results.write(buffer, 0, length);
        }
        return (JSONObject) JSONObject.parse(results.toString());
    }


    /**
     * 删除单个文件
     *
     * @param fileName 被删除文件的文件名
     * @return 单个文件删除成功返回true, 否则返回false
     */
    public static boolean deleteFile(String fileName) {
        File file = new File(fileName);
        if (file.isFile() && file.exists()) {
            file.delete();
            System.out.println("删除单个文件" + fileName + "成功！");
            return true;
        } else {
            System.out.println("删除单个文件" + fileName + "失败！");
            return false;
        }
    }

    /**
     * 删除目录（文件夹）以及目录下的文件
     *
     * @param dir 被删除目录的文件路径
     * @return 目录删除成功返回true, 否则返回false
     */
    public static boolean deleteDirectory(String dir) {
        // 如果dir不以文件分隔符结尾，自动添加文件分隔符
        if (!dir.endsWith(File.separator)) {
            dir = dir + File.separator;
        }
        File dirFile = new File(dir);
        // 如果dir对应的文件不存在，或者不是一个目录，则退出
        if (!dirFile.exists() || !dirFile.isDirectory()) {
            System.out.println("删除目录失败" + dir + "目录不存在！");
            return false;
        }
        boolean flag = true;
        // 删除文件夹下的所有文件(包括子目录)
        File[] files = dirFile.listFiles();
        for (int i = 0; i < files.length; i++) {
            // 删除子文件
            if (files[i].isFile()) {
                flag = deleteFile(files[i].getAbsolutePath());
                if (!flag) {
                    break;
                }
            }
            // 删除子目录
            else {
                flag = deleteDirectory(files[i].getAbsolutePath());
                if (!flag) {
                    break;
                }
            }
        }

        if (!flag) {
            System.out.println("删除目录失败");
            return false;
        }

        // 删除当前目录
        if (dirFile.delete()) {
            System.out.println("删除目录" + dir + "成功！");
            return true;
        } else {
            System.out.println("删除目录" + dir + "失败！");
            return false;
        }
    }

    // param path 文件夹完整绝对路径
    public static boolean delAllFile(String path) {
        boolean flag = false;
        File file = new File(path);
        if (!file.exists()) {
            return flag;
        }
        if (!file.isDirectory()) {
            return flag;
        }
        String[] tempList = file.list();
        File temp = null;
        for (int i = 0; i < tempList.length; i++) {
            if (path.endsWith(File.separator)) {
                temp = new File(path + tempList[i]);
            } else {
                temp = new File(path + File.separator + tempList[i]);
            }
            if (temp.isFile()) {
                temp.delete();
            }
            if (temp.isDirectory()) {
                delAllFile(path + "/" + tempList[i]);// 先删除文件夹里面的文件
                delFolder(path + "/" + tempList[i]);// 再删除空文件夹
                flag = true;
            }
        }
        return flag;
    }

    // 删除文件夹
    // param folderPath 文件夹完整绝对路径

    public static void delFolder(String folderPath) {
        try {
            delAllFile(folderPath); // 删除完里面所有内容
            String filePath = folderPath;
            filePath = filePath.toString();
            File myFilePath = new File(filePath);
            myFilePath.delete(); // 删除空文件夹
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static int getImagePixel(File file) throws Exception {
        int[] rgb = new int[3];
        BufferedImage bi = null;
        try {
            bi = ImageIO.read(file);
        } catch (Exception e) {
            e.printStackTrace();
        }
        int width = bi.getWidth();
        int height = bi.getHeight();
        int minx = bi.getMinX();
        int miny = bi.getMinY();
        for (int i = minx; i < width; i++) {
            for (int j = miny; j < height; j++) {
                int pixel = bi.getRGB(i, j); // 下面三行代码将一个数字转换为RGB数字
                rgb[0] = (pixel & 0xff0000) >> 16;
                rgb[1] = (pixel & 0xff00) >> 8;
                rgb[2] = (pixel & 0xff);
                if (rgb[2] != 0) {

                    return rgb[2];

                }
            }
        }

        return 0;
    }



    public static List<File> unzipFileBak(File zipFile, String descDir) {
        List<File> _list = new ArrayList<File>();
        try {
            ZipFile _zipFile = new ZipFile(zipFile, "GBK");
            for (Enumeration entries = _zipFile.getEntries(); entries.hasMoreElements(); ) {
                ZipEntry entry = (ZipEntry) entries.nextElement();
                File _file = new File(descDir + File.separator + entry.getName());
                if (entry.isDirectory()) {
                    _file.mkdirs();
                } else {
                    File _parent = _file.getParentFile();
                    if (!_parent.exists()) {
                        _parent.mkdirs();
                    }
                    InputStream _in = _zipFile.getInputStream(entry);
                    OutputStream _out = new FileOutputStream(_file);
                    int len = 0;
                    byte[] _byte = new byte[1024];
                    while ((len = _in.read(_byte)) > 0) {
                        _out.write(_byte, 0, len);
                    }
                    _in.close();
                    _out.flush();
                    _out.close();
                    _list.add(_file);
                }
            }
        } catch (IOException e) {
        }
        return _list;
    }

    /**
     * @param delpath
     * @return void
     * @Author AlphaJunS
     * @Date 11:36 2020/3/8
     * @Description 对临时生成的文件夹和文件夹下的文件进行删除
     */
    public static void deletefile(String delpath) {
        try {
            File file = new File(delpath);
            if (!file.isDirectory()) {
                file.delete();
            } else if (file.isDirectory()) {
                String[] fileList = file.list();
                for (int i = 0; i < fileList.length; i++) {
                    File delfile = new File(delpath + File.separator + fileList[i]);
                    if (!delfile.isDirectory()) {
                        delfile.delete();
                    } else if (delfile.isDirectory()) {
                        deletefile(delpath + File.separator + fileList[i]);
                    }
                }
                file.delete();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static class WenBen4json {
        private String pathKey;
        private ZipFile zipFile;
        private long fileSize;
        private long fileCount;
        private Map<String, Integer> classMap;
        private ZipEntry entry;
        private int count;
        private String name;

        public WenBen4json(String pathKey, ZipFile _zipFile, long fileSize, long fileCount, Map<String, Integer> classMap, ZipEntry entry, int count, String name) {
            this.pathKey = pathKey;
            zipFile = _zipFile;
            this.fileSize = fileSize;
            this.fileCount = fileCount;
            this.classMap = classMap;
            this.entry = entry;
            this.count = count;
            this.name = name;
        }

        public long getFileSize() {
            return fileSize;
        }

        public long getFileCount() {
            return fileCount;
        }

        public WenBen4json invoke() throws IOException {
            InputStream _in = zipFile.getInputStream(entry);
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            byte[] buffers = new byte[1024];
            int len;
            while ((len = _in.read(buffers)) > -1) {
                baos.write(buffers, 0, len);
            }
            baos.flush();
            InputStream stream1 = new ByteArrayInputStream(baos.toByteArray());
            InputStream stream2 = new ByteArrayInputStream(baos.toByteArray());

            //优化 多级目录取值
            name = getName(entry, count, name);
            if (name.indexOf("outputs") > -1) {
                JSONObject parse = getJsonObject(stream1);
                Object outputs = parse.get("outputs");
                JSONObject parseOutputs = (JSONObject) JSONObject.parse(outputs.toString());
                readTypeAndCount(classMap, parseOutputs);
            } else {
                fileSize += entry.getSize();
                ++fileCount;
            }
            return this;
        }
    }

    private static class WeiZhi4JSON {
        private String pathKey;
        private ZipFile zipFile;
        private long fileSize;
        private long fileCount;
        private Map<String, Integer> classMap;
        private ZipEntry entry;
        private int count;
        private String name;

        public WeiZhi4JSON(String pathKey, ZipFile _zipFile, long fileSize, long fileCount, Map<String, Integer> classMap, ZipEntry entry, int count, String name) {
            this.pathKey = pathKey;
            zipFile = _zipFile;
            this.fileSize = fileSize;
            this.fileCount = fileCount;
            this.classMap = classMap;
            this.entry = entry;
            this.count = count;
            this.name = name;
        }

        public long getFileSize() {
            return fileSize;
        }

        public long getFileCount() {
            return fileCount;
        }

        public WeiZhi4JSON invoke() throws IOException {
            InputStream _in = zipFile.getInputStream(entry);
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            byte[] buffers = new byte[1024];
            int len;
            while ((len = _in.read(buffers)) > -1) {
                baos.write(buffers, 0, len);
            }
            baos.flush();
            InputStream stream1 = new ByteArrayInputStream(baos.toByteArray());
            InputStream stream2 = new ByteArrayInputStream(baos.toByteArray());

            //优化 多级目录取值
            if (count == 2 || count == 1) {
                String str = entry.getName();
                if (str.indexOf("outputs") < 0) {
                    name = str.substring(str.indexOf("/") + 1);

                }
            }

            //TODO 获取分类和分类明细
            if (name.indexOf("outputs") > -1) {
                if (name.indexOf("/outputs/") > -1) {
                    name = name.substring(name.indexOf("/") + 1);

                }
                JSONObject parse = getJsonObject(stream1);
                Object outputs = parse.get("outputs");
                JSONObject parseOutputs = (JSONObject) JSONObject.parse(outputs.toString());
                getType(classMap, parseOutputs);
            } else {
                if (ValidationUtil.compareObj("label.txt", name)) {

                } else {
                    fileSize += entry.getSize();
                    ++fileCount;
                }

            }
            return this;
        }
    }


}