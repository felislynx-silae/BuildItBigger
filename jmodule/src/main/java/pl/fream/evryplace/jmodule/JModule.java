package pl.fream.evryplace.jmodule;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Jokes pasted from https://onelinefun.com/it/
 */
public class JModule {
    private List<String> jokes = new ArrayList<>();
    private Random random = new Random();

    public JModule() {
        jokes.add("Moses had the first tablet that could connect to the cloud.");
        jokes.add("The first computer dates back to Adam and Eve. It was an Apple with limited memory, just one byte. And then everything crashed.");
        jokes.add("Maybe if we start telling people the brain is an app they will start using it.");
        jokes.add("We just got a fax. At work. We didn't know we had a fax machine. The entire department just stared at it. I poked it with a stick.");
        jokes.add("My email password has been hacked. That's the third time I've had to rename the cat.");
    }

    public String provideJoke() {
        return jokes.get(random.nextInt(jokes.size()));
    }
}
