# RISK-Game
<div align="center">    
   <img src="https://github.com/LewisZhang-01/RISK-Game/blob/main/About RISK/Photos/Cover.jpg" width = 340 height = 397.4>
   
  <br>[![](https://img.shields.io/badge/author-ZhiZhang-red "author")](https://github.com/LewisZhang-01/)
  ![](https://img.shields.io/badge/dynamic/json?label=GitHub%20Followers&query=%24.data.totalSubs&url=https%3A%2F%2Fapi.spencerwoo.com%2Fsubstats%2F%3Fsource%3Dgithub%26queryKey%3DLewisZhang-01&labelColor=282c34&color=181717&logo=github&longCache=true "followers")
  ![](https://img.shields.io/badge/JavaSE-1.8-green.svg "JAVA")
</div>

## About The Project

This project is written in Java language called “RISK” which is a strategy board and card game of diplomacy, conflict, and conquest. 
Win by capturing the opponent's territory, acquiring and trade cards. The game ends when all territories are occupied by one player.

<div align="center">    
   <img src="https://github.com/LewisZhang-01/RISK-Game/blob/main/About RISK/Photos/Init.png" width = 600 height = 480>
</div>

### The UI consists of 3 panels:
1. A panel that displays the Risk map as a (Computer Science) graph with nodes inter-connected using lines. Use colours to indicate the continents and text to show the country names. The occupying armies should be indicated with colours (player) and text (number of units).
2. A panel that allows display of text message instructing the player on what is happening, prompts them with what to do and shows previous user inputs.
3. A panel that allow the user to enter textual commands.

### The Game have three main phases:
1. Initialisation.
2. Turns. (reinforce, combat, fortify, and trade cards)
3. Winner detect & finish.

More details on the rules of the game can be found here on the [Rules pdf](https://github.com/LewisZhang-01/RISK-Game/blob/main/About%20RISK/Risk%20Rules.pdf) file and also [Wikipedia website](https://en.wikipedia.org/wiki/Risk_(game)).

## Usage

### User can run this [JAR file](https://github.com/LewisZhang-01/RISK-Game/blob/main/RISK/Sprint-4-JAR/Sprint4_BadGuy.jar) to play this game.

At first, the program will asks the player to enter their name and allocate colours to them, followed by the game initialization which give real players 36 armies and neutrals player 24 armies. Players are allow to draw territory cards from the deck. Program will inform the users which cards are drawn. Players should place the number of armies accordingly.

<div align="center">    
   <img src="https://github.com/LewisZhang-01/RISK-Game/blob/main/About RISK/Photos/Start-1.png" width = 600 height = 480>
</div>

Roll a dice each to see who places their reinforcements first. Highest roll does first. Re-roll if a draw. Players take it in turns to place 3 units at a time on a territory that they control and then one unit for each neutral. The user could achieve this by entering the name of the territory. (Users are allowed to enter abbreviations of names)

Next, roll a dice each to see who takes the first turn. Highest roll does first. Re-roll if a draw. When it's the player's turn, the program will check and calculate the number of reinforcements that a user gets based on the number of occupied countries and continents. User can place their reinforcements on their territories. Then, user can combat between armies. Combat takes place according to the rules of Risk. If the user does not want a further attack, user could enter the command “skip” to go to fortify section. Player can choose move any number of unit between their own territories according to the rules. If the user does not want to fortify, the user may also enter the command “skip”.

<div align="center">    
   <img src="https://github.com/LewisZhang-01/RISK-Game/blob/main/About RISK/Photos/Card&CardSet.jpg" width = 638 height = 364.4>
</div>

Player will winning of territory cards after combat and could exchange of territory cards for reinforcements at this moment. For card trading, players can also type in the first letter of the insignias that they wish to exchange, e.g. “III” for exchange of 3 infantry cards. 

After this, multiple players will continue their turn until all the real player's territory is occupied by one player.

## Play with BadGuy Bot
<div align="center">    
   <img src="https://github.com/LewisZhang-01/RISK-Game/blob/main/About RISK/Photos/Start-2.png" width = 600 height = 480>
</div>

### User can run this [JAR file](https://github.com/LewisZhang-01/RISK-Game/blob/main/RISK%20(Play%20with%20BadGuy%20Bot)/Sprint5_BadGuy.jar) to play this game with bot.

This means users can compete with our robot - BadGuy, which is now a middle level opponent.

The algorithm for this bot can be found [here](https://github.com/LewisZhang-01/RISK-Game/blob/main/RISK%20(Play%20with%20BadGuy%20Bot)/BadGuy_Bot_Algorithm.pdf).

## Author info
Author Name: Zhi Zhang, Zhonghe Chen, Yunlong Cheng.

Email: 
* @Zhi - lewiszhang01@gmail.com <br>
* @Zhonghe - zhonghe.chen@ucdconnect.ie <br> 
* @Yunlong - yunlong.cheng@ucdconnect.ie <br> 
      
Project Link: [https://github.com/LewisZhang-01/RISK-Game](https://github.com/LewisZhang-01/RISK-Game)
