const endButton = document.QuerySelector()
async function attemptEnd() {
    try {
        const response = await fetch('/end');
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

attemptEnd();