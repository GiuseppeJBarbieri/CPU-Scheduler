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

public class First_Come_First_Serve {

	private ArrayList<Process_Object> processList;
	private ArrayList<TextField> textFieldsList;
	private int numOfProcesses;

	public First_Come_First_Serve(ArrayList<TextField> textFieldsList, int numOfProcesses) {
		super();
		this.textFieldsList = textFieldsList;
		this.numOfProcesses = numOfProcesses;
	}
	/*
	 * This method calculates the wait time and turn around time for the set of processes.
	 * It adds the turn around time, then adds it to the process. next it adds the wait time and
	 * adds it on the next loop. this is because the first wait time is 0 and doesnt get increased until the following
	 * process.
	 */
	public ArrayList<Process_Object> simulateFCFS() {
		processList = new ArrayList<>();
		int waitTime = 0;
		int turnAroundTime = 0;
		
		for (int i = 0; i < numOfProcesses; i++) {
			turnAroundTime += Integer.parseInt(textFieldsList.get(i).getText());
			processList.add(new Process_Object((i + 1), waitTime, turnAroundTime, waitTime));
			waitTime += Integer.parseInt(textFieldsList.get(i).getText());
		}
		return processList;
	}
	
	/*
	 * This method calculates the average wait time and the average turn around time and prints them to the corresponding text fields.
	 * It also sets all the wait time and turn around time text fields.
	 */
	public void setTextFields(ArrayList<TextField> waitTxtList, ArrayList<TextField> taTxtList, TextField avgWaitTxt, TextField avgTaTxt) {
		double avgWait = 0;
		double avgTa = 0;
		for(int i = 0; i < processList.size(); i++) {
			waitTxtList.get(i).setText(Integer.toString(processList.get(i).getArrivalTime()));
			taTxtList.get(i).setText(Integer.toString(processList.get(i).getExitTime()));
			avgWait += processList.get(i).getArrivalTime();
			avgTa += processList.get(i).getExitTime();
		}
		avgWaitTxt.setText(Double.toString(avgWait/processList.size()));
		avgTaTxt.setText(Double.toString(avgTa/processList.size()));
	}
	
}
