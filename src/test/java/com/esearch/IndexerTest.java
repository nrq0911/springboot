package com.esearch;

import com.example.dao.IndexDao;
import com.example.domain.Article;
import com.example.model.QueryResult;
import org.junit.Test;

import java.util.List;
import java.util.Random;

public class IndexerTest {

    @Test
    public void search() throws  Exception {
        long start = System.currentTimeMillis();

        IndexDao  indexDao = new IndexDao();
        QueryResult result =  indexDao.search("hello", 0, 100);

        System.out.println("花费了" + (System.currentTimeMillis() - start) + " 毫秒");
        System.out.println(result);
    }

    @Test
    public void save() throws  Exception {
        long start = System.currentTimeMillis();

        Article article = new Article();
        Random random = new Random();
        article.setId(random.nextInt());
        article.setTitle("Hello world" + random.nextInt());
        article.setContent("welcome to china" + random.nextInt());
        new IndexDao().save(article);

        System.out.println("花费了" + (System.currentTimeMillis() - start) + " 毫秒");
    }

    @Test
    public void update() throws  Exception {
        long start = System.currentTimeMillis();

        Article article = new Article();
        article.setId(2);
        article.setTitle("Hello world");
        article.setContent("welcome to Shanghai");
        new IndexDao().save(article);

        System.out.println("花费了" + (System.currentTimeMillis() - start) + " 毫秒");
    }


}
