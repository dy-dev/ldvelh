package com.arcreane.ldvelh.service;

import com.arcreane.ldvelh.repository.JSonRepository;

/**
 * Class used by the controller to manage all the player  functionnalities
 */
public class PlayerService {
    JSonRepository repository;

    /**
     * Default Constructor
     */
    public PlayerService() {
        repository = new JSonRepository("Saved Games");
    }
}
