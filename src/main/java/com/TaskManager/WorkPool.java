package com.TaskManager;

import java.util.ArrayList;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class WorkPool {

	private int maxThreads;
	private final Queue queue = new Queue();
	private final Queue waitQueue = new Queue();
	private final ArrayList<Worker> threadList = new ArrayList<Worker>();
	
	//CONSTRUTOR CRIA AS THREADS
	public WorkPool(int maxThreads) {
		this.maxThreads = maxThreads;
		for(int i = 0; i < maxThreads; i++) {
			Worker w = new Worker(queue);
			threadList.add(w);
			w.start();
		}
	}
	
	//TROCA A QUANTIDADE DE THREADS
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
	
    public void submit(TaskRecord tk) {
        queue.add(tk);
    }
   
    //METODOS PARA ADICAO DE TAREFAS SEM EXECUTAR
    public void submitAndWait(TaskRecord tk) {
    	waitQueue.add(tk);
    }
    
    public void execute() {
    	queue.addQueue(waitQueue);
    }
}
