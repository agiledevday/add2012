package add.haslearntit.application.entry.validators;

import org.apache.wicket.validation.IValidatable;
import org.apache.wicket.validation.IValidator;
import org.apache.wicket.validation.ValidationError;

import add.haslearntit.domain.entry.Entry;

public class TimeValidator implements IValidator<String> {

    @Override
    public void validate(IValidatable<String> field) {
        try {
            int value = Integer.parseInt(field.getValue());
            if (value < Entry.MIN_TIME_SPENT || value > Entry.MAX_TIME_SPENT) {
                String message = String.format("Provided time is out of range %d-%d!",
                        Entry.MIN_TIME_SPENT, Entry.MAX_TIME_SPENT);
                addErrorToField(field, message);
            }
        } catch (NumberFormatException ex) {
            addErrorToField(field, "Please enter correct time in hours!");
        }
    }

    private void addErrorToField(IValidatable<String> field, String message) {
        ValidationError error;
        error = new ValidationError(message);
        field.error(error);
    }

}
