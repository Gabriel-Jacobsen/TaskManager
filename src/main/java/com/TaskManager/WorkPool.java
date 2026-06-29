package com.TaskManager;

import java.util.ArrayList;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class WorkPool {

	private int maxThreads;
	private final BlockingQueue<Task> queue = new LinkedBlockingQueue<>();
	private final ArrayList<Worker> threadList = new ArrayList<Worker>();
	
	public WorkPool(int maxThreads) {
		this.maxThreads = maxThreads;
		for(int i = 0; i < maxThreads; i++) {
			Worker w = new Worker(queue);
			threadList.add(w);
			w.start();
		}
	}
	
	public void setMaxThreads(int MaxThreads){
		this.maxThreads = MaxThreads;
		if (maxThreads > threadList.size()) {
			for(int i =  threadList.size(); i < maxThreads; i++) {
				Worker w = new Worker(queue);
				threadList.add(w);
				w.start();
			}
		}
		else if (maxThreads < threadList.size()) {
			for(int i =  threadList.size()-1; i > maxThreads; i--) {
				Worker w = threadList.get(i);
				w.shutdown();
				threadList.remove(i);
			}
		}
	}
	
    public void submit(Task task, Priority pri) {
        queue.add(task);
    }
   
}
