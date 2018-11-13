/*
 * 
 * Created By Giuseppe Barbieri
 * Cpu Scheduler App
 * Com 310-S01
 * 11/04/2018
 * 
 *  Left to do
 *  	-Document
 *  	-Purpose of functions/classes
 *  	-Maybe add one more algorithm
 *  
 */

package view;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import alerts.Missing_Information_Alert;
import algorithm_simulations.First_Come_First_Serve;
import algorithm_simulations.Priority_Algorithm;
import algorithm_simulations.Round_Robin;
import algorithm_simulations.Shortest_Job_First;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import model.Process_Object;

public class Main_Screen_View_Controller implements Initializable {
	/*
	 * The first things declared are all the nodes used in this view.
	 */
	@FXML
	private TableView<Process_Object> schedulerTbl;
	@FXML
	private TableColumn<Process_Object, Integer> processCol, arrivalTimeCol, exitTimeCol;
	@FXML
	private ChoiceBox<String> numofProcessesChoiceBox, algorithmChoiceBox, quantumNumChoice;
	@FXML
	private Button randomBurstBtn, calculateBtn;
	@FXML
	private Label p1Lbl, p2Lbl, p3Lbl, p4Lbl, p5Lbl, p6Lbl, p7Lbl, p8Lbl, p9Lbl, p10Lbl;
	@FXML
	private TextField p1BTxt, p2BTxt, p3BTxt, p4BTxt, p5BTxt, p6BTxt, p7BTxt, p8BTxt, p9BTxt, p10BTxt;
	@FXML
	private TextField p1WTxt, p2WTxt, p3WTxt, p4WTxt, p5WTxt, p6WTxt, p7WTxt, p8WTxt, p9WTxt, p10WTxt;
	@FXML
	private TextField p1TTxt, p2TTxt, p3TTxt, p4TTxt, p5TTxt, p6TTxt, p7TTxt, p8TTxt, p9TTxt, p10TTxt;
	@FXML
	private TextField priorityTxtP1, priorityTxtP2, priorityTxtP3, priorityTxtP4, priorityTxtP5, priorityTxtP6,
			priorityTxtP7, priorityTxtP8, priorityTxtP9, priorityTxtP10;
	@FXML
	private HBox p1HBox, p2HBox, p3HBox, p4HBox, p5HBox, p6HBox, p7HBox, p8HBox, p9HBox, p10HBox;
	@FXML
	private TextField avgWaitTimeTxt, avgTurnaroundTimeTxt;
	@FXML
	private Label processLbl, burstLbl, waitLbl, taLbl, quantumLbl;
	/*
	 * Then I like to declare all the objects I will need that are not GUI related
	 * (essentially)
	 */
	private ArrayList<Process_Object> processList;
	private ArrayList<TextField> burstTxtList;
	private ArrayList<TextField> priorityTxtList;
	private ArrayList<TextField> waitTxtList;
	private ArrayList<TextField> taTxtList;
	private ArrayList<HBox> priorityHboxList;
	private ArrayList<Label> labelList;
	private String[] processNumList = { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10" };
	private String[] quantumList = { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15",
			"16", "17", "18", "19", "20" };
	private String[] algorithmList = { "First Come First Serve", "Shortest Job First", "Round Robin",
			"Priority Algorithm" };
	private int numOfProcesses;

	/*
	 * When the controller is loaded for the view, using the Initializable class, it
	 * will first call the initialize method.
	 * 
	 * I set the choice boxes to be preselected because of bugs that occurred when
	 * they loaded unselected.
	 * 
	 * I have 7 methods which aid the GUI.
	 * 
	 * 1.) The setSchedulerTableColumns() method sets the table columns to match the
	 * Process_Object object so when I set a list to the table the data
	 * automatically knows where to go because the columns are predefined on which
	 * part of the object goes where.
	 * 
	 * 2.) The setChoiceBoxListeners() method sets listeners to the choiceBoxes.
	 * 
	 * 3.) The createTextFieldArray() method sets all the text fields and labels
	 * into and array list.
	 * 
	 * 4.) The setTextFieldListeners() method sets listeners to all the text fields.
	 * 
	 * 5.) Due to this being a last minute add on the
	 * setPriorityAlgorithmSelectionListener() method creates a listener for when
	 * the priority algorithm is selected.
	 * 
	 * 6.) The setArrayLists() method initializes each array list needed.
	 * 
	 * 7.) The buttonListeners() method sets action listeners to the buttons.
	 */
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		numofProcessesChoiceBox.getSelectionModel().select(0);
		algorithmChoiceBox.getSelectionModel().select(0);
		
		setArrayLists();
		setSchedulerTableColumns();
		setChoiceBoxListeners();
		createTextFieldArray();
		setTextFieldListeners();
		setPriorityAlgorithmSelectionListener();
		buttonListeners();
	}

