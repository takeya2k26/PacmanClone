# 🎮 PacmanClone — Java Pac-Man Game

PacmanClone is a Java-based arcade game inspired by the classic Pac-Man.

This project was developed as part of a Software Development course to demonstrate Java programming, Object-Oriented Programming (OOP), GUI development, game loops, collision detection, scoring, sound management, and basic enemy AI.

---

## 🛠️ Technologies Used

- Java
- Java Swing
- Java AWT
- Object-Oriented Programming (OOP)
- Java JDK 17+
- VS Code / NetBeans
- Git & GitHub
- No Database

---

## 🎮 Main Features

- Pac-Man movement using keyboard
- Maze with walls and pellets
- Power pellets
- Four different ghosts
- Custom ghost AI
- Collision detection
- Score system
- Lives system
- Ghost frightened mode
- Ghost eating bonus
- Game states
- Start/Menu screen
- Pause functionality
- Game Over screen
- Win screen
- Sound effects
- Animated Pac-Man

---

## 👻 Ghost AI

The game contains four different ghost classes:

- Ghost.java
- RedGhost.java
- BrownGhost.java
- BlueGhost.java
- YellowGhost.java

Each ghost has different movement and targeting behavior.

### RedGhost

Directly targets Pac-Man's current position.

### BrownGhost

Targets a position several tiles ahead of Pac-Man.

### BlueGhost

Uses Pac-Man's position and Red Ghost's position to calculate its target.

### YellowGhost

Changes between chasing Pac-Man and moving toward its scatter position depending on distance.

---

# 📅 10-Week Development Plan

## Week 1 — Project Setup & Game Window

- Set up the Java project.
- Create App.java.
- Create GameWindow.java.
- Create GamePanel.java.
- Implement the main game loop.
- Run the game at approximately 60 FPS.

**Deliverable:** Black game window opens successfully.

---

## Week 2 — Maze Design

- Create Maze.java.
- Represent the maze using a 2D grid.
- Add walls.
- Add pellets.
- Render the maze.

**Deliverable:** Static maze with walls and pellets.

---

## Week 3 — Pac-Man Character

- Create PacMan.java.
- Add keyboard controls.
- Implement Pac-Man movement.
- Add wall collision detection.
- Load Pac-Man sprite.
- Add basic animation.

**Deliverable:** Pac-Man can move inside the maze.

---

## Week 4 — Dot Collection & Scoring

- Implement pellet collection.
- Create ScoreManager.java.
- Increase score when pellets are eaten.
- Display score.
- Display lives.

**Deliverable:** Score changes when Pac-Man collects pellets.

---

## Week 5 — Ghost Base Class

- Create Ghost.java.
- Implement common ghost properties.
- Add ghost position and movement.
- Add ghost speed.
- Add ghost states.
- Load ghost images.

**Deliverable:** Basic ghost movement works.

---

## Week 6 — Four Custom Ghosts & AI

- Create RedGhost.java.
- Create BrownGhost.java.
- Create BlueGhost.java.
- Create YellowGhost.java.
- Implement different target-tile AI for each ghost.
- Add different ghost behaviors.

**Deliverable:** Four ghosts move using different AI behaviors.

---

## Week 7 — Power Pellets & Frightened Mode

- Add power pellets.
- Make ghosts frightened after a power pellet is eaten.
- Reduce ghost speed during frightened mode.
- Allow Pac-Man to eat frightened ghosts.
- Add bonus points for eating ghosts.
- Reset eaten ghosts to their starting position.

**Deliverable:** Power pellet and frightened mode work correctly.

---

## Week 8 — Collision, Lives & Game States

- Implement Pac-Man vs Ghost collision.
- Lose a life when Pac-Man is caught.
- Reset Pac-Man after losing a life.
- Add GameState.java.
- Implement MENU, PLAYING, PAUSED, GAME_OVER, and WIN states.

**Deliverable:** Complete game flow with lives and game states.

---

## Week 9 — Polish & Audio

