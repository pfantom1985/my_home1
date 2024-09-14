package org.example.dto;

import java.util.Arrays;
import java.util.Objects;

// DTO - data transfer object
public class VoteDTO {
    private String artist;
    private String[] genre;
    private String about;

    public VoteDTO() {
    }

    public VoteDTO(String artist, String[] genre, String about) {
        this.artist = artist;
        this.genre = genre;
        this.about = about;
    }

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String[] getGenre() {
        return genre;
    }

    public void setGenre(String[] genre) {
        this.genre = genre;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        VoteDTO voteDTO = (VoteDTO) o;
        return Objects.equals(artist, voteDTO.artist) && Objects.deepEquals(genre, voteDTO.genre) && Objects.equals(about, voteDTO.about);
    }

    @Override
    public int hashCode() {
        return Objects.hash(artist, Arrays.hashCode(genre), about);
    }


}