	/*
	 * The calculateAlgorithm() method works as such..
	 * 
	 * First it checks to make sure that both choice boxes are not unselected.
	 * 
	 * Then using a try catch block which checks to make sure there are no empty
	 * burst times. If information is missing it will alert the user.
	 * 
	 * Finally using a few if statements, depending on which algorithm is selected,
	 * it will call the corresponding method which calculates it.
	 * 
	 */
	private void calculateAlgorithm() {
		if (numofProcessesChoiceBox.getSelectionModel().getSelectedItem() == null
				|| algorithmChoiceBox.getSelectionModel().getSelectedItem() == null) {
			new Missing_Information_Alert("Number of processes or algorithm selection choice boxes are empty!");
		} else {
			try {
				if (algorithmChoiceBox.getSelectionModel().getSelectedIndex() == 0) {
					calculateFCFS();
				} else if (algorithmChoiceBox.getSelectionModel().getSelectedIndex() == 1) {
					calculateSJF();
				} else if (algorithmChoiceBox.getSelectionModel().getSelectedIndex() == 2) {
					calculateRR();
				} else if (algorithmChoiceBox.getSelectionModel().getSelectedIndex() == 3) {
					calculatePA();
				}
				processList.clear();
			} catch (NumberFormatException ex) {
				new Missing_Information_Alert("Missing burst time from one or more processes!");
			}
		}
	}

	/*
	 * The calculatePa() method creates a new class Priority_Algorithm which is used
	 * to calculate the Priority Algorithm and then it sets the gannt chart and all
	 * the textfields.
	 */
	private void calculatePA() {
		Priority_Algorithm priority = new Priority_Algorithm(burstTxtList, numOfProcesses, priorityTxtList);
		ObservableList<Process_Object> tableList = FXCollections
				.observableArrayList(priority.calculatePriorityAlgorithm());
		schedulerTbl.setItems(tableList);
		priority.setTextFields(waitTxtList, taTxtList, avgWaitTimeTxt, avgTurnaroundTimeTxt);
	}

	/*
	 * The calculatePa() method creates a new class First_Come_First_Serve which is
	 * used to calculate the First Come First Server Algorithm and then it sets the
	 * gannt chart and all the text fields.
	 */
	private void calculateFCFS() {
		First_Come_First_Serve fcfs = new First_Come_First_Serve(burstTxtList, numOfProcesses);
		ObservableList<Process_Object> tableList = FXCollections.observableArrayList(fcfs.simulateFCFS());
		schedulerTbl.setItems(tableList);
		fcfs.setTextFields(waitTxtList, taTxtList, avgWaitTimeTxt, avgTurnaroundTimeTxt);
	}

	/*
	 * The calculateSJF() method creates a new class Shortest_Job_First which is
	 * used to calculate the Shortest Job First Algorithm and then it sets the gannt
	 * chart and all the textfields.
	 */
	private void calculateSJF() {
		setProcessesList();
		Shortest_Job_First sjf = new Shortest_Job_First(processList);
		ObservableList<Process_Object> tableList = FXCollections.observableArrayList(sjf.simulateSJF());
		schedulerTbl.setItems(tableList);
		sjf.setTextFields(waitTxtList, taTxtList, avgWaitTimeTxt, avgTurnaroundTimeTxt);
	}

