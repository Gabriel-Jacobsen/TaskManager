package com.TaskManager;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class Queue {

	private final BlockingQueue<Task> HIGH = new LinkedBlockingQueue<>();
	private final BlockingQueue<Task> MEDIUM = new LinkedBlockingQueue<>();
	private final BlockingQueue<Task> LOW = new LinkedBlockingQueue<>();
	
	public void add(Task task, Priority pri) {
		switch (pri) {
			case HIGH: HIGH.add(task); break;
			case MEDIUM: MEDIUM.add(task); break;
			case LOW: LOW.add(task); break;
		}
	}
	
	public Task get() {
		try {
			if (! HIGH.isEmpty()) {
				return HIGH.take();
			}
			else if (! MEDIUM.isEmpty()) {
				return MEDIUM.take();
			} else if (! LOW.isEmpty()) {
				return LOW.take();
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
