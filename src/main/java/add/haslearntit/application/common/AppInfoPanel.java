package add.haslearntit.application.common;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.spring.injection.annot.SpringBean;

/**
 * Simpel panel displaying current application version.
 */
public class AppInfoPanel extends Panel {

    @SpringBean
    private String appInfoText;

    public AppInfoPanel(String id) {
        super(id);
    }

    @Override
    protected void onInitialize() {
        super.onInitialize();
        add(new Label("appInfoText", appInfoText));
    }
}
