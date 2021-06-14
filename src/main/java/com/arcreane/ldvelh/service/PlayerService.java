package com.arcreane.ldvelh.service;

import com.arcreane.ldvelh.repository.JSonRepository;

public class PlayerService {
    JSonRepository repository;

    public PlayerService() {
        repository = new JSonRepository("Saved Games");
    }
}
