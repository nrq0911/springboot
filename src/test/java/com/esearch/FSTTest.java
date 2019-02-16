package com.esearch;

import org.apache.lucene.util.BytesRef;
import org.apache.lucene.util.IntsRef;
import org.apache.lucene.util.IntsRefBuilder;
import org.apache.lucene.util.fst.Builder;
import org.apache.lucene.util.fst.FST;
import org.apache.lucene.util.fst.PositiveIntOutputs;
import org.apache.lucene.util.fst.Util;
import org.junit.Test;

public class FSTTest {

    public static void main(String[] args) throws Exception {
        String inputValues[] = {"cat", "deep", "do", "dog", "dogs"};
        long outputValues[] = {5, 7, 17, 18, 21};
        PositiveIntOutputs outputs = PositiveIntOutputs.getSingleton();
        Builder<Long> builder = new Builder<Long>(FST.INPUT_TYPE.BYTE1, outputs);
        BytesRef scratchBytes = new BytesRef();
        IntsRef scratchInts = new IntsRef();
        for (int i = 0; i < inputValues.length; i++) {
            /*scratchBytes.copyChars(inputValues[i]);
            builder.add(Util.toIntsRef(scratchBytes, scratchInts), outputValues[i]);*/
        }
        FST<Long> fst = builder.finish();
        Long value = Util.get(fst, new BytesRef("dog"));
        System.out.println(value); // 18

    }

    @Test
    public void a(){
        String inputValues[] = {"cat", "deep", "do", "dog", "dogs"};
        long outputValues[] = {5, 10, 15, 2, 8};

        PositiveIntOutputs outputs = PositiveIntOutputs.getSingleton();
        Builder<Long> builder = new Builder<Long>(FST.INPUT_TYPE.BYTE1, outputs);
        IntsRefBuilder scratchInts = new IntsRefBuilder();
        for (int i = 0; i < inputValues.length; i++) {
            BytesRef scratchBytes = new BytesRef(inputValues[i]);
            //builder.add(Util.toIntsRef(scratchBytes, scratchInts), outputValues[i]);
        }
        //FST<Long> fst = builder.finish();
    }


}
