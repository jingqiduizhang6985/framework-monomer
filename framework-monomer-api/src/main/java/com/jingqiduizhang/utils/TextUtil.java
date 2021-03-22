package com.jingqiduizhang.utils;

import org.apache.commons.io.IOUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 从文本中获取内容 进行整理后写入一个新的文本
 *
 * @author duran
 * @date 2021.03.22
 */
public class TextUtil {
    private static TextContent textContent = null;
    public static void main(String[] args) throws Exception {
        List<String> listNot = new ArrayList<>(2);
        //获取数据
        List<String> list = IOUtils.readLines(new FileInputStream("F:"+File.separator+"data"+File.separator+"source.txt"));
        //去空格
        for (String text : list) {
            text = text.trim();
            listNot.add(text);
        }

        //整理数据
        List<TextContent> textList = new ArrayList<>(2);
        int num = 0;
        for (String newText : listNot) {
            if ("{".equals(newText.trim())) {
                if(textContent!=null){
                    textList.add(textContent);
                }
                num = 1;
                textContent = new TextContent();
            }
            switch (num) {
                case 2:
                    textContent = setCourse(newText,textContent);
                    break;
                case 5:
                    textContent = setLocation1(newText,textContent);
                    break;
                case 6:
                    textContent = setLocation2(newText,textContent);
                    break;
                case 9:
                    textContent = setGpsTime(newText,textContent);
                    break;
                case 10:
                    textContent = setSpeed(newText,textContent);
            }
            num++;
        }
        //保存数据
        createFile(textList);

        System.out.println("执行完成");
    }
    //course: NumberInt(9080),   2   ===》9080
    private static TextContent setCourse(String str, TextContent textContent) {
        if(str==null||"".equals(str)){
            return textContent;
        }
        String[] strs = str.split("\\(");
        String[] strs2 = strs[1].split("\\)");
        textContent.setCourse(strs2[0]);
        return textContent;
    }
    //107.982861762003,  5     ===》107.982861762003
    private static TextContent setLocation1(String str, TextContent textContent) {
        if(str==null||"".equals(str)){
            return textContent;
        }
        String location1 = str.substring(0,str.length()-1);
        textContent.setLocation1(location1);
        return textContent;
    }
    //26.5812011955274   6
    private static TextContent setLocation2(String str, TextContent textContent) {
        textContent.setLocation2(str);
        return textContent;
    }
   //gpsTime: NumberLong(1614355372000),   9  ====》1614355372000
    private static TextContent setGpsTime(String str, TextContent textContent) {
        if(str==null||"".equals(str)){
            return textContent;
        }
//        System.out.print(str);
        String[] strs = str.split("\\(");
        String strs2 = strs[1];
        String value = strs2.substring(0,strs2.length()-2);

        value = value.substring(1,value.length()-1);
        textContent.setGpsTime(value);

        Date d = new Date(Long.valueOf(value));
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateTime = sdf.format(d);
        textContent.setDateTime(dateTime);
        return textContent;
    }
    //speed: 2.9    10         =====》2.9
    private static TextContent setSpeed(String str, TextContent textContent) {
        if(str==null||"".equals(str)){
            return textContent;
        }
        String[] strs = str.split(":");
        textContent.setSpeed(strs[1]);
        return textContent;
    }




//[
//        { 1
//        course: NumberInt(9080),2
//        location: {3
//        loc: [4
//        107.982861762003,5
//        26.58120119552746
//        ]7
//        },8
//        gpsTime: NumberLong(1614355372000),9
//        speed: 2.9 10
//        },11
//        {
//        course: NumberInt(9080),
//        location: {
//        loc: [
//        107.982861762003,
//        26.5812011955274
//        ]
//        },
//        gpsTime: NumberLong(1614355552000),
//        speed: 2.9
//        },
//]



    public static void createFile(List<TextContent> textContentList) throws IOException {
//        TextContent  textContent = textContentList.get(0);
        String filePath = "F://data";
        File dir = new File(filePath);
        // 一、检查放置文件的文件夹路径是否存在，不存在则创建
        if (!dir.exists()) {
            dir.mkdirs();// mkdirs创建多级目录
        }
        File checkFile = new File(filePath + "/newData.txt");
        FileWriter writer = null;
        try {
            // 二、检查目标文件是否存在，不存在则创建
            if (!checkFile.exists()) {
                checkFile.createNewFile();// 创建目标文件
            }
            // 三、向目标文件中写入内容
            // FileWriter(File file, boolean append)，append为true时为追加模式，false或缺省则为覆盖模式
            writer = new FileWriter(checkFile, false);
//            writer.append("your content");
            int size = 0;
            for(TextContent textContent: textContentList){
                size++;
                writer.append(size +" 时间戳 "+textContent.getGpsTime()+" 时间 "+textContent.getDateTime()+" 速度 "+textContent.getSpeed()+" 经度 "+textContent.getLocation1()+" 维度 "+textContent.getLocation2()+" 里程"+textContent.getCourse());
                String str = System.getProperty("line.separator");
                writer.write(str);
                writer.write(str);
                writer.flush();
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (null != writer)
                writer.close();
        }
    }




}