- Add sound effects.
- Add chomp sound.
- Add death sound.
- Add ghost-eating sound.
- Add siren/background sound.
- Improve Pac-Man animation.
- Improve ghost movement.
- Add pause menu.
- Improve overall gameplay.

**Deliverable:** More complete and polished game experience.

---

# Week 10 — Final Build, JAR Packaging & Documentation

### Final Tasks

- Final gameplay testing.
- Fix bugs.
- Clean and organize code.
- Complete final integration.
- Improve game flow.
- Prepare final project for distribution.
- Create final README.md.
- Prepare GitHub repository.
- Package the game as a .jar file.
- Prepare required game assets.

### JAR Packaging

The final project is intended to be packaged as a Java .jar executable.

The distribution package will contain:

- Game JAR
- Sprite images
- Sound files
- Required game resources

**Deliverable:** Final playable Pac-Man game with JAR packaging and complete documentation.

---

# 🎮 Controls

| Key | Action |
|---|---|
| Up Arrow | Move Up |
| Down Arrow | Move Down |
| Left Arrow | Move Left |
| Right Arrow | Move Right |
| P | Pause / Resume |
| Enter | Start / Select |
| R | Restart |

---

# 📂 Project Structure

    PacmanClone/
    ├── assets/
    │   ├── sprites/
    │   │   ├── PacMan.png
    │   │   ├── RedGhost.png
    │   │   ├── BrownGhost.png
    │   │   ├── BlueGhost.png
    │   │   └── YellowGhost.png
    │   └── sounds/
    │       ├── chomp.wav
    │       ├── death.wav
    │       ├── eatghost.wav
    │       └── siren.wav
    │
    ├── src/
    │   ├── App.java
    │   ├── GameWindow.java
    │   ├── GamePanel.java
    │   ├── Maze.java
    │   ├── PacMan.java
    │   ├── Ghost.java
    │   ├── RedGhost.java
    │   ├── BrownGhost.java
    │   ├── BlueGhost.java
    │   ├── YellowGhost.java
    │   ├── ScoreManager.java
    │   ├── SoundManager.java
    │   └── GameState.java
    │
    ├── .gitignore
    └── README.md

---

# 💻 Requirements

Before running the game, install:

- Java JDK 17 or newer
- Git (optional, only needed for cloning or updating)

Check Java installation:

    java -version

Check Java compiler:

    javac -version

---

# ▶️ How to Run the Game

## Option 1 — Run from VS Code Terminal

Open the project folder in VS Code.

Open the terminal and run:

    cd src
    javac *.java -d ../bin
    cd ..
    java -cp bin App

The Pac-Man game window should open.

---

## Option 2 — Run from PowerShell

Navigate to the project folder:

    cd "C:\Users\MIM\Desktop\TAKEYA ALL\PacMan\PacManGame"

Then compile the source files:

    cd src
    javac *.java -d ../bin

Return to the project folder:

    cd ..

Run the game:

    java -cp bin App

---

## Important

Make sure the assets folder remains in the main project folder.

The game needs the following resources:

- Pac-Man sprite
- Ghost sprites
- Chomp sound
- Death sound
- Ghost-eating sound
- Siren sound

Without the required assets, some graphics or sounds may not work correctly.

---

# 🖼️ Important Assets

### Sprite Assets

The following images are stored inside:

    assets/sprites/

- PacMan.png
- RedGhost.png
- BrownGhost.png
- BlueGhost.png
- YellowGhost.png

### Sound Assets

The sound files are stored inside:

    assets/sounds/

- chomp.wav
- death.wav
- eatghost.wav
- siren.wav

Keep these files in their original folders so the game can load them correctly.

---

# 🔄 Updating the Project

If the project is already cloned from GitHub, use:

    git pull

This downloads the latest changes from the GitHub repository.

After updating the source code, compile the project again:

    cd src
    javac *.java -d ../bin
    cd ..
    java -cp bin App

---

# 🌐 GitHub Repository

The project is available on GitHub:

    https://github.com/takeya2k26/PacmanClone

The repository contains the Java source code, game assets, documentation, and weekly project showcase materials.

