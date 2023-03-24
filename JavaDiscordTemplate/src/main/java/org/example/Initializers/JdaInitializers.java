package org.example.Initializers;

import io.github.cdimascio.dotenv.Dotenv;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.api.utils.MemberCachePolicy;
import org.example.commands.CommandInfo;
import org.reflections.Reflections;

import javax.security.auth.login.LoginException;
import java.lang.reflect.InvocationTargetException;
import java.util.EnumSet;

public class JdaInitializers {
    private JDA jda;

    public void bot() throws LoginException, InterruptedException {
        Dotenv dotenv = Dotenv.configure().filename("token").load();
        String token = dotenv.get("TOKEN");
        System.out.println(token);
        jda = JDABuilder.createDefault(token)
                .enableIntents(EnumSet.allOf(GatewayIntent.class))
                .setActivity(Activity.playing("MINECRAFT"))
                .setMemberCachePolicy(MemberCachePolicy.ALL)
                .build().awaitReady();
    }

    public void initializeCommands() throws NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException {

        String packageName = "org.example";
        for (Class<?> clazz : new Reflections(packageName + ".commands").getTypesAnnotatedWith(CommandInfo.class)) {
            System.out.println(clazz.getSimpleName());
            ListenerAdapter adapter = (ListenerAdapter) clazz.getDeclaredConstructor().newInstance();
            jda.addEventListener(adapter);
            CommandInfo commandInfo = clazz.getAnnotation(CommandInfo.class);
            boolean option1isNeeded = commandInfo.option1();
            boolean option2isNeeded = commandInfo.option2();
            boolean option3isNeeded = commandInfo.option3();
            if (option1isNeeded && option2isNeeded && option3isNeeded) {
                jda.upsertCommand(clazz.getSimpleName().toLowerCase(), commandInfo.description())
                        .addOption(commandInfo.option1Type(), commandInfo.option1Name(), commandInfo.option1Desc())
                        .addOption(commandInfo.option2Type(), commandInfo.option2Name(), commandInfo.option2Desc())
                        .addOption(commandInfo.option3Type(), commandInfo.option2Name(), commandInfo.option3Desc()).queue();
            } else if (option1isNeeded && option2isNeeded) {
                jda.upsertCommand(clazz.getSimpleName().toLowerCase(), commandInfo.description())
                        .addOption(commandInfo.option1Type(), commandInfo.option1Name(), commandInfo.option1Desc())
                        .addOption(commandInfo.option2Type(), commandInfo.option2Name(), commandInfo.option2Desc()).queue();
            } else if (option1isNeeded) {
                jda.upsertCommand(clazz.getSimpleName().toLowerCase(), commandInfo.description())
                        .addOption(commandInfo.option1Type(), commandInfo.option1Name(), commandInfo.option1Desc()).queue();
            } else {
                jda.upsertCommand(clazz.getSimpleName().toLowerCase(), commandInfo.description()).queue();
            }
        }
    }
    public void intializeListener () throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        String packageName = "org.example";
        for (Class<?> clazz : new Reflections(packageName + ".listeners").getSubTypesOf(ListenerAdapter.class)) {
            ListenerAdapter adapter = (ListenerAdapter) clazz.getDeclaredConstructor().newInstance();
            System.out.println(clazz.getSimpleName());
            jda.addEventListener(adapter);
        }
    }

}