package pl.chylu;

import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.events.message.react.MessageReactionAddEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;


public class EventListener extends ListenerAdapter {
    @Override
    public void onMessageReactionAdd(@NotNull MessageReactionAddEvent event) {
        User user = event.getUser();
        String emoji = event.getReaction().getEmoji().getAsReactionCode();
        String channelMention = event.getChannel().getAsMention();
        String jumpLink = event.getJumpUrl();

        String message = user.getAsMention() + " zareagował na wiadomość " + jumpLink + " emotką " + emoji + " na kanale " + channelMention;
        event.getGuild().getDefaultChannel().asStandardGuildMessageChannel().sendMessage(message).queue();
    }

    @Override
    public void onMessageReceived(@NotNull MessageReceivedEvent event) {
        /*String message = event.getMessage().getContentRaw();
        if (message.contains("help")) {
            User user = Main.getChylu();
            event.getChannel().sendMessage(
                    "Napisz /play a następnie link do YT lub hasła po których mam wyszukać. Jakbyś miał większe problemy to napisz do "
                            + user.getAsMention()).queue();
        } else if (message.contains("admin")) {
            User user = Main.getBios();
            event.getChannel().sendMessage(
                    "Tu masz admina: "
                            + user.getAsMention()).queue();
        } else {
            event.getChannel().sendMessage("Elo mordo");
        }*/
    }
}
