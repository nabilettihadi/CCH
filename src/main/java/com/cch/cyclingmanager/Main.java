package com.cch.cyclingmanager;

import com.cch.cyclingmanager.config.JpaConfig;

import com.cch.cyclingmanager.dto.CompetitionDto;
import com.cch.cyclingmanager.service.CompetitionService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(JpaConfig.class);

        CompetitionService competitionService = context.getBean(CompetitionService.class);

        CompetitionDto competitionDto = new CompetitionDto();
        competitionDto.setName("Tour de France 2023");
        competitionDto.setStartDate(LocalDate.of(2023, 7, 1));
        competitionDto.setEndDate(LocalDate.of(2023, 7, 23));
        competitionDto.setLocation("France");

        CompetitionDto savedCompetition = competitionService.save(competitionDto);
        System.out.println("Compétition sauvegardée avec l'ID : " + savedCompetition.getId());

        CompetitionDto retrievedCompetition = competitionService.findById(savedCompetition.getId()).orElse(null);
        if (retrievedCompetition != null) {
            System.out.println("Compétition récupérée : " + retrievedCompetition.getName());
        }

        System.out.println("Liste des compétitions :");
        competitionService.findAll().forEach(c -> System.out.println(c.getName()));

        context.close();
    }
}