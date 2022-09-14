package jm.task.core.jdbc;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserServiceImpl;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.util.Util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        final UserService userService = new UserServiceImpl();
        String[][] userTest = {{"Gey", "Orgii", "20"},
                {"Ivan", "Petrov", "23"},
                {"One", "PanchMan", "22"},
                {"Bonnie", "Rotten", "33"}};

        userService.dropUsersTable(); // очистка таблицы если есть.
        userService.createUsersTable(); // создание таблицы
        userService.cleanUsersTable(); // очистка всех юзеров в таблице

        userService.saveUser(userTest[0][0], userTest[0][1], (byte) Integer.parseInt(userTest[0][2]));
        userService.saveUser(userTest[1][0], userTest[1][1], (byte) Integer.parseInt(userTest[1][2]));
        userService.saveUser(userTest[2][0], userTest[2][1], (byte) Integer.parseInt(userTest[2][2]));
        userService.saveUser(userTest[3][0], userTest[3][1], (byte) Integer.parseInt(userTest[3][2]));

        List<User> users = userService.getAllUsers();

        for (int i = 0; i < users.size(); i++) {
            System.out.println(users.get(i).getName() + " " + users.get(i).getLastName() + " " + users.get(i).getAge());
        }

        userService.removeUserById(1L);

        users = userService.getAllUsers();

        for (int i = 0; i < users.size(); i++) {
            System.out.println(users.get(i).getName() + " " + users.get(i).getLastName() + " " + users.get(i).getAge());
        }
    }
}
