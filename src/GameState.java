package src;

enum GameState {
    exploration,
    fight,

    justPickedUpItem,
    inFightTransition,
    inLevelTransition,

    waitingForPlayerMove,
    playerChoosingAttack,
    playerChoosingDefense,
    playerChoosingHeal,

    waitingForOpponentMove,
    opponentAttack,
    opponentDefense,
    opponentHeal,
    
    postWin,
    postFinalWin,
    postLose
}