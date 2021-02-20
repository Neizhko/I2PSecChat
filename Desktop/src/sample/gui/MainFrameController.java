package sample.gui;

import com.sun.corba.se.impl.orb.DataCollectorBase;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
//import javafx.scene.text.Text;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import javax.imageio.ImageIO;
import javax.xml.crypto.Data;
//import javax.swing.text.html.ImageView;
import javafx.scene.image.ImageView;
//import java.awt.*;
import javafx.scene.image.Image;
import sample.Database.Database;
import sample.I2PConnector.I2PConnector;
import sample.Main;
import sample.Objects.Account;
import sample.Objects.Message;
import sample.Objects.Room;
import sample.Objects.TypeOfMessage;
import sample.Utils.Utils;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.image.DataBuffer;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.List;
import java.util.Objects;

/**
 * This class realizing controller for GUI of main frame.
 * @author Vladimir Neizhko
 **/

public class MainFrameController {

    @FXML
    private Label timeText;

    @FXML
    private Text Username;

    @FXML
    private Text roomName;

    @FXML
    private Button sendButton;

    @FXML
    private Button publicKeyButton;

    @FXML
    private Button changeRoomAvatarButton;

    @FXML
    private Button changeUserAvatarButton;

    @FXML
    private Button leaveRoomButton;

    @FXML
    private Button addParticipantButton;

    @FXML
    private Button createRoomButton;

    @FXML
    private TextField messageTextField;

    @FXML
    private ImageView userAvatarImageView;

    @FXML
    private ImageView roomAvatarImage;

    @FXML
    private VBox messagesVBox;

    @FXML
    private VBox roomVBox;

    @FXML
    private ListView participantsListView;

    private File chooseImage() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open image");
        FileChooser.ExtensionFilter extFilter =
                new FileChooser.ExtensionFilter("JPG", "*.jpg");
        fileChooser.getExtensionFilters().add(extFilter);
        Stage stage = new Stage();
        return fileChooser.showOpenDialog(stage);
    }

    /**
     * Method that changing avatar for current room.
     **/
    private void changeRoomAvatar() {
        if (chooseImage() != null) {
            System.out.println("Opening file..." + chooseImage().getPath());
            MainFrameLogic.getInstance().setRoomAvatarPath(chooseImage().getPath());
        }
    }

    /**
     * Method that changing avatar for user.
     */
    private void changeUserAvatar() {
        if (chooseImage() != null) {
            System.out.print("Opening file..." + chooseImage().getPath());
            MainFrameLogic.getInstance().setUserAvatarPath(chooseImage().getPath());
        }
    }

    /**
     * Method that sending message to current room.
     */
    private void sendMessage() {
        String message = messageTextField.getText();
        if (message != null && !message.equals("")) {
            MainFrameLogic.getInstance().addNewMessage(message);
            messageTextField.setText("");
            System.out.println("LOG EVENT: message has been sent");
        } else {
            System.out.println("LOG EVENT: message sending error");
        }
    }

    /**
     * Method copied public key to clipboard
     */
    private void getPublicKey() {
        StringSelection stringSelection = new StringSelection(MainFrameLogic.getInstance().getPublicKey());
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        clipboard.setContents(stringSelection, null);
    }

    /**
     * Method filling GUI message area in current room with list of messages.
     * @param messagesList: List of Message objects.
     */
    private void fillMessagesArea(List<Message> messagesList) {
        //TODO: add processing to convert Messages
        //MainFrameLogic.getInstance().getMessagesList();
        for (Message message : messagesList) {
            Label label = new Label(message.message);
            messagesVBox.getChildren().add(label);
        }
    }

    /**
     * Method filling GUI rooms area in UI with list of rooms.
     * @param roomsList: List of Room objects.
     */
    private void fillRooms(List<Room> roomsList) {
        //TODO: add processing to convert Rooms
    }

    /**
     * Method filling GUI participants area in current room with list of participants.
     * @param participantsList: List of Account objects.
     */
    private void fillParticipants(List<Account> participantsList) {
        //TODO: add processing to convert Accounts
    }

    /**
     * Method filling Avatar area of current room.
     * @param roomAvatarImage: Avatar image.
     */
    private void fillRoomAvatar(Image roomAvatarImage) {
        this.roomAvatarImage.setImage(roomAvatarImage);
    }

    /**
     * Method filling name of current room.
     * @param roomName: String with name of room.
     */
    private void fillRoomName(String roomName) {
        this.roomName.setText(roomName);
    }

    /**
     * Method filling date in GUI.
     * @param date: String with current date.
     */
    private void fillDate(String date) {
        timeText.setText(date);
    }

    @FXML
    void initialize() {

        fillDate(MainFrameLogic.getInstance().getDate());
        fillMessagesArea(MainFrameLogic.getInstance().getMessagesList());
        //fillParticipants();
        //fillRooms();
        //fillRoomAvatar(MainFrameLogic.getInstance().getCurrentRoom().getAvatar());
        //fillRoomName(MainFrameLogic.getInstance().getCurrentRoom().getName());

        sendButton.setOnAction(event -> {
            System.out.println("LOG EVENT: Button SEND was pressed");
            sendMessage();
        });

        publicKeyButton.setOnAction(event -> {
            System.out.println("Button KEY was pressed");
            getPublicKey();
        });

        changeRoomAvatarButton.setOnAction(event -> {
            System.out.println("Button CHANGE ROOM AVATAR was pressed");
            changeRoomAvatar();
        });

        changeUserAvatarButton.setOnAction(event -> {
            System.out.println("Button CHANGE USER AVATAR was pressed");
            changeUserAvatar();
        });

        leaveRoomButton.setOnAction(event -> {
            System.out.println("Button LEAVE ROOM was pressed");
            //TODO: complete it
        });

        addParticipantButton.setOnAction(event -> {
            System.out.println("Button ADD PARTICIPANT was pressed");
            //TODO: complete it
        });

        createRoomButton.setOnAction(event -> {
            System.out.println("Button CREATE ROOM was pressed");
            //TODO: complete it
        });
    }
}
