# BVA: ChessClock

### Method under test: `ChessClock(long initialTime, ClockListener listener)`
| ID  | State of the System   | Expected output                                                                 | Implemented?       |
| --- | ----------------------| ------------------------------------------------------------------------------- | ------------------ |
| TC1 | initialTime = 300000  | ChessClock created, `getTimeRemaining(WHITE)`=`getTimeRemaining(BLACK)`=300000  | :white_check_mark: |
| TC2 | initialTime = 600000  | ChessClock created, `getTimeRemaining(WHITE)`=`getTimeRemaining(BLACK)`=600000  | :white_check_mark: |
| TC3 | initialTime = 3600000 | ChessClock created, `getTimeRemaining(WHITE)`=`getTimeRemaining(BLACK)`=3600000 | :white_check_mark: |
| TC4 | listener = `null`     | `IllegalArgumentException` thrown                                               | :white_check_mark: |
 
### Method under test: `start()`
| ID  | State of the System        | Expected output            | Implemented? |
| --- | -------------------------- | -------------------------- | ------------ |
| TC5 | new `ChessClock()` created | `isRunning()` returns true |      :x:     |
| TC6 | `start()` already called   | `isRunning()` returns true |      :x:     |

### Method under test: `stop()`
| ID  | State of the System                              | Expected output             | Implemented? |
| --- | ------------------------------------------------ | --------------------------- | ------------ |
| TC7 | new `ChessClock()` created, `start()` called     | `isRunning()` returns false |      :x:     |
| TC8 | new `ChessClock()` created, `start()` not called | `isRunning()` returns false |      :x:     |

### Method under test: `switchClock()`
| ID    | State of the System         | Expected output                    | Implemented? |
| ----  | --------------------------- | ---------------------------------- | ------------ |
| TC9   | `activeColor`= `WHITE`      | activeColor= `BLACK`               |      :x:     |
|       | `whiteTimeRemaining`=299999 | `getTimeRemaining(WHITE)` = 299999 |              |
|       | `blackTimeRemaining`=300000 | `getTimeRemaining(BLACK)` = 300000 |              |
| ----  | --------------------------- | ---------------------------------- | ------------ |
| TC10  | `activeColor`= `BLACK`      | activeColor= `WHITE`               |      :x:     |
|       | `blackTimeRemaining`=299999 | `getTimeRemaining(BLACK)` = 299999 |              |
|       | `whiteTimeRemaining`=1      | `getTimeRemaining(WHITE)` = 1      |              | 
| ----  | --------------------------- | ---------------------------------- | ------------ |
| TC11  | `activeColor`= `BLACK`      | activeColor= `WHITE`               |      :x:     |
|       | `blackTimeRemaining`=1      | `getTimeRemaining(WHITE)` = 299999 |              |
|       | `whiteTimeRemaining`=299999 | `getTimeRemaining(BLACK)` = 1      |              |       
| ----  | --------------------------- | ---------------------------------- | ------------ |
| TC12  | `activeColor`= `WHITE`      | activeColor= `BLACK`               |      :x:     |
|       | `whiteTimeRemaining`=1      | `getTimeRemaining(WHITE)` = 1      |              |
|       | `blackTimeRemaining`=1      | `getTimeRemaining(BLACK)` = 1      |              |   
| ----  | --------------------------- | ---------------------------------- | ------------ |

### Method under test: `getTimeRemaining(Color color)`
| ID   | State of the System                         | Expected output            | Implemented? |
| ---- | ------------------------------------------- | -------------------------- | ------------ |
| TC13 | color= `WHITE`, `whiteTimeRemaining`=299999 | 299999                     |      :x:     |
| TC14 | color= `BLACK`, `blackTimeRemaining`=299999 | 299999                     |      :x:     |
| TC15 | color= `WHITE`, `whiteTimeRemaining`=0      | 0                          |      :x:     |
| TC16 | color= `BLACK`, `blackTimeRemaining`=0      | 0                          |      :x:     |
| TC17 | color= `null`                               | `IllegalArugmentException` |      :x:     |

### Method under test: `isRunning()`
| ID   | State of the System                             | Expected output | Implemented? |
| ---- | ----------------------------------------------- | --------------- | ------------ |
| TC18 | `start()` called                                | true            |      :x:     |
| TC19 | `start()` not called                            | false           |      :x:     |
| TC20 | `start()` and `stop()` both called successively | false           |      :x:     |
