import java.util.Timer;

public class FightMap {

    private int playerHP;
    private int bossHP;
    private Object boss; // Replace Object with a specific class representing the boss
    private boolean completed;
    private Timer timer;

    public FightMap(int playerHP, int bossHP, Object boss) {
        this.playerHP = playerHP;
        this.bossHP = bossHP;
        this.boss = boss;
        this.completed = false;
        this.timer = new Timer(); 
    }

    public void initiateBossCombo() {
        System.out.println("Boss is performing a combo attack!");
        // Implement the combo attack logic (reduce playerHP, animations, etc.)
        playerHP -= 20; // Example logic
        if (playerHP <= 0) {
            playerHP = 0;
            System.out.println("Player is defeated!");
            completed = true;
        }
    }

    public void playerAttack() {
        System.out.println("Player attacks the boss!");
        // Implement the attack logic (reduce bossHP, animations, etc.)
        bossHP -= 15; // Example logic
        if (bossHP <= 0) {
            bossHP = 0;
            System.out.println("Boss is defeated!");
            completed = true;
        }
    }

    // Check if the fight is completed
    public boolean getCompleted() {
        return completed;
    }

    // Play a cutscene (optional)
    public void cutscene() {
        System.out.println("Cutscene is playing...");
        // Implement cutscene logic here
    }

    public int getPlayerHP() {
        return playerHP;
    }

    public void setPlayerHP(int playerHP) {
        this.playerHP = playerHP;
    }

    public int getBossHP() {
        return bossHP;
    }

    public void setBossHP(int bossHP) {
        this.bossHP = bossHP;
    }

    public Object getBoss() {
        return boss;
    }

    public void setBoss(Object boss) {
        this.boss = boss;
    }
}
