package jm.task.core.jdbc;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserServiceImpl;

public class Main {
    public static void main(String[] args) {


        UserServiceImpl userService = new UserServiceImpl();
        userService.createUsersTable();
        userService.saveUser("ss", "ss", (byte)23);
        userService.saveUser("dd", "dd", (byte)21);
        userService.saveUser("gg", "gg", (byte)24);
        userService.saveUser("ff", "ff", (byte)26);
        System.out.println(userService.getAllUsers());
        userService.cleanUsersTable();
        System.out.println(userService.getAllUsers());
        userService.dropUsersTable();
    }
}