---

# 🧠 Object-Oriented Programming Concepts

This project demonstrates several important Object-Oriented Programming concepts.

### Encapsulation

Classes keep their data and methods together.

Examples:

- PacMan
- Ghost
- ScoreManager
- Maze

### Inheritance

Different ghost classes inherit common properties and methods from the Ghost base class.

    Ghost
    ├── RedGhost
    ├── BrownGhost
    ├── BlueGhost
    └── YellowGhost

### Polymorphism

Different ghost classes provide different behaviors while using the common Ghost structure.

### Abstraction

The Ghost class provides common functionality for all ghosts, while individual ghost classes implement their own target and movement behavior.

---

# 💻 Development Environment

### Programming Language

Java

### GUI Framework

Java Swing and Java AWT

### Java Version

JDK 17 or newer

### IDE

VS Code / NetBeans

### Version Control

Git and GitHub

### Database

No database is used.

### Operating System

Windows

---

# 📚 Educational Purpose

This project was created for academic and learning purposes.

It demonstrates practical use of:

- Java programming
- Object-Oriented Programming
- Java Swing GUI
- Game loops
- Keyboard input
- Collision detection
- File and resource handling
- Sound management
- Basic Artificial Intelligence
- Git and GitHub
- Software development and project organization

---

# 📅 Weekly Project Showcase

The project development was documented throughout the 10-week development period.

Weekly showcase materials include progress updates, implemented features, and development milestones.

These materials are available in the GitHub repository as PDF documents.

---

# 🚀 Future Improvements

Possible future improvements include:

- More Pac-Man levels
- Improved ghost AI
- Additional animations
- More sound effects
- Background music
- High-score saving
- Improved menu design
- Additional difficulty levels
- Improved game balancing
- Better JAR distribution
- Cross-platform testing

---

# 📌 Project Status

The project is currently in the final development and documentation stage.

Core gameplay features have been implemented, including:

- Pac-Man movement
- Maze system
- Pellet collection
- Four ghost characters
- Ghost AI
- Power pellets
- Frightened mode
- Score and lives
- Collision detection
- Game states
- Sound effects
- Pac-Man animation

Final testing, code cleanup, documentation, and JAR packaging are part of the final development stage.

---

# 👨‍💻 Author

**Takeya**

Computer Science and Engineering (CSE) Student

This project was developed as part of a Software Development course.

---

# 📄 Project Information

**Project Name:** PacmanClone

**Language:** Java

**GUI:** Java Swing / AWT

**Java Version:** JDK 17+

**Database:** None

**Version Control:** Git / GitHub

---

# ⭐ Acknowledgement

This project was inspired by the classic Pac-Man arcade game and was developed for educational purposes.

The project focuses on learning software development concepts through practical implementation.

---

# 🎮 Thank You

Thank you for checking out **PacmanClone**!

Enjoy the game and keep coding! 👾

---
# 🎮 Download & Play

Want to play the game without opening the source code?

You can download the ready-to-run **PacmanClone.jar** file directly from this GitHub repository.

### 📥 How to Download

1. Open the **PacmanClone** GitHub repository.
2. Click **PacmanClone.jar**.
3. Click the **Download** button.
4. Save the `.jar` file to your computer.

### ☕ Java Requirement

The game requires **Java 17 or newer**.

Check whether Java is installed:

    java -version

If Java is installed, you can run the game from PowerShell or Command Prompt:

    java -jar PacmanClone.jar

### 🖱️ Windows

You may also be able to double-click **PacmanClone.jar** to start the game if Java is correctly associated with `.jar` files on your computer.

### 🎮 No IDE Required

You do **not** need:

- VS Code
- NetBeans
- Eclipse
- The Java source code

to play the packaged game.

The JAR contains the compiled game and required game resources.

### ⚠️ If the Game Does Not Start

Make sure:

- Java 17 or newer is installed.
- The downloaded file is named `PacmanClone.jar`.
- You are running the correct JAR file.

You can also run it from a terminal using:

    java -jar PacmanClone.jar

---
