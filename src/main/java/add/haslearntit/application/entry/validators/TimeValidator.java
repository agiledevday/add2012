package add.haslearntit.application.entry.validators;

import org.apache.wicket.validation.IValidatable;
import org.apache.wicket.validation.IValidator;
import org.apache.wicket.validation.ValidationError;

public class TimeValidator implements IValidator<String> {

    @Override
    public void validate(IValidatable<String> field) {
        ValidationError error;
        try {
            float parseFloat = Float.parseFloat(field.getValue());
        } catch (NumberFormatException ex) {
            error = new ValidationError("Please enter correct time in hours!");
            field.error(error);
        }
    }

}
