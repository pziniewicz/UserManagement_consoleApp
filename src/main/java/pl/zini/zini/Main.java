package pl.zini.zini;

import java.util.LinkedList;
import java.util.Scanner;

public class Main {

    private static Scanner scanner = new Scanner(System.in);
    public static LinkedList<User> usersList = new LinkedList();

    public static void main(String[] args) {
        UserDAO.findAll();
        while(true){
            showOptions();
            String option = scanner.nextLine();
            if(option.equalsIgnoreCase("q")){
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
        User user = new User(email, username,password);
        System.out.println(user.getId() + " " + user.getEmail() + " " + user.getUserName() + user.getPassword());
        System.out.println(username + " created");
        usersList.add(user);
    }

    public static void removeUser() {

    }

    public static void displayUser() {

    }

    public static void printAllUsers() {
        usersList = UserDAO.findAll();
        for (User user : usersList) {
            System.out.println(user.getId() + " | " + user.getEmail() +
                    " | " + user.getUserName() + " | " + user.getPassword());
        }
    }
}
