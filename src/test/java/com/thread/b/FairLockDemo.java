package com.thread.b;

import java.util.ArrayList;
import java.util.List;

public class FairLockDemo {

	private boolean isLocked = false;
	private Thread lockingThread = null;
	private List<QueueObject> waitingThreads = new ArrayList<QueueObject>();

	public void lock() throws InterruptedException {
		QueueObject queueObject = new QueueObject();
		synchronized (this) {
			waitingThreads.add(queueObject);
		}

		try {
			queueObject.doWait();
		} catch (InterruptedException e) {
			synchronized (this) {
				waitingThreads.remove(queueObject);
			}
			throw e;
		}
	}

	public synchronized void unlock() {
		if (this.lockingThread != Thread.currentThread()) {
			throw new IllegalMonitorStateException("Calling thread has not locked this lock");
		}
		isLocked = false;
		lockingThread = null;
		if (waitingThreads.size() > 0) {
			waitingThreads.get(0).doNotify();
		}
	}

	public static class QueueObject {

		private boolean isNotified = false;

		public synchronized void doWait() throws InterruptedException {

			while (!isNotified) {
				this.wait();
			}

			this.isNotified = false;

		}

		public synchronized void doNotify() {
			this.isNotified = true;
			this.notify();
		}

		public boolean equals(Object o) {
			return this == o;
		}

	}
}