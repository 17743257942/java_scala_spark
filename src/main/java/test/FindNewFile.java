package test;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;

public class FindNewFile {
    public static void main(String[] args) throws Exception {
        long currentTimeMillis = System.currentTimeMillis();
        scanFilesWithRecursion("D:\\Workspace");
        System.out.println(scanFiles.size());
        System.out.println(count);
        long currentTimeMillis2 = System.currentTimeMillis();
        System.out.println("耗费时间毫秒数：" + (currentTimeMillis2 - currentTimeMillis));

    }

    private static ArrayList<Object> scanFiles = new ArrayList<Object>();
    private static int count = 0;

    /**
     * TODO:递归扫描指定文件夹下面的指定文件
     *
     * @return ArrayList<Object>
     * @throws FileNotFoundException
     */
    public static void scanFilesWithRecursion(String folderPath) throws FileNotFoundException {
        File directory = new File(folderPath);
        if (!directory.isDirectory()) {
            throw new FileNotFoundException('"' + folderPath + '"' + " input path is not a Directory , please input the right path of the Directory. ^_^...^_^");
        }
        if (directory.isDirectory()) {
            File[] filelist = directory.listFiles();
            for (int i = 0; i < filelist.length; i++) {
                /**如果当前是文件夹，进入递归扫描文件夹**/
                if (filelist[i].isDirectory()) {
                    /**递归扫描下面的文件夹**/
                    count++;
                    scanFilesWithRecursion(filelist[i].getAbsolutePath());
                }
                /**非文件夹**/
                else {
                    scanFiles.add(filelist[i].getAbsolutePath());
                    System.out.println(filelist[i].getAbsolutePath());
                }
            }
        }

    }
}
