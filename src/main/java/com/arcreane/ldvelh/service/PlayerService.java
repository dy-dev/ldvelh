package com.arcreane.ldvelh.service;

import com.arcreane.ldvelh.repository.IRepository;
import com.arcreane.ldvelh.repository.json.JSonRepository;

/**
 * Class used by the controller to manage all the player  functionnalities
 */
public class PlayerService {
    IRepository repository;

    /**
     * Default Constructor
     */
    public PlayerService() {
        repository = new JSonRepository("Saved Games");
    }
}
