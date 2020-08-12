package app.controller;

import app.entity.Client;
import app.service.ClientService;
import app.service.RequestService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Comparator;
import java.util.List;

@AllArgsConstructor
@RestController
public class EmployeeController {

    private final ClientService clientService;
    private final RequestService requestService;

    /**
     * http://localhost:8080/clients?amount=6000&duration=10
     * http://localhost:8080/clients?amount=6000&duration=15
     * http://localhost:8080/clients?amount=6000&duration=9
     */

    private List<Client> getClients(@RequestParam int amount, @RequestParam int duration) {
        List<Client> clients = clientService.getAll(amount, duration);
        clients.sort(Comparator.comparingInt(o -> o.getFee().getDelayDaysCount()));
        return clients;
    }

    @GetMapping("clients")
    List<Client> handleUsers(@RequestParam int amount, @RequestParam int duration) {
        //duration is month
        return getClients(amount, duration);
    }

    @PostMapping("send-request")
    String handleRequest(@RequestParam int amount, @RequestParam int duration) {
        return requestService.checkRequest(getClients(amount, duration), amount, duration);
    }
}
