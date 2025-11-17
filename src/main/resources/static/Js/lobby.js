const startButton = document.getElementById("gameStart")

async function attemptStart(){
    try {
        const response = await fetch('/start', {
            method: 'GET',
            credentials: 'include'});
        const isReady = await response.json();

        if (isReady === true) {
            window.location.href = "/GameHome";
        } else {
            // Check again in 2 seconds
            setTimeout(attemptStart, 2000);
        }
    } catch (e) {
        console.error("Error checking status", e);
        setTimeout(attemptStart, 2000);
    }
}
async function checkStatus(){
    try {
        const response = await fetch('/isStarted');
        const isReady = await response.json();

        if (isReady === true) {
            window.location.href = "/GameHome";
        } else {
            // Check again in 2 seconds
            setTimeout(checkStatus, 2000);
        }
    } catch (e) {
        console.error("Error checking status", e);
        setTimeout(checkStatus, 2000);
    }
}

startButton.addEventListener("click", attemptStart)
checkStatus();