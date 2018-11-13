/*
 * 
 * Created By Giuseppe Barbieri
 * Cpu Scheduler App
 * Com 310-S01
 * 11/04/2018
 * 
 */

package model;

public class Process_Object {
	private int processNum;
	private int burstTime;
	private int arrivalTime;
	private int waitTime;
	private int exitTime;
	private int priority;
	
	public Process_Object(int processNum, int burstTime, int arrivalTime, int waitTime, int turnAroundTime) {
		super();
		this.processNum = processNum;
		this.burstTime = burstTime;
		this.arrivalTime = arrivalTime;
		this.waitTime = waitTime;
		this.exitTime = turnAroundTime;
	}

	public Process_Object(int processNum, int waitTime, int turnAroundTime, int arrivalTime) {
		super();
		this.processNum = processNum;
		this.waitTime = waitTime;
		this.exitTime = turnAroundTime;
		this.arrivalTime = arrivalTime;
	}
	
	
	public Process_Object(int processNum, int arrivalTime, int exitTime) {
		super();
		this.processNum = processNum;
		this.arrivalTime = arrivalTime;
		this.exitTime = exitTime;
	}

	public Process_Object(int processNum, int burstTime) {
		super();
		this.processNum = processNum;
		this.burstTime = burstTime;
	}

	public int getProcessNum() {
		return processNum;
	}

	public void setProcessNum(int processNum) {
		this.processNum = processNum;
	}

	public int getBurstTime() {
		return burstTime;
	}

	public void setBurstTime(int burstTime) {
		this.burstTime = burstTime;
	}

	public int getArrivalTime() {
		return arrivalTime;
	}

	public void setArrivalTime(int arrivalTime) {
		this.arrivalTime = arrivalTime;
	}

	public int getWaitTime() {
		return waitTime;
	}

	public void setWaitTime(int waitTime) {
		this.waitTime = waitTime;
	}

	public int getExitTime() {
		return exitTime;
	}

	public void setExitTime(int exitTime) {
		this.exitTime = exitTime;
	}

	public int getPriority() {
		return priority;
	}

	public void setPriority(int priority) {
		this.priority = priority;
	}
	
	

}
