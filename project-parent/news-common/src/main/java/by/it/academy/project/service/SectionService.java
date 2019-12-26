package by.it.academy.project.service;

import by.it.academy.project.model.Section;

import java.util.Set;

public interface SectionService {

    Set<Section> getSections();

    Section findSectionByID (Long id);

}
