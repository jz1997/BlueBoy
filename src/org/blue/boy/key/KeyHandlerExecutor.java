package org.blue.boy.key;

import org.blue.boy.key.state.*;
import org.blue.boy.main.GamePanel;
import org.blue.boy.main.GameState;

import java.awt.event.KeyEvent;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class KeyHandlerExecutor {
    public GamePanel gp;
    public Map<GameState, KeyHandler> HANDLERS = new HashMap<>();
    public KeyHandler emptyKeyHandler;

    public KeyHandlerExecutor(GamePanel gp) {
        this.gp = gp;

        setup();
    }

    public void setup() {
        emptyKeyHandler = new EmptyStateKeyHandler(gp);
        HANDLERS.put(GameState.PLAY, new PlayStateKeyHandler(gp));
        HANDLERS.put(GameState.PAUSED, new PausedStateKeyHandler(gp));
        HANDLERS.put(GameState.TITLE, new TitleStateKeyHandler(gp));
        HANDLERS.put(GameState.DIALOGUE, new DialogueStateKeyHandler(gp));
    }

    public void execute(KeyEvent e, KeyEventType keyType) {
        KeyHandler keyHandler = Optional.ofNullable(HANDLERS.get(gp.gameState)).orElse(emptyKeyHandler);
        switch (keyType) {
            case TYPED:
                keyHandler.handleTyped(e);
                break;
            case PRESSED:
                keyHandler.handlePressed(e);
                break;
            case RELEASED:
                keyHandler.handleReleased(e);
                break;
            case RESET:
                keyHandler.reset();
                break;
        }
    }

}
