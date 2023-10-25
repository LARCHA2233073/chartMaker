package com.example.chartmaker;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.chart.*;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.*;
import java.nio.file.Files;
import java.util.List;

import static javafx.scene.control.skin.MenuBarSkin.setDefaultSystemMenuBar;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) {
        //borderpane
        BorderPane borderPane = new BorderPane();

        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Veuillez choisir un fichier");
        FileChooser.ExtensionFilter filter = new FileChooser.ExtensionFilter("Fichiers de format .dat", "*.dat");
        fileChooser.getExtensionFilters().addAll(filter);
        //menuBar
        MenuBar menuBar = new MenuBar();
        setDefaultSystemMenuBar(menuBar);

        Menu importer = new Menu("Importer");
        Menu exporter = new Menu("Exporter");

        MenuItem lignes = new MenuItem("Lignes");
        lignes.setOnAction( (e) -> {
            File file = fileChooser.showOpenDialog(stage);
            List<String> allLines;
            try {
                allLines = Files.readAllLines(file.toPath());
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
            List<String> mois = List.of(allLines.get(0).split(","));
            List<String> temperatures = List.of(allLines.get(1).split(","));

            CategoryAxis xAxis = new CategoryAxis();
            NumberAxis yAxis = new NumberAxis();

            xAxis.setLabel("Mois");
            yAxis.setLabel("Température");

            LineChart<String,Number> lineChart = new LineChart<>(xAxis, yAxis);
            lineChart.setTitle("Température moyenne selon le mois");

            XYChart.Series<String, Number> series = new XYChart.Series<>();
            series.setName("2022");
            for (int i = 0; i < 12; i++) {
                series.getData().add(new XYChart.Data<>(mois.get(i), Double.valueOf(temperatures.get(i))));
            }
            lineChart.getData().addAll(series);

            borderPane.setCenter(lineChart);
        });

        MenuItem regions = new MenuItem("Régions");
        regions.setOnAction( (e) -> {
            File file = fileChooser.showOpenDialog(stage);
            List<String> allLines;
            try {
                allLines = Files.readAllLines(file.toPath());
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
            List<String> mois = List.of(allLines.get(0).split(","));
            List<String> temperatures = List.of(allLines.get(1).split(","));

            CategoryAxis xAxis = new CategoryAxis();
            NumberAxis yAxis = new NumberAxis();

            xAxis.setLabel("Mois");
            yAxis.setLabel("Température");

            AreaChart<String,Number> areaChart = new AreaChart<>(xAxis, yAxis);
            areaChart.setTitle("Température moyenne selon le mois");

            XYChart.Series<String, Number> series = new XYChart.Series<>();
            series.setName("2022");
            for (int i = 0; i < 12; i++) {
                series.getData().add(new XYChart.Data<>(mois.get(i), Double.valueOf(temperatures.get(i))));
            }
            areaChart.getData().addAll(series);

            borderPane.setCenter(areaChart);
        });

        MenuItem barres = new MenuItem("Barres");
        barres.setOnAction( (e) -> {
            File file = fileChooser.showOpenDialog(stage);
            List<String> allLines;
            try {
                allLines = Files.readAllLines(file.toPath());
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
            List<String> mois = List.of(allLines.get(0).split(","));
            List<String> temperatures = List.of(allLines.get(1).split(","));

            CategoryAxis xAxis = new CategoryAxis();
            NumberAxis yAxis = new NumberAxis();

            xAxis.setLabel("Mois");
            yAxis.setLabel("Température");

            BarChart<String,Number> barChart = new BarChart<>(xAxis, yAxis);
            barChart.setTitle("Température moyenne selon le mois");

            XYChart.Series<String, Number> series = new XYChart.Series<>();
            series.setName("2022");
            for (int i = 0; i < 12; i++) {
                series.getData().add(new XYChart.Data<>(mois.get(i), Double.valueOf(temperatures.get(i))));
            }
            barChart.getData().addAll(series);

            borderPane.setCenter(barChart);
        });

        importer.getItems().addAll(lignes, regions, barres);
        menuBar.getMenus().addAll(importer, exporter);

        borderPane.setTop(menuBar);

        //série
        stage.setScene(new Scene(borderPane));
        stage.setFullScreen(true);
        stage.show();
    }
    public static void main(String[] args) {
        launch();
    }
}