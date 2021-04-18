package com.example.word;

import com.alibaba.dubbo.common.utils.Assert;
import com.deepoove.poi.XWPFTemplate;
import com.deepoove.poi.data.PictureRenderData;
import com.deepoove.poi.data.Pictures;
import org.apache.log4j.Logger;

import java.io.File;
import java.util.*;
import java.util.stream.Collectors;

public class CreateWord {

    private static Logger logger = Logger.getLogger(CreateWord.class);

    public static void main(String[] args) {
        Map<String, Object> params = new HashMap<>();
        // 参数设置
        params.put("projectName", "星河盛境");
        params.put("email", "xxx@email.com");
        params.put("phone", "133xxxxxxxx");

        String basePath = getBasePath();
        addImgToParam(basePath, params);

        String templatePath = basePath + "/template/template.docx";
        // 放到项目路径下去
        String fileDir = basePath.replace("/target/classes", "") + "/output";
        String fileName = "result";

        String wordPath = createWord(templatePath, fileDir, fileName, params);
        System.out.println("生成文档路径：" + wordPath);
    }

    private static String getBasePath() {
        String resources = CreateWord.class.getClassLoader().getResource("").getPath();
        return resources.replace("/target/classes", "") + "src/main/resources";
    }

    private static void addImgToParam(String basePath, Map<String, Object> params) {
        // 渲染图片
        addImgToWord(basePath + "/img/开始图表", "first_img_", 700, 400, params);

        addImgToWord(basePath + "/img/去重数据", "duplicate_remove_img_",700, 400, params);

        addImgToWord(basePath + "/img/合作前数据", "before_cooperation_img_",700, 400, params);

        addImgToWord(basePath + "/img/合作前数据2", "before_cooperation_2_img_",400, 400, params);

        addImgToWord(basePath + "/img/用户分析", "user_analysis_img_",700, 300, params);

        addImgToWord(basePath + "/img/首页热门楼盘", "home_page_hot_building_img_",700, 400, params);

        addImgToWord(basePath + "/img/项目详情页", "project_details_img_",700, 400, params);

        addImgToWord(basePath + "/img/项目详情手机页", "project_details_phone_img_",350, 550, params);

        addImgToWord(basePath + "/img/新房列表页右侧橱窗", "new_house_list_right_img_",700, 400, params);

        addImgToWord(basePath + "/img/新房房源频道右侧橱窗", "new_house_resources_right_img_",700, 400, params);

        addImgToWord(basePath + "/img/看房团频道右侧橱窗", "group_channel_img_",700, 400, params);

        addImgToWord(basePath + "/img/优惠楼盘列表优先", "discount_page_list_img_",700, 400, params);

        addImgToWord(basePath + "/img/活动信息展示+热门活动频道", "activity_channel_img_",700, 400, params);

        addImgToWord(basePath + "/img/活动信息展示+热门活动频道-手机", "activity_channel_img_phone_",350, 550, params);

        addImgToWord(basePath + "/img/竞品项目活动-置业顾问植入", "competitive_project_activity_img_",700, 400, params);

        addImgToWord(basePath + "/img/首页热门导购", "home_page_hot_img_",700, 400, params);

        addImgToWord(basePath + "/img/首页热门导购", "home_page_hot_shop_guide_img_",700, 400, params);

        addImgToWord(basePath + "/img/实地探盘", "home_page_field_exploration_img_",700, 400, params);

        addImgToWord(basePath + "/img/搜索零少结果页优先展示", "home_page_search_less_img_",700, 400, params);

        addImgToWord(basePath + "/img/首页新房推荐", "home_page_new_house_img_",350, 550, params);

        addImgToWord(basePath + "/img/新房筛选列表", "new_house_screening_list_img_",700, 400, params);

        addImgToWord(basePath + "/img/新房筛选列表-手机", "new_house_screening_list_phone_img_",350, 550, params);

        addImgToWord(basePath + "/img/二手房3区域10板块引流", "second_hand_house_drainage_img_",700, 400, params);

        addImgToWord(basePath + "/img/二手房3区域10板块引流-手机", "second_hand_house_drainage_phone_img_",350, 550, params);

        addImgToWord(basePath + "/img/新房移动端（APP、TW）搜索-热搜推荐", "new_house_app_hot_search_img_",700, 400, params);

        addImgToWord(basePath + "/img/免费楼盘单页--推荐合作盘", "new_house_free_cooperation_img_",700, 400, params);

        addImgToWord(basePath + "/img/百度关键词+星盘通+全幕名片", "baidu_keyword_img_",350, 550, params);
    }


    /**
     * 根据模板填充内容生成word
     * 调用方法参考下面的main方法，详细文档参考官方文档
     * Poi-tl模板引擎官方文档：http://deepoove.com/poi-tl/
     *
     * @param templatePath word模板文件路径
     * @param fileDir      生成的文件存放地址
     * @param fileName     生成的文件名,不带格式。假如要生成abc.docx，则fileName传入abc即可
     * @param paramMap     替换的参数集合
     * @return 生成word成功返回生成的文件的路径，失败返回空字符串
     */
    public static String createWord(String templatePath, String fileDir, String fileName, Map<String, Object> paramMap) {
        Assert.notNull(templatePath, "word模板文件路径不能为空");
        Assert.notNull(fileDir, "生成的文件存放地址不能为空");
        Assert.notNull(fileName, "生成的文件名不能为空");

        // 生成的word格式
        String formatSuffix = ".docx";
        // 拼接后的文件名
        fileName = fileName + formatSuffix;

        // 生成的文件的存放路径
        if (!fileDir.endsWith("/")) {
            fileDir = fileDir + File.separator;
        }

        File dir = new File(fileDir);
        if (!dir.exists()) {
            logger.info("生成word数据时存储文件目录{}不存在,为您创建文件夹!" +  fileDir);
            dir.mkdirs();
        }

        String filePath = fileDir + fileName;
        // 读取模板templatePath并将paramMap的内容填充进模板，即编辑模板+渲染数据
        XWPFTemplate template = XWPFTemplate.compile(templatePath).render(paramMap);
        try {
            // 将填充之后的模板写入filePath
            template.writeToFile(filePath);
            template.close();
        } catch (Exception e) {
            logger.error("生成word异常", e);
            e.printStackTrace();
            return "";
        }
        return filePath;
    }


    private static void addImgToWord(String filePath, String imgNamePrefix, int width, int height, Map<String, Object> params) {
        List<File> files = listAllFiles(filePath);
        for (int i = 0; i < files.size(); i++) {
            String imgPath = files.get(i).getPath();
            PictureRenderData pictureRenderData = Pictures.ofLocal(imgPath).size(width, height).create();
            params.put(imgNamePrefix + i, pictureRenderData);
        }
    }

    private static List<File>  listAllFiles(String filePath) {
        List<File> fileList = new ArrayList<>();
        File file = new File(filePath);

        if (!file.exists()) {
            logger.info("file is not exist" + filePath);
        }

        // 扫描文件夹
        if (file.exists() && file.isDirectory()) {
            File[] files = file.listFiles();
            fileList.addAll(Arrays.asList(files));
        } else {
            // 文件夹场景
            fileList.add(file);
        }

        return fileList.stream().sorted((f1,f2) -> f1.getName().compareTo(f2.getName())).collect(Collectors.toList());
    }

}
