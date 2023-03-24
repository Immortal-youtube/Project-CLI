package org.example;

import org.example.Initializers.JdaInitializers;

import javax.security.auth.login.LoginException;
import java.lang.reflect.InvocationTargetException;

public class Main {
    public static void main(String[] args) throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException, LoginException, InterruptedException {
        JdaInitializers initializers = new JdaInitializers();
        initializers.bot();
        initializers.initializeCommands();
        initializers.intializeListener();
    }
}