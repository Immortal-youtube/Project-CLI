package org.example.commands.lavaplayer;

import com.sedmelluq.discord.lavaplayer.player.AudioLoadResultHandler;
import com.sedmelluq.discord.lavaplayer.player.AudioPlayerManager;
import com.sedmelluq.discord.lavaplayer.player.DefaultAudioPlayerManager;
import com.sedmelluq.discord.lavaplayer.source.AudioSourceManagers;
import com.sedmelluq.discord.lavaplayer.tools.FriendlyException;
import com.sedmelluq.discord.lavaplayer.track.AudioPlaylist;
import com.sedmelluq.discord.lavaplayer.track.AudioTrack;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.TextChannel;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PlayerManager {
    private static PlayerManager INSTANCE;
    private final Map<Long, GuildMusicManager> musicManager;
    private final AudioPlayerManager playerManager;

    public PlayerManager(){
        this.musicManager =  new HashMap<>();
        this.playerManager = new DefaultAudioPlayerManager();
        AudioSourceManagers.registerRemoteSources(this.playerManager);
        AudioSourceManagers.registerLocalSource(this.playerManager);

    }

    public GuildMusicManager getMusicManager(Guild guild){
        return this.musicManager.computeIfAbsent(guild.getIdLong(),(guildId) -> {
            final GuildMusicManager musicManager = new GuildMusicManager(this.playerManager);
            guild.getAudioManager().setSendingHandler(musicManager.getSendHandler());
            return musicManager;
        });
    }

    public static PlayerManager getINSTANCE(){
        if(INSTANCE == null){
            INSTANCE = new PlayerManager();
        }
        return INSTANCE;
    }

    public void loadAndPlay(TextChannel channel,String trackUrl){
        final GuildMusicManager musicManager = this.getMusicManager(channel.getGuild());
        this.playerManager.loadItemOrdered(musicManager, trackUrl, new AudioLoadResultHandler() {
            @Override
            public void trackLoaded(AudioTrack audioTrack) {
                musicManager.scheduler.queue(audioTrack);
                //channel.sendMessage("Added to queue: " + audioTrack.getInfo().title).queue();
            }

            @Override
            public void playlistLoaded(AudioPlaylist audioPlaylist) {
                final List<AudioTrack> tracks = audioPlaylist.getTracks();
                if(!tracks.isEmpty()){
                    musicManager.scheduler.queue(tracks.get(0));
                    //channel.sendMessage("Added to queue: " + tracks.get(0).getInfo().title).queue();
                }

            }

            @Override
            public void noMatches() {

            }

            @Override
            public void loadFailed(FriendlyException e) {

            }
        });
    }

    public GuildMusicManager getGuildMusicManager(Guild guild) {
        return this.musicManager.get(guild.getIdLong());
    }

    public boolean isPlaying(){
        if(!musicManager.isEmpty()){
            return true;
        }
        else{
            return false;
        }
    }

    public void clear(){
        this.musicManager.clear();
    }
}
