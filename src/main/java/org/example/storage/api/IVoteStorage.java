package org.example.storage.api;

import org.example.dto.VoteDTO;

public interface IVoteStorage {
    void create(VoteDTO vote);
}
