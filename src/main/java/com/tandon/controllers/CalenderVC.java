package com.tandon.controllers;

import com.tandon.DAO.POJOs.Schedule;
import com.tandon.DAO.POJOs.Timeblock;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.ScrollEvent;
import net.rgielen.fxweaver.core.FxmlView;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Arrays;

@Component
@FxmlView("/views/calender.fxml")
public class CalenderVC {

    //private final ApplicationContext applicationContext;
    private final int comboxlen = 12;
    @FXML
    private Button reserveBtn;
    @FXML
    private Button cancelBtn;
    @FXML
    private TableView<Timeblock> scheduletable;
    @FXML
    private TableView<Timeblock> timeslotview;
    @FXML
    private TableColumn<Timeblock, String> header;
    @FXML
    private ComboBox datePicker;
    @FXML
    private ScrollBar scrollBar;

    ObservableList timeblocklist;
    ObservableList timeslotlist;

    @FXML
    public void initialize() {
        initCombobox();
        initializeSchecduleView();
    }

    @FXML
    public void onReserverBtnClicked() {

    }

    @FXML
    public void onCancelBtnClicked() {

    }

    @FXML
    public void ondatePickerAction(){
        String selectedDate = (String)datePicker.getValue();
    }

    public void initCombobox() {
        LocalDate current_date = LocalDate.now();
        int year = current_date.getYear();
        int month = current_date.getMonthValue();
        String[] names = new String[comboxlen];
        int idx = 0;
        while (month <= 12) {
            names[idx++] = year + "-" + month;
            month++;
        }
        year++;
        month = 1;
        while (idx < comboxlen) {
            names[idx++] = year + "-" + month;
            month++;
        }
        ObservableList<String> list = FXCollections.observableArrayList(names);
        datePicker.setItems(list);
    }

    public void initializeSchecduleView() {
        scheduletable.getSelectionModel().setCellSelectionEnabled(true);
        timeslotview.addEventFilter(ScrollEvent.ANY, Event::consume);

        header.setCellValueFactory(cellData -> cellData.getValue().timenameProperty());
        header.setCellFactory(col -> {
            TableCell<Timeblock, String> cell = new TableCell<Timeblock, String>() {
                @Override
                public void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);
                    if (item != null) {
                        this.setText(item);
                    }
                }
            };
            cell.prefHeightProperty().bind(scheduletable.heightProperty().multiply(0.1));
            return cell;
        });
        header.setSortable(false);
        for (int i = 1; i <= 31; i++) {
            TableColumn<Timeblock, String> temp = new TableColumn(Integer.toString(i));
            temp.prefWidthProperty().bind(scheduletable.widthProperty().multiply(0.15));
            temp.setSortable(false);
            int index = i - 1;
            temp.setCellValueFactory(cellData -> cellData.getValue().getLocationElement(index));
            temp.setCellFactory(col -> {
                TableCell<Timeblock, String> cell = new TableCell<Timeblock, String>() {
                    @Override
                    public void updateItem(String item, boolean empty) {
                        super.updateItem(item, empty);
                        if (item != null) {
                            StringBuilder sb = new StringBuilder();
                            String[] arr = item.split("(?<=\\G.....)");
                            for (int i = 0; i < arr.length - 1; i++) {
                                sb.append(arr[i] + "\n");
                            }
                            sb.append(arr[arr.length - 1]);
                            this.setText(sb.toString());

                        }
                    }
                };
                cell.prefHeightProperty().bind(scheduletable.heightProperty().multiply(0.1));
                return cell;
            });
            scheduletable.getColumns().add(temp);
        }
        timeblocklist = FXCollections.observableArrayList();
        timeslotlist = FXCollections.observableArrayList();
        Schedule schedule = new Schedule("asd",31);
        for (int i = 0; i < 12; i++) {
            timeblocklist.add(schedule.getSingleTimeBlock(i));
            timeslotlist.add(schedule.getSingleTimeBlock(i));
        }
        scheduletable.setItems(timeblocklist);
        scheduletable.setStyle("-fx-font-size :14");
        timeslotview.setItems(timeslotlist);
        timeslotview.setStyle("-fx-font-size :14");

        timeslotview.getStylesheets().add("scrollbar.css");
        scheduletable.getStylesheets().add("scrollbar.css");
        scrollBar.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number number, Number t1) {
                scheduletable.scrollTo(t1.intValue());
                timeslotview.scrollTo(t1.intValue());
            }
        });

    }
}
