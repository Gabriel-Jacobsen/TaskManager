package com.TaskManager;

public class TaskRecord {

    private final Task task;
    private final Priority priority;
    private int retries;
	
    public TaskRecord(Task task,  Priority pri, int retries) {
        this.task = task;
        this.priority = pri;
        this.retries = retries;
    }

	public int getRetries() {
		return retries;
	}

	public Task getTask() {
		return task;
	}

	public Priority getPriority() {
		return priority;
	}
	
	public void diminuirRetry() {
		retries -= 1;
	}

}
