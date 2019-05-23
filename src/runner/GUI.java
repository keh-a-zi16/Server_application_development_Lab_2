package runner;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.*;

import java.io.IOException;
import java.util.*;

import static runner.IOData.*;

public class GUI extends Application {

    public static final String PATH = "motoList.txt";
    private static int countOfMouseClick = 0;
    private static Stage stage;

    private static ListView<String> listView;
    private static ListView<String> listViewPrice;
    private static ListView<String> listViewWeight;
    private static ListView<String> listViewNameOfEq;
    private static ListView<String> listViewFeature1;
    private static ListView<String> listViewFeature2;

    private static List<Motorcyclist> listMotoObjects;
    private static List<String> listForNameOfMotorcyclists;

    private static HBox hboxFor2Fields;
    private static Button addButt;
    private static Button remButt;
    private static Button sortButton;
    private static Button findButt;
    private static Button refreshButton;


    private static TextField firstField;
    private static TextField secondField;



    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {


        primaryStage.setTitle("Мотоцикл");
        GridPane gridPane = new GridPane();
        gridPane.setHgap(10);
        gridPane.setVgap(12);
        gridPane.setPadding(new Insets(12));
        if (!isExist(PATH)) {
            writeMotoObjToMotoList();
        }

        hboxFor2Fields = new HBox();

        addButt = new Button("Добавить");
        addButt.setPrefWidth(85);
        remButt = new Button("Удалить");
        remButt.setPrefWidth(85);
        sortButton = new Button("Сортировка");
        sortButton.setPrefWidth(70);
        findButt = new Button("Поиск");
        findButt.setPrefWidth(70);
        refreshButton = new Button("Обновить");
        refreshButton.setPrefWidth(100);


        firstField = new TextField();
        firstField.setPrefWidth(60);
        firstField.setPromptText("мин");
        secondField = new TextField();
        secondField.setPrefWidth(60);
        secondField.setPromptText("макс");


        //read MotoObj from file and adding in ListView
        listForNameOfMotorcyclists = new LinkedList<>();
        listMotoObjects = readFromMotoList();
        for (Motorcyclist el : listMotoObjects) {
            listForNameOfMotorcyclists.add(el.getFirstNameOfMotorcyclist() + " " + el.getSecondNameOfMotorcyclist());
        }
        listView = new ListView<>();

        listViewPrice = new ListView<>();
        listViewPrice.addEventFilter(MouseEvent.ANY, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                mouseEvent.consume();
            }
        });

        listViewWeight = new ListView<>();
        listViewWeight.addEventFilter(MouseEvent.ANY, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                mouseEvent.consume();
            }
        });

        listViewNameOfEq = new ListView<>();
        listViewNameOfEq.addEventFilter(MouseEvent.ANY, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                mouseEvent.consume();
            }
        });

        listViewFeature1 = new ListView<>();
        listViewFeature1.addEventFilter(MouseEvent.ANY, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                mouseEvent.consume();
            }
        });

        listViewFeature2 = new ListView<>();
        listViewFeature2.addEventFilter(MouseEvent.ANY, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                mouseEvent.consume();
            }
        });

        listView.getItems().addAll(listForNameOfMotorcyclists);

        addButt.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                stage = new Stage();
                stage.initModality(Modality.WINDOW_MODAL);
                stage.initOwner(primaryStage);
                stage.setScene(newScene());
                stage.setTitle("Добавить мотоциклиста");
                stage.show();

            }
        });
        sortButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                countOfMouseClick++;
                List<Double> listOfWeight = new ArrayList<>();
                List<Equipment> list = getEquipmentListOfSelectedMotorcyclist();

                for (int i = 0; i < listViewWeight.getItems().size(); i++) {
                    listOfWeight.add(Double.parseDouble(listViewWeight.getItems().get(i)));
                }
                if (countOfMouseClick % 2 != 0) {
                    Collections.sort(listOfWeight);

                } else Collections.sort(listOfWeight, new Comparator<Double>() {
                    @Override
                    public int compare(Double o1, Double o2) {
                        return o2.compareTo(o1);
                    }
                });

                clearAllViewLists();

                for (int i = 0; i < listOfWeight.size(); i++) {
                    for (int j = 0; j < list.size(); j++) {
                        if (list.get(j).getWeight() == listOfWeight.get(i)) {
                            if (isJacket(list.get(j))) {
                                createJacketForViewList(list, j);
                            } else if (isHelmet(list.get(j))) {
                                createHelmetForViewList(list, j);
                            } else if (isGloves(list.get(j))) {
                                createGlovesForViewList(list, j);
                            } else if (isMotoProtection(list.get(j))) {
                                createMotoProtForViewList(list, j);
                            }
                        }
                    }

                }


            }
        });

        findButt.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                if (!firstField.getText().equals("") && !secondField.getText().equals("")) {
                    clearAllViewLists();
                    List<Equipment> list = getEquipmentListOfSelectedMotorcyclist();
                    for (int i = 0; i < list.size(); i++) {
                        if (list.get(i).getPrice() >= Double.parseDouble(firstField.getText()) &&
                                list.get(i).getPrice() <= Double.parseDouble(secondField.getText())) {

                            if (isJacket(list.get(i))) {
                                createJacketForViewList(list, i);
                            } else if (isHelmet(list.get(i))) {
                                createHelmetForViewList(list, i);
                            } else if (isGloves(list.get(i))) {
                                createGlovesForViewList(list, i);
                            } else if (isMotoProtection(list.get(i))) {
                                createMotoProtForViewList(list, i);
                            }

                        }
                    }
                    firstField.clear();
                    secondField.clear();
                }
            }
        });

        refreshButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                clearAllViewLists();
                if (listView.getSelectionModel().getSelectedItem() != null) {

                    List<Equipment> list = getEquipmentListOfSelectedMotorcyclist();
                    for (int j = 0; j < list.size(); j++) {
                        if (isJacket(list.get(j))) {
                            createJacketForViewList(list, j);
                        } else if (isHelmet(list.get(j))) {
                            createHelmetForViewList(list, j);
                        } else if (isGloves(list.get(j))) {
                            createGlovesForViewList(list, j);
                        } else if (isMotoProtection(list.get(j))) {
                            createMotoProtForViewList(list, j);
                        }
                    }
                    firstField.clear();
                    secondField.clear();
                }
            }
        });

        listView.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (listView.getSelectionModel().getSelectedItem() == null) {
                    return;
                }
                countOfMouseClick = 0;
                clearAllViewLists();
                List<Equipment> list = getEquipmentListOfSelectedMotorcyclist();
                for (int j = 0; j < list.size(); j++) {
                    if (isJacket(list.get(j))) {
                        createJacketForViewList(list, j);
                    } else if (isHelmet(list.get(j))) {
                        createHelmetForViewList(list, j);
                    } else if (isGloves(list.get(j))) {
                        createGlovesForViewList(list, j);
                    } else if (isMotoProtection(list.get(j))) {
                        createMotoProtForViewList(list, j);
                    }
                }
            }


        });



        hboxFor2Fields.getChildren().addAll(firstField, new Label("  "), secondField);
        HBox hboxForButtAddRem = new HBox();
        hboxForButtAddRem.getChildren().addAll(addButt, new Label("  "), remButt);
        gridPane.add(new Label("Название"), 0, 0);
        gridPane.add(new Label("Снаряжение"), 1, 0);
        gridPane.add(new Label("Цена, Руб"), 2, 0);
        gridPane.add(new Label("Вес, кг"), 3, 0);
        gridPane.add(new Label("Характеристики 1"), 4, 0);
        gridPane.add(new Label("Характиристики 2"), 5, 0);
        gridPane.add(listView, 0, 1);
        gridPane.add(hboxForButtAddRem, 0,2);
        gridPane.add(listViewNameOfEq, 1, 1);
        gridPane.add(hboxFor2Fields, 1, 2);
        gridPane.add(listViewPrice, 2, 1);
        gridPane.add(findButt, 2, 2);
        gridPane.add(listViewWeight, 3, 1);
        gridPane.add(sortButton, 3, 2);
        gridPane.add(listViewFeature1, 4, 1);
        gridPane.add(listViewFeature2, 5, 1);
        gridPane.add(refreshButton, 5, 2);


        ColumnConstraints col1 = new ColumnConstraints();
        col1.setPrefWidth(170);
        ColumnConstraints col2 = new ColumnConstraints();
        col2.setPrefWidth(120);
        ColumnConstraints col3 = new ColumnConstraints();
        col3.setPrefWidth(80);
        ColumnConstraints col4 = new ColumnConstraints();
        col4.setPrefWidth(70);
        ColumnConstraints col5 = new ColumnConstraints();
        col5.setPrefWidth(140);
        ColumnConstraints col6 = new ColumnConstraints();
        col6.setPrefWidth(110);
        gridPane.getColumnConstraints().addAll(col1, col2, col3, col4, col5, col6);

        Scene scene = new Scene(gridPane, 700, 400);
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();

    }


    private static boolean isJacket(Equipment el) {
        try {
            Jacket jacket = (Jacket) el;
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    private static boolean isHelmet(Equipment el) {
        try {
            Helmet helmet = (Helmet) el;
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    private static boolean isGloves(Equipment el) {
        try {
            Gloves gloves = (Gloves) el;
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    private static boolean isMotoProtection(Equipment el) {
        try {
            MotoProtection jacket = (MotoProtection) el;
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    private static void clearAllViewLists() {

        listViewPrice.getItems().clear();
        listViewWeight.getItems().clear();
        listViewNameOfEq.getItems().clear();
        listViewFeature1.getItems().clear();
        listViewFeature2.getItems().clear();
    }

    private static void createJacketForViewList(List<Equipment> list, int j) {
        Jacket jk = (Jacket) list.get(j);
        listViewPrice.getItems().add(jk.getPrice() + "");
        listViewWeight.getItems().add(jk.getWeight() + "");
        listViewNameOfEq.getItems().add(jk.getNameOfEquipment());
        listViewFeature1.getItems().add("Материал: " + jk.getMaterial());
        listViewFeature2.getItems().add("Размер: " + jk.getSize());
    }

    private static void createHelmetForViewList(List<Equipment> list, int j) {
        Helmet jk = (Helmet) list.get(j);
        listViewPrice.getItems().add(jk.getPrice() + "");
        listViewWeight.getItems().add(jk.getWeight() + "");
        listViewNameOfEq.getItems().add(jk.getNameOfEquipment());
        listViewFeature1.getItems().add("Цвет: " + jk.getColor());
        listViewFeature2.getItems().add(jk.isOpen() ? "Открытый" : "Закрытый");
    }

    private static void createGlovesForViewList(List<Equipment> list, int j) {
        Gloves jk = (Gloves) list.get(j);
        listViewPrice.getItems().add(jk.getPrice() + "");
        listViewWeight.getItems().add(jk.getWeight() + "");
        listViewNameOfEq.getItems().add(jk.getNameOfEquipment());
        listViewFeature1.getItems().add(jk.isClipped() ? "С застёжкой" : "Без застёжки");
        listViewFeature2.getItems().add("Цвет: " + jk.getColor());
    }

    private static void createMotoProtForViewList(List<Equipment> list, int j) {
        MotoProtection jk = (MotoProtection) list.get(j);
        listViewPrice.getItems().add(jk.getPrice() + "");
        listViewWeight.getItems().add(jk.getWeight() + "");
        listViewNameOfEq.getItems().add(jk.getNameOfEquipment());
        listViewFeature1.getItems().add("Тип: " + jk.getTypeProtection());
        listViewFeature2.getItems().add("Размер: " + jk.getSize());
    }

    private static List<Equipment> getEquipmentListOfSelectedMotorcyclist() {
        List<Equipment> list = new ArrayList<>();
        String selectedName = "";
        if (listView.getSelectionModel().getSelectedItem() != null) {
            selectedName = listView.getSelectionModel().getSelectedItem();
        } else return null;
        String nameOfMoto;
        for (int i = 0; i < listMotoObjects.size(); i++) {
            nameOfMoto = listMotoObjects.get(i).getFirstNameOfMotorcyclist() + " " + listMotoObjects.get(i).getSecondNameOfMotorcyclist();
            if (selectedName.equals(nameOfMoto)) {
                list = listMotoObjects.get(i).getEquipmentList();
            }
        }
        return list;
    }

    private Scene newScene() {


        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20, 20, 20, 20));
        TextField motoName = new TextField();
        motoName.setPromptText("Имя");
        TextField motoName2 = new TextField();
        motoName2.setPromptText("Фамилия");

        TextField priceFieldJacket = new TextField();
        priceFieldJacket.setPromptText("Цена");
        TextField weightFieldJacket = new TextField();
        weightFieldJacket.setPromptText("Вес");
        TextField materialFieldJacket = new TextField();
        materialFieldJacket.setPromptText("Материал");
        TextField sizeFieldJacket = new TextField();
        sizeFieldJacket.setPromptText("Размер");
        TextField priceFieldHelmet = new TextField();
        priceFieldHelmet.setPromptText("Цена");
        TextField weightFieldHelmet = new TextField();
        weightFieldHelmet.setPromptText("Вес");
        TextField colorFieldHelmet = new TextField();
        colorFieldHelmet.setPromptText("Цвет");
        ComboBox<String> openOrClosedComboBox = new ComboBox<String>();
        openOrClosedComboBox.getItems().addAll("Открытый", "Закрытый");
        openOrClosedComboBox.setPrefWidth(150);
        TextField priceFieldGloves = new TextField();
        priceFieldGloves.setPromptText("Цена");
        TextField weightFieldGloves = new TextField();
        weightFieldGloves.setPromptText("Вес");
        ComboBox<String> clippedOrNotComboBox = new ComboBox<String>();
        clippedOrNotComboBox.getItems().addAll("С застёжкой", "Без застёжки");
        clippedOrNotComboBox.setPrefWidth(150);
        TextField colorFieldGloves = new TextField();
        colorFieldGloves.setPromptText("Цвет");
        TextField priceFieldMotoProt = new TextField();
        priceFieldMotoProt.setPromptText("Цена");
        TextField weightFieldMotoProt = new TextField();
        weightFieldMotoProt.setPromptText("Вес");
        TextField typeFieldMotoProt = new TextField();
        typeFieldMotoProt.setPromptText("Тип");
        TextField sizeFieldMotoProt = new TextField();
        sizeFieldMotoProt.setPromptText("Размер");
        Button addButton = new Button("Добавить");
        addButton.setPrefWidth(70);
        Button cancelButton = new Button("Отменить");
        cancelButton.setPrefWidth(70);
        HBox hBox = new HBox();
        HBox.setMargin(addButton, new Insets(0, 10, 0, 0));
        hBox.getChildren().addAll(addButton, cancelButton);


        grid.add(new Label("ФИО"), 0, 0);
        grid.add(motoName, 1, 0);
        grid.add(motoName2, 2, 0);
        grid.add(new Label("Куртка"), 0, 1);
        grid.add(priceFieldJacket, 1, 1);
        grid.add(weightFieldJacket, 2, 1);
        grid.add(materialFieldJacket, 3, 1);
        grid.add(sizeFieldJacket, 4, 1);
        grid.add(new Label("Шлем"), 0, 2);
        grid.add(priceFieldHelmet, 1, 2);
        grid.add(weightFieldHelmet, 2, 2);
        grid.add(colorFieldHelmet, 3, 2);
        grid.add(openOrClosedComboBox, 4, 2);
        grid.add(new Label("Перчатки"), 0, 3);
        grid.add(priceFieldGloves, 1, 3);
        grid.add(weightFieldGloves, 2, 3);
        grid.add(clippedOrNotComboBox, 3, 3);
        grid.add(colorFieldGloves, 4, 3);
        grid.add(new Label("Защита"), 0, 4);
        grid.add(priceFieldMotoProt, 1, 4);
        grid.add(weightFieldMotoProt, 2, 4);
        grid.add(typeFieldMotoProt, 3, 4);
        grid.add(sizeFieldMotoProt, 4, 4);
        grid.add(hBox, 4, 7);

        addButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                List<Equipment> equipmentListForMoto = new ArrayList<>();
                if (!motoName.getText().equals("") && !motoName2.getText().equals("")) {
                    if (!priceFieldJacket.getText().equals("") && !weightFieldJacket.getText().equals("")
                            && !materialFieldJacket.getText().equals("") && !sizeFieldJacket.getText().equals("") &&
                            !priceFieldHelmet.getText().equals("") && !weightFieldHelmet.getText().equals("")
                            && !colorFieldHelmet.getText().equals("") && openOrClosedComboBox.getSelectionModel().getSelectedItem() != null &&
                            !priceFieldGloves.getText().equals("") && !weightFieldGloves.getText().equals("")
                            && clippedOrNotComboBox.getSelectionModel().getSelectedItem() != null && !colorFieldGloves.getText().equals("") &&
                            !priceFieldMotoProt.getText().equals("") && !weightFieldMotoProt.getText().equals("")
                            && !typeFieldMotoProt.getText().equals("") && !sizeFieldMotoProt.getText().equals("")) {
                        equipmentListForMoto.add(new Jacket(Double.parseDouble(priceFieldJacket.getText()), Double.parseDouble(weightFieldJacket.getText()),
                                "Куртка", materialFieldJacket.getText(), sizeFieldJacket.getText()));
                        equipmentListForMoto.add(new Helmet(Double.parseDouble(priceFieldHelmet.getText()), Double.parseDouble(weightFieldHelmet.getText()),
                                "Шлем", colorFieldHelmet.getText(), openOrClosedComboBox.getSelectionModel().getSelectedItem().equals("Open") ? true : false));
                        equipmentListForMoto.add(new Gloves(Double.parseDouble(priceFieldGloves.getText()), Double.parseDouble(weightFieldGloves.getText()),
                                "Перчатки", clippedOrNotComboBox.getSelectionModel().getSelectedItem().equals("С застёжкой") ? true : false, colorFieldGloves.getText()));
                        equipmentListForMoto.add(new MotoProtection(Double.parseDouble(priceFieldMotoProt.getText()), Double.parseDouble(weightFieldMotoProt.getText()),
                                "Защита", typeFieldMotoProt.getText(), sizeFieldMotoProt.getText()));
                    } else return;
                    Motorcyclist motorcyclist = new Motorcyclist(motoName.getText(), motoName2.getText(), equipmentListForMoto);
                    try {
                        writeMotoObj(motorcyclist);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    stage.close();
                    try {
                        List<Motorcyclist> list = new ArrayList<Motorcyclist>();
                        list = readMotoObj(motorcyclist);
                        listMotoObjects.addAll(list);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    listForNameOfMotorcyclists.add(listMotoObjects.get(listMotoObjects.size() - 1).getFirstNameOfMotorcyclist() + " " + listMotoObjects.get(listMotoObjects.size() - 1).getSecondNameOfMotorcyclist());
                    listView.getItems().clear();
                    listView.getItems().addAll(listForNameOfMotorcyclists);

                    clearAllViewLists();
                    if (listView.getSelectionModel().getSelectedItem() != null) {
                        List<Equipment> list = getEquipmentListOfSelectedMotorcyclist();
                        for (int j = 0; j < list.size(); j++) {
                            if (isJacket(list.get(j))) {
                                createJacketForViewList(list, j);
                            } else if (isHelmet(list.get(j))) {
                                createHelmetForViewList(list, j);
                            } else if (isGloves(list.get(j))) {
                                createGlovesForViewList(list, j);
                            } else if (isMotoProtection(list.get(j))) {
                                createMotoProtForViewList(list, j);
                            }
                        }
                    }

                }

            }
        });

        cancelButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                stage.close();
            }
        });

      Scene  newSc = new Scene(grid, 800, 250);
        return newSc;
    }


}
