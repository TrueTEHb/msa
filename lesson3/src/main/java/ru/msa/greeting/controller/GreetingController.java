package ru.msa.greeting.controller;

import org.springframework.web.bind.annotation.*;
import ru.msa.greeting.exceptions.NotFoundException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("greeting")
public class GreetingController {

    private final List<Map<String, String>> greetings = new ArrayList<>() {{
        add(new HashMap<>(){{put("id", "1"); put("content","First message");}});
        add(new HashMap<>(){{put("id", "2"); put("content","Second message");}});
        add(new HashMap<>(){{put("id", "3"); put("content","Third message");}});
    }};
    private long id = 4;

    @GetMapping
    public List<Map<String, String>> getAll() {
        if (!greetings.isEmpty()) {
            return greetings;
        }
        throw new NotFoundException();
    }

    @GetMapping("{id}")
    public Map<String, String> get(@PathVariable String id) {
        return greetings.stream()
                .filter(greeting -> greeting.get(id).equals(id))
                .findFirst()
                .orElseThrow(NotFoundException::new);
    }

    @PostMapping
    public Map<String, String> create(@RequestBody Map<String, String> params) {
        params.put("id", String.valueOf(this.id++));
        greetings.add(params);
        return params;
    }

    @PutMapping("{id}")
    public Map<String, String> update(@PathVariable String id, @RequestBody Map<String, String> params) {
        Map<String, String> greeting = greetings.stream()
                .filter(gr -> gr.get("id").equals(params.get("id")))
                .findFirst()
                .orElseThrow(NotFoundException::new);
        greeting.putAll(params);
        params.put("id", id);
        return params;
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable String id) {
        Map<String, String> greeting = greetings.stream()
                .filter(gr -> gr.get("id").equals(id))
                .findFirst()
                .orElseThrow(NotFoundException::new);
        greetings.remove(greeting);
    }
}