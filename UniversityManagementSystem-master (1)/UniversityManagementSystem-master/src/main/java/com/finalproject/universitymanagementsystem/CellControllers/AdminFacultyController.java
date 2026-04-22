package com.finalproject.universitymanagementsystem.CellControllers;

import com.finalproject.identification.Faculty;
import com.finalproject.universitymanagementsystem.DataLists.FacultyList;
import com.finalproject.universitymanagementsystem.ListCells.AdminFacultyCell;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

import java.io.IOException;

public class AdminFacultyController {

    @FXML
    private ListView<Faculty> facultyListView;

    @FXML
    private TextField searchField;

    // Holds the full list of faculties (unfiltered)
    private ObservableList<Faculty> masterFacultyList;

    @FXML
    public void initialize() {
        // Load all faculties from your data source
        masterFacultyList = FXCollections.observableArrayList(FacultyList.getFacultyList());

        // Set up the custom cell factory so each faculty item uses adminFacultyCell.fxml
        facultyListView.setCellFactory(list -> new AdminFacultyCell());

        // Optional: Wrap in a FilteredList for search
        FilteredList<Faculty> filteredData = new FilteredList<>(masterFacultyList, p -> true);

        // Listen for changes in the search field
        searchField.textProperty().addListener((obs, oldValue, newValue) -> {
            String lower = newValue.toLowerCase().trim();
            filteredData.setPredicate(fac -> fac.getName().toLowerCase().contains(lower));
        });

        // Finally, assign filtered list to the ListView
        facultyListView.setItems(filteredData);
    }

    /**
     * Called when the user clicks "Add Faculty" button at the top.
     * We open a new window (dialog) for the user to fill out faculty details.
     */
    @FXML
    private void onAddFaculty() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/finalproject/universitymanagementsystem/interface/addFacultyForm.fxml"));
        Parent root = loader.load();

        Stage dialogStage = new Stage();
        dialogStage.setTitle("Add New Faculty");
        dialogStage.setScene(new Scene(root));
        dialogStage.initOwner(facultyListView.getScene().getWindow()); // optional
        dialogStage.showAndWait();

        // After the dialog closes, refresh the list in case a new faculty was added
        masterFacultyList.setAll(FacultyList.getFacultyList());
    }
}
