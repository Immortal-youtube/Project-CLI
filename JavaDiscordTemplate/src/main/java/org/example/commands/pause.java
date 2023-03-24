package org.example.commands;

import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.example.commands.lavaplayer.PlayerManager;
import org.jetbrains.annotations.NotNull;
@CommandInfo
public class pause extends ListenerAdapter {

    @Override
    public void onSlashCommandInteraction(@NotNull SlashCommandInteractionEvent event) {
        if(event.getName().equals("pause")){
            if(event.getGuild().getAudioManager().isConnected()){
                PlayerManager.getINSTANCE().getGuildMusicManager(event.getGuild()).scheduler.pauseTrack();
                event.deferReply().queue();
                event.getHook().sendMessage("Paused the music").queue();
            }else{
                event.getHook().sendMessage("I am not playing anything").queue();
            }
        }
    }
}
