## PacmanClone

Pacman Clone is a Java-based arcade game inspired by the classic Pac-Man. In this game, players navigate through a maze, collect pellets to earn points, and avoid enemy ghosts. The project focuses on implementing core game development concepts such as character movement, collision detection, score management, and basic enemy AI.

This project was developed as part of a Software Development course to demonstrate object-oriented programming (OOP), game logic implementation, and graphical user interface design using Java. The game provides an engaging experience while showcasing fundamental software engineering and game development principles.


# Pac-Man in Java — 10-Week Build Plan

**Stack:** VS Code · Java JDK 17+ · 4 Ghosts · No Database

---

## Week 1 — Project Setup & Game Window
- Set up folders, `App.java`, `GameWindow.java`, `GamePanel.java`
- Game loop running at ~60 FPS

**Deliverable:** ✅ Black window opens

---

## Week 2 — Maze Design
- Build maze as 2D grid (`Maze.java`)
- Render walls + dots

**Deliverable:** ✅ Static maze visible

---

## Week 3 — Pac-Man Character
- `PacMan.java` — movement, keyboard input, collision
- Load `PacMan.png`, animate direction

**Deliverable:** ✅ Pac-Man moves inside maze

---

## Week 4 — Dot Collection & Scoring
- Eat dots → increase score (`ScoreManager.java`)
- Display HUD (score, lives)

**Deliverable:** ✅ Score updates live

---

## Week 5 — Ghost Base Class
- `Ghost.java` — shared logic (position, speed, state)
- Load one ghost image, random movement

**Deliverable:** ✅ One ghost wanders maze

---

## Week 6 — All 4 Custom Ghosts
- Create `RedGhost`, `YellowGhost`, `BlueGhost`, `BrownGhost`
- Implement unique target-tile AI for each
- Load respective PNGs

**Deliverable:** ✅ All 4 ghosts moving with different behavior

---

## Week 7 — Power Pellets & Frightened Mode
- Power pellet eaten → all ghosts turn frightened (slow + blue tint)
- Eating frightened ghost → bonus points, ghost resets

**Deliverable:** ✅ Power pellet mechanic works

---

## Week 8 — Collisions, Lives & Game States
- Pac-Man vs Ghost collision → lose life or eat ghost
- `GameState.java`: `MENU`, `PLAYING`, `GAME_OVER`, `WIN`

**Deliverable:** ✅ Full life + win/lose flow

---

## Week 9 — Polish & Audio
- Add sound effects (chomp, death, siren)
- Improve animations, add pause menu

**Deliverable:** ✅ Game feels complete

---

## Week 10 — Levels & Final Build
- Add Level 2 (faster ghosts)
- Main menu screen
- Package as `.jar`, write `README.md`

**Deliverable:** ✅ 🎉 Finished playable game
