package com.esearch;

import org.apache.lucene.util.BytesRef;
import org.apache.lucene.util.IntsRefBuilder;
import org.apache.lucene.util.fst.Builder;
import org.apache.lucene.util.fst.FST;
import org.apache.lucene.util.fst.PositiveIntOutputs;
import org.apache.lucene.util.fst.Util;

public class FSTTest {

    public static void main(String[] args) throws Exception {
        String inputValues[] = {"cat", "deep", "do", "dog", "dogs"};
        long outputValues[] = {5, 7, 17, 18, 21};
        PositiveIntOutputs outputs = PositiveIntOutputs.getSingleton();
        Builder<Long> builder = new Builder<>(FST.INPUT_TYPE.BYTE1, outputs);
        IntsRefBuilder scratchInts = new IntsRefBuilder();
        for (int i = 0; i < inputValues.length; i++) {
            BytesRef bytesRef = new BytesRef(inputValues[i]);
            builder.add(Util.toIntsRef(bytesRef, scratchInts), outputValues[i]);
        }
        FST<Long> fst = builder.finish();
        Long value = Util.get(fst, new BytesRef("dog"));
        System.out.println(value); // 18

    }


}
