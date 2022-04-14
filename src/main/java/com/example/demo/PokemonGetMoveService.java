package com.example.demo;

import com.example.demo.Pokemon;
import com.example.demo.PokemonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static java.lang.Integer.parseInt;
import static javax.xml.bind.DatatypeConverter.parseString;
@Service
public class PokemonGetMoveService {


    public List<String> getMoveService(List<Pokemon> Pokemon) {
        return Pokemon.stream().map(p -> p.getMove()).collect(Collectors.toList());
    }
}
