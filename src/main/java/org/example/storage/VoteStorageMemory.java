package org.example.storage;

import org.example.dto.VoteDTO;
import org.example.storage.api.IVoteStorage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class VoteStorageMemory implements IVoteStorage {

    private final static VoteStorageMemory instance = new VoteStorageMemory();
    private Map<String, Integer> artist = new HashMap<>();
    private Map<String, Integer> genre = new HashMap<>();
    private List<String> abouts = new ArrayList<>();

    private VoteStorageMemory() {
    }

    @Override
    public void create(VoteDTO vote) {
        inc(artist, vote.getArtist());

        for (String genre : vote.getGenre()) {
            inc(this.genre, genre);
        }
        abouts.add(vote.getAbout());
    }

    private void inc(Map<String, Integer> data, String key) {
        data.compute(key, (k, v) -> {
            if (v == null) {
                return 1;
            }
            return v + 1;
        });
    }

    public static VoteStorageMemory getInstance() {
        return instance;
    }
}
