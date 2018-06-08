package com.git.demo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.CopyOnWriteArrayList;

import org.omg.CORBA.Current;

/**
 * 删除ArrayList集合中的重复元素，且保证是线程安全的
 * @author lee
 *
 */
public class ArrayListRemoveElementDemo implements Runnable{
	
	static List<String> synList = Collections.synchronizedList(new ArrayList<String>());
	
	@SuppressWarnings("unused")
	public static void main(String[] args) {
//		List<String> arrayList = new ArrayList<String>();
		synList.add("2");
		synList.add("5");
		synList.add("1");
		synList.add("1");
		synList.add("3");
		synList.add("3");
		synList.add("1");
		synList.add("3");
		synList.add("3");
//		System.out.println(removeListElement(arrayList));
		Thread thread1 = new Thread(new ArrayListRemoveElementDemo());
		Thread thread2 = new Thread(new ArrayListRemoveElementDemo());
		thread1.start();
		thread2.start();
		
	}
	
//	public static List<String> removeListElement(List<String> list) {
//		List<String> synList = Collections.synchronizedList(list);
//		Iterator<String> ite = synList.iterator();
//		while(ite.hasNext()) {
//			String str = ite.next();
//			if("1".equals(str)) {
//				ite.remove();
//			}
//		}
//		return list;
//	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		
			Iterator<String> ite = synList.iterator();
			
			while(ite.hasNext()) {
				synchronized (synList) {
				synList.notify();
//				String str = ite.next();
//				if("1".equals(str)) {
//					ite.remove();
					System.out.println(Thread.currentThread().getName() + " : " +synList);
//				}
				try {
					synList.wait();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				}
			}
	}
}
