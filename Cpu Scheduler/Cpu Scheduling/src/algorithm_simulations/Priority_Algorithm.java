/*
 * 
 * Created By Giuseppe Barbieri
 * Cpu Scheduler App
 * Com 310-S01
 * 11/04/2018
 * 
 */

package algorithm_simulations;

import java.util.ArrayList;

import javafx.scene.control.TextField;
import model.Process_Object;

public class Priority_Algorithm {

	ArrayList<TextField> textFieldsList;
	private ArrayList<Process_Object> processList;
	private ArrayList<Process_Object> orderedProcessList;
	int numOfProcesses;
	ArrayList<TextField> priorityList;

	public Priority_Algorithm(ArrayList<TextField> textFieldsList, int numOfProcesses, ArrayList<TextField> priorityList) {
		this.textFieldsList = textFieldsList;
		this.numOfProcesses = numOfProcesses;
		this.priorityList = priorityList;
	}

	/*
	 * This method calculates the priority algorithm.
	 * It starts by adding the processes to the list and sets the priority.
	 * I didn't do this in the main class because this was added last minute
	 * and was easier to do it here so i didn't have to specify cases on when to add the piority.
	 */
	public ArrayList<Process_Object> calculatePriorityAlgorithm() {
		processList = new ArrayList<>();
		orderedProcessList = new ArrayList<>();

		//This loop adds the burst time and priority time to the process list.
		for (int i = 0; i < numOfProcesses; i++) {
			processList.add(new Process_Object(i + 1, Integer.parseInt(textFieldsList.get(i).getText())));
			processList.get(i).setPriority(Integer.parseInt(priorityList.get(i).getText()));
		}

		/*
		 * This loop organizes the process list so that the highest priority job will
		 * be first. If there are 2 processes with the same priority it'll add the first
		 * one it sees.
		 */
		int j = 0;
		while (j < processList.size()) {
			int highestPriority = 0;
			int index = 0;
			for (int i = 0; i < processList.size(); i++) {
				if (highestPriority < processList.get(i).getPriority()) {
					highestPriority = processList.get(i).getPriority();
					index = i;
				}
			}
			orderedProcessList.add(processList.get(index));
			processList.remove(index);			
		}
		/*
		 * This loop sets the arrival time and wait time of the processes.
		 */
		int taTime = 0;
		for(Process_Object e : orderedProcessList) {
			e.setArrivalTime(taTime);
			taTime += e.getBurstTime();
			e.setExitTime(taTime);			
		}
		return orderedProcessList;
	}
	/*
	 * This method calculates the average wait time and the average turn around time and prints them to the corresponding text fields.
	 * It also sets all the wait time and turn around time text fields.
	 */
	public void setTextFields(ArrayList<TextField> waitTxtList, ArrayList<TextField> taTxtList, TextField avgWaitTimeTxt, TextField avgTurnaroundTimeTxt) {
		for(int i = 0; i < numOfProcesses; i++) {
			for(Process_Object e : orderedProcessList) {
				if(e.getProcessNum() == (i+1)) {
					waitTxtList.get(i).setText(Integer.toString(e.getArrivalTime()));
					taTxtList.get(i).setText(Integer.toString(e.getExitTime()));
				}
			}
		}
		double waitTime = 0;
		double taTime = 0;
		for(int i = 0; i < numOfProcesses; i++) {
			waitTime += Double.parseDouble(waitTxtList.get(i).getText());
			taTime += Double.parseDouble(taTxtList.get(i).getText());
		}
		avgWaitTimeTxt.setText(Double.toString(waitTime/numOfProcesses));
		avgTurnaroundTimeTxt.setText(Double.toString(taTime/numOfProcesses));
	}
}
