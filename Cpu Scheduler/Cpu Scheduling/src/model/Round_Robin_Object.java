/*
 * 
 * Created By Giuseppe Barbieri
 * Cpu Scheduler App
 * Com 310-S01
 * 11/04/2018
 * 
 */

package model;

public class Round_Robin_Object {

	int processNum;
	int remainingTime;
	int arrivalTime;
	int exitTime;

	public Round_Robin_Object(int processNum, int remainingTime) {
		super();
		this.processNum = processNum;
		this.remainingTime = remainingTime;
	}

	public Round_Robin_Object(int processNum, int remainingTime, int arrivalTime, int exitTime) {
		super();
		this.processNum = processNum;
		this.remainingTime = remainingTime;
		this.arrivalTime = arrivalTime;
		this.exitTime = exitTime;
	}

	public int getArrivalTime() {
		return arrivalTime;
	}

	public void setArrivalTime(int arrivalTime) {
		this.arrivalTime = arrivalTime;
	}

	public int getExitTime() {
		return exitTime;
	}

	public void setExitTime(int exitTime) {
		this.exitTime = exitTime;
	}

	public int getProcessNum() {
		return processNum;
	}

	public void setProcessNum(int processNum) {
		this.processNum = processNum;
	}

	public int getRemainingTime() {
		return remainingTime;
	}

	public void setRemainingTime(int remainingTime) {
		this.remainingTime = remainingTime;
	}

}
