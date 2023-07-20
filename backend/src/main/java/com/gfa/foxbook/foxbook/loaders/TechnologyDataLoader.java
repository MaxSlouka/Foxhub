package com.gfa.foxbook.foxbook.loaders;


import com.gfa.foxbook.foxbook.models.nonusermodels.Technology;
import com.gfa.foxbook.foxbook.repositories.TechnologyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class TechnologyDataLoader implements CommandLineRunner {

    private final TechnologyRepository technologyRepository;

    @Autowired
    public TechnologyDataLoader(TechnologyRepository technologyRepository) {
        this.technologyRepository = technologyRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        if (technologyRepository.count() == 0) {
            // Load initial technologies if the database is empty
            loadInitialTechnologies();
        }
    }

    private void loadInitialTechnologies() {
        // Add the initial technologies here

        // HTML
        Technology html = new Technology();
        html.setName("HTML");
        technologyRepository.save(html);

        // CSS
        Technology css = new Technology();
        css.setName("CSS");
        technologyRepository.save(css);

        // JavaScript
        Technology js = new Technology();
        js.setName("JavaScript");
        technologyRepository.save(js);

        // Python
        Technology python = new Technology();
        python.setName("Python");
        technologyRepository.save(python);

        // Java
        Technology java = new Technology();
        java.setName("Java");
        technologyRepository.save(java);

        // PHP
        Technology php = new Technology();
        php.setName("PHP");
        technologyRepository.save(php);

        // Ruby
        Technology ruby = new Technology();
        ruby.setName("Ruby");
        technologyRepository.save(ruby);

        // TypeScript
        Technology ts = new Technology();
        ts.setName("TypeScript");
        technologyRepository.save(ts);

        // C#
        Technology cSharp = new Technology();
        cSharp.setName("C#");
        technologyRepository.save(cSharp);

        // Swift
        Technology swift = new Technology();
        swift.setName("Swift");
        technologyRepository.save(swift);

        // Go
        Technology go = new Technology();
        go.setName("Go");
        technologyRepository.save(go);

        // Kotlin
        Technology kotlin = new Technology();
        kotlin.setName("Kotlin");
        technologyRepository.save(kotlin);

        // Rust
        Technology rust = new Technology();
        rust.setName("Rust");
        technologyRepository.save(rust);

        // SQL
        Technology sql = new Technology();
        sql.setName("SQL");
        technologyRepository.save(sql);

        // React
        Technology react = new Technology();
        react.setName("React");
        technologyRepository.save(react);

        // Angular
        Technology angular = new Technology();
        angular.setName("Angular");
        technologyRepository.save(angular);

        // Vue.js
        Technology vuejs = new Technology();
        vuejs.setName("Vue.js");
        technologyRepository.save(vuejs);

        // Django
        Technology django = new Technology();
        django.setName("Django");
        technologyRepository.save(django);

        // Spring Boot
        Technology springBoot = new Technology();
        springBoot.setName("Spring Boot");
        technologyRepository.save(springBoot);

        // Ruby on Rails
        Technology rails = new Technology();
        rails.setName("Ruby on Rails");
        technologyRepository.save(rails);

        // Express.js
        Technology express = new Technology();
        express.setName("Express.js");
        technologyRepository.save(express);

        // Flask
        Technology flask = new Technology();
        flask.setName("Flask");
        technologyRepository.save(flask);

        // Laravel
        Technology laravel = new Technology();
        laravel.setName("Laravel");
        technologyRepository.save(laravel);

        // ASP.NET
        Technology aspNet = new Technology();
        aspNet.setName("ASP.NET");
        technologyRepository.save(aspNet);

        // Node.js
        Technology nodejs = new Technology();
        nodejs.setName("Node.js");
        technologyRepository.save(nodejs);

        // React Native
        Technology reactNative = new Technology();
        reactNative.setName("React Native");
        technologyRepository.save(reactNative);

        // Flutter
        Technology flutter = new Technology();
        flutter.setName("Flutter");
        technologyRepository.save(flutter);

        // Xamarin
        Technology xamarin = new Technology();
        xamarin.setName("Xamarin");
        technologyRepository.save(xamarin);

        // Unity
        Technology unity = new Technology();
        unity.setName("Unity");
        technologyRepository.save(unity);

        // Unreal Engine
        Technology unrealEngine = new Technology();
        unrealEngine.setName("Unreal Engine");
        technologyRepository.save(unrealEngine);
    }
}
