package pl.zini.zini;

import com.mysql.cj.util.StringUtils;

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
                case "e" -> editUser();
                case "p" -> printAllUsers();
                // default -> System.out.println("invalid option chosen");
            }
        }
    }

    public static void showOptions() {
        System.out.println("(c)reate user");
        System.out.println("(r)emove user");
        System.out.println("(d)isplay user");
        System.out.println("(e)dit user");
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
        while (true) {
            System.out.print("Enter user id: ");
            String option = scanner.nextLine();
            if (option.equalsIgnoreCase("q")) {
                break;
            } else if (StringUtils.isStrictlyNumeric(option)) {
                long id = Long.parseLong(option);
                int deleted = User.deleteUser(id);
                if (deleted > 0) {
                    System.out.println("User deleted.");
                } else {
                    System.out.println("No such user");
                }
                break;
            } else {
                continue;
            }
        }
    }


    public static long displayUser() {
        long id = 0;
        while (true) {
            System.out.print("Enter user id: ");
            String option = scanner.nextLine();
            if (option.equalsIgnoreCase("q")) {
                break;
            } else if (StringUtils.isStrictlyNumeric(option)) {
                id = Long.parseLong(option);
                for (User user : usersList) {
                    if (user.getId() == id) {
                        System.out.println(user.getId() + " | " + user.getEmail() +
                                " | " + user.getUserName() + " | " + user.getPassword());
                    }
                }
                break;
            }
        }
        return id;
    }

    public static void editUser() {
        long id = displayUser();
        System.out.println("Edit User");
        System.out.print("Enter email: ");
        String email = scanner.nextLine();
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();
        User.updateUser(id,email, username, password);
    }

    public static void printAllUsers() {
        usersList = UserDAO.findAll();
        for (User user : usersList) {
            System.out.println(user.getId() + " | " + user.getEmail() +
                    " | " + user.getUserName() + " | " + user.getPassword());
        }
    }
}
