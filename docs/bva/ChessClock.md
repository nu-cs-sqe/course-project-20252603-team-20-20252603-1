# BVA: ChessClock

### Method under test: `ChessClock(long initialTime, ClockListener listener)`
| ID  | State of the System   | Expected output                                                                 | Implemented?       |
| --- | ----------------------| ------------------------------------------------------------------------------- | ------------------ |
| TC1 | initialTime = 300000  | ChessClock created, `getTimeRemaining(WHITE)`=`getTimeRemaining(BLACK)`=300000  | :white_check_mark: |
| TC2 | initialTime = 600000  | ChessClock created, `getTimeRemaining(WHITE)`=`getTimeRemaining(BLACK)`=600000  | :white_check_mark: |
| TC3 | initialTime = 3600000 | ChessClock created, `getTimeRemaining(WHITE)`=`getTimeRemaining(BLACK)`=3600000 | :white_check_mark: |
| TC4 | listener = `null`     | `IllegalArgumentException` thrown                                               | :white_check_mark: |
 
### Method under test: `start()`
| ID  | State of the System        | Expected output            | Implemented?       |
| --- | -------------------------- | -------------------------- | ------------------ |
| TC5 | new `ChessClock()` created | `isRunning()` returns true | :white_check_mark: |
| TC6 | `start()` already called   | `isRunning()` returns true | :white_check_mark: |

### Method under test: `stop()`
| ID  | State of the System                              | Expected output             | Implemented?       |
| --- | ------------------------------------------------ | --------------------------- | ------------------ |
| TC7 | new `ChessClock()` created, `start()` called     | `isRunning()` returns false | :white_check_mark: |
| TC8 | new `ChessClock()` created, `start()` not called | `isRunning()` returns false | :white_check_mark: |

### Method under test: `switchClock()`
| ID    | State of the System         | Expected output                    | Implemented?       |
| ----  | --------------------------- | ---------------------------------- | ------------------ |
| TC9   | `activeColor`= `WHITE`      | `getActiveColor()` = `BLACK`       | :white_check_mark: |
|       | `whiteTimeRemaining`=299999 | `getTimeRemaining(WHITE)` = 299999 |                    |
|       | `blackTimeRemaining`=300000 | `getTimeRemaining(BLACK)` = 300000 |                    |
| ----  | --------------------------- | ---------------------------------- | ------------------ |
| TC10  | `activeColor`= `BLACK`      | `getActiveColor()` = `WHITE`       | :white_check_mark: |
|       | `blackTimeRemaining`=299999 | `getTimeRemaining(BLACK)` = 299999 |                    |
|       | `whiteTimeRemaining`=1      | `getTimeRemaining(WHITE)` = 1      |                    | 
| ----  | --------------------------- | ---------------------------------- | ------------------ |
| TC11  | `activeColor`= `BLACK`      | `getActiveColor()` = `WHITE`       | :white_check_mark: |
|       | `blackTimeRemaining`=1      | `getTimeRemaining(WHITE)` = 299999 |                    |
|       | `whiteTimeRemaining`=299999 | `getTimeRemaining(BLACK)` = 1      |                    |       
| ----  | --------------------------- | ---------------------------------- | ------------------ |
| TC12  | `activeColor`= `WHITE`      | `getActiveColor()` = `BLACK`       | :white_check_mark: |
|       | `whiteTimeRemaining`=1      | `getTimeRemaining(WHITE)` = 1      |                    |
|       | `blackTimeRemaining`=1      | `getTimeRemaining(BLACK)` = 1      |                    |   
| ----  | --------------------------- | ---------------------------------- | ------------------ |

### Method under test: `getTimeRemaining(Color color)`
| ID   | State of the System                         | Expected output            | Implemented?       |
| ---- | ------------------------------------------- | -------------------------- | ------------------ |
| TC13 | color= `WHITE`, `whiteTimeRemaining`=299999 | 299999                     | :white_check_mark: | *Covered by TC11*
| TC14 | color= `BLACK`, `blackTimeRemaining`=299999 | 299999                     | :white_check_mark: | *Covered by TC10*
| TC15 | color= `WHITE`, `whiteTimeRemaining`=0      | 0                          | :white_check_mark: |
| TC16 | color= `BLACK`, `blackTimeRemaining`=0      | 0                          | :white_check_mark: |
| TC17 | color= `null`                               | `IllegalArugmentException` | :white_check_mark: |

### Method under test: `isRunning()`
| ID   | State of the System                             | Expected output | Implemented?       |
| ---- | ----------------------------------------------- | --------------- | ------------------ |
| TC18 | `start()` called                                | true            | :white_check_mark: | *Covered by TC5*
| TC19 | `start()` not called                            | false           | :white_check_mark: | *Covered by TC8*
| TC20 | `start()` and `stop()` both called successively | false           | :white_check_mark: | *Covered by TC7*

### Method under test: `tick()`
| ID   | State of the System                               | Expected output                                      | Implemented?       |
| ---- | ------------------------------------------------- | ---------------------------------------------------- | ------------------ |
| TC21 | `whiteTimeRemaining`=2000, activeColor=`WHITE`    | `whiteTimeRemaining`=1000, onTimerTick() is called   | :white_check_mark: | 
| TC22 | `blackTimeRemaining`=2000, activeColor=`BLACK`    | `blackTimeRemaining`=1000, onTimerTick() is called   | :white_check_mark: |
| TC23 | `whiteTimeRemaining`=1000, activeColor=`WHITE`    | `whiteTimeRemaining`=0, onTimeout() is called        | :white_check_mark: | 
| TC24 | `blackTimeRemaining`=1000, activeColor=`BLACK`    | `blackTimeRemaining`=0, onTimeout() is called        | :white_check_mark: | 
| TC25 | `whiteTimeRemaining`=300000, activeColor=`WHITE`  | `whiteTimeRemaining`=299000, onTimerTick() is called | :white_check_mark: | 
| TC26 | `blackTimeRemaining`=300000, activeColor=`BLACK`  | `blackTimeRemaining`=299000, onTimerTick() is called | :white_check_mark: |
| TC27 | `whiteTimeRemaining`=1000, activeColor=`WHITE`, `tick()` called | `isRunning()` returns false | :x: |
| TC28 | `blackTimeRemaining`=1000, activeColor=`BLACK`, `tick()` called | `isRunning()` returns false | :x: | 
