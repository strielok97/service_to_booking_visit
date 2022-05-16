package web;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import persistance.Client;
import service.ClientService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/clients")
public class ClientController {


    private final ClientService clientService;

    @GetMapping
    ResponseEntity<List<Client>> getAll() {
        return ResponseEntity.ok(clientService.findAll());
    }

    @GetMapping("/{id}")
    ResponseEntity<Client> getId(@PathVariable Long id){
        return ResponseEntity.ok(clientService.findById(id));
    }

    @PostMapping
    public ResponseEntity<Client> save(@RequestBody Client client) {
        return ResponseEntity.ok(clientService.save(client));
    }

    @DeleteMapping
    public void delete(@PathVariable Long id) {
        clientService.deleteById(id);
    }

}
