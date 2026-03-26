package kg.attractor.jobsearch.exception;

public class VacancyNotFoundException extends NotFoundEntryException {
    public VacancyNotFoundException() {
        super("Vacancy not found");
    }
}