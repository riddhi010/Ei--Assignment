package exercise1.creational.singleton;

import java.util.logging.Logger;

/**
 * Thread-safe Singleton to manage global configuration settings.
 */
public class ConfigurationManager {
    private static volatile ConfigurationManager instance;
    private static final Logger logger = Logger.getLogger(ConfigurationManager.class.getName());

    private String defaultDeadline;
    private int maxLoginAttempts;
    private boolean notificationsEnabled;

    // Private constructor prevents instantiation
    private ConfigurationManager() {
        // Default values
        this.defaultDeadline = "23:59";
        this.maxLoginAttempts = 5;
        this.notificationsEnabled = true;
        logger.info("Default configuration loaded.");
    }

    /**
     * Double-checked locking for thread-safe lazy initialization.
     */
    public static ConfigurationManager getInstance() {
        if (instance == null) {
            synchronized (ConfigurationManager.class) {
                if (instance == null) {
                    instance = new ConfigurationManager();
                    logger.info("ConfigurationManager instance created.");
                }
            }
        }
        return instance;
    }

    // --- Getters ---
    public String getDefaultDeadline() {
        return defaultDeadline;
    }

    public int getMaxLoginAttempts() {
        return maxLoginAttempts;
    }

    public boolean isNotificationsEnabled() {
        return notificationsEnabled;
    }

    // --- Setters with validation ---
    public void setDefaultDeadline(String deadline) {
        if (deadline == null || !deadline.matches("^([01]?\\d|2[0-3]):[0-5]\\d$")) {
            logger.warning("Invalid deadline format. Use HH:mm.");
            return;
        }
        this.defaultDeadline = deadline;
        logger.info("Default deadline updated to " + deadline);
    }

    public void setMaxLoginAttempts(int attempts) {
        if (attempts <= 0) {
            logger.warning("Max login attempts must be greater than zero.");
            return;
        }
        this.maxLoginAttempts = attempts;
        logger.info("Max login attempts updated to " + attempts);
    }

    public void setNotificationsEnabled(boolean enabled) {
        this.notificationsEnabled = enabled;
        logger.info("Notifications enabled set to " + enabled);
    }

    // Reload defaults
    public void loadDefaultSettings() {
        this.defaultDeadline = "23:59";
        this.maxLoginAttempts = 5;
        this.notificationsEnabled = true;
        logger.info("Default settings reloaded.");
    }

    // Print current configuration
    public void viewConfiguration() {
        System.out.println("\n===== Current Configuration =====");
        System.out.println("Default Deadline: " + defaultDeadline);
        System.out.println("Max Login Attempts: " + maxLoginAttempts);
        System.out.println("Notifications Enabled: " + notificationsEnabled);
    }
}
