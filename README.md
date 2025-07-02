# 🎮 Monster Survival Game

A 2D side-scrolling survival game developed in Java using AWT/Swing. Players control a character navigating multi-layer platforms while battling various enemy types. The game features smooth animations, interactive gameplay mechanics, and an in-game tutorial to guide players.

## 🧩 Game Features

- **Multiple Enemy Types with Unique Behaviors:**
  - **Shielder:** A close-range enemy that rushes toward the player, then jumps and shields itself. Reacts visually when hit.
  - **Tank:** Fires cannonballs at the player from a distance. Tanks move toward a target position and launch projectiles when in range.
  - **Flying Enemies:** Appear in the air and perform simple movement patterns.

- **Player Animations:** Includes running, jumping, and getting hurt states to enhance immersion.

- **Layered Platforms:** The environment consists of several platforms at different heights, providing vertical gameplay elements.

- **Game Rules & Tutorial Screens:** An interactive rule interface displaying animations and instructions to help new players learn the game mechanics.

- **Hit Feedback:** Both player and enemies show visual feedback when damaged, including explosion animations for projectiles.

## Module intriduction
### 👨‍🚀 Playable Character System: ControlMan.java
The ControlMan class handles the main character's movement, input, status, and shooting mechanics. It is the heart of player interactivity in this Java-based monster survival game. Designed using Java AWT event listeners and custom rendering logic, this component offers responsive and versatile gameplay.
### 🌍 Ground System: Ground.java
The Ground class defines the static platforms that form the terrain and stage layout. It provides basic support for rendering solid surfaces and enables platformer-style movement by acting as collision reference for the player and enemies.
### 📘 Tutorial & Rule Page System: Rule.java
The Rule class implements an interactive game tutorial interface using AWT Frame, which guides the player through game mechanics and enemy behavior across six visual pages. This module combines user interaction with animated visuals for an engaging learning experience.
### 🧟‍♂️ Enemy Module: FlyingBaLaLa.java
The FlyingBaLaLa class implements the logic and animation for flying enemies that track, attack, and interact with the player. Each enemy is autonomous and capable of ranged attacks using a multi-stage bullet and explosion system.
### 🛡️ Enemy Module: Shielder.java
The Shielder class implements a defensive enemy type that features multiple behavior phases and animation transitions. Designed to approach the player strategically, the Shielder exhibits a guarded posture and responds dynamically to incoming attacks.
### 🔥 Enemy Module: Tank.java
The Tank class defines a long-range heavy enemy capable of dynamic directional movement, projectile attacks, and reactive animations. This enemy adds tactical diversity and challenge to gameplay.

## 🔧 Technical Details

- Rendering is handled via Java's AWT `Graphics` object using `drawImage()`.
- User input is managed through `MouseListener` and `KeyListener` interfaces.
- Enemy and player states are tracked using arrays and timers to manage animations and movements.
- The game implements basic collision detection for hits and damage.


