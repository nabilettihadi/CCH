package com.cch.cyclingmanager;

import com.cch.cyclingmanager.config.JpaConfig;
import com.cch.cyclingmanager.entity.Competition;
import com.cch.cyclingmanager.repository.CompetitionRepository;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {

        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(JpaConfig.class);

        CompetitionRepository competitionRepository = context.getBean(CompetitionRepository.class);


        Competition competition = new Competition();
        competition.setName("Tour de France 2023");
        competition.setStartDate(LocalDate.of(2023, 7, 1));
        competition.setEndDate(LocalDate.of(2023, 7, 23));
        competition.setLocation("France");


        competition = competitionRepository.save(competition);
        System.out.println("Compétition sauvegardée avec l'ID : " + competition.getId());


        Competition retrievedCompetition = competitionRepository.findById(competition.getId()).orElse(null);
        if (retrievedCompetition != null) {
            System.out.println("Compétition récupérée : " + retrievedCompetition.getName());
        }


        System.out.println("Liste des compétitions :");
        competitionRepository.findAll().forEach(c -> System.out.println(c.getName()));


        context.close();
    }
}