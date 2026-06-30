package com.TaskManager;

public class TesteMain {

	    public static void main(String[] args) {

	        TaskManager manager = new TaskManager(3);

	        System.out.println("=== Enviando tarefas ===");

	        manager.run(() -> {
	            System.out.println("[LOW 1] Iniciando...");
	            Thread.sleep(3000);
	            System.out.println("[LOW 1] Finalizada");
	        });

	        manager.run(() -> {
	            System.out.println("[HIGH 1] Iniciando...");
	            Thread.sleep(1000);
	            System.out.println("[HIGH 1] Finalizada");
	        });

	        manager.run(() -> {
	            System.out.println("[MEDIUM 1] Iniciando...");
	            Thread.sleep(2000);
	            System.out.println("[MEDIUM 1] Finalizada");
	        });

	        manager.run(() -> {
	            System.out.println("[HIGH 2] Iniciando...");
	            Thread.sleep(1000);
	            System.out.println("[HIGH 2] Finalizada");
	        });

	        manager.run(() -> {
	            System.out.println("[LOW 2] Iniciando...");
	            Thread.sleep(1500);
	            System.out.println("[LOW 2] Finalizada");
	        });

	        manager.run(() -> {
	            System.out.println("[ERRO] Vou lançar uma exceção...");
	            throw new Exception("Erro proposital");
	        });

	        manager.run(() -> {
	            System.out.println("[MEDIUM 2] Iniciando...");
	            Thread.sleep(1000);
	            System.out.println("[MEDIUM 2] Finalizada");
	        });

	        System.out.println("=== Todas as tarefas foram enviadas ===");
	    }
	}