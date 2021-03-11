package innopolis.ui;

import org.aeonbits.owner.Config;

@Config.LoadPolicy(Config.LoadType.MERGE)
@Config.Sources({
        "system:properties",
        "system:env",
        "file:src/main/resources/config.properties"
})
public interface Props extends Config {
    @Key("google.url")
    String googleUrl();

    @Key("yandex.url")
    String yandexUrl();

    @Key("swagLabs.url")
    String swagLabsUrl();

    @Key("selenium.prac.url")
    String seleniumPracUrl();
}
