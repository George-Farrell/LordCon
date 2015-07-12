package strategies;

import data.Area;
import data.Constants;
import org.parabot.core.ui.Logger;
import org.parabot.environment.api.utils.Time;
import org.parabot.environment.scripts.framework.SleepCondition;
import org.parabot.environment.scripts.framework.Strategy;
import org.rev317.min.api.methods.*;
import org.rev317.min.api.wrappers.SceneObject;
import org.rev317.min.api.wrappers.Tile;

public class Remove implements Strategy{
    private final Area house = new Area(new Tile(1901, 5146, 0), new Tile(1901, 5104, 0), new Tile(1945, 5104, 0), new Tile(1945, 5146, 0));
    private SceneObject[] rack;

    @Override
    public boolean activate() {

        if ((rack = SceneObjects.getNearest(Constants.MADE_RACK)) != null && rack.length > 0) {
            if (Inventory.getCount(Constants.OAK_PLANKS) > 4
                    && rack[0].distanceTo() < 25
                    && Players.getMyPlayer().getAnimation() == -1
                    && house.contains(Players.getMyPlayer().getLocation())) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void execute() {
        if (Game.getOpenInterfaceId() != 38272) {
            Logger.addMessage("Removing Oak Rack",true);
            SceneObjects.getNearest(18766)[0].interact(SceneObjects.Option.FIFTH);
            Time.sleep(new SleepCondition() {
                @Override
                public boolean isValid() {
                    return Players.getMyPlayer().getAnimation() == -1;
                }
            }, 2500);
            Time.sleep(2000);
        }
    }
    private void clickOption(int option) {

        int optionAction = 2494;
        switch (option) {
            case 0:
                optionAction = 2494;
                break;
            case 1:
                optionAction = 2495;
                break;
            case 2:
                optionAction = 2496;
                break;
            case 3:
                optionAction = 2497;
                break;
            case 4:
                optionAction = 2498;
                break;
            case 5:
                optionAction = 2472;
        }
        Menu.sendAction(315, 0, 0, optionAction, 0);
        Time.sleep(1500);
    }

}

