package jm.task.core.jdbc;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;
import jm.task.core.jdbc.util.Util;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        Util.getConnection();

        UserService userService = new UserServiceImpl();

        userService.createUsersTable();

        userService.saveUser("Pasha", "Texnik", (byte) 28);
        userService.saveUser("Ilya", "Korolev", (byte) 31);
        userService.saveUser("Misha", "Lukin", (byte) 23);
        userService.saveUser("Lena", "Kraineva", (byte) 25);

        List<User> userList = userService.getAllUsers();
        for (User user : userList) {
            System.out.println(user);
        }

        userService.cleanUsersTable();
        userService.dropUsersTable();
    }
}
