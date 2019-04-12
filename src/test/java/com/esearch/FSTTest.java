package com.esearch;

import org.apache.lucene.util.BytesRef;
import org.apache.lucene.util.IntsRefBuilder;
import org.apache.lucene.util.fst.Builder;
import org.apache.lucene.util.fst.FST;
import org.apache.lucene.util.fst.PositiveIntOutputs;
import org.apache.lucene.util.fst.Util;
import org.junit.Test;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.LinkedTransferQueue;

public class FSTTest {

    private String inputValues[] = {"cat", "deep", "do", "dog", "dogs"};
    private long outputValues[] = {5, 7, 17, 18, 21};

    @Test
    public void test() throws Exception {
        System.out.println(getValue("dog"));
    }

    private Long getValue(String input) throws Exception {
        PositiveIntOutputs outputs = PositiveIntOutputs.getSingleton();
        Builder<Long> builder = new Builder<>(FST.INPUT_TYPE.BYTE1, outputs);
        IntsRefBuilder scratchInts = new IntsRefBuilder();
        for (int i = 0; i < inputValues.length; i++) {
            BytesRef bytesRef = new BytesRef(inputValues[i]);
            builder.add(Util.toIntsRef(bytesRef, scratchInts), outputValues[i]);
        }
        FST<Long> fst = builder.finish();
        return Util.get(fst, new BytesRef(input));
    }




}
