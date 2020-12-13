package home;


import com.fasterxml.jackson.core.JsonProcessingException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;


import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    @FXML
    private Button menuStartBtn;

    @FXML
    private Button menuEncryptBtn;

    @FXML
    private Button menuDecryptBtn;

    @FXML
    private Button menuAboutBtn;

    @FXML
    private Button chooseFileBtnD;

    @FXML
    private Button chooseFileBtnE;

    @FXML
    private Button exportBtnD;

    @FXML
    private Button exportBtnE;

    @FXML
    private Button encryptBtn;

    @FXML
    private Button decryptBtn;

    @FXML
    private Button importKeyE;

    @FXML
    private Button importKeyD;

    @FXML
    private Button generateKeyBtn;

    @FXML
    private Button exitBtn;

    @FXML
    private AnchorPane apStart;

    @FXML
    private AnchorPane apEncrypt;

    @FXML
    private AnchorPane apDecrypt;

    @FXML
    private AnchorPane apAbout;

    @FXML
    private TextField tfFileE;

    @FXML
    private TextField tfFileD;

    @FXML
    private TextField tfKeyD;

    @FXML
    private TextField tfKeyE;

    @FXML
    private TextField tfKeyE1;

    @FXML
    private TextArea tfResultD;

    @FXML
    private TextArea tfResultE;

    @FXML
    private TextArea tfTypeE;

    @FXML
    private TextArea tfTypeD;

    @FXML
    private Label warningLabelE;

    @FXML
    private Label warningLabelD;

    HashMap<Character, ArrayList<Integer>> keyMapImported = new HashMap<>();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        apStart.setVisible(true);
        apStart.setOpacity(1);
        tfFileE.setEditable(false);
        tfFileD.setEditable(false);
        tfResultE.setEditable(false);
        tfResultD.setEditable(false);
        tfKeyE.setEditable(false);
        tfKeyE1.setEditable(false);
        tfKeyD.setEditable(false);
        warningLabelE.setVisible(false);
        warningLabelD.setVisible(false);
    }

    public void handleClicks(ActionEvent actionEvent) throws JsonProcessingException {
        if (actionEvent.getSource() == menuStartBtn) {
            apStart.setVisible(true);
            apStart.setOpacity(1);
            apEncrypt.setVisible(false);
            apEncrypt.setOpacity(0);
            apDecrypt.setVisible(false);
            apDecrypt.setOpacity(0);
            apAbout.setVisible(false);
            apAbout.setOpacity(0);
        }
        if (actionEvent.getSource() == menuEncryptBtn) {
            apEncrypt.setVisible(true);
            apEncrypt.setOpacity(1);
            apStart.setVisible(false);
            apStart.setOpacity(0);
            apDecrypt.setVisible(false);
            apDecrypt.setOpacity(0);
            apAbout.setVisible(false);
            apAbout.setOpacity(0);
        }
        if (actionEvent.getSource() == menuDecryptBtn) {
            apDecrypt.setVisible(true);
            apDecrypt.setOpacity(1);
            apStart.setVisible(false);
            apStart.setOpacity(0);
            apEncrypt.setVisible(false);
            apEncrypt.setOpacity(0);
            apAbout.setVisible(false);
            apAbout.setOpacity(0);
        }
        if (actionEvent.getSource() == menuAboutBtn) {
            apAbout.setVisible(true);
            apAbout.setOpacity(1);
            apDecrypt.setVisible(false);
            apDecrypt.setOpacity(0);
            apStart.setVisible(false);
            apStart.setOpacity(0);
            apEncrypt.setVisible(false);
            apEncrypt.setOpacity(0);
        }
        if (actionEvent.getSource() == chooseFileBtnD) {
            Import importFile = new Import();
            importFile.importText(menuDecryptBtn);
            tfFileD.setText(importFile.getPath());
            tfTypeD.setText(importFile.getReadedContent());
        }
        if (actionEvent.getSource() == chooseFileBtnE) {
            Import importFile = new Import();
            importFile.importText(menuEncryptBtn);
            tfFileE.setText(importFile.getPath());
            tfTypeE.setText(importFile.getReadedContent());
        }
        if (actionEvent.getSource() == importKeyE) {
            Import importKe = new Import();
            keyMapImported.clear();
            importKe.importJson(menuEncryptBtn);
            keyMapImported = importKe.getKeyMap();
            tfKeyE.setText(importKe.getPath());
        }
        if (actionEvent.getSource() == importKeyD) {
            Import importKd = new Import();
            keyMapImported.clear();
            importKd.importJson(menuDecryptBtn);
            keyMapImported = importKd.getKeyMap();
            tfKeyD.setText(importKd.getPath());
        }
        if (actionEvent.getSource() == generateKeyBtn) {
            Export exportKe = new Export();
            Encrypt encrypt = new Encrypt();
            encrypt.generateRandom();
            exportKe.setMapToExport(encrypt.getKeyMap());
            exportKe.exportMap(menuEncryptBtn);
            tfKeyE1.setText(exportKe.getPath());
        }
        if (actionEvent.getSource() == exportBtnE) {
            Export export = new Export();
            export.setStringToExport(tfResultE.getText());
            export.exportText(menuEncryptBtn);
        }
        if (actionEvent.getSource() == exportBtnD) {
            Export export = new Export();
            export.setStringToExport(tfResultD.getText());
            export.exportText(menuDecryptBtn);
        }
        if (actionEvent.getSource() == encryptBtn){
            Encrypt encrypt = new Encrypt();
            if(tfKeyE.getText().length() > 0 && tfTypeE.getText().length() > 0){
                encrypt.setTextToEncrypt(tfTypeE.getText());
                encrypt.setKeyMap(keyMapImported);
                encrypt.encrypt();
                tfResultE.clear();
                tfResultE.setText(encrypt.getEncrypted());
                warningLabelE.setVisible(false);
            }else{
                warningLabelE.setVisible(true);
            }
        }
        if (actionEvent.getSource() == decryptBtn){
            Decrypt decrypt = new Decrypt();
            if(tfKeyD.getText().length() > 0 && tfTypeD.getText().length() > 0) {
                decrypt.setTextToDecrypt(tfTypeD.getText());
                decrypt.decrypt(keyMapImported);
                tfResultE.clear();
                tfResultD.setText(decrypt.getDecrypted());
                if(decrypt.checkKey() == false){
                    warningLabelD.setText("Wrong key or typed text format!");
                    warningLabelD.setVisible(true);
                }else{
                    warningLabelD.setVisible(false);
                }
            }else{
                warningLabelD.setVisible(true);
            }

        }
        if (actionEvent.getSource() == exitBtn){
            Stage stage = (Stage) exitBtn.getScene().getWindow();
            stage.close();
        }
    }
}
