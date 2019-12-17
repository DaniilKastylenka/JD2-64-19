package by.it.academy.service;

import by.it.academy.model.Section;

import java.util.HashSet;
import java.util.Set;

public class SectionServiceImpl implements SectionService {

    private static final SectionService INSTANCE = new SectionServiceImpl();

    private Set<Section> sections;

    private SectionServiceImpl(){
        sections = new HashSet<>();
        sections.add(new Section(1L, "People"));
        sections.add(new Section(2L, "Technology"));
        sections.add(new Section(3L, "Politics"));
        sections.add(new Section(4L, "Entertainment"));
        sections.add(new Section(5L, "Game"));
        sections.add(new Section(6L, "World"));
        sections.add(new Section(7L, "Education"));
    }

    public static SectionService getINSTANCE() {
        return INSTANCE;
    }

    @Override
    public Set<Section> getSections() {
        return sections;
    }




}
