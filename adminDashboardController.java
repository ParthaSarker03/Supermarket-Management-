/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package suparmarket;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 *
 * @author ASUS
 */
public class adminDashboardController implements Initializable {

    @FXML
    private Button close;

    @FXML
    private Button logout;

    @FXML
    private AnchorPane main_form;

    @FXML
    private Button minimize;

    @FXML
    private Button purchase_addBtn;

    @FXML
    private TextField purchase_brand;

    @FXML
    private TableColumn<customerData, String> purchase_col_brand;

    @FXML
    private TableColumn<customerData, String> purchase_col_price;

    @FXML
    private TableColumn<customerData, String> purchase_col_productName;

    @FXML
    private TableColumn<customerData, String> purchase_col_quantity;

    @FXML
    private Label purchase_employeeId;

    @FXML
    private Button purchase_pay;

    //@FXML
    //private ComboBox<?> purchase_productName;
    @FXML
    private TextField purchase_productName;

    @FXML
    private Spinner<Integer> purchase_quantity;

    @FXML
    private Button purchase_receiptBtn;
      @FXML
    private Button purchase_reset;

    @FXML
    private TableView<customerData> purchase_tableView;

    @FXML
    private Label purchase_total;

    private Connection connect;
    private PreparedStatement prepare;
    private Statement statement;
    private ResultSet result;

