package by.it.academy.project.service;

import by.it.academy.project.model.Section;

import java.util.Optional;
import java.util.Set;

public interface SectionService {

    Set<Section> getSections();

    Optional<Section> findSectionByID (Long id);

}
