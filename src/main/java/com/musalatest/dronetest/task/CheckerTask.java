package com.musalatest.dronetest.task;

import com.musalatest.dronetest.model.Checks;
import com.musalatest.dronetest.repository.ChecksRepository;
import com.musalatest.dronetest.repository.DroneRepository;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

//@Slf4j
@Component
public class CheckerTask {

    private DroneRepository droneRepository;
    private ChecksRepository checksRepository;

    public CheckerTask(DroneRepository droneRepository, ChecksRepository checksRepository) {
        this.droneRepository = droneRepository;
        this.checksRepository = checksRepository;
    }

    @Scheduled(cron = "${tasks.cron.battery-check}")
    @Async
    public void batteryCheck() {
        try {
            System.out.println("Llega");
            List<Checks> checks = StreamSupport.stream(droneRepository.findAll().spliterator(), false)
                    .map(d -> {
                        Checks check = new Checks();
                        check.setDate(LocalDateTime.now());
                        check.setDescription(String.format("Drone #%d, Battery Level %d",d.getId(),d.getBatteryCapacity()));
                        return check;
                    })
                    .collect(Collectors.toList());

            try {
                checksRepository.saveAll(checks);
            } catch (Exception exc) {
                System.out.println("Battery check fail");
//                log.warn("Cannot save battery check event", exc);
            }
            System.out.println("Executing battery check");
        } catch (Exception e) {
            System.out.println("EXCEPTIONS HAPPENS");
            e.printStackTrace();
            // continue
        }
    }

}
