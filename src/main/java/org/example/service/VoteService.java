package org.example.service;

import org.example.dto.VoteDTO;
import org.example.service.api.IVoteService;
import org.example.storage.VoteStorageMemory;
import org.example.storage.api.IVoteStorage;


public class VoteService implements IVoteService {

    private final static VoteService instance = new VoteService();
    private final static IVoteStorage voteStorage = VoteStorageMemory.getInstance();

    private VoteService() {
    }

    @Override
    public void create(VoteDTO vote) {
        if(vote.getArtist() == null || vote.getArtist().isBlank()) {
            throw new IllegalArgumentException("Артист пуст");
        }
        voteStorage.create(vote);
}

public static VoteService getInstance(){
        return instance;
    }
}