package com.veto.reports;

import com.veto.event.PetEvent;
import com.veto.event.PetPhoneChanged;
import com.veto.event.PetPhoneNotChanged;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class ChangedPetEventListener {
    private static final Logger logger = LoggerFactory.getLogger(ChangedPetEventListener.class);

    private final PersistedPetEventRepository repository;

    public ChangedPetEventListener(PersistedPetEventRepository repository) {
        this.repository = repository;
    }

    @Async
    @EventListener
    public void on(PetPhoneChanged event){
        onChanged(event);
    }

    @Async
    @EventListener
    public void on(PetPhoneNotChanged event){
        onChanged(event);
    }

    private void onChanged(final PetEvent event){
        logger.info("Got " + event);
        repository.save(new PersistedPetEvent(event));
    }
}
