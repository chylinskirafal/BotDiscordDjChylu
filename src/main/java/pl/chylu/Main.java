package pl.chylu;

import io.github.cdimascio.dotenv.Dotenv;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.api.utils.MemberCachePolicy;
import pl.chylu.music.*;

public class Main {
    private static final Dotenv config = Dotenv.configure().ignoreIfMissing().load();
    public static JDA jda;
    private static User chylu;
    private static User bios;
    public static User getBios() {
        return bios;
    }

    public static User getChylu() {
        return chylu;
    }

    public static Dotenv getConfig() {
        return config;
    }

    public static void main(String[] args) throws Exception {
        String token = getConfig().get("TOKEN");
        jda = JDABuilder.createDefault(token)
                .enableIntents(
                        GatewayIntent.GUILD_MESSAGES,
                        GatewayIntent.DIRECT_MESSAGES,
                        GatewayIntent.GUILD_EMOJIS_AND_STICKERS,
                        GatewayIntent.MESSAGE_CONTENT,
                        GatewayIntent.DIRECT_MESSAGE_TYPING,
                        GatewayIntent.GUILD_MESSAGE_REACTIONS,
                        GatewayIntent.GUILD_MODERATION,
                        GatewayIntent.AUTO_MODERATION_CONFIGURATION,
                        GatewayIntent.DIRECT_MESSAGE_REACTIONS,
                        GatewayIntent.GUILD_MEMBERS,
                        GatewayIntent.GUILD_VOICE_STATES)
                .setMemberCachePolicy(MemberCachePolicy.ALL)
                //.addEventListeners(new EventListener())
                .setActivity(Activity.playing("przeCHYLenia"))
                .setStatus(OnlineStatus.ONLINE)
                .build().awaitReady();

        Guild guild = jda.getGuildById("1036330977141735534");

        String useChyluId = "224489297124786176";
        jda.retrieveUserById(useChyluId).queue(
                user -> chylu = user,
                failure -> System.out.println("Nie znaleziono użytkownika o ID: " + 224489297124786176L)
        );
        String useBiosId = "555819981212876820";
        jda.retrieveUserById(useBiosId).queue(
                user -> bios = user,
                failure -> System.out.println("Nie znaleziono użytkownika o ID: " + 555819981212876820L)
        );


        CommandManager manager = new CommandManager();
        manager.add(new Play());
        manager.add(new Skip());
        manager.add(new Stop());
        manager.add(new NowPlaying());
        manager.add(new Queue());
        manager.add(new Repeat());
        jda.addEventListener(manager);
    }


}