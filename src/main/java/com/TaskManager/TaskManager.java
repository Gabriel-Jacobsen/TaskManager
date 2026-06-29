package com.TaskManager;

public class TaskManager {

	WorkPool workPool;
	
	public TaskManager() {
		workPool = new WorkPool(5);
	}
	
	public TaskManager(int maxThreads) {
		workPool = new WorkPool(maxThreads);
	}
	
	public void setMaxThreads(int maxThreads) {
		workPool.setMaxThreads(maxThreads);
	}
	
	public TaskManager run(Task task) {
		workPool.submit(task, Priority.MEDIUM);
		return this;
	}
	
	public TaskManager run(Task task, Priority pri) {
		workPool.submit(task, pri);
		return this;
	}
}
