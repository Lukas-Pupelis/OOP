package com.studentregistry.business;

import com.studentregistry.dto.NewStudentInputData;
import com.studentregistry.dto.UpdateStudentInputData;
import com.studentregistry.io.Exportable;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

import static java.time.LocalDate.parse;

public class Student implements Exportable {
  private final SimpleStringProperty name = new SimpleStringProperty(this, "name");
  private final SimpleStringProperty surname = new SimpleStringProperty(this, "surname");
  private Integer course;
  private final SimpleStringProperty group = new SimpleStringProperty(this, "group");
  private final TableView<Student> table;
  private final Map<LocalDate, Boolean> attendance = new HashMap<>();

  public Student(NewStudentInputData inputData, TableView<Student> table) {
    name.set(inputData.name);
    surname.set(inputData.surname);
    course = inputData.course;
    group.set(inputData.group);
    this.table = table;
  }

  public void update(UpdateStudentInputData inputData) {
    name.set(inputData.name);
    surname.set(inputData.surname);
    course = inputData.course;
    group.set(inputData.group);
    table.refresh();
  }

  public void updateAttendance(LocalDate date, Boolean value) {
    attendance.put(date, value);

    boolean columnExists = false;
    for (TableColumn<Student, ?> column : table.getColumns()) {
      if (column.getText().equals(date.toString())) {
        columnExists = true;
      }
    }

    // Add new column if needed
    if (!columnExists && Boolean.TRUE.equals(value)) {
      TableColumn<Student, String> newColumn = new TableColumn<>(date.toString());
      newColumn.setCellValueFactory(
              param -> {
            String columnName = param.getTableColumn().getText();
            LocalDate dateFromColumnName = parse(columnName);
            Boolean hasAttendance = param.getValue().getAttendance(dateFromColumnName);
            return new ReadOnlyObjectWrapper<>(Boolean.TRUE.equals(hasAttendance) ? "X" : "");
          });
      table.getColumns().add(newColumn);

      // Sort columns by date
      var comparator = (Comparator<TableColumn<Student, ?>>) (TableColumn<Student, ?> c1, TableColumn<Student, ?> c2) -> {
        LocalDate date1, date2;
        try {
          date1 = parse(c1.getText());
          date2 = parse(c2.getText());
        } catch (DateTimeParseException e) {
          return 0;
        }
        return date1.compareTo(date2);
      };
      table.getColumns().sort(comparator);
    }

    table.refresh();
  }

  public Boolean getAttendance(LocalDate date) {
    return attendance.getOrDefault(date, false);
  }

  public String getName() {
    return name.get();
  }

  public String getSurname() {
    return surname.get();
  }

  public Integer getCourse() {
    return course;
  }

  public String getGroup() {
    return group.get();
  }

  @Override
  public String[] getExportableRow() {
    return new String[] {name.get(), surname.get(), course.toString(), group.get()};
  }
}
