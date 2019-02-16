package com.example.common;

import org.apache.log4j.Logger;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;

import java.nio.file.Paths;

public class LuceneUtils {

    private static Logger logger = Logger.getLogger(LuceneUtils.class);
    private static Directory directory;
    private static Analyzer analyzer;
    static {
        try {
            directory = FSDirectory.open(Paths.get("./logs/test/index"));
            analyzer = new StandardAnalyzer();
        } catch (Exception e) {
            logger.error("LuceneUtils error!", e);
        }
    }

    public static Directory getDirectory() {
        return directory;
    }

    public static Analyzer getAnalyzer() {
        return analyzer;
    }

    public static void closeIndexWriter(IndexWriter indexWriter) {
        if (indexWriter != null) {
            try {
                indexWriter.close();
            } catch (Exception e2) {
                logger.error("indexWriter.close error", e2);
            }
        }
    }


}
