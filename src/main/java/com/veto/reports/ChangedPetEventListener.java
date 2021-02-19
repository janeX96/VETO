package com.veto.reports;

import com.veto.event.PetPhoneChanged;
import com.veto.event.PetPhoneNotChanged;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

@Service
public class ChangedPetEventListener {
    public static final Logger logger = LoggerFactory.getLogger(ChangedPetEventListener.class);

    @EventListener
    public void on(PetPhoneChanged event){
        logger.info("Got " + event);
    }

    @EventListener
    public void on(PetPhoneNotChanged event){
        logger.info("Got " + event);
    }
}
