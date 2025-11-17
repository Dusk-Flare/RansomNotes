const endButton = document.getElementById("gameEnd")
async function attemptEnd(){
    try {
        const response = await fetch('/end', {
            method: 'GET',
            credentials: 'include'});
        const isReady = await response.json();

        if (isReady === true) {
            window.location.href = "/GameOver";
        } else {
            // Check again in 2 seconds
            setTimeout(attemptEnd, 2000);
        }
    } catch (e) {
        console.error("Error checking status", e);
        setTimeout(attemptEnd, 2000);
    }
}

endButton.addEventListener("click", attemptEnd)