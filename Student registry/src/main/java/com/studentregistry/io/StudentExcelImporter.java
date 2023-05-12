package com.studentregistry.io;

import com.studentregistry.dto.NewStudentInputData;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.util.ArrayList;

import static java.lang.Integer.parseInt;

public class StudentExcelImporter extends Importer<NewStudentInputData> {

  public StudentExcelImporter(Stage stage) {
    super(stage, "Import students", "students.xlsx");
  }

  @Override
  ExtensionFilter[] getExtensions() {
    return new ExtensionFilter[] {
        new ExtensionFilter("Excel", "*.xlsx"),
    };
  }

  @Override
  public NewStudentInputData[] importData() {
    ArrayList<NewStudentInputData> result = new ArrayList<>();

    try {
      try (var workbook = new XSSFWorkbook(file)) {
        var sheet = workbook.getSheetAt(0);

        var iterator = sheet.iterator();
        if (iterator.hasNext()) {
          do {
            var row = iterator.next();
            var cellIterator = row.cellIterator();

            var inputData = new NewStudentInputData(cellIterator.next().getStringCellValue(),
                    cellIterator.next().getStringCellValue(),
                    parseInt(cellIterator.next().getStringCellValue()),
                    cellIterator.next().getStringCellValue());

            result.add(inputData);
          } while (iterator.hasNext());
        }
      }
    } catch (Exception e) {
      e.printStackTrace();
    }

    return result.toArray(NewStudentInputData[]::new);
  }
}