    public void purchaseAdd() {

        purchaseCustomerId();
        purchaseSpinnerValue();
        purchasePrice();

        String insertProd = "INSERT INTO customer"
                + "(customer_id,brand,productName,quantity,price)"
                + "VALUES(?,?,?,?,?)";

        connect = database.connectDB();
        try {

            Alert alert;

            if (purchase_brand.getText().isEmpty()
                    || purchase_productName.getText() == null
                    || qty == 0) {

                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Please Fill All Blank Fields");
                alert.showAndWait();

            } else {

                prepare = connect.prepareStatement(insertProd);

                prepare = connect.prepareStatement(insertProd);
                prepare.setString(1, String.valueOf(customerId));
                prepare.setString(2, purchase_brand.getText());

                prepare.setString(3, (String) purchase_productName.getText());
                prepare.setString(4, String.valueOf(qty));
                totalprice = (int) (qty * price);
                prepare.setString(5, String.valueOf(totalprice));

                prepare.executeUpdate();

                purchaseShowListData();
                purchesdisplayTotal();

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private double total = 0;

    public void purchesdisplayTotal() {

        String sql = "SELECT SUM(price) FROM customer WHERE customer_id='"
                + customerId + "'";
        connect = database.connectDB();
        try {

            statement = connect.createStatement();
            result = statement.executeQuery(sql);

            if (result.next()) {
                total = result.getDouble("SUM(price)");
            }

            purchase_total.setText(String.valueOf(total) + "TK");

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    
    
    
    
     /*public void purcheseReset() {

        purchaseCustomerId();

        String reserData = "DELETE FORM customer WHERE customer_id ='" + customerId + "'";

        connect = database.connectDB();

        try {

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmation Message");
            alert.setHeaderText(null);
            alert.setContentText("Are you sure to Reset ??");
            Optional<ButtonType> option = alert.showAndWait();

            if (option.get().equals(ButtonType.OK)) {
                statement = connect.createStatement();
                statement.executeUpdate(reserData);

                purchase_brand.setText("");
                purchase_productName.setText("");
                
               purchaseSpinner();
               
               purchase_total.setText("0.00");

            } else {
                return;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    
    */
    
    
    

    private double price = 0;
    private int totalprice = 0;

    public void purchasePrice() {

        String gprice = "SELECT price FROM product WHERE product_name='"
                + purchase_productName.getText() + "'";
        connect = database.connectDB();

        try {

            statement = connect.createStatement();
            result = statement.executeQuery(gprice);

            if (result.next()) {
                price = result.getDouble("price");

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void purchesSearchBrand() {
        String searchB = "SELECT * FROM product WHERE brand ='"
                + purchase_brand.getText() + "' and status ='Available'";
        connect = database.connectDB();

        try {

            prepare = connect.prepareStatement(searchB);
            result = prepare.executeQuery();

            ObservableList listProduct = FXCollections.observableArrayList();

            if (result.next()) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                //alert.setTitle("Confirmation Message");
                alert.setHeaderText(null);
                alert.setContentText(purchase_brand.getText() + "Not Found");
                alert.showAndWait();

            } else {

                while (result.next()) {
                    listProduct.add(result.getString("product_name"));
                }
            }

            // purchase_productName.setItems(listProduct);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private SpinnerValueFactory spinner;

    public void purchaseSpinner() {
        spinner = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 20, 0);

        purchase_quantity.setValueFactory(spinner);

    }

    private int qty;

    public void purchaseSpinnerValue() {
        qty = purchase_quantity.getValue();
        //System.out.println(qty);
    }

    public ObservableList<customerData> purchaseListData() {

        purchaseCustomerId();

        ObservableList<customerData> customerList = FXCollections.observableArrayList();
        String sql = "SELECT *FROM customer WHERE customer_id = '" + customerId + "' ";
        connect = database.connectDB();
        try {
            customerData custD;

            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();

            while (result.next()) {
                custD = new customerData(result.getInt("customer_id"),
                        result.getString("brand"),
                        result.getString("productName"),
                        result.getInt("quantity"),
                        result.getDouble("price")
                );

                customerList.add(custD);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return customerList;

    }

    private ObservableList<customerData> purchaseList;

    public void purchaseShowListData() {

        purchaseList = purchaseListData();

        purchase_col_brand.setCellValueFactory(new PropertyValueFactory<>("brand"));
        purchase_col_productName.setCellValueFactory(new PropertyValueFactory<>("productName"));
        purchase_col_quantity.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        purchase_col_price.setCellValueFactory(new PropertyValueFactory<>("price"));

        purchase_tableView.setItems(purchaseList);

    }

    private int customerId;

    public void purchaseCustomerId() {

        String cID = "SELECT customer_id FROM customer";
        connect = database.connectDB();
        try {

            prepare = connect.prepareStatement(cID);
            result = prepare.executeQuery();

            while (result.next()) {
                customerId = result.getInt("customer_id");
            }

            int checkNum = 0;
            String chakeCustomerId = "SELECT customer_id FROM customer_receipt";
            statement = connect.createStatement();
            result = statement.executeQuery(chakeCustomerId);

            while (result.next()) {
                checkNum = result.getInt("customer_id");
            }

            if (customerId == 0) {
                customerId += 1;
            } else if (checkNum == customerId) {
                customerId += 1;

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void displayEmployeeId() {
        purchase_employeeId.setText(getData.employeeId);
    }

   

    /* public void pay() {

        try {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmation Message");
            alert.setHeaderText(null);
            alert.setContentText("Are you sure to Pay ??");
            Optional<ButtonType> option = alert.showAndWait();

            if (option.get().equals(ButtonType.OK)) {

                //logout.getScene().getWindow().hide();
                Parent root = FXMLLoader.load(getClass().getResource("adminDashboard.fxml"));
                Stage stage = new Stage();
                Scene scene = new Scene(root);

                stage.setScene(scene);
                stage.show();
            } else {
                return;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }*/
    public void purchasePay() {
        purchaseCustomerId();
        purchesdisplayTotal();

        String sql = "INSERT INTO customer_receipt (customer_id,total)"
                + "VALUES(?,?)";
        connect = database.connectDB();

        try {

            if (purchase_tableView.getItems().isEmpty()) {

                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Chose the product");
                alert.showAndWait();

            } else {

                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Confirmation Message");
                alert.setHeaderText(null);
                alert.setContentText("Are you sure to Pay ??");
                Optional<ButtonType> option = alert.showAndWait();

                if (option.get().equals(ButtonType.OK)) {
                    prepare = connect.prepareStatement(sql);
                    prepare.setString(1, String.valueOf(customerId));
                    prepare.setString(2, String.valueOf(total));

                    prepare.executeUpdate();
                    alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Information Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Successfully  !!!");
                    alert.showAndWait();

                } else {
                    return;
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void logout() {
        try {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmation Message");
            alert.setHeaderText(null);
            alert.setContentText("Are you sure to logout ??");
            Optional<ButtonType> option = alert.showAndWait();

            if (option.get().equals(ButtonType.OK)) {

                logout.getScene().getWindow().hide();

                Parent root = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
                Stage stage = new Stage();
                Scene scene = new Scene(root);

                stage.setScene(scene);
                stage.show();
            } else {
                return;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void close() {
        System.exit(0);
    }

    public void minimize() {
        Stage stage = (Stage) main_form.getScene().getWindow();
        stage.setIconified(true);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        displayEmployeeId();
        purchaseShowListData();
        purchaseSpinner();
        //purchesSearchBrand();
        //purchaseSpinnerValue();

    }

}
