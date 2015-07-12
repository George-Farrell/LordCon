package strategies;

import data.Constants;
import main.LordCon;
import org.parabot.core.ui.Logger;
import org.parabot.environment.api.utils.Time;
import org.parabot.environment.scripts.framework.SleepCondition;
import org.parabot.environment.scripts.framework.Strategy;
import org.rev317.min.api.methods.*;
import org.rev317.min.api.wrappers.SceneObject;

public class MakeRack implements Strategy{
    private SceneObject[] oakrack;

    public boolean activate() {
        return (oakrack = SceneObjects.getNearest(Constants.OAK_RACK)) != null && oakrack.length > 0 && Inventory.getCount(Constants.OAK_PLANKS) > 4 && Players.getMyPlayer().getAnimation() == -1;
    }

    public void execute() {
        Logger.addMessage("Making Oak Rack",true);
        oakrack[0].interact(SceneObjects.Option.FIFTH);
        Time.sleep(new SleepCondition() {
            @Override
            public boolean isValid() {
                return Game.getOpenInterfaceId() == 37272;
            }
        }, 2000);
        Menu.sendAction(1125, 9817, 0, 38274);
        Time.sleep(new SleepCondition() {
            @Override
            public boolean isValid() {
                return Players.getMyPlayer().getAnimation() == -1;
            }
        }, 5000);
        LordCon.racksMade += 1;
        Time.sleep(500);
    }
}
