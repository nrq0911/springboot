package com.example.word;

import org.apache.log4j.Logger;

import java.io.File;

public class ClearHistoryData {

    private static Logger logger = Logger.getLogger(ClearHistoryData.class);

    public static void main(String[] args) {
        createEmptyFile(getBasePath());
    }

    private static void createEmptyFile(String basePath) {

        clearPathFile(basePath + "/img/开始图表");

        clearPathFile(basePath + "/img/去重数据");

        clearPathFile(basePath + "/img/合作前数据");

        clearPathFile(basePath + "/img/合作前数据2");

        clearPathFile(basePath + "/img/用户分析");

        clearPathFile(basePath + "/img/首页热门楼盘");

        clearPathFile(basePath + "/img/项目详情页");

        clearPathFile(basePath + "/img/项目详情手机页");

        clearPathFile(basePath + "/img/新房列表页右侧橱窗");

        clearPathFile(basePath + "/img/新房房源频道右侧橱窗");

        clearPathFile(basePath + "/img/看房团频道右侧橱窗");

        clearPathFile(basePath + "/img/优惠楼盘列表优先");

        clearPathFile(basePath + "/img/活动信息展示+热门活动频道");

        clearPathFile(basePath + "/img/活动信息展示+热门活动频道-手机");

        clearPathFile(basePath + "/img/竞品项目活动-置业顾问植入");

        clearPathFile(basePath + "/img/首页热门导购");

        clearPathFile(basePath + "/img/首页热门导购");

        clearPathFile(basePath + "/img/实地探盘");

        clearPathFile(basePath + "/img/搜索零少结果页优先展示");

        clearPathFile(basePath + "/img/首页新房推荐");

        clearPathFile(basePath + "/img/新房筛选列表");

        clearPathFile(basePath + "/img/新房筛选列表-手机");

        clearPathFile(basePath + "/img/二手房3区域10板块引流");

        clearPathFile(basePath + "/img/二手房3区域10板块引流-手机");

        clearPathFile(basePath + "/img/新房移动端（APP、TW）搜索-热搜推荐");

        clearPathFile(basePath + "/img/免费楼盘单页--推荐合作盘");

        clearPathFile(basePath + "/img/百度关键词+星盘通+全幕名片");
    }

    private static void clearPathFile(String filePath){
        File file = new File(filePath);

        if (!file.exists()) {
            logger.info("file is not exist" + filePath);
            return;
        }

        // 扫描文件夹
        if (file.isDirectory()) {
            File[] files = file.listFiles();
            for (File f : files) {
                f.delete();
            }
        } else {
            // 文件夹场景
            file.delete();
        }
    }

    private static String getBasePath() {
        String resources = ClearHistoryData.class.getClassLoader().getResource("").getPath();
        return resources.replace("/target/classes", "") + "src/main/resources";
    }

}
