package com.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.lang.String.format;

@SpringBootApplication
public class DemoIssueSpringDataCouchbaseApplication implements CommandLineRunner {

    @Autowired
    private SettingsRepository settingsRepository;

    public static void main(String[] args) {
        SpringApplication.run(DemoIssueSpringDataCouchbaseApplication.class, args);
    }


    @Override
    public void run(String... args) throws Exception {
        final Value property1 = Value.VALUE1;
        final Value property2 = Value.VALUE2;
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        try {
            Future<List<Settings>> allShouldContainValue1 =
                    executorService.submit(() -> Stream.generate(new Random()::nextInt).limit(100).map(x -> settingsRepository.findByProperty(property1)).collect(Collectors.toList()));
            Future<List<Settings>> allShouldContainValue2 =
                    executorService.submit(() -> Stream.generate(new Random()::nextInt).limit(100).map(x -> settingsRepository.findByProperty(property2)).collect(Collectors.toList()));
            List<Settings> theseDoesNotContainValue1 = allShouldContainValue1.get().stream().filter(doc -> doc.getProperty() != property1).collect(Collectors.toList());
            List<Settings> theseDoesNotContainValue2 = allShouldContainValue2.get().stream().filter(doc -> doc.getProperty() != property2).collect(Collectors.toList());
            if (theseDoesNotContainValue1.size() > 0) {
                throw new RuntimeException(format("%d=%s", theseDoesNotContainValue1.size(), theseDoesNotContainValue1.toString()));
            }
            if (theseDoesNotContainValue2.size() > 0) {
                throw new RuntimeException(format("%d=%s", theseDoesNotContainValue2.size(), theseDoesNotContainValue2.toString()));
            }
        } finally {
            executorService.shutdown();
        }
    }
}
