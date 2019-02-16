package com.example.dao;

import com.example.common.DocumentUtils;
import com.example.common.LuceneUtils;
import com.example.domain.Article;
import com.example.model.QueryResult;
import org.apache.log4j.Logger;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.index.Term;
import org.apache.lucene.queryparser.classic.MultiFieldQueryParser;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.search.highlight.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Lucene是一个纯java全文检索工具包，采用倒排索引原理。
 * 全文检索：指的是计算机索引程序通过扫描文章的每一个词，对每一个词建立一个索引，并指明该词在文章中出现的次数和位置。
 * 索引的类型分为：1：为一索引、2：主键索引、3：聚集索引。索引就是加快检索表中数据的方法。
 * 搜索：
 *     一：按被搜索的资源类型
 *     1、可以转为文本的
 *     2、多媒体类型的
 *     二：按照搜索方式：
 *     1、不处理语义，只是找出现了指定词语的所有文本。（指对词语进行匹配）
 * 基本概念：
 *     1、使用流程：先建立索引，（索引库）在进行搜索。
 *     2、使用Lucene的数据结构，document、field。
 * 建立索引的过程：
 *     1、定义一个语法分词器
 *     2、确定索引存储的位置
 *     3、创建IndexWriter，进行索引的写入
 *     4、内容提取，进行索引文件的写入
 *     5、关闭indexWriter
 * 从索引库中搜索的过程：
 *     1、打开存储位置
 *     2、创建搜索器
 *     3、类似SQL进行查询
 *     4、处理结果
 *     5、关闭DirectoryReader
 *
 */
public class IndexDao {

    private static Logger logger = Logger.getLogger(IndexDao.class);

    public long save(Article article) {
        Document doc = DocumentUtils.article2Document(article);
        IndexWriter indexWriter = null;
        long result = 0;
        try {
            IndexWriterConfig config = new IndexWriterConfig(LuceneUtils.getAnalyzer());
            indexWriter = new IndexWriter(LuceneUtils.getDirectory(), config);
            result = indexWriter.addDocument(doc);
        } catch (Exception e) {
            logger.error("IndexDao.save error", e);
        } finally {
            LuceneUtils.closeIndexWriter(indexWriter);
        }
        return result;
    }

    public void delete(String id) {
        IndexWriter indexWriter = null;
        try {
            Term term = new Term("id", id);
            IndexWriterConfig config = new IndexWriterConfig(LuceneUtils.getAnalyzer());
            indexWriter = new IndexWriter(LuceneUtils.getDirectory(), config);
            indexWriter.deleteDocuments(term);// 删除含有指定term的所有文档
        } catch (Exception e) {
            logger.error("IndexDao.save error", e);
        } finally {
            LuceneUtils.closeIndexWriter(indexWriter);
        }
    }

    public long update(Article article) {
        Document doc = DocumentUtils.article2Document(article);
        IndexWriter indexWriter = null;
        long result = 0;
        try {
            Term term = new Term("id", article.getId().toString());
            IndexWriterConfig config = new IndexWriterConfig(LuceneUtils.getAnalyzer());
            indexWriter = new IndexWriter(LuceneUtils.getDirectory(), config);
            result = indexWriter.updateDocument(term, doc);// 先删除，后创建。
        } catch (Exception e) {
            logger.error("IndexDao.save error", e);
        } finally {
            LuceneUtils.closeIndexWriter(indexWriter);
        }
        return result;
    }

    public QueryResult search(String queryString, int firstResult, int maxResult) {
        List<Article> list = new ArrayList<>();
        try {
            DirectoryReader ireader = DirectoryReader.open(LuceneUtils.getDirectory());
            // 2、第二步，创建搜索器
            IndexSearcher isearcher = new IndexSearcher(ireader);

            // 3、第三步，类似SQL，进行关键字查询
            String[] fields = { "title", "content" };
            QueryParser parser = new MultiFieldQueryParser(fields, LuceneUtils.getAnalyzer());
            Query query = parser.parse(queryString);

            TopDocs topDocs = isearcher.search(query, firstResult + maxResult);
            int count = topDocs.totalHits;// 总记录数
            System.out.println("总记录数为：" + topDocs.totalHits);// 总记录数
            ScoreDoc[] hits = topDocs.scoreDocs;// 第二个参数，指定最多返回前n条结果

            // 高亮
            Formatter formatter = new SimpleHTMLFormatter("<font color='red'>", "</font>");
            Scorer source = new QueryScorer(query);
            Highlighter highlighter = new Highlighter(formatter, source);

            // 摘要
            //  Fragmenter fragmenter = new SimpleFragmenter(5);
            //	highlighter.setTextFragmenter(fragmenter);

            // 处理结果
            int endIndex = Math.min(firstResult + maxResult, hits.length);
            for (int i = firstResult; i < endIndex; i++) {
                Document hitDoc = isearcher.doc(hits[i].doc);
                Article article = DocumentUtils.document2Ariticle(hitDoc);
                //
                String text = highlighter.getBestFragment(LuceneUtils.getAnalyzer(), "content", hitDoc.get("content"));
                if (text != null) {
                    article.setContent(text);
                }
                list.add(article);
            }
            ireader.close();
            return new QueryResult(count, list);
        } catch (Exception e) {
            logger.error("IndexDao.search error", e);
        }
        return null;
    }
}
