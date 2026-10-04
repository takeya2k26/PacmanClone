import javax.sound.sampled.*;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class SoundManager {
    private Map<String, Clip> clips = new HashMap<>();
    private Clip sirenClip;

    public SoundManager() {
        load("chomp", "assets/sounds/chomp.wav");
        load("death", "assets/sounds/death.wav");
        load("eatGhost", "assets/sounds/eatghost.wav");
        loadSiren("assets/sounds/siren.wav");
    }

    private void load(String name, String path) {
        try {
            AudioInputStream stream = AudioSystem.getAudioInputStream(new File(path));
            Clip clip = AudioSystem.getClip();
            clip.open(stream);
            clips.put(name, clip);
        } catch (Exception e) {
            System.out.println("Could not load sound: " + path);
        }
    }

    private void loadSiren(String path) {
        try {
            AudioInputStream stream = AudioSystem.getAudioInputStream(new File(path));
            sirenClip = AudioSystem.getClip();
            sirenClip.open(stream);
        } catch (Exception e) {
            System.out.println("Could not load sound: " + path);
        }
    }

    public void play(String name) {
        Clip clip = clips.get(name);
        if (clip == null) return;
        clip.stop();
        clip.setFramePosition(0);
        clip.start();
    }

    public void startSiren() {
        if (sirenClip == null) return;
        if (!sirenClip.isRunning()) {
            sirenClip.setFramePosition(0);
            sirenClip.loop(Clip.LOOP_CONTINUOUSLY);
        }
    }

    public void stopSiren() {
        if (sirenClip != null) sirenClip.stop();
    }
}