package com.TaskManager;

public class TaskManager {

	WorkPool workPool;
	
    private Priority priority = Priority.MEDIUM;
    private int retry = 0;
    private long timeout = 0;
	
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
		TaskRecord tr = new TaskRecord(task, priority, retry);
		workPool.submit(tr);
		return this;
	}
	
	public TaskManager priority(Priority pri) {
		priority = pri;
		return this;
	}
	
	public TaskManager retry(int i) {
		retry = i;
		return this;
	}
	
	public TaskManager addAndWait(Task task) {
		TaskRecord tr = new TaskRecord(task, priority, retry);
		workPool.submitAndWait(tr);
		return this;
	}
	
	public TaskManager execute() {
		workPool.execute();
		return this;
	}
}
