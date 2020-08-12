package app.service;

import app.entity.Client;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class RequestService {

    private final String OKAY = "Accepted";
    private final String NOTOKAY = "Not accepted";

    public String checkRequest(List<Client> clients, int amount, int duration){
        if (clients.isEmpty()) return NOTOKAY;
        return clients.stream()
                .allMatch(client -> client.getFee().getMaxMonthlyFeeAbility() >= (float) amount / duration) ?
                OKAY : NOTOKAY;
    }
}
