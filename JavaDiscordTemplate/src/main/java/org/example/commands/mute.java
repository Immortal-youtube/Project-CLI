package org.example.commands;

import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.commands.OptionMapping;
import org.jetbrains.annotations.NotNull;
@CommandInfo
public class mute extends ListenerAdapter {
    @Override
    public void onSlashCommandInteraction(@NotNull SlashCommandInteractionEvent event) {
        if(event.getName().equals("mute")) {
            if(!event.getMember().getRoles().contains(event.getGuild().getRoleById(System.getenv("GOD")))) {
                event.deferReply().queue();
                event.getHook().sendMessage("You don't have the necessary permissions").queue();
                return;
            }

            OptionMapping map = event.getOption("user");
            if(map == null){
                event.deferReply(true).queue();
                event.getHook().sendMessage("You need to specify a user to mute").queue();
            }else{
                User user = map.getAsUser();
                Member member = event.getGuild().getMember(user);
                if(member.getRoles().contains(event.getGuild().getRoleById(System.getenv("MUTE")))){
                    event.deferReply(true).queue();
                    event.getHook().sendMessage("This user is already muted").queue();
                }else{
                    event.getGuild().addRoleToMember(member, event.getGuild().getRoleById(System.getenv("MUTE"))).queue();
                    event.deferReply(true).queue();
                    event.getHook().sendMessage("Muted " + user.getAsMention()).queue();
                }
            }

        }
    }
}
