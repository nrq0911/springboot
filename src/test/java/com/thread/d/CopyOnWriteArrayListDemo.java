package com.thread.d;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.concurrent.CopyOnWriteArrayList;

public class CopyOnWriteArrayListDemo {
	
	public static void main(String[] args) {

		ArrayList<String> s = new ArrayList<>();
		Collections.synchronizedList(s);

		HashMap<String, Object> res = new HashMap<>();
		Collections.synchronizedMap(res);

		CopyOnWriteArrayList<String> arrayList = new CopyOnWriteArrayList<>();
		
	}

}
