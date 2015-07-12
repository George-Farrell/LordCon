package strategies;


import org.parabot.core.ui.Logger;
import org.parabot.environment.api.utils.Time;
import org.parabot.environment.input.Mouse;
import org.parabot.environment.scripts.framework.SleepCondition;
import org.parabot.environment.scripts.framework.Strategy;
import org.rev317.min.api.methods.Game;

import java.awt.*;

public class Login implements Strategy {

    @Override
    public boolean activate() {
        return !Game.isLoggedIn();
    }

    @Override
    public void execute() {
        Logger.addMessage("Logging in", true);
        Mouse.getInstance().click(new Point(380,315));
        Time.sleep(new SleepCondition() {
            @Override
            public boolean isValid() {
                return Game.isLoggedIn();
            }
        }, 5000);
    }
}