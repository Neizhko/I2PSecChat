package sample.gui;

import javafx.scene.image.Image;
import sample.Objects.Account;
import sample.Objects.Message;
import sample.Objects.Room;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import sample.Database.Database;
import sample.Utils.Utils;

/**
 * This class realizing logic of interacting with GUI.
 * @author Lev Averin
 **/

public class MainFrameLogic {

    private static MainFrameLogic uniqueInstance;

    private MainFrameLogic() {}

    public static synchronized MainFrameLogic getInstance() {
        if (uniqueInstance == null) {
            uniqueInstance = new MainFrameLogic();
        }
        return uniqueInstance;
    }

    private Account currentUser;

    private Room currentRoom;

    private void setCurrentUser(Account account) {
        /**
         * Setter method that sets current user.
         * TODO: ACHTUNG! Method is private for protect against changing user.
         *       I think it should be set only one time after log in.
         *       And maybe it's necessary to create new singleton object as User instead Account currentUser.
         * @param account Account of current user.
         */
        currentUser = account;
    }

    protected Account getCurrentUser() {
        /**
         * Getter function that returns current user as Account object.
         * @return current user as Account.
         */
        return currentUser;
    }

    /**
     * Setter method that sets current room.
     * @param room current room as Room object.
     */
    protected void setCurrentRoom(Room room) {
        currentRoom = room;
    }

    /**
     * Getter function that returns current room.
     * @return current room as Room.
     */
    Room getCurrentRoom() {
        return currentRoom;
    }

    public List<Message> getMessagesList() {
        /**
         * Function that returns messages for current room.
         * TODO: Maybe we should find out how to protect this method from unauthorized access to hidden rooms.
         *       Hint: you can check is current user are participant of room from parameter.
         * @return List of Message object.
         */
        int room_id = MainFrameLogic.getInstance().getCurrentRoom().getId();
        List<List<String>> messages = Database.getMessagesInRoom(room_id);
        return null;
    }

    public List<Room> getRoomsList() {
        /**
         * Function that returns list of rooms for current user.
         * @return List of Room object.
         */
        List<List<Object>> allRooms = Database.getAllRooms();
        List<Room> roomList = new ArrayList<>();
        for (int i = 0; i < allRooms.size(); i++) {
            String filepath = "";
            try {
                filepath = allRooms.get(i).get(5).toString();
            }
            catch(NullPointerException e) {
                filepath = "";
            }
            roomList.add(new Room((int)allRooms.get(i).get(0), allRooms.get(i).get(1).toString(), allRooms.get(i).get(2).toString(), (int)allRooms.get(i).get(3), allRooms.get(i).get(4).toString(), filepath));
            System.out.println(i);
        }
        return roomList;
    }

    protected List<Account> getParticipantsList() {
        /**
         * Function that returns participants of current room.
         * @return List of Account objects.
         */
        return null;
    }

    protected List<Account> getContactsList() {
        /**
         * Function that returns contacts of user.
         * @return List of Account objects.
         *TODO: So as you can see there is no parameter with User whose contacts we should get like in previous method.
         *      It means that we should find out how to get contacts personal for logged user.
         *      There is shouldn't be any ways to get contacts of another user.
         */
        return null;
    }

    protected String getPublicKey() {
        /**
         * Function that returns Public Key of user.
         * @return public key as String.
         */
        return "public key";
    }

    protected String getDate() {
        /**
         * Function that generates current date in String format.
         * @return String with current date in dd.mm.yyyy format.
         */
        SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy");
        Date date = new Date();
        return formatter.format(date);
    }

    protected void addNewMessage(String message) {
        /**
         * Method that adds messages to database.
         * @param message String with text of message.
         */
        //int room_id = 0;
        //int user_id = 0;
        //Database.register_message(room_id, user_id, message, this.getDate());
    }

    public void addNewRoom(Room room) {
        /**
         * Method that adds room to database.
         * @param room Room object.
         */
        Database.add_room(room.getName(), room.getDeleteMessageTime(), room.getInfo(), room.getAESKey(), room.getFilepath());
    }

    protected void addNewMember(Account account) {
        /**
         * Method that adds new member to current room.
         * @param account Account object that will be added to room.
         */
    }

    /**
     * Method that adds room avatar path to the database.
     * @param roomAvatarPath String object.
     */
    void setRoomAvatarPath(String roomAvatarPath) {
        Database.update_picture("room", 1, roomAvatarPath);
        String path = "";
        List<List<Object>> rooms = Database.getAllRooms();
        for(int i = 0; i < rooms.size(); i++) {
            if ((int)rooms.get(i).get(0) == 1){
                path = Utils.bytesToImagePath((byte[])rooms.get(i).get(5));
                break;
            }
        }

        File file = new File(path);
        Image roomAvatar = new Image(file.toURI().toString());
        //roomAvatarImageView.setImage(roomAvatar);
        /** PLZ DO SOMETHING ABOUT !!!**/
    }

    /**
     * Method that adds user avatar path to the database.
     * @param userAvatarPath String object.
     * TODO: complete this method!
     */
    void setUserAvatarPath(String userAvatarPath) {}
}
