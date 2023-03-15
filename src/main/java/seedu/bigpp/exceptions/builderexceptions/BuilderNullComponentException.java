package seedu.bigpp.exceptions.builderexceptions;

import seedu.bigpp.exceptions.PPException;

public class BuilderNullComponentException extends PPException {
    @Override
    public String getMessage() {
        return "Component is already unselected!";
    }
}
