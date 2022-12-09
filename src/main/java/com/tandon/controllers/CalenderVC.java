package com.tandon.controllers;

import com.tandon.DAO.POJOs.Calender;
import com.tandon.DAO.POJOs.Schedule;
import com.tandon.DAO.POJOs.Timeblock;
import com.tandon.DAO.POJOs.UserSession;
import com.tandon.DAO.Service.CalenderService;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.ScrollEvent;
import net.rgielen.fxweaver.core.FxmlView;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
@FxmlView("/views/calender.fxml")
public class CalenderVC implements ApplicationContextAware {

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

    private ObservableList timeblocklist;
    private ObservableList timeslotlist;
    private Schedule schedule;

    @Autowired
    private CalenderService calenderService;
    private ApplicationContext applicationContext;

    @FXML
    public void initialize() {
        initCombobox();
        initSchedule();
        initializeScheduleView();
        updateSchedule((String) datePicker.getSelectionModel().getSelectedItem());
    }

    @FXML
    public void onReserverBtnClicked() {
        Timeblock tb = this.scheduletable.getSelectionModel().getSelectedItem();
        Calender calender = new Calender();
        int day = this.scheduletable.getFocusModel().getFocusedCell().getColumn() + 1;
        calender.setDate((String) this.datePicker.getSelectionModel().getSelectedItem());
        calender.setTime(tb.getTimename());
        calender.setDay(day);
        calender.setClient(LoginVC.name);
        boolean addresult = calenderService.add(calender);
        if(addresult){
            tb.setLocationElement(LoginVC.name,day-1);
            scheduletable.refresh();
        }

    }

    @FXML
    public void onCancelBtnClicked() {
        Timeblock tb = this.scheduletable.getSelectionModel().getSelectedItem();
        String date = (String) this.datePicker.getSelectionModel().getSelectedItem();
        String time = tb.getTimename();
        int day = this.scheduletable.getFocusModel().getFocusedCell().getColumn() + 1;
        Calender c = calenderService.getCalender(date,day,time);
        if(c != null && c.getClient().equals(LoginVC.name)){
            calenderService.remove(date,day,time);
            tb.setLocationElement("/",day-1);
            scheduletable.refresh();
        }
    }

    @FXML
    public void ondatePickerAction(){
        String selectedDate = (String)datePicker.getValue();
        initSchedule();
        initializeScheduleView();
        updateSchedule(selectedDate);
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
        datePicker.getSelectionModel().selectFirst();
    }

    public void initSchedule(){
        this.schedule = new Schedule("asd",31);
    }

    public void updateSchedule(String date){
        List<Calender> calenders = calenderService.getList(date);
        for(Calender calender : calenders){
            String time = calender.getTime();
            Timeblock tb = (Timeblock) timeblocklist.get(Schedule.convertIdx(time));
            tb.setLocationElement(calender.getClient(),calender.getDay()-1);
        }
        scheduletable.refresh();
    }

    public void initializeScheduleView() {
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
        for (int i = 0; i < 12; i++) {
            timeblocklist.add(this.schedule.getSingleTimeBlock(i));
            timeslotlist.add(this.schedule.getSingleTimeBlock(i));
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
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
