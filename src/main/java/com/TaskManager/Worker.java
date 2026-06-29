package com.TaskManager;

import java.util.concurrent.BlockingQueue;

public class Worker extends Thread {

	private volatile boolean running = true;
	
    private BlockingQueue<Task> queue;

    public Worker(BlockingQueue<Task> queue) {
    	this.queue = queue;
    }
    
    public void shutdown() {
        running = false;
        interrupt();
    }
    
    public void addTask(Task task) {
    	queue.add(task);
    }
    
    @Override
    public void run() {
        while(running) {
        	if (! queue.isEmpty()) {
        		try {
        			Task task = queue.take();
        			task.execute();
        		} catch (Exception e) {
        			e.printStackTrace();
        		}
        	}
        }
    }
}