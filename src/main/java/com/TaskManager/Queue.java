package com.TaskManager;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class Queue {

	private final BlockingQueue<TaskRecord> HIGH = new LinkedBlockingQueue<>();
	private final BlockingQueue<TaskRecord> MEDIUM = new LinkedBlockingQueue<>();
	private final BlockingQueue<TaskRecord> LOW = new LinkedBlockingQueue<>();
	
	public synchronized void add(TaskRecord tk) {
		switch (tk.getPriority()) {
			case HIGH: HIGH.add(tk); break;
			case MEDIUM: MEDIUM.add(tk); break;
			case LOW: LOW.add(tk); break;
		}
		notifyAll();
	}
	
	public synchronized TaskRecord get() {
		try {
		    while (HIGH.isEmpty() &&
		            MEDIUM.isEmpty() &&
		            LOW.isEmpty()) {

		         wait();
		     }
		    TaskRecord task = HIGH.poll();

		    if (task != null)
		        return task;
		    
		    task = MEDIUM.poll();

		    if (task != null)
		        return task;

		    return LOW.poll();
		}catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public boolean isEmpty() {
		return (HIGH.isEmpty() && MEDIUM.isEmpty() && LOW.isEmpty());
	}
	
	public void addQueue(Queue queue) {
		while(!queue.HIGH.isEmpty()){

		    HIGH.add(queue.HIGH.poll());

		}
		while(!queue.MEDIUM.isEmpty()){

			MEDIUM.add(queue.MEDIUM.poll());

		}
		while(!queue.LOW.isEmpty()){

			LOW.add(queue.LOW.poll());

		}
		notifyAll();
	}
	
}
