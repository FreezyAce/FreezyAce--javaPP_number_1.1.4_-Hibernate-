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
//        userService.cleanUsersTable(); // очистка всех юзеров в таблице
//        Util.getSessionFactory();



        userService.saveUser(userTest[0][0], userTest[0][1], (byte) Integer.parseInt(userTest[0][2]));
        userService.saveUser(userTest[1][0], userTest[1][1], (byte) Integer.parseInt(userTest[1][2]));
        userService.saveUser(userTest[2][0], userTest[2][1], (byte) Integer.parseInt(userTest[2][2]));
        userService.saveUser(userTest[3][0], userTest[3][1], (byte) Integer.parseInt(userTest[3][2]));
//
//        List<User> users = userService.getAllUsers();
//
//        System.out.println(users.get(0).getName() + " " + users.get(0).getLastName() + " " + users.get(0).getAge());
//        System.out.println(users.get(1).getName() + " " + users.get(1).getLastName() + " " + users.get(1).getAge());
//        System.out.println(users.get(2).getName() + " " + users.get(2).getLastName() + " " + users.get(2).getAge());
//        System.out.println(users.get(3).getName() + " " + users.get(3).getLastName() + " " + users.get(3).getAge());
    }
}
