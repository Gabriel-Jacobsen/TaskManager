package com.TaskManager;

public class Worker extends Thread {

	private volatile boolean running = true;
	
	private Queue queue;

    public Worker(Queue queue) {
    	this.queue = queue;
    }
    
    public void shutdown() {
        running = false;
        interrupt();
    }
    
    @Override
    public void run() {
        while(running) {
        	TaskRecord task = queue.get();
        		try {
        			task.getTask().execute();
        		} catch (Exception e) {
        		    if(task.getRetries() > 0){

        		    	task.diminuirRetry();

        		        queue.add(task);

        		    }
        		}
        }
    }
}