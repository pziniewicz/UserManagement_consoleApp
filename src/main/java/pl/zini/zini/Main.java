package pl.zini.zini;

import java.util.LinkedList;
import java.util.Scanner;

public class Main {

    private static Scanner scanner = new Scanner(System.in);
    public static LinkedList<User> usersList = new LinkedList();

    public static void main(String[] args) {
        usersList = UserDAO.findAll();
        while (true) {
            showOptions();
            String option = scanner.nextLine();
            if (option.equalsIgnoreCase("q")) {
                break;
            }
            switch (option.toLowerCase()) {
                case "c" -> createUser();
                case "r" -> removeUser();
                case "d" -> displayUser();
                case "p" -> printAllUsers();
                default -> System.out.println("invalid option chosen");
            }
        }
    }

    public static void showOptions() {
        System.out.println("(c)reate user");
        System.out.println("(r)emove user");
        System.out.println("(d)isplay user");
        System.out.println("(p)rint all users");
        System.out.println("(q)uit");
        System.out.print("Enter option --> ");
    }

    public static void createUser() {
        System.out.println("Create new User");
        System.out.print("Enter email: ");
        String email = scanner.nextLine();
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();
        User user = new User(email, username, password);
        System.out.println(user.getId() + " | " + user.getEmail() +
                " | " + user.getUserName() + " | " + user.getPassword());
        System.out.println(username + " created");
        usersList.add(user);
    }

    public static void removeUser() {
        System.out.print("Enter user id: ");
        long id = scanner.nextLong();
        String tmpUserName = usersList.get((int) id).getUserName();
        User.deleteUser(id);
        System.out.println(tmpUserName + " removed");
    }

    public static User chooseUser() {

        User userSearched = null;
        System.out.print("Enter user id: ");
        long id = scanner.nextLong();
        for (User user : usersList) {
            if (user.getId() == id) {
                userSearched = user;
            } else {
                return null;
            }
        }
        return userSearched;
    }

    public static void displayUser() {
        User user = chooseUser();
        System.out.println(user.getId() + " | " + user.getEmail() +
                " | " + user.getUserName() + " | " + user.getPassword());
    }

    public static void printAllUsers() {
        usersList = UserDAO.findAll();
        for (User user : usersList) {
            System.out.println(user.getId() + " | " + user.getEmail() +
                    " | " + user.getUserName() + " | " + user.getPassword());
        }
    }
}