	/*
	 * The calculateRR() method creates a new class Round_Robin which is used to
	 * calculate the Round Robin Algorithm and then it sets the gannt chart and all
	 * the textfields.
	 */
	private void calculateRR() {
		if (quantumNumChoice.getSelectionModel().getSelectedItem() == null) {
			new Missing_Information_Alert("Quantum not selected!");
		} else {
			setProcessesList();
			Round_Robin rr = new Round_Robin(processList, burstTxtList,
					quantumNumChoice.getSelectionModel().getSelectedIndex());
			ObservableList<Process_Object> tableList = FXCollections.observableArrayList(rr.simulateRR());
			schedulerTbl.setItems(tableList);
			rr.setTextFieldInfo(waitTxtList, taTxtList, avgWaitTimeTxt, avgTurnaroundTimeTxt);
		}
	}

	/*
	 * The setProcessesList() method adds new process objects to the process list
	 * taking in the process number and burst time. I used a loop because all the
	 * burst time text fields are in a list so there in less redundant code.
	 */
	private void setProcessesList() {
		for (int i = 0; i < numOfProcesses; i++) {
			processList.add(new Process_Object((i + 1), Integer.parseInt(burstTxtList.get(i).getText())));
		}
	}

	/*
	 * The setRandomNumberToTextFields() sets random numbers to the burst time text
	 * fields and the priority text fields. This is used by the Random button.
	 */
	private void setRandomNumberToTextFields() {
		for (int i = 0; i < burstTxtList.size(); i++) {
			burstTxtList.get(i).setText(Integer.toString((int) (Math.random() * 10) + 1));
			priorityTxtList.get(i).setText(Integer.toString((int) (Math.random() * 10) + 1));
		}
	}

	/*
	 * The setSchedulerTableColumns() method sets the Process_Object object to the
	 * columns in the gannt chart table. This tells each column which part of the
	 * object it will display.
	 */
	private void setSchedulerTableColumns() {
		processCol.setCellValueFactory(new PropertyValueFactory<Process_Object, Integer>("processNum"));
		arrivalTimeCol.setCellValueFactory(new PropertyValueFactory<Process_Object, Integer>("arrivalTime"));
		exitTimeCol.setCellValueFactory(new PropertyValueFactory<Process_Object, Integer>("exitTime"));
	}

	/*
	 * The setPriorityAlgorithmSelectionListener() method sets an action listener to
	 * the algorithm choice box. If the Priority Algorithm isn't selected it will
	 * hide all the corresponding textfields. Otherwise it will show all the
	 * necessary text fields.
	 */
	private void setPriorityAlgorithmSelectionListener() {
		algorithmChoiceBox.setOnAction(e -> {
			// If Priority Algorithm isn't selected hide each text field
			if (algorithmChoiceBox.getSelectionModel().getSelectedIndex() != 3) {
				for (int i = 0; i < priorityHboxList.size(); i++) {
					priorityHboxList.get(i).setVisible(false);
				}
				// ELSE if it is selected
			} else {
				// set all the priority text fields to visible.
				for (int i = 0; i < priorityHboxList.size(); i++) {
					priorityHboxList.get(i).setVisible(true);
				}
				// then based on the number of processes it will hide the unnecessary text
				// fields.
				for (int i = 9; i > numofProcessesChoiceBox.getSelectionModel().getSelectedIndex(); i--) {
					priorityHboxList.get(i).setVisible(false);
				}
			}
		});
	}

	/*
	 * The buttonListeners() method sets listeners to the randomBurstBtn(random
	 * burst button) and the calculateBtn(calculates the selected algorithm).
	 */
	private void buttonListeners() {
		randomBurstBtn.setOnAction(e -> setRandomNumberToTextFields());
		calculateBtn.setOnAction(e -> calculateAlgorithm());
	}

