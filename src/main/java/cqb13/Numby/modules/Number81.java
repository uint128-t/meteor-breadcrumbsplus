package cqb13.Numby.modules;

import cqb13.Numby.Numby;
import meteordevelopment.meteorclient.events.world.TickEvent;
import meteordevelopment.meteorclient.systems.modules.Module;
import meteordevelopment.orbit.EventHandler;


public class Number81 extends Module {

    public Number81() {
        super(Numby.CATEGORY, "81", "Counts to 81 in chat.");
    }

    private int timer;
    private int count;
    private boolean setTimer;

    @Override
    public void onActivate() {
        count = 1;
        mc.player.sendChatMessage(String.valueOf(count));
    }

    @Override
    public void onDeactivate() {
        if (count != 81) {
            mc.player.sendChatMessage("I am a lazy bitch and did not count to 81!");
            mc.player.sendChatMessage("I am a disgrace and should be punished!");
            mc.player.sendChatMessage("I am a very bad person!");
        }
    }


    @EventHandler
    private void onTick(TickEvent.Pre event) {
        if (setTimer){
            timer = (int)(Math.random() * 70 + 40);
            setTimer = false;
        }
        timer--;
        if(timer < 0){
            count++;
            mc.player.sendChatMessage(String.valueOf(count));
            setTimer = true;
        }
        if(count == 81){
            mc.player.sendChatMessage("Number81 on top");
            toggle();
        }
    }
}


