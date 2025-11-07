package x.withlithum.openew.mixin;

import net.minecraft.commands.CommandSourceStack;
import net.minecraft.server.commands.ScoreboardCommand;
import net.minecraft.world.scores.Objective;
import net.minecraft.world.scores.ScoreHolder;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import x.withlithum.openew.network.ClientStateManager;
import x.withlithum.openew.util.properties.ClientFlags;

import java.util.Collection;

@Mixin(ScoreboardCommand.class)
abstract class ScoreboardCommandHudFlagMixin {

    @Inject(method = "setScore", at = @At("TAIL"))
    private static void setScore(CommandSourceStack source,
                                 Collection<ScoreHolder> targets,
                                 Objective objective,
                                 int newValue,
                                 CallbackInfoReturnable<Integer> cir) {
        final var server = source.getServer();

        if (!objective.getName().equals("constant")) {
            return;
        }

        for (var target : targets) {
            if (target.getScoreboardName().equals("no_hud")) {
                ClientStateManager.broadcastClientFlag(server,
                        ClientFlags.NO_HEALTH_DISPLAY,
                        newValue > 0);
                break;
            }
        }
    }

    @Inject(method = "addScore", at = @At("HEAD"))
    private static void addScore(CommandSourceStack source,
                                 Collection<ScoreHolder> targets,
                                 Objective objective,
                                 int amount,
                                 CallbackInfoReturnable<Integer> cir) {
        final var scoreboard = source.getServer().getScoreboard();

        if (!objective.getName().equals("constant")) {
            return;
        }

        for (var target : targets) {
            if (target.getScoreboardName().equals("no_hud")) {
                final var scoreAccess = scoreboard.getOrCreatePlayerScore(target, objective);
                final var value = scoreAccess.get() + amount;

                ClientStateManager.broadcastClientFlag(
                        source.getServer(),
                        ClientFlags.NO_HEALTH_DISPLAY,
                        value > 0);
                break;
            }
        }
    }

    @Inject(method = "removeScore", at = @At("HEAD"))
    private static void removeScore(CommandSourceStack source,
                                 Collection<ScoreHolder> targets,
                                 Objective objective,
                                 int amount,
                                 CallbackInfoReturnable<Integer> cir) {
        final var scoreboard = source.getServer().getScoreboard();

        if (!objective.getName().equals("constant")) {
            return;
        }

        for (var target : targets) {
            if (target.getScoreboardName().equals("no_hud")) {
                final var scoreAccess = scoreboard.getOrCreatePlayerScore(target, objective);
                final var value = scoreAccess.get() - amount;

                ClientStateManager.broadcastClientFlag(
                        source.getServer(),
                        ClientFlags.NO_HEALTH_DISPLAY,
                        value > 0);
                break;
            }
        }
    }

    @Inject(method = "resetScore", at = @At("TAIL"))
    private static void resetScore(CommandSourceStack source,
                                   Collection<ScoreHolder> targets,
                                   Objective objective,
                                   CallbackInfoReturnable<Integer> cir) {
        if (!objective.getName().equals("constant")) {
            return;
        }

        for (var target : targets) {
            if (target.getScoreboardName().equals("no_hud")) {
                ClientStateManager.broadcastClientFlag(
                        source.getServer(),
                        ClientFlags.NO_HEALTH_DISPLAY,
                        false);

                break;
            }
        }
    }
}