	/*
	 * The setChoiceBoxListeners() method sets listeners to the
	 * numofProcessesChoiceBox and algorithmChoiceBox.
	 */
	private void setChoiceBoxListeners() {
		// Sets the processNumList to the numofProcessesChoiceBox choice box.
		ObservableList<String> processObsList = FXCollections.observableArrayList(processNumList);
		numofProcessesChoiceBox.setItems(processObsList);
		// Depending on how many processes are selected it uses the setTxtFields method
		// to show the necessary textfields and labels.
		// It also clears the text fields when a new number of processes are selected.
		numofProcessesChoiceBox.setOnAction(e -> {
			setTxtFields((numofProcessesChoiceBox.getSelectionModel().getSelectedIndex()) + 1);
			for (int i = 0; i < burstTxtList.size(); i++) {
				waitTxtList.get(i).clear();
				taTxtList.get(i).clear();
			}
		});
		// Sets the algorithmList to the algorithmChoiceBox choice box.
		ObservableList<String> algorithmObsList = FXCollections.observableArrayList(algorithmList);
		algorithmChoiceBox.setItems(algorithmObsList);
		// If the Round Robin algorithm is selected it'll show the quantum label and
		// quantumNumChoice choice box.
		// If any other algorithm is selected it will hide the quantum information.
		// After it clears all the textfields and the gannt chart table.
		algorithmChoiceBox.setOnAction(e -> {
			if (algorithmChoiceBox.getSelectionModel().getSelectedIndex() == 2) {
				quantumLbl.setVisible(true);
				quantumNumChoice.setVisible(true);

			} else {
				quantumLbl.setVisible(false);
				quantumNumChoice.setVisible(false);
			}
			schedulerTbl.setItems(null);
			for (int i = 0; i < burstTxtList.size(); i++) {
				waitTxtList.get(i).clear();
				taTxtList.get(i).clear();
			}
		});

		// Sets the quantumList to the quantumNumChoice choice box.
		ObservableList<String> quantumObsList = FXCollections.observableArrayList(quantumList);
		quantumNumChoice.setItems(quantumObsList);

	}

	/*
	 * The setTextFieldListeners() method loops through the burstTxtList and the
	 * priorityTxtList which holds the burst time text fields and the priority text
	 * fields. As loops through it will add a listener to each text field which
	 * listens and makes sure that the user is only inputting numbers into the text
	 * fields.
	 */
	private void setTextFieldListeners() {
		for (TextField e : burstTxtList) {
			e.textProperty().addListener(new ChangeListener<String>() {
				@Override
				public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
					if (!newValue.matches("\\d*")) {
						e.setText(newValue.replaceAll("[^\\d]", ""));
					}
				}
			});
		}

