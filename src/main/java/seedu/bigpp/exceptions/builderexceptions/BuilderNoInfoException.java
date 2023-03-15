package seedu.bigpp.exceptions.builderexceptions;

import seedu.bigpp.exceptions.PPException;

public class BuilderNoInfoException extends PPException {
    @Override
    public String getMessage() {
        return "You have not have selected anything for this component";
    }
}
