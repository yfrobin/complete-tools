package com.yf.completetools.file;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.io.FilenameUtils;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.*;

/**
 * @Author: yefei
 * @Date: create in 2019-07-05
 * @Desc:
 */
@Api("文件操作类API")
public class FileTool {

    private static Logger logger = LoggerFactory.getLogger(FileTool.class);

    /**
     * @param fileName 文件名称或者文件的全路径
     * @return
     */
    @ApiOperation("获取文件的扩展名")
    public static String getFileExtension(String fileName) {
        return FilenameUtils.getExtension(fileName);
    }

    /**
     * @param fileName 文件名称或者文件的全路径
     * @return
     */
    @ApiOperation("获取文件的名称")
    public static String getFileName(String fileName) {
        return FilenameUtils.getName(fileName);
    }


    @ApiOperation("传入文件路径复制文件")
    public static void copyFile(String srcPath, String targetPath) throws IOException {
        FileInputStream in = new FileInputStream(new File(srcPath));
        FileOutputStream out = new FileOutputStream(new File(targetPath));
        copyFile(in, out);
    }

    @ApiOperation("传入文件类复制文件")
    public static void copyFile(File srcFile, File targetFile) throws IOException {
        FileInputStream in = new FileInputStream(srcFile);
        FileOutputStream out = new FileOutputStream(targetFile);
        copyFile(in, out);
    }

    @ApiOperation("传入流复制文件")
    public static void copyFile(FileInputStream in, FileOutputStream out) throws IOException {
        byte[] buff = new byte[512];
        int n = 0;
        while ((n = in.read(buff)) != -1) {
            out.write(buff, 0, n);
        }
        out.flush();
        in.close();
        out.close();
    }


    /**
     * @param path        文件路径
     * @param allFileName 用来接受文件名称的列表，传size为0的空集合即可
     * @return
     */
    @ApiOperation("递归获取一个路径下的所有文件")
    public static List<String> recursiveFiles(String path, List<String> allFileName) {
        // 创建 File对象
        File file = new File(path);
        // 取 文件/文件夹
        File files[] = file.listFiles();
        // 对象为空 直接返回
        if (files == null || files.length == 0) {
            return allFileName;
        }

        for (File f : files) {              // 存在文件 遍历 判断
            if (f.isDirectory()) {          // 判断是否为 文件夹
                // 为 文件夹继续遍历
                recursiveFiles(f.getAbsolutePath(), allFileName);
            } else if (f.isFile()) {        // 判断是否为 文件
                allFileName.add(f.getAbsolutePath());
            } else {
                logger.error("未知错误文件");
            }
        }
        return allFileName;
    }


    @ApiOperation("不使用递归获取一个路径下所有文件")
    public static List<String> getFiles(String path) {
        List<String> fileNames = new ArrayList<>();
        File file = new File(path);
        if (file.exists()) {
            LinkedList<File> list = new LinkedList<>();
            File[] files = file.listFiles();
            for (File file2 : files) {
                if (file2.isDirectory()) {
                    list.add(file2);
                }
            }
            File temp_file;
            while (!list.isEmpty()) {
                temp_file = list.removeFirst();
                files = temp_file.listFiles();
                for (File file2 : files) {
                    if (file2.isDirectory()) {
                        list.add(file2);
                    } else {
                        fileNames.add(file2.getAbsolutePath());
                    }
                }
            }
        } else {
            logger.error("文件不存在!");
        }
        return fileNames;
    }


    @Test
    public void test() {

    }
}