		for (TextField e : priorityTxtList) {
			e.textProperty().addListener(new ChangeListener<String>() {
				@Override
				public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
					if (!newValue.matches("\\d*")) {
						e.setText(newValue.replaceAll("[^\\d]", ""));
					}
				}
			});
		}
	}

	/*
	 * The createTextFieldArray() method adds each of the text fields and labels into
	 * the corresponding arrays to help simplify code.
	 */
	private void createTextFieldArray() {
		burstTxtList.add(p1BTxt);
		burstTxtList.add(p2BTxt);
		burstTxtList.add(p3BTxt);
		burstTxtList.add(p4BTxt);
		burstTxtList.add(p5BTxt);
		burstTxtList.add(p6BTxt);
		burstTxtList.add(p7BTxt);
		burstTxtList.add(p8BTxt);
		burstTxtList.add(p9BTxt);
		burstTxtList.add(p10BTxt);

		waitTxtList.add(p1WTxt);
		waitTxtList.add(p2WTxt);
		waitTxtList.add(p3WTxt);
		waitTxtList.add(p4WTxt);
		waitTxtList.add(p5WTxt);
		waitTxtList.add(p6WTxt);
		waitTxtList.add(p7WTxt);
		waitTxtList.add(p8WTxt);
		waitTxtList.add(p9WTxt);
		waitTxtList.add(p10WTxt);

		taTxtList.add(p1TTxt);
		taTxtList.add(p2TTxt);
		taTxtList.add(p3TTxt);
		taTxtList.add(p4TTxt);
		taTxtList.add(p5TTxt);
		taTxtList.add(p6TTxt);
		taTxtList.add(p7TTxt);
		taTxtList.add(p8TTxt);
		taTxtList.add(p9TTxt);
		taTxtList.add(p10TTxt);

		priorityTxtList.add(priorityTxtP1);
		priorityTxtList.add(priorityTxtP2);
		priorityTxtList.add(priorityTxtP3);
		priorityTxtList.add(priorityTxtP4);
		priorityTxtList.add(priorityTxtP5);
		priorityTxtList.add(priorityTxtP6);
		priorityTxtList.add(priorityTxtP7);
		priorityTxtList.add(priorityTxtP8);
		priorityTxtList.add(priorityTxtP9);
		priorityTxtList.add(priorityTxtP10);

		priorityHboxList.add(p1HBox);
		priorityHboxList.add(p2HBox);
		priorityHboxList.add(p3HBox);
		priorityHboxList.add(p4HBox);
		priorityHboxList.add(p5HBox);
		priorityHboxList.add(p6HBox);
		priorityHboxList.add(p7HBox);
		priorityHboxList.add(p8HBox);
		priorityHboxList.add(p9HBox);
		priorityHboxList.add(p10HBox);

		labelList.add(p1Lbl);
		labelList.add(p2Lbl);
		labelList.add(p3Lbl);
		labelList.add(p4Lbl);
		labelList.add(p5Lbl);
		labelList.add(p6Lbl);
		labelList.add(p7Lbl);
		labelList.add(p8Lbl);
		labelList.add(p9Lbl);
		labelList.add(p10Lbl);

	}

	/*
	 * The setTxtFields() method controls which text fields are to be shown. It
	 * starts by making every text field and label visible. Next using a switch it
	 * determines which text fields need to be shown by looping through each array
	 * list containing text fields and labels and sets them to not be visible.
	 */
	private void setTxtFields(int numOfProcessesChoice) {
		if (algorithmChoiceBox.getSelectionModel().getSelectedIndex() != 3) {
			for (int i = 0; i < priorityHboxList.size(); i++) {
				priorityHboxList.get(i).setVisible(false);
			}
		}
		numOfProcesses = numOfProcessesChoice;
		schedulerTbl.setItems(null);
		processLbl.setVisible(true);
		burstLbl.setVisible(true);
		waitLbl.setVisible(true);
		taLbl.setVisible(true);
		for (int i = 0; i < 10; i++) {
			burstTxtList.get(i).setVisible(true);
			waitTxtList.get(i).setVisible(true);
			taTxtList.get(i).setVisible(true);
			labelList.get(i).setVisible(true);
			if (algorithmChoiceBox.getSelectionModel().getSelectedIndex() == 3) {
				priorityHboxList.get(i).setVisible(true);
			}
		}
		avgWaitTimeTxt.clear();
		avgTurnaroundTimeTxt.clear();

		switch (numOfProcessesChoice) {
		case 1:
			for (int i = 1; i < 10; i++) {
				burstTxtList.get(i).setVisible(false);
				waitTxtList.get(i).setVisible(false);
				taTxtList.get(i).setVisible(false);
				labelList.get(i).setVisible(false);
				if (algorithmChoiceBox.getSelectionModel().getSelectedIndex() == 3) {
					priorityHboxList.get(i).setVisible(false);
				}
			}
			processLbl.setVisible(false);
			burstLbl.setVisible(false);
			waitLbl.setVisible(false);
			taLbl.setVisible(false);
			break;
		case 2:
			for (int i = 2; i < 10; i++) {
				burstTxtList.get(i).setVisible(false);
				waitTxtList.get(i).setVisible(false);
				taTxtList.get(i).setVisible(false);
				labelList.get(i).setVisible(false);
				if (algorithmChoiceBox.getSelectionModel().getSelectedIndex() == 3) {
					priorityHboxList.get(i).setVisible(false);
				}
			}
			processLbl.setVisible(false);
			burstLbl.setVisible(false);
			waitLbl.setVisible(false);
			taLbl.setVisible(false);
			break;
		case 3:
			for (int i = 3; i < 10; i++) {
				burstTxtList.get(i).setVisible(false);
				waitTxtList.get(i).setVisible(false);
				taTxtList.get(i).setVisible(false);
				labelList.get(i).setVisible(false);
				if (algorithmChoiceBox.getSelectionModel().getSelectedIndex() == 3) {
					priorityHboxList.get(i).setVisible(false);
				}
			}
			processLbl.setVisible(false);
			burstLbl.setVisible(false);
			waitLbl.setVisible(false);
			taLbl.setVisible(false);
			break;
		case 4:
			for (int i = 4; i < 10; i++) {
				burstTxtList.get(i).setVisible(false);
				waitTxtList.get(i).setVisible(false);
				taTxtList.get(i).setVisible(false);
				labelList.get(i).setVisible(false);
				if (algorithmChoiceBox.getSelectionModel().getSelectedIndex() == 3) {
					priorityHboxList.get(i).setVisible(false);
				}
			}
			processLbl.setVisible(false);
			burstLbl.setVisible(false);
			waitLbl.setVisible(false);
			taLbl.setVisible(false);
			break;
		case 5:
			for (int i = 5; i < 10; i++) {
				burstTxtList.get(i).setVisible(false);
				waitTxtList.get(i).setVisible(false);
				taTxtList.get(i).setVisible(false);
				labelList.get(i).setVisible(false);
				if (algorithmChoiceBox.getSelectionModel().getSelectedIndex() == 3) {
					priorityHboxList.get(i).setVisible(false);
				}
			}
			processLbl.setVisible(false);
			burstLbl.setVisible(false);
			waitLbl.setVisible(false);
			taLbl.setVisible(false);
			break;
		case 6:
			for (int i = 6; i < 10; i++) {
				burstTxtList.get(i).setVisible(false);
				waitTxtList.get(i).setVisible(false);
				taTxtList.get(i).setVisible(false);
				labelList.get(i).setVisible(false);
				if (algorithmChoiceBox.getSelectionModel().getSelectedIndex() == 3) {
					priorityHboxList.get(i).setVisible(false);
				}
			}
			break;
		case 7:
			for (int i = 7; i < 10; i++) {
				burstTxtList.get(i).setVisible(false);
				waitTxtList.get(i).setVisible(false);
				taTxtList.get(i).setVisible(false);
				labelList.get(i).setVisible(false);
				if (algorithmChoiceBox.getSelectionModel().getSelectedIndex() == 3) {
					priorityHboxList.get(i).setVisible(false);
				}
			}
			break;
		case 8:
			for (int i = 8; i < 10; i++) {
				burstTxtList.get(i).setVisible(false);
				waitTxtList.get(i).setVisible(false);
				taTxtList.get(i).setVisible(false);
				labelList.get(i).setVisible(false);
				if (algorithmChoiceBox.getSelectionModel().getSelectedIndex() == 3) {
					priorityHboxList.get(i).setVisible(false);
				}
			}
			break;
		case 9:
			p10HBox.setVisible(false);
			p10BTxt.setVisible(false);
			p10WTxt.setVisible(false);
			p10TTxt.setVisible(false);
			p10Lbl.setVisible(false);
			break;
		case 10:
			break;
		}
	}

	/*
	 * The setArrayLists() method initializes each ArrayList needed.
	 */
	private void setArrayLists() {
		processList = new ArrayList<>();
		burstTxtList = new ArrayList<>();
		waitTxtList = new ArrayList<>();
		taTxtList = new ArrayList<>();
		priorityTxtList = new ArrayList<>();
		priorityHboxList = new ArrayList<>();
		labelList = new ArrayList<>();

	}

}
