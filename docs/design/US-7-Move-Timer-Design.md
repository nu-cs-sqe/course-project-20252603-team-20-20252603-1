# Move Timer Design

## Overview

When a game starts, both players each have a timer that only counts down when it's their turn. If a player's timer runs out, as long as the opponent has sufficient material, the opponent wins. When a player makes a move, their timer stops and their opponent's timer starts. This is visible via the GUI. Players can select from a pre-determined range of time controls before the game starts in a new TimeControlView.java interface. 

### Additions

#### ChessClock

Fields:
- `initialTime: long` (private) - initialized to what user selects
- `whiteTimeRemaining: long` (private) - initialized to `initialTime`
- `blackTimeRemaining: long` (private) - initialized to `initialTime`
- `activeColor: Color` (private) - initialized to `WHITE`
- `running: boolean` (private) - initialized to True, changed to False when a timer runs out
- `timer: Timer` (private) - fires the countdown ticks
- `listener: BoardChangeListener` (private): allows updates to UI, and receives when a turn changes

Methods:
- `ChessClock(long initialTime, BoardChangeListener)`-  constructor 
- `start(): void`: begins countdown for `activeColor`
- `stop(): void`: pauses, not resetting
- `switchClock(): void`: switches `activeColor`
- `getTimeRemaining(Color color): long`: gets `whiteTimeRemaining` or `blackTimeRemaining`
- `isRunning(): boolean`: returns value of `running`.


### GUI Updates

#### MainView
- Take on `BoardChangeListener`. This way, the clock and board can be updated with the same listener
- `BoardController` moves here
- `onTimerTick()` updates timer labels in GameStatsView
- `onTimeout()` ends the game

