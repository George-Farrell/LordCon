package strategies;

import data.Constants;
import org.parabot.core.ui.Logger;
import org.parabot.environment.api.utils.Time;
import org.parabot.environment.scripts.framework.SleepCondition;
import org.parabot.environment.scripts.framework.Strategy;
import org.rev317.min.api.methods.*;
import org.rev317.min.api.wrappers.SceneObject;

public class Restock implements Strategy {
    private SceneObject[] hportal;

    public boolean activate() {
        return Inventory.getCount(Constants.OAK_PLANKS) <= 3 && Players.getMyPlayer().getAnimation() == -1 && (Bank.getNearestBanks() ==null || Bank.getNearestBanks().length < 1);
    }



    public void execute() {
        if ((hportal = SceneObjects.getNearest(Constants.HOUSE_PORTAL)) != null && hportal.length > 0
                && hportal[0].distanceTo() > 0
                && Players.getMyPlayer().getAnimation() == -1) {
            hportal[0].interact(SceneObjects.Option.FIRST);
            Time.sleep(new SleepCondition() {
                @Override
                public boolean isValid() {
                    return Game.getOpenBackDialogId() > 0;
                }
            }, 1000);
            if (Game.getOpenBackDialogId() == 2469) {
            clickOption(5);
            final int x = Players.getMyPlayer().getLocation().getX();
            final int y = Players.getMyPlayer().getLocation().getY();
            Time.sleep(new SleepCondition() {
                @Override
                public boolean isValid() {
                    return Players.getMyPlayer().getLocation().getX() != x && Players.getMyPlayer().getLocation().getY() != y;
                }
            }, 10000);
        }
            Time.sleep(2500);
            Logger.addMessage("Restocking",true);
            Time.sleep(new SleepCondition() {
                @Override
                public boolean isValid() {
                    return Bank.getBank().distanceTo() < 25;
                }
            }, 2500);
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
