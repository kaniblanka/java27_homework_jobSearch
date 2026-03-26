package kg.attractor.jobsearch.exception;

public class ResumeNotFoundException extends NotFoundEntryException {
    public ResumeNotFoundException() {
        super("Resume not found");
    }
}