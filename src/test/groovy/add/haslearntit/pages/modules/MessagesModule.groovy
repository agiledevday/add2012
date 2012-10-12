package add.haslearntit.pages.modules

import geb.Module

class MessagesModule extends Module{

    static base = { $(".messages") }

    static content = {
        entries { $("li")*.text() }
    }
}
