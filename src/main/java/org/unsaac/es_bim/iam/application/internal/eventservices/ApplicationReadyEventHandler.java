package org.unsaac.es_bim.iam.application.internal.eventservices;

import lombok.RequiredArgsConstructor;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
import org.unsaac.es_bim.iam.application.internal.commandservices.RoleCommandService;
import org.slf4j.Logger;
import org.unsaac.es_bim.iam.domain.model.commands.role.SeedRolesCommand;

import java.sql.Timestamp;

@Service
@RequiredArgsConstructor
public class ApplicationReadyEventHandler {
    private final RoleCommandService roleCommandService;
    private static final Logger LOGGER= LoggerFactory.getLogger(ApplicationReadyEventHandler.class);
    /**
     * This method is responsible for handling the application ready event and
     * seeding the roles if needed.
     *
     * @param event ApplicationReadyEvent object
     *              This event is published when the ApplicationContext is fully
     *              refreshed and ready for use but before any actual work has been
     *              done.
     * @see ApplicationReadyEvent
     */
    @EventListener
    public void on(ApplicationReadyEvent event) {
        var name = event.getApplicationContext().getId();
        LOGGER.info("Starting to seed roles if needed for {} at {}", name, new Timestamp(System.currentTimeMillis()));
        var seedRolesCommand = new SeedRolesCommand();
        roleCommandService.handle(seedRolesCommand);
        LOGGER.info("Finished seeding roles if needed for {} at {}", name, new Timestamp(System.currentTimeMillis()));
    }
}
