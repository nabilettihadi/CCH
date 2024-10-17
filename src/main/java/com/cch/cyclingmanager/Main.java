package com.cch.cyclingmanager;

import com.cch.cyclingmanager.config.HibernateConfig;
import com.cch.cyclingmanager.entity.Competition;
import com.cch.cyclingmanager.repository.CompetitionRepository;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        // Initialiser le contexte Spring
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(HibernateConfig.class);

        // Récupérer le repository
        CompetitionRepository competitionRepository = context.getBean(CompetitionRepository.class);

        // Créer une nouvelle compétition
        Competition competition = new Competition();
        competition.setName("Tour de France 2023");
        competition.setStartDate(LocalDate.of(2023, 7, 1));
        competition.setEndDate(LocalDate.of(2023, 7, 23));
        competition.setLocation("France");

        // Sauvegarder la compétition
        competition = competitionRepository.save(competition);
        System.out.println("Compétition sauvegardée avec l'ID : " + competition.getId());

        // Récupérer la compétition
        Competition retrievedCompetition = competitionRepository.findById(competition.getId()).orElse(null);
        if (retrievedCompetition != null) {
            System.out.println("Compétition récupérée : " + retrievedCompetition.getName());
        }

        // Lister toutes les compétitions
        System.out.println("Liste des compétitions :");
        competitionRepository.findAll().forEach(c -> System.out.println(c.getName()));

        // Fermer le contexte Spring
        context.close();
    }
}