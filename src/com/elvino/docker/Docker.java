package com.elvino.docker;

public class Docker {

	public void run() {
		System.out.println("Hallo World from Docker");
	}
	
	public static void main(String[] args) {
		Docker docker = new Docker();
		docker.run();
	}
}
