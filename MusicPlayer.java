package com.example.demo;

//public class MusicPlayer {
//}
import java.util.*;
import java.util.stream.Collectors;
import java.io.*;


//import java.util.*;
//import java.util.stream.Collectors;


class MusicPlayer {
    private static final long serialVersionUID = 1L;
    private final List<Song> songs;
    private static final String DATA_FILE = "music_player_data.ser";

    public MusicPlayer() {
        this.songs = new ArrayList<>();
    }

    // Add a new song
    public void addSong(String name, String singer) {
        songs.add(new Song(name, singer, new Date()));

        System.out.println("Song added successfully: " + name);
    }

    // Play a song by name and/or singer
    public void playSong(String name, String singer) {
        List<Song> filteredSongs = songs.stream()
                .filter(song -> (name == null || song.getName().equalsIgnoreCase(name)) &&
                        (singer == null || song.getSinger().equalsIgnoreCase(singer)))
                .collect(Collectors.toList());

        if (filteredSongs.isEmpty()) {
            System.out.println("Song doesn't exist.");
        } else {
            Song songToPlay = filteredSongs.get(0);
//
            songToPlay.incrementPlayCount();

            System.out.println("Now playing: " + songToPlay);
        }
    }

    // Get top 10 songs of the day
    public void getTopSongsOfDay() {
        Date today = new Date();
        List<Song> topSongs = songs.stream()
                .filter(song -> isSameDay(song.getAddedDate(), today))
                .sorted((s1, s2) -> Integer.compare(s2.getPlayCount(), s1.getPlayCount()))
                .limit(10)
                .collect(Collectors.toList());

        System.out.println("Top 10 songs of the day: " + topSongs);
    }

    // Get top 10 songs of a singer
    public void getTopSongsBySinger(String singer) {
        List<Song> topSongs = songs.stream()
                .filter(song -> song.getSinger().equalsIgnoreCase(singer))
                .sorted((s1, s2) -> Integer.compare(s2.getPlayCount(), s1.getPlayCount()))
                .limit(10)
                .collect(Collectors.toList());

        System.out.println("Top 10 songs by " + singer + ": " + topSongs);
    }

    // Get top 10 songs of a particular date

    // Get all artist names whose songs were played more than X times
    public void getArtistsByPlayCount(int playCountThreshold) {
        Set<String> artists = songs.stream()
                .filter(song -> song.getPlayCount() > playCountThreshold)
                .map(Song::getSinger)
                .collect(Collectors.toSet());

        System.out.println("Artists with songs played more than " + playCountThreshold + " times: " + artists);
    }

    // Utility to check if two dates are the same day
    private boolean isSameDay(Date date1, Date date2) {
        Calendar cal1 = Calendar.getInstance();
        Calendar cal2 = Calendar.getInstance();
        cal1.setTime(date1);
        cal2.setTime(date2);
        return cal1.get(Calendar.YEAR) == cal2.get(Calendar.YEAR) &&
                cal1.get(Calendar.DAY_OF_YEAR) == cal2.get(Calendar.DAY_OF_YEAR);
    }
}



