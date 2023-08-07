package com.gfa.foxbook.foxbook.loaders;

import com.gfa.foxbook.foxbook.repositories.DonutFillingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class CityDataLoader implements CommandLineRunner {

    private final DonutFillingRepository donutFillingRepository;

    @Autowired
    public CityDataLoader(DonutFillingRepository donutFillingRepository) {
        this.donutFillingRepository = donutFillingRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        if (donutFillingRepository.findAll().isEmpty()) {
            loadInitialCities();
        }
    }

    private void loadInitialCities() {
        List<String> citiesList = Arrays.asList(
                "Praha",
                "Brno",
                "Ostrava",
                "Plzen",
                "Liberec",
                "Olomouc",
                "Usti nad Labem",
                "Hradec Kralove",
                "Pardubice",
                "Zlin",
                "Ceske Budejovice",
                "Havirov",
                "Kladno",
                "Most",
                "Opava",
                "Frydek-Mistek",
                "Karvina",
                "Jihlava",
                "Teplice",
                "Decin",
                "Chomutov",
                "Jablonec nad Nisou",
                "Mlada Boleslav",
                "Prostejov",
                "Trebic",
                "Prerov",
                "Karlovy Vary",
                "Kolin",
                "Havirov",
                "Tabor"
        );

        for (String c : citiesList) {
            City city = new City();
            city.setName(c);
            donutFillingRepository.save(city);
        }
    }
}
