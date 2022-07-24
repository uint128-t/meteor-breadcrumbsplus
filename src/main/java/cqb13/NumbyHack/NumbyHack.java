package cqb13.NumbyHack;

import com.mojang.logging.LogUtils;
import cqb13.NumbyHack.modules.commands.*;
import cqb13.NumbyHack.modules.general.*;
import cqb13.NumbyHack.modules.hud.*;
import cqb13.NumbyHack.utils.*;
import meteordevelopment.meteorclient.MeteorClient;
import meteordevelopment.meteorclient.addons.MeteorAddon;
import meteordevelopment.meteorclient.systems.commands.Commands;
import meteordevelopment.meteorclient.systems.hud.Hud;
import meteordevelopment.meteorclient.systems.hud.HudGroup;
import meteordevelopment.meteorclient.systems.modules.Category;
import meteordevelopment.meteorclient.systems.modules.Modules;
import meteordevelopment.meteorclient.utils.misc.MeteorStarscript;
import meteordevelopment.starscript.value.Value;
import meteordevelopment.starscript.value.ValueMap;
import net.minecraft.item.Items;
import org.slf4j.Logger;

import java.lang.invoke.MethodHandles;

public class NumbyHack extends MeteorAddon {
	public static final Category CATEGORY = new Category("Numby Hack", Items.TURTLE_HELMET.getDefaultStack());
	public static final HudGroup HUD_GROUP = new HudGroup("Numby Hack");
	public static final Logger LOGGER = LogUtils.getLogger();

	@Override
	public void onInitialize() {
	    Log("Beginning initialization.");

		// Required when using @EventHandler
		MeteorClient.EVENT_BUS.registerLambdaFactory("cqb13.NumbyHack", (lookupInMethod, klass) -> (MethodHandles.Lookup) lookupInMethod.invoke(null, klass, MethodHandles.lookup()));

		Log("Adding Player Particles...");
		PlayerParticle.init();

		StatsUtils.init();
		MeteorStarscript.ss.set("numbyhack", Value.map(new ValueMap()
				.set("kills", StatsUtils::getKills)
				.set("deaths", StatsUtils::getDeaths)
				.set("kdr", StatsUtils::getKDR)
				.set("killstreak", StatsUtils::getKillstreak)
				.set("highscore", StatsUtils::getHighscore)
				.set("crystalsps", StatsUtils::getCrystalsPs))
		);

		Log("Adding modules...");
		Modules.get().add(new AutoLogPlus());
		Modules.get().add(new ChatEncryption());
		Modules.get().add(new ChorusExploit());
		Modules.get().add(new Confetti());
		Modules.get().add(new NewChunks());
		Modules.get().add(new Number81());
		Modules.get().add(new SafeFire());
		Modules.get().add(new SafetyNet());
		Modules.get().add(new TunnelESP());

		Log("Adding HUD modules...");
		Hud.get().register(CombatHUD.INFO);
		Hud.get().register(ItemCounter.INFO);
		Hud.get().register(Logo.INFO);
		Hud.get().register(TextPresets.INFO);

		Log("Adding Commands...");
		Commands.get().add(new Trash());

		Log("Initialized successfully!");
	}

	@Override
	public void onRegisterCategories() {
		Modules.registerCategory(CATEGORY);
	}

	public static void Log(String text) {
		LOGGER.info(text);
	}
}